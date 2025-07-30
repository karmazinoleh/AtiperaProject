package com.kafka.atiperaproject.service.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryResponse {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchResponse> branches;
}
