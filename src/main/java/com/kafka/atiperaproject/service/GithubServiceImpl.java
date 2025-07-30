package com.kafka.atiperaproject.service;

import com.kafka.atiperaproject.service.dto.RepositoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GithubServiceImpl implements GithubService {
    @Override
    public List<RepositoryResponse> getUserRepositories(String userName) {
        return List.of();
    }
}
