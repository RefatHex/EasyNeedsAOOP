package com.example.easyneedsaoop;

import java.io.IOException;

public class Client {
    private String clientName;
    public Client(String serverAddress, int serverPort) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(serverAddress, serverPort);
        networkUtil.write(this.clientName);

    }
}
