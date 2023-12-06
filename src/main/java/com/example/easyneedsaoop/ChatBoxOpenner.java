package com.example.easyneedsaoop;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatBoxOpenner {
    public void chatBtnHandler(String receiver) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatBox.fxml"));
        AnchorPane pane = loader.load();
        ChatBox cardR = loader.getController();
        cardR.setData(receiver);
        System.out.println(receiver);
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
