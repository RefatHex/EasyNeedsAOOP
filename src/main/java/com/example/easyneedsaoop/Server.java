package com.example.easyneedsaoop;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
public class Server {
    private ServerSocket serverSocket;


    public Server(ServerSocket serverSocket){
       this.serverSocket=serverSocket;
    }

    public void startServer(){
        try{
            while (!serverSocket.isClosed()){
                Socket socket=serverSocket.accept();
                System.out.println("A user connected");
                ClientHandler clientHandler=new ClientHandler(socket);

                Thread thread=new Thread(clientHandler);
                thread.start();
            }
        }catch (Exception e){

        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(1234);
        Server server=new Server(serverSocket);
        server.startServer();
    }
}
