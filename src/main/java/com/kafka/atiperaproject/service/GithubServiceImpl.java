package com.kafka.atiperaproject.service;

import com.kafka.atiperaproject.exception.ExternalApiException;
import com.kafka.atiperaproject.service.dto.BranchResponse;
import com.kafka.atiperaproject.service.dto.RepositoryResponse;
import com.kafka.atiperaproject.service.dto.github.GithubApiBranch;
import com.kafka.atiperaproject.service.dto.github.GithubApiRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class GithubServiceImpl implements GithubService {

    private final RestClient restClient;

    public GithubServiceImpl(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://api.github.com").build();
    }

    @Override
    public List<RepositoryResponse> getUserRepositories(String userName) {
        GithubApiRepository[] repositories = restClient.get()
                .uri("/users/{username}/repos", userName)
                .retrieve()
                .body(GithubApiRepository[].class);

        if (repositories == null) {
            throw new ExternalApiException("Empty response from GitHub API");
        }

        return Arrays.stream(repositories)
                .filter(repo -> !repo.fork())
                .map(repo -> new RepositoryResponse(
                        repo.name(),
                        repo.owner().login(),
                        getBranches(repo.owner().login(), repo.name())
                ))
                .toList();
    }

    @Override
    public List<BranchResponse> getBranches(String ownerName, String repoName) {
        GithubApiBranch[] branches = restClient.get()
                .uri("/repos/{owner}/{repo}/branches", ownerName, repoName)
                .retrieve()
                .body(GithubApiBranch[].class);

        if (branches == null) {
            throw new ExternalApiException("Empty response from GitHub API");
        }

        return Arrays.stream(branches)
                .map(branch -> new BranchResponse(branch.name(), branch.commit().sha()))
                .toList();
    }
}
