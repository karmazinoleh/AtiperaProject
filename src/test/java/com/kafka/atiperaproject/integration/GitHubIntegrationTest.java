package com.kafka.atiperaproject.integration;

import com.kafka.atiperaproject.service.dto.BranchResponse;
import com.kafka.atiperaproject.service.dto.RepositoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GitHubIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnRepositoriesWithBranchesAndCommits_happyPath(){
        String url = "http://localhost:" + port + "/api/github/octocat";

        ResponseEntity<RepositoryResponse[]> response = restTemplate.getForEntity(url, RepositoryResponse[].class);
        RepositoryResponse[] repositories = response.getBody();
        assertThat(repositories).isNotNull();

        for (RepositoryResponse repo : repositories) {
            assertThat(repo.getRepositoryName()).isNotBlank();
            assertThat(repo.getOwnerLogin()).isEqualTo("octocat");
            List<BranchResponse> branches = repo.getBranches();
            assertThat(branches).isNotNull();

            for(BranchResponse branch : branches){
                assertThat(branch.getBranchName()).isNotBlank();
                assertThat(branch.getLastCommitHash()).isNotBlank();
            }
        }
    }
}
