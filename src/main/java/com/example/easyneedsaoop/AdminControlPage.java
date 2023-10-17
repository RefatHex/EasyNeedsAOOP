package com.example.easyneedsaoop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminControlPage {
    @FXML
    private Button signoutButton;

    @FXML
    public void signoutButtonAction() throws IOException {
        Stage stage = (Stage)signoutButton.getScene().getWindow();
        stage.close();
    }
}
