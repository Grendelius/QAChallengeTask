package com.qa.task.core.github;

import com.qa.task.model.GitHubPullRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GitHubStore {

    private final String state;
    private final List<GitHubPullRequest> pullRequests;

}
