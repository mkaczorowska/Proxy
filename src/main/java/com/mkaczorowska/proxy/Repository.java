package com.mkaczorowska.proxy;

import java.util.HashMap;
import java.util.Map;

public class Repository {
    private String name;
    private String login;
    private Map<String, String> branchNameToCommitSha = new HashMap<>();

    public Repository(String name, String login) {
        this.name = name;
        this.login = login;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Map<String, String> getBranchNameAndCommitSha() {
        return branchNameToCommitSha;
    }

    public void addBranch(String name, String commitSha) {
        branchNameToCommitSha.put(name, commitSha);
    }
}
