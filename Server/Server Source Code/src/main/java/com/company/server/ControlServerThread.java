package com.company.server;

import com.company.clientList.ClientList;

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
                ClientList.showDisconnected();
                if (ClientList.getInstance().readyToClose) {
                    close = true;

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}