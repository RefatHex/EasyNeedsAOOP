package com.example.easyneedsaoop;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenUserDetails {
    public void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDetails.fxml"));
        AnchorPane pane = loader.load();
        UserDetails cardR = loader.getController();
        cardR.setData();
        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
