package com.example.easyneedsaoop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class About_Us {
    @FXML
    private Button back_btn;

    public void back_btn_Action(ActionEvent event) {
        back_btn.getScene().getWindow().hide();
    }
}
