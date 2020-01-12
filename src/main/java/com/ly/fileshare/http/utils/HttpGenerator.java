package com.ly.fileshare.http.utils;

import com.ly.fileshare.http.config.ServerConfig;

import java.io.File;

public class HttpGenerator {

    public static String generateHeader(File file) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Connection: close\r\n");
        sb.append("Server: wetty\r\n");
        //sb.append("Date: Tue, 09 Aug 2011 15:44:04 GMT");

        if (file.isDirectory()) {
            sb.append("Content-Type: text/html;charset=UTF-8\r\n\r\n");
        } else {
            sb.append("Content-Type: ");
            String filename = file.getName();
            int index = filename.lastIndexOf(".") + 1;
            String externalName = filename.substring(index);
            sb.append(ServerConfig.mime.get(externalName));
            sb.append("\r\n\r\n");
        }

        return sb.toString();
    }

    public static String generateHtml(File file) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<title>\n");
        sb.append("fileshare");
        sb.append("</title>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        for (File ele: file.listFiles()) {
            sb.append("<a href='./");
            sb.append(ele.getName());
            if (ele.isDirectory()) {
                sb.append("/");
            }
            sb.append("'>");
            sb.append(ele.getName());
            sb.append("</a>\n");
            sb.append("<br>");
        }
        sb.append("</body>\n");
        sb.append("</html>\n");

        return sb.toString();
    }

    public static String generateNotFoundPage() {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Connection: close\r\n");
        sb.append("Server: wetty\r\n");
        sb.append("Content-Type: text/html;charset=UTF-8\r\n\r\n");
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<title>\n");
        sb.append("fileshare");
        sb.append("</title>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("<h1>404</h1>\n");
        sb.append("</body>\n");
        sb.append("</html>\n");

        return sb.toString();
    }
}
