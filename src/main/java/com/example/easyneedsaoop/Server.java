package com.example.easyneedsaoop;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
public class Server {
    private ServerSocket serverSocket;


    public Server(){
        try{
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server has accepted a connection...");
                serve(clientSocket);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException{
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
    }

}
