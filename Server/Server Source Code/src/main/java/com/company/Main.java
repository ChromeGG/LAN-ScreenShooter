package com.company;

import com.company.main.MainThread;
import com.company.server.SocketServer;

public class Main {

    public static void main(String[] args) {
        MainThread mainThread = new MainThread();
        SocketServer server = new SocketServer();

        System.out.println("SERVER RUN");
        server.start();

        System.out.println("START MAIN");
        mainThread.start();

    }
}
