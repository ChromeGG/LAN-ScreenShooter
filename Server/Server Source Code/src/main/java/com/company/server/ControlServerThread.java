package com.company.server;

import com.company.clientList.ClientList;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

public class ControlServerThread extends Thread {

    @Override
    public void run() {
        startControl();
    }

    private void startControl() {
        boolean close = false;
        while (!close) {
            try {
                Thread.sleep(3000);
                //ClientList.showDisconnected();
                ClientList.showDisconnected();
                if (ClientList.getInstance().readyToClose) {
                    close = true;

                    //socketServer.stopServer();
//                    Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//                    for (Thread thread : threadSet) {
//                        if (thread.getName().equals("ServerThread")) {
//                            System.out.println(thread.getName() + " Was interrupted");
//                            //thread.interrupt();
//
//                            if (thread.getState() == Thread.State.RUNNABLE) {
//                                thread.interrupt();
//                            }
//
//                        }
//
////                        System.out.println("Thread: " + thread.getName() + ",\n Group: " + thread.getThreadGroup().getName());
////                        System.out.println();
////                        thread.interrupt();
//                    }
//
//                    System.out.println("Stopping all threads ...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}