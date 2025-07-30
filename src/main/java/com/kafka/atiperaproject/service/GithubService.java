package com.kafka.atiperaproject.service;

import com.kafka.atiperaproject.service.dto.BranchResponse;
import com.kafka.atiperaproject.service.dto.RepositoryResponse;

import java.util.List;

public interface GithubService {
    public List<RepositoryResponse> getUserRepositories(String userName);
    public List<BranchResponse> getBranches(String ownerName, String repoName);

}
