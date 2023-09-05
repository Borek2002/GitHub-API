package com.apigit.controller;

import com.apigit.client.GitHubClient;
import com.apigit.domain.GitHubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GitHubController {

    @Autowired
    private GitHubClient gitHubClient;

    @GetMapping(value = "/userRepositories")
    public ResponseEntity<Object> listRepositories(@RequestParam String username,
                                                   @RequestHeader("Accept") String acceptHeader) {
        if (username.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("status", HttpStatus.BAD_REQUEST.value(), "Message", "Enter the username"));
        }
        if ("application/xml".equalsIgnoreCase(acceptHeader)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(Map.of("status", HttpStatus.NOT_ACCEPTABLE.value(), "Message", "Only JSON is supported"));

        }
        List<GitHubRepository> repositories = gitHubClient.getGitHubRepositories(username);
        if (repositories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("status", HttpStatus.NOT_FOUND.value(), "Message", "User not found"));
        }

        return new ResponseEntity<>(repositories, HttpStatus.OK);
    }
}
