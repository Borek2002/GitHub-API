package com.apigit.client;

import com.apigit.domain.GitHubBranch;
import com.apigit.domain.GitHubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GitHubClient {
    private static final String URL_GITHUB_API = "https://api.github.com";
    @Autowired
    private RestTemplate restTemplate;

    public List<GitHubRepository> getGitHubRepositories(String username) {
        String url = URL_GITHUB_API + "/users/" + username + "/repos";
        try {
            List<GitHubRepository> repositories = new ArrayList<>();
            GitHubRepository[] repositoriesResponse = restTemplate.getForObject(
                    url, GitHubRepository[].class);
            if (repositoriesResponse != null) {
                for (GitHubRepository repository : repositoriesResponse) {
                    if (!repository.getFork()) {
                        repository.setGitHubBranches(getBranches(username, repository.getRepositoryName()));
                        repositories.add(repository);
                    }
                }
                return repositories;
            }
            return new ArrayList<>();
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public List<GitHubBranch> getBranches(String username, String repositoryName) {
        try {
            String url = URL_GITHUB_API + "/repos/" + username + "/" + repositoryName + "/branches";
            GitHubBranch[] gitHubBranches = restTemplate.getForObject(
                    url, GitHubBranch[].class);
            if (gitHubBranches != null) {
                return Arrays.asList(gitHubBranches);
            }
            return new ArrayList<>();
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }
}
