package com.example.easyneedsaoop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MoneyTransferStage {

    @FXML
    private TextField receiverMobileTxt;

    @FXML
    private TextField sendAmountTxt;

    @FXML
    private TextField userMobileTxt;

    @FXML
    private PasswordField userPasswordTxt;

    private Alert alert;

    private double amount ;

    @FXML
    private Button confirmPaymentButton;

    @FXML
    public void confirmPaymentButtonAction() throws IOException {
        if(userMobileTxt.getText()!= "" && userPasswordTxt.getText()!= "" && sendAmountTxt.getText()!= "" && receiverMobileTxt.getText() != ""){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulation");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Sent!");
            alert.showAndWait();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("consumerPage.fxml"));
            Stage stage=new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("EasyNeeds");
            stage.setScene(scene);
            stage.show();
            confirmPaymentButton.getScene().getWindow().hide();
        }else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Attempt");
            alert.setHeaderText(null);
            alert.setContentText("Please try again");
            alert.showAndWait();
            userMobileTxt.clear();
            receiverMobileTxt.clear();
            userPasswordTxt.clear();
        }
    }

}
