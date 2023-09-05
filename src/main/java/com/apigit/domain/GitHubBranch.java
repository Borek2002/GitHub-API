package com.apigit.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GitHubBranch {
    @JsonProperty("name")
    private String branchName;
    @JsonProperty("commit")
    private GitHubCommit commit;
}
