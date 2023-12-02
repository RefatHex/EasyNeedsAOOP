package com.example.easyneedsaoop;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    public  static ArrayList<ClientHandler> clientHandlers=new ArrayList<>();
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String clientName;
    public ClientHandler(Socket socket){
        try{
            this.socket=socket;
            this.writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientName =reader.readLine();
            clientHandlers.add(this);
            broadcastMessage("Server: "+ clientName +" has entered the chat");
        }catch(Exception e){
            closeAll(socket,writer,reader);
        }
    }
    @Override
    public void run() {
        String messageFromClient;
        while(socket.isConnected()){
            try{
                messageFromClient=reader.readLine();
                broadcastMessage(messageFromClient);
            }catch(Exception e){
                closeAll(socket,writer,reader);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend){
        for(ClientHandler clientHandler:clientHandlers){
            try{
                if(!clientHandler.clientName.equals(clientName)){
                    clientHandler.writer.write(messageToSend);
                    clientHandler.writer.newLine();
                    clientHandler.writer.flush();
                }
            }catch (Exception e){
                closeAll(socket,writer,reader);
            }
        }
    }
    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("SERVER:" +clientName+"has left the chat");
    }

    public void closeAll(Socket socket,BufferedWriter bufferedWriter,BufferedReader bufferedReader){
        removeClientHandler();
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
}
