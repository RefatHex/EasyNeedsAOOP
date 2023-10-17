package com.example.easyneedsaoop;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class OwnerTenantAgreementController {
    @FXML
    private Button completeButton;
    @FXML
    public void completeButtonAction(){
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Massage");
        alert.setHeaderText(null);
        alert.setContentText("Terms & Conditions Successfully Submitted");
        alert.showAndWait();
    }
}
