package com.apigit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class GitHubRepository {
    @JsonProperty("name")
    private String repositoryName;
    @JsonProperty("owner")
    private GitHubOwner owner;
    @JsonProperty("branches")
    private List<GitHubBranch> gitHubBranches;

    private Boolean fork;

    @JsonIgnore
    public Boolean getFork() {
        return fork;
    }

    @JsonProperty("fork")
    public void setFork(Boolean fork) {
        this.fork = fork;
    }

    public void setGitHubBranches(List<GitHubBranch> gitHubBranches) {
        this.gitHubBranches = gitHubBranches;
    }
}
