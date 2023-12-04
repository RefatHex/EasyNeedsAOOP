package com.example.easyneedsaoop;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 5555;
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running and waiting for clients...");

            ExecutorService executorService = Executors.newFixedThreadPool(10);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                // Create a new PrintWriter for the client
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                // Add the PrintWriter to the set of client writers
                clientWriters.add(writer);

                // Handle each client in a separate thread
                executorService.submit(() -> handleClient(clientSocket, writer));
            }
        } catch (IOException e) {

        }
    }

    private static void handleClient(Socket clientSocket, PrintWriter writer) {
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

                // Broadcast the received message to all connected clients
                broadcastMessage(message, writer);
            }
        } catch (IOException e) {
        } finally {
            // Remove the writer when the client disconnects
            clientWriters.remove(writer);
            broadcastMessage("Server: Client left the room.",writer);
        }
    }

    private static void broadcastMessage(String message, PrintWriter senderWriter) {
        System.out.println("Broadcasting message: " + message);
        for (PrintWriter writer : clientWriters) {
            if (writer != senderWriter) {
                writer.println(message);
            }
        }
    }

}
