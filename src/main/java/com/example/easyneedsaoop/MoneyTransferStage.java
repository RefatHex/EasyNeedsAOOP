package com.example.easyneedsaoop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MoneyTransferStage {

    private double amount ;

    public MoneyTransferStage(double amount){
        this.amount = amount;
    }

    @FXML
    private Button confirmPaymentButton;

    @FXML
    public void confirmPaymentButtonAction(){

    }

}
