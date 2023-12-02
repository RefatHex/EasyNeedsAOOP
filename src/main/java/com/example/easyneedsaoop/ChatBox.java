package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class ChatBox {

    @FXML
    private TextField msgBox;

    @FXML
    private Label receivername;
    @FXML
    private Button sendBtn;


    private ObservableList<messages> messageList = FXCollections.observableArrayList();
    public String senderUsername=data.username;
    public String receiverUsername;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void setData(String receiverUsername){
        this.receiverUsername=receiverUsername;
    }

    private void retrievemessages() {
        try {
            connect = database.connectDB();
            String sql = "SELECT id, message, senderUsername, receiverUsername FROM messages WHERE receiverUsername = ? AND senderUsername = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, receiverUsername);
            prepare.setString(2, senderUsername);
            result = prepare.executeQuery();

            messages retrievedMessage;
            while (result.next()) {
                retrievedMessage = new messages(
                        result.getInt("id"),
                        result.getString("message"),
                        result.getString("senderUsername"),
                        result.getString("receiverUsername")
                );
                messageList.add(retrievedMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
