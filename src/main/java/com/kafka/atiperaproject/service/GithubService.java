package com.kafka.atiperaproject.service;

import com.kafka.atiperaproject.service.dto.BranchResponce;
import com.kafka.atiperaproject.service.dto.RepositoryResponse;

import java.util.List;

public interface GithubService {
    public List<RepositoryResponse> getUserRepositories(String userName);
    public List<BranchResponce> getBranches(String ownerName, String repoName);

}
