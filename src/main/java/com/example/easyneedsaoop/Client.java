package com.example.easyneedsaoop;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Client {
    private String receiverName;
    // private classes for the clien
    private Socket socket;
    private BufferedReader buffReader;
    private BufferedWriter buffWriter;
    private String senderName;
    ChatBox chatBox;
    private String message;


    public Client(Socket socket, String senderName, String receiverName,ChatBox chatBox) {
        try {
            // Constructors of all the private classes
            this.socket = socket;
            this.buffWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.senderName = senderName;
            this.receiverName = receiverName;  // Add receiverName as a field
            this.chatBox=chatBox;
            sendUsername();

        } catch (Exception e) {
            closeAll(socket, buffReader, buffWriter);
        }
    }
    //send username to the server
    private void sendUsername() throws Exception {
        buffWriter.write(senderName);
        buffWriter.newLine();
        buffWriter.flush();

    }
    // Add this method to send messages to the server
    public void sendMessageToServer(String message) {
        try {
            buffWriter.write(senderName + ":" + receiverName + ":" + message);
            buffWriter.newLine();
            buffWriter.flush();
            try {
                executeInsert(senderName,receiverName,message);
                System.out.println("Added to database");
            }catch(Exception e){
                System.out.println("Couldn't add to database");
            }

        } catch (Exception e) {
            closeAll(socket, buffReader, buffWriter);
        }
    }

    // method to read messages using thread
    public void readMessage(){
        new Thread( new Runnable() {

            @Override
            public void run() {
                String msfFromGroupChat;

                while(socket.isConnected()){
                    try{
                        msfFromGroupChat = buffReader.readLine();
                        System.out.println(msfFromGroupChat);
                        chatBox.receiveMessageFromServer(msfFromGroupChat);
                    } catch (Exception e){
                        closeAll(socket, buffReader, buffWriter);
                    }

                }

            }

        }).start();
    }
    private boolean executeInsert(String senderUsername, String receiverUsername, String message) {
        String insertSql = "INSERT INTO messages (senderUsername, receiverUsername, message) VALUES (?, ?, ?)";

        try (Connection connection = database.connectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

            preparedStatement.setString(1, senderUsername);
            preparedStatement.setString(2, receiverUsername);
            preparedStatement.setString(3, message);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            closeAll(socket, buffReader, buffWriter);
            return false;
        }
    }

    // method to close everything in the socket
    public void closeAll(Socket socket, BufferedReader buffReader, BufferedWriter buffWriter){
        try{
            if(buffReader!= null){
                buffReader.close();
            }
            if(buffWriter != null){
                buffWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch (Exception e){
            e.getStackTrace();
        }
    }

//    // main method
//    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter your name:");
//        String senderName = sc.nextLine();
//        System.out.println("Enter the receiver's name:");
//        String receiverName = sc.nextLine();
//
//        Socket socket = new Socket("localhost", 1234);
//        Client client = new Client(socket, senderName, receiverName);
//        client.readMessage();
//        client.sendMessage();
//    }

}
