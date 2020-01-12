package com.ly.fileshare.http.handler;

import com.ly.fileshare.http.config.ServerConfig;
import com.ly.fileshare.http.parser.HttpParser;
import com.ly.fileshare.http.utils.HttpGenerator;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class HttpHandler {
    public static void handle(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        HashMap<String, String> status = HttpParser.parseStatus(inputStream);
        String route = status.get("route");
        System.out.println(route);

        OutputStream outputStream = socket.getOutputStream();
        File file = new File(ServerConfig.rootPath + route);
        if (file.exists()) {
            outputStream.write(HttpGenerator.generateHeader(file).getBytes());
            if (file.isDirectory()) {
                outputStream.write(HttpGenerator.generateHtml(file).getBytes());
            } else {
                byte[] byteArray = new byte[1024];
                FileInputStream fileInputStream = new FileInputStream(file);
                int readLength = fileInputStream.read(byteArray);
                while (readLength != -1) {
                    outputStream.write(byteArray, 0, readLength);
                    readLength = fileInputStream.read(byteArray);
                }
            }
        } else {
            outputStream.write(HttpGenerator.generateNotFoundPage().getBytes());
        }

        outputStream.flush();
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
