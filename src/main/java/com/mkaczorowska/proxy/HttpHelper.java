package com.mkaczorowska.proxy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpHelper {
    public static String fetchData(URL url, Runnable onNotFound) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", RepositoriesApi.ACCEPTABLE_HEADER);

        if (connection.getResponseCode() == 404) {
            onNotFound.run();
        }

        Scanner scan = new Scanner(connection.getInputStream());
        StringBuilder responseBuilder = new StringBuilder();
        while (scan.hasNext()) {
            responseBuilder.append(scan.nextLine());
        }
        scan.close();
        connection.disconnect();

        return responseBuilder.toString();
    }
}
