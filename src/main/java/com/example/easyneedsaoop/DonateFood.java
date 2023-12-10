package com.example.easyneedsaoop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class DonateFood {
    @FXML
    private Button backBtn;
    public void backBtnAction(ActionEvent actionEvent) {
        backBtn.getScene().getWindow().hide();
    }
}
