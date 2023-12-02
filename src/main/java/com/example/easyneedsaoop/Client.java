package com.example.easyneedsaoop;

import javafx.scene.layout.BorderWidths;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String username;

    public Client(Socket socket,String username){
        try{
            this.socket=socket;
            this.reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (Exception e){
            closeAll(socket,reader,writer);
        }
    }

    public void sendMessage(){
        try{
            writer.write(username);
            writer.newLine();
            writer.flush();

            Scanner scan=new Scanner(System.in);
            while(socket.isConnected()){
                String messageToSend=scan.nextLine();
                writer.write(username+": "+messageToSend);
                writer.newLine();
                writer.flush();
            }
        }catch (Exception e){
            closeAll(socket,reader,writer);
        }
    }
    public void listenForMessage(){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        String msgFromGroupChat;
                        while (socket.isConnected()){
                            try{
                                msgFromGroupChat=reader.readLine();
                                System.out.println(msgFromGroupChat);
                            }catch (Exception e){
                                closeAll(socket,reader,writer);
                            }
                        }
                    }
                }
        ).start();
    }
    public void closeAll(Socket socket ,BufferedReader bufferedReader,BufferedWriter bufferedWriter){
        try{
            if(bufferedReader!=null){
                bufferedReader.close();
            }
            if(bufferedWriter!=null){
                bufferedWriter.close();
            }
            if(socket!=null){
                socket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username=scan.nextLine();
        Socket socket=new Socket("localhost",1234);
        Client client=new Client(socket,username);
        client.listenForMessage();
        client.sendMessage();
    }
}
