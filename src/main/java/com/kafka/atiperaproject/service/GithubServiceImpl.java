package com.kafka.atiperaproject.service;

import com.kafka.atiperaproject.service.dto.BranchResponce;
import com.kafka.atiperaproject.service.dto.RepositoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GithubServiceImpl implements GithubService {
    private final RestTemplate restTemplate;

    public GithubServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<RepositoryResponse> getUserRepositories(String userName) {
        String url = "https://api.github.com/users/" + userName + "/repos";
        ResponseEntity<List> request = restTemplate.getForEntity(url, List.class);
        List<Map<String, Object>> repositories = request.getBody();
        if (repositories == null) return List.of();
        List<RepositoryResponse> responceList = new ArrayList<>();

        for (Map<String, Object> repo : repositories) {
            if((Boolean) repo.get("fork") != true){
                Map<String, Object> owner = (Map<String, Object>) repo.get("owner");

                RepositoryResponse repositoryResponse = new RepositoryResponse();
                repositoryResponse.setOwnerLogin(owner.get("login").toString());
                repositoryResponse.setRepositoryName(repo.get("name").toString());
                repositoryResponse.setBranches(getBranches(userName, repo.get("name").toString()));

                responceList.add(repositoryResponse);
            }
        }



        return responceList;
    }

    @Override
    public List<BranchResponce> getBranches(String ownerName, String repoName) {
        String url = "https://api.github.com/repos/" + ownerName + "/" + repoName + "/branches";
        ResponseEntity<List> request = restTemplate.getForEntity(url, List.class);
        List<Map<String, Object>> branches = request.getBody();

        if (branches == null) return List.of();

        List<BranchResponce> branchList = new ArrayList<>();

        for (Map<String, Object> branch : branches) {
            Map<String, Object> commit = (Map<String, Object>) branch.get("commit");

            BranchResponce branchResponce = new BranchResponce();
            branchResponce.setBranchName(branch.get("name").toString());
            branchResponce.setLastCommitHash(commit.get("sha").toString());

            branchList.add(branchResponce);
        }


        return branchList;
    }

}
