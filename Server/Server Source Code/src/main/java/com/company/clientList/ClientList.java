package com.company.clientList;

import com.company.printer.Colors;
import com.company.server.SocketServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientList {

    private static ClientList INSTANCE;
    public volatile boolean readyToClose = false;
    public volatile List<SocketServer.EchoClientHandler> handlerList = new ArrayList<>();

    private ClientList() {
    }

    public static ClientList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientList();
        }

        return INSTANCE;
    }

    public static void showClients() {
        ClientList.refreshWithoutLogs();
        if (ClientList.getInstance().handlerList.isEmpty()) {
            System.out.println("No targets connected!");
        } else {
            List<SocketServer.EchoClientHandler> clientList = ClientList.getInstance().handlerList;
            int i = 0;

            for (SocketServer.EchoClientHandler client : clientList) {
                System.out.println(i + ". " + client.getPaintedIp());
                i++;
            }
        }
    }

    public static void refreshWithLogs() {
        if (ClientList.getInstance().handlerList.isEmpty()) {
            System.out.println("No targets connected!");
        } else {
            showDisconnected();
        }
    }

    public static void showDisconnected() {
        List<SocketServer.EchoClientHandler> toRemove = new ArrayList<>();
        for (SocketServer.EchoClientHandler clientThread : ClientList.getInstance().getHandlerList()) {
            clientThread.sendInstruction(2);
            try {
                String ip = clientThread.getIn().readLine();
            } catch (IOException e) {
                String clientIp = clientThread.getClientSocket().getInetAddress().toString().replace("/", "");
                System.out.println(Colors.RED + clientIp + " disconnect from server!" + Colors.RESET);
                toRemove.add(clientThread);
                clientThread.close();
                clientThread.interrupt();
            }
        }
        ClientList.getInstance().getHandlerList().removeAll(toRemove);
    }

    public static void refreshWithoutLogs() {

        List<SocketServer.EchoClientHandler> toRemove = new ArrayList<>();
        for (SocketServer.EchoClientHandler clientThread : ClientList.getInstance().getHandlerList()) {
            clientThread.sendInstruction(2);
            try {
                clientThread.getIn().readLine();
            } catch (IOException e) {
                toRemove.add(clientThread);
                clientThread.close();
            }
        }
        ClientList.getInstance().getHandlerList().removeAll(toRemove);
    }

    public void close() {
        readyToClose = true;
    }

    public List<SocketServer.EchoClientHandler> getHandlerList() {
        return handlerList;
    }

    public void setHandlerList(List<SocketServer.EchoClientHandler> handlerList) {
        this.handlerList = handlerList;
    }
}
