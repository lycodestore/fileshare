package com.ly.fileshare.http.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class HttpParser {
    public static HashMap<String, String> parseStatus(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String statusLine = bufferedReader.readLine();
        System.out.println(statusLine);
        String[] statusItems = statusLine.split(" ");
        String method = statusItems[0];
        String route = statusItems[1];
        String protocol = statusItems[2];

        HashMap<String, String> result = new HashMap<String, String>();
        result.put("method", method);
        result.put("route", route);
        result.put("protocol", protocol);

        return result;
    }
}
