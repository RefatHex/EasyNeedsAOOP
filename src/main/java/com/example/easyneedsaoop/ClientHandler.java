package com.example.easyneedsaoop;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String clientUsername;

    public ClientHandler(Socket socket){
        try{
            this.socket=socket;
            this.writer =new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.reader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername= reader.readLine();
            clientHandlers.add(this);
            broadcastMSG("Server: "+clientUsername+"has entered the chat room.");

        }catch(IOException e){
            closeAll(socket, reader, writer);

        }
    }

    @Override
    public void run() {

        String messageFromClient;
        while(socket.isConnected()){
            try{
                messageFromClient= reader.readLine();
                broadcastMSG(messageFromClient);
            }catch(IOException e){
                closeAll(socket, reader, writer);
                break;
            }
        }

    }

    public void broadcastMSG(String messageToSend){
        for(ClientHandler clientHandler: clientHandlers){
            try{
                if(!clientHandler.clientUsername.equals(clientUsername)){
                    clientHandler.writer.write(messageToSend);
                    clientHandler.writer.newLine();
                    clientHandler.writer.flush();
                }
            }catch(IOException e){
                closeAll(socket, reader, writer);
            }
        }
    }
    public void removeCliHandler(){
        clientHandlers.remove(this);
        broadcastMSG("Server: "+clientUsername+" left the room.");
    }

    public void closeAll(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeCliHandler();
        try{
            if(bufferedReader !=null){
                bufferedReader.close();
            }
            if(bufferedWriter !=null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}