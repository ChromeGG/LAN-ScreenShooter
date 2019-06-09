package com.company.client;

import com.company.screenShoter.SSmaker;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.Socket;
import java.util.Base64;

public class SocketClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {
        System.out.println("Trying to connect with server");
        while (true) {
            try {
                clientSocket = new Socket(ip, port);
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                System.out.println("Error w ustawianiu");
            }
            if (clientSocket != null) {
                waitForInstructions();
            }
        }
    }

    private void waitForInstructions() {

        String choose = null;

        boolean connected = true;

        while (connected) {
            try {
                choose = in.readLine();
            } catch (IOException e) {
                System.out.println("Error w readLine");
                connected = false;
            }

            System.out.println("Waiting for instructions.");

            if (choose != null) {
                switch (choose) {
                    case "1":
                        SSmaker robot = new SSmaker();
                        BufferedImage screen = robot.screenCapture();
                        System.out.println("Screen captured!");

                        String convertedScreen = imgToBase64String(screen, "jpg");

                        sendSS(convertedScreen);

                        System.out.println("Screen sent to server");
                        break;
                    case "2":
                        System.out.println("Connecting test");
                        out.println(clientSocket.getInetAddress());
                        break;
                    default:
                        System.out.println("ERROR");
                }
            }
        }
    }

    private void sendSS(String encodedString) {
        out.println(encodedString);
    }

    private static String imgToBase64String(final RenderedImage img, final String formatName) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            ImageIO.write(img, formatName, os);
            return Base64.getEncoder().encodeToString(os.toByteArray());
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}

