package com.mkaczorowska.proxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {
    private static final int STATUS = 200;
    private static final String MESSAGE = "message";
    private Response response;

    @BeforeEach
    void setUp() {
        response = new Response(STATUS, MESSAGE);
    }

    @Test
    void getStatus() {
        assertEquals(STATUS, response.getStatus());
    }

    @Test
    void getMessage() {
        assertEquals(MESSAGE, response.getMessage());
    }
}