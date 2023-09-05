package com.apigit.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubOwner {
    @JsonProperty("login")
    private String login;
}
