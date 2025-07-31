package com.kafka.atiperaproject.service.dto.github;

public record GithubApiRepository(String name, boolean fork, GithubApiOwner owner) {}
