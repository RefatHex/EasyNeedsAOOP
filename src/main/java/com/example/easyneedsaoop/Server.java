package com.example.easyneedsaoop;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 5555;
    private static Map<String, PrintWriter> clientWriters = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running and waiting for clients...");

            ExecutorService executorService = Executors.newFixedThreadPool(10);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                // Create a new PrintWriter for the client
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                // Add the PrintWriter to the map with the client username
                String clientUsername = readUsername(clientSocket);
                clientWriters.put(clientUsername, writer);

                // Handle each client in a separate thread
                executorService.submit(() -> handleClient(clientSocket, writer, clientUsername));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket, PrintWriter writer, String clientUsername) {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String clientAddress = clientSocket.getInetAddress().toString();
            System.out.println("Handling client at " + clientAddress);

            // Your logic for handling messages from the client goes here
            while (true) {
                String message = reader.readLine();
                if (message == null) {
                    System.out.println("Client disconnected: " + clientAddress);
                    break;
                }

                // Broadcast the received message to all clients except the sender
                sendBroadcastMessage(clientUsername, message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Remove the writer when the client disconnects
            clientWriters.remove(clientUsername);
            broadcastMessage("Server: Client left the room.");
        }
    }

    private static void sendBroadcastMessage(String senderUsername, String message) {
        System.out.println("Broadcasting message from " + senderUsername + ": " + message);
        for (Map.Entry<String, PrintWriter> entry : clientWriters.entrySet()) {
            String username = entry.getKey();
            PrintWriter writer = entry.getValue();

            // Skip the sender's PrintWriter
            if (!username.equals(senderUsername)) {
                writer.println(senderUsername + ": " + message);
            }
        }
    }


    private static String readUsername(Socket clientSocket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String username = reader.readLine();
        System.out.println("Username received: " + username);
        return username;
    }

    private static void broadcastMessage(String message) {
        System.out.println("Broadcasting message: " + message);
        for (PrintWriter writer : clientWriters.values()) {
            writer.println(message);
        }
    }
}