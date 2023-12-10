package com.example.easyneedsaoop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Donate {
    @FXML
    private Button backBtn;
    @FXML
    private Button donateCloth_btn;

    @FXML
    private Button donateFood_btn;

    @FXML
    private Button donateMoney_btn;

    public void backBtnAction(ActionEvent actionEvent) {
            backBtn.getScene().getWindow().hide();
    }

    public void handleEvent(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource()==donateMoney_btn){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoneyTransferStage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setTitle("Easy Pay");
            stage.show();
        }else if (actionEvent.getSource()==donateFood_btn){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DonateFood.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setTitle("Easy Pay");
            stage.show();
        } else if (actionEvent.getSource()==donateCloth_btn) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DonateCloth.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setTitle("Easy Pay");
            stage.show();
        }
    }
}
