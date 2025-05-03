package com.example.tobyspringinaction.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class SimpleApiExecutor implements ApiExecutor{
    @Override
    public String execute(URI uri) throws IOException {
        String response;
        URLConnection urlConnection = (URLConnection) uri.toURL().openConnection();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            response = br.lines().collect(Collectors.joining());
        }
        return response;
    }
}
