package com.qa.task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubPullRequest(String url, String title, User user, Date created_at, String state) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record User(String login, Integer id) { }

}
