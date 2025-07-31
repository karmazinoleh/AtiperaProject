package com.kafka.atiperaproject.service.dto;

import lombok.*;

import java.util.List;


public record RepositoryResponse(String repositoryName, String ownerLogin, List<BranchResponse> branches) {}
