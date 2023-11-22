package com.example.easyneedsaoop;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class OwnerTenantAgreementController {
    Alert alert;

    @FXML
    private CheckBox ck1;

    @FXML
    private CheckBox ck2;

    @FXML
    private CheckBox ck3;

    @FXML
    private CheckBox ck4;

    @FXML
    private CheckBox ck5;

    @FXML
    private CheckBox ck6;

    @FXML
    private CheckBox ck7;

    @FXML
    private CheckBox ck8;

    @FXML
    private CheckBox ck9;

    @FXML
    private CheckBox ck10;

    @FXML
    private CheckBox ck11;

    @FXML
    private CheckBox ck12;

    @FXML
    private Button completeButton;
    @FXML
    public void completeButtonAction(){
        if(ck1.isSelected() && ck2.isSelected() && ck3.isSelected() && ck4.isSelected() &&
        ck5.isSelected() && ck6.isSelected() && ck7.isSelected() && ck8.isSelected() &&
        ck9.isSelected() && ck10.isSelected() && ck11.isSelected() && ck12.isSelected()){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Massage");
            alert.setHeaderText(null);
            alert.setContentText("All terms & conditions Agreed");
            alert.showAndWait();
            completeButton.getScene().getWindow().hide();
        }
        else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Already registered");
            alert.setHeaderText(null);
            alert.setContentText("Cannot proceed without agreeing to terms and conditions");
            alert.showAndWait();
        }

    }
}
