package com.mkaczorowska.proxy;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HttpHelperTest {

    @Test
    void fetchCorrectData() throws IOException, JSONException {
        final URL url = new URL("https://api.github.com/users/octocat/repos");
        final int expected = 8;
        Runnable onNotFound = mock(Runnable.class);

        String result = HttpHelper.fetchData(url, onNotFound);
        JSONArray jsonArrayResult = new JSONArray(result);

        assertEquals(expected, jsonArrayResult.length());
        verify(onNotFound, never()).run();
    }

    @Test
    void dataNotFound() throws MalformedURLException {
        final URL url = new URL("https://api.github.com/users/mkaczorowska11/repos");
        Runnable onNotFound = mock(Runnable.class);

        try {
            HttpHelper.fetchData(url, onNotFound);
        } catch (IOException ex) {
            verify(onNotFound).run();
        }
    }
}