package com.kafka.atiperaproject.service.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchResponce {
    String branchName;
    String lastCommitHash;
}
