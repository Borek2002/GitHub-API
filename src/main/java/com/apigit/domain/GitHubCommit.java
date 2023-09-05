package com.apigit.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GitHubCommit {
    @JsonProperty("sha")
    private String sha;
}
