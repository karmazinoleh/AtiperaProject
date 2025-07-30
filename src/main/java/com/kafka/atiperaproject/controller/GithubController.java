package com.kafka.atiperaproject.controller;

import com.kafka.atiperaproject.service.GithubService;
import com.kafka.atiperaproject.service.dto.RepositoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/github")
public class GithubController {
    private final GithubService githubService;

    @GetMapping("/{username}")
    public List<RepositoryResponse> getRepositories(@PathVariable String username) {
        return githubService.getUserRepositories(username);
    }

}
