package com.mkaczorowska.proxy;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RepositoriesApiTest {
    private static final String EXISTING_USER = "octocat";
    private static final String ACCEPTABLE_HEADER = "application/json";
    private static final String PARAM = "username";
    private static final String HEADER = "Accept";
    private static final String TEMPLATE = "/repositories/getForUser";


    @Autowired
    private MockMvc mvc;

    @Test
    void getRepositories() throws Exception {
        final int expectedSize = 6;
        mvc.perform(get(TEMPLATE).param(PARAM, EXISTING_USER).header(HEADER, ACCEPTABLE_HEADER))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    void getRepositoriesForNotExistingUser() throws Exception {
        mvc.perform(get(TEMPLATE).param(PARAM, "mkaczorowska11").header(HEADER, ACCEPTABLE_HEADER))
                .andExpect(status().isNotFound());
    }

    @Test
    void getRepositoriesForNotAcceptableHeader() throws Exception {
        mvc.perform(get(TEMPLATE).param(PARAM, EXISTING_USER).header(HEADER, "application/xml"))
                .andExpect(status().isNotAcceptable());
    }
}