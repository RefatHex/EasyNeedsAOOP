package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Message {
    @FXML
    private Button chatBtn;

    @FXML
    private Label customer_name;

    @FXML
    private Label last_msg;
    private messageData data;
    public void setData(messageData data){
        this.data=data;
        customer_name.setText(data.getSenderUsername());
        last_msg.setText(data.getMessage());
    }
    public void chatBtnHandler() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatBox.fxml"));
        AnchorPane pane = loader.load();
        ChatBox cardR = loader.getController();
        cardR.setData(data.getSenderUsername());
        System.out.println(data.getSenderUsername());
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(pane));
        stage.show();
    }

}
