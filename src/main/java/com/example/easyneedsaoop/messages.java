package com.example.easyneedsaoop;

public class messages {
    int id;
    private String message;
    private String senderUsername;
    private String receiverUsername;

    public messages(int id, String message, String senderUsername, String receiverUsername) {
        this.id = id;
        this.message = message;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
