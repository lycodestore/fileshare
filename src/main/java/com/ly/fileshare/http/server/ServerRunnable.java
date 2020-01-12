package com.ly.fileshare.http.server;

import com.ly.fileshare.http.handler.HttpHandler;
import com.ly.fileshare.http.parser.HttpParser;
import com.ly.fileshare.http.utils.HttpGenerator;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class ServerRunnable implements Runnable {
    private Socket socket;

    public ServerRunnable(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            HttpHandler.handle(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
