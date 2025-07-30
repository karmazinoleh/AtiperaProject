# GitHub Repositories API

This is a Spring Boot application that provides an API to retrieve GitHub repositories and branches of a given user.  

## Endpoints

### GET /api/github/{username}
Returns a list of non-fork repositories and their branches.
Example response:
```
[
  {
    "repositoryName": "Hello-World",
    "ownerLogin": "octocat",
    "branches": [
      {
        "branchName": "main",
        "lastCommitHash": "f5a1a3d..."
      }
    ]
  }
]
```
#### If user does not exist:
```
{
  "status": 404,
  "message": "GitHub user not found"
}
```

## Run application
Run Spring Boot application
```
bash
./mvnw spring-boot:run
```
