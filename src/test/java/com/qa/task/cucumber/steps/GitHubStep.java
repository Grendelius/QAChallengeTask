package com.qa.task.cucumber.steps;

import com.qa.task.core.github.GitHubApi;
import com.qa.task.core.github.GitHubStore;
import com.qa.task.model.GitHubPullRequest;
import com.qa.task.util.CsvWriter;
import io.cucumber.java.en.Step;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class GitHubStep extends BaseStep {

    @Autowired
    private GitHubApi gitHubApi;

    @Step("user loads available {string} pull requests")
    public void listPullRequests(String state) {
        var pulls = gitHubApi.byPrState(state);

        assertThat(pulls)
                .as("PR list is not null.")
                .isNotNull();

        save(state + "_pull_requests", pulls);
    }

    @Step("user saves {string} pull requests data to file {string}")
    public void savePullRequestsToFile(String state, String fileName) throws FileNotFoundException {
        var prs = ((GitHubStore) load(state + "_pull_requests")).getPullRequests().parallelStream()
                .map(line -> new String[]{line.title(), line.created_at().toString(), line.user().login()})
                .toList();

        var csv = CsvWriter.writeCsv(fileName, new String[]{"TITLE", "CREATED_AT", "AUTHOR"}, prs);

        assertThat(csv)
                .as("CSV report file has been created")
                .exists();

        Allure.addAttachment("Pull requests", "text/csv", new FileInputStream(csv.getAbsolutePath()), ".csv");
    }

}
