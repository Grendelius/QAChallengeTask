package com.qa.task.core.github;

import com.fasterxml.jackson.core.type.TypeReference;
import com.qa.task.core.JsonObjectMapper;
import com.qa.task.model.GitHubPullRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class GitHubApi {

    @Value("${core.github.url}")
    private String gitHubUrl;
    @Value("${core.github.owner}")
    private String gitHubOwner;
    @Value("${core.github.repo}")
    private String gitHubRepo;

    private final HttpClient httpClient;

    private volatile List<GitHubPullRequest> pullRequests;

    public GitHubStore byPrState(String state) {
        return new GitHubStore(state, pullRequests().parallelStream()
                .filter(pr -> pr.state().equalsIgnoreCase(state))
                .toList()
        );
    }

    private synchronized List<GitHubPullRequest> pullRequests() {
        if (pullRequests != null) {
            return pullRequests;
        }
        return pullRequests = fetch();
    }

    private List<GitHubPullRequest> fetch() {
        HttpGet get = new HttpGet("%s/%s/%s/pulls?sort=created".formatted(gitHubUrl, gitHubOwner, gitHubRepo));
        get.addHeader("Accept", " application/vnd.github.v3+json");

        try {
            log.info("Executing GET request: {}", get);
            return httpClient.execute(get, response -> {
                int status = response.getCode();
                if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
                    HttpEntity entity = response.getEntity();
                    String content;
                    try {
                        content = entity != null ? EntityUtils.toString(entity) : null;
                    } catch (final ParseException ex) {
                        throw new ClientProtocolException(ex);
                    }
                    log.debug("Got content: {}", content);
                    return JsonObjectMapper.INSTANCE.readValue(Optional.ofNullable(content).orElse(""), new TypeReference<List<GitHubPullRequest>>() { });
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            });
        } catch (IOException e) {
            log.error("Error GET pull requests", e);
            return Collections.emptyList();
        }

    }

}
