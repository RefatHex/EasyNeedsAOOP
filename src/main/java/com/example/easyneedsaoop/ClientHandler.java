package com.example.easyneedsaoop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;

public class ClientHandler implements Runnable {

    private static final HashMap<String, ClientHandler> clientHandlers = new HashMap<>();

    private Socket socket;
    private BufferedReader buffReader;
    private BufferedWriter buffWriter;
    private String name;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.buffWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.name = buffReader.readLine();

            synchronized (clientHandlers) {
                clientHandlers.put(name, this);
            }

            broadcastMessage("SERVER" + name + " has entered the room");

        } catch (Exception e) {
            closeAll(socket, buffReader, buffWriter);
        }
    }

    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()) {
            try {
                messageFromClient = buffReader.readLine();
                broadcastMessage(messageFromClient);
            } catch (Exception e) {
                closeAll(socket, buffReader, buffWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend) {
        synchronized (clientHandlers) {
            for (ClientHandler clientHandler : clientHandlers.values()) {
                try {
                    if (messageToSend != null) {
                        String[] parts = messageToSend.split(":");
                        if (parts.length >= 3) {
                            String sender = parts[0];
                            String receiver = parts[1];
                            String message = parts[2];
                            System.out.println(message);

                            if (!clientHandler.name.equals(sender) && clientHandler.name.equals(receiver)) {
                                clientHandler.buffWriter.write(messageToSend);
                                clientHandler.buffWriter.newLine();
                                clientHandler.buffWriter.flush();
                            }
                        }
                    }
                } catch (Exception e) {
                    closeAll(socket, buffReader, buffWriter);
                }
            }
        }
    }



    public void removeClientHandler() {
        synchronized (clientHandlers) {
            clientHandlers.remove(name);
            broadcastMessage("server " + name + " has gone");
        }
    }

    public void closeAll(Socket socket, BufferedReader buffReader, BufferedWriter buffWriter) {
        removeClientHandler();
        try {
            if (buffReader != null) {
                buffReader.close();
            }
            if (buffWriter != null) {
                buffWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
