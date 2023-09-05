# GitHub-API

## Description
It's a simple project that uses the GitHub Api. Provides an endpoint that takes an argument and returns the given user's repositories owner login and For each branch it's name and last commit sha.

## Usage
When we run this operation:
```bash
 http://localhost:8080/userRepositories?username={your username}
```
we get user's respositories with the data mentioned in the description.
When consumer given not existing github user, I would like to receive 404 response.
When consumer given header “Accept: application/xml”, I would like to receive 406 response.
