package com.mkaczorowska.proxy;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/repositories")
public class RepositoriesApi {
    public static final String ACCEPTABLE_HEADER = "application/json";
    private static final String NOT_ACCEPTABLE_HEADER = "application/xml";
    @GetMapping("/getForUser")
    public List<Repository> get(@RequestHeader("Accept") String header, @RequestParam("username") String username) {
        checkHeader(header);
        List<Repository> repositoryList = new ArrayList<>();
        try {
            final URL url = new URL("https://api.github.com/users/" + username + "/repos");
            String response = HttpHelper.fetchData(url, () -> { throw new UserNotFoundException(username); });
            JSONArray repositories = new JSONArray(response);

            for (int i = 0; i < repositories.length(); i++) {
                JSONObject githubRepository = repositories.getJSONObject(i);
                if (githubRepository.getBoolean("fork")) continue;

                final String repoName = githubRepository.getString("name");
                final String ownerLogin = githubRepository.getJSONObject("owner").getString("login");
                Repository repo = new Repository(repoName, ownerLogin);

                String branchesUrl = githubRepository.getString("branches_url").replaceAll("\\{.+\\}", "");
                String branchesResponse = HttpHelper.fetchData(new URL(branchesUrl), () -> {});
                JSONArray branches = new JSONArray(branchesResponse);

                for (int j = 0; j < branches.length(); j++) {
                    JSONObject branch = branches.getJSONObject(j);
                    String branchName = branch.getString("name");
                    String lastCommitSha = branch.getJSONObject("commit").getString("sha");
                    repo.addBranch(branchName, lastCommitSha);
                }
                repositoryList.add(repo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repositoryList;
    }

    private void checkHeader(String header) {
        if (!ACCEPTABLE_HEADER.equals(header)) {
            if (NOT_ACCEPTABLE_HEADER.equals(header)) {
                throw new NotAcceptableHeaderException(NOT_ACCEPTABLE_HEADER);
            } else {
                throw new RuntimeException("Incorrect header");
            }
        }
    }
}
