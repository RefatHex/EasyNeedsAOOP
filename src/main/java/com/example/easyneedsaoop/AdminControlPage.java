package com.example.easyneedsaoop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminControlPage {
    @FXML
    private Button signoutButton;

    @FXML
    public void signoutButtonAction(){
        Stage stage = (Stage)signoutButton.getScene().getWindow();
        stage.close();
    }
}
