package com.kafka.atiperaproject.service.dto;

import lombok.*;

public record BranchResponse(String branchName, String lastCommitHash) {}
