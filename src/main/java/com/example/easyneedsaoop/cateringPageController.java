package com.example.easyneedsaoop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class cateringPageController implements Initializable {

    @FXML
    private AnchorPane adminAnchorPane;
    @FXML
    private Button cateringPaymentBtn;
    @FXML
    private Button dashboard_btn;
    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private AnchorPane menuAnchorPane;
    @FXML
    private AnchorPane catering_form;

    @FXML
    private Button catering_btn;

    @FXML
    private Label userName;

    private Alert alert ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
    }

    public void handleEvent(ActionEvent event) throws IOException {
        if (event.getSource() == catering_btn) {
            dashboard_form.setVisible(false);
            catering_form.setVisible(true);
        } else if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            catering_form.setVisible(false);
        } else if (event.getSource() == cateringPaymentBtn) {
            dashboard_form.setVisible(false);
            catering_form.setVisible(false);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoneyTransferStage.fxml"));
            Stage stage=new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setTitle("Easy Pay");
            stage.show();
            cateringPaymentBtn.getScene().getWindow().hide();
        }
    }
    @FXML
    void logout(ActionEvent event) {
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logoutBtn.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginsignup.fxml"));
                Stage stage=new Stage();
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("EasyNeeds");
                stage.setScene(scene);
                stage.show();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void displayUsername(){
        String user=data.username;
        user=user.substring(0,1).toUpperCase()+ user.substring(1);

        userName.setText(user);
    }


}
