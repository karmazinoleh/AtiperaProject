package com.kafka.atiperaproject.service.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchResponse {
    String branchName;
    String lastCommitHash;
}
