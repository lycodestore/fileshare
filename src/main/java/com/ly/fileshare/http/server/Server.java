package com.ly.fileshare.http.server;

import com.ly.fileshare.http.config.ServerConfig;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket;
    private Executor pool;

    public Server(int port, int poolSize) {
        try {
            serverSocket = new ServerSocket(port);
            pool = Executors.newFixedThreadPool(poolSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startService() {
        System.out.println("listening: " +
                serverSocket.getInetAddress().getHostName() + ":" +
                ServerConfig.port);
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                pool.execute(new ServerRunnable(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(ServerConfig.port, ServerConfig.poolSize);
        server.startService();
    }
}
