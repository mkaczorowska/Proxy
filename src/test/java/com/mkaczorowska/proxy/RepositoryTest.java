package com.mkaczorowska.proxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    private static final String REPO_NAME = "name";
    private static final String REPO_LOGIN = "login";
    private Repository repo;

    @BeforeEach
    void setUp() {
        repo = new Repository(REPO_NAME, REPO_LOGIN);
    }

    @Test
    void getName() {
        assertEquals(REPO_NAME, repo.getName());
    }

    @Test
    void getLogin() {
        assertEquals(REPO_LOGIN, repo.getLogin());
    }

    @Test
    void getBranchNameAndCommitSha() {
        final String branchName = "branchName";
        final String commitSha = "commitSha";
        final int expected = 1;

        repo.addBranch(branchName, commitSha);
        assertEquals(expected, repo.getBranchNameAndCommitSha().size());
    }
}