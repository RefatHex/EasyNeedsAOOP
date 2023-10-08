package com.example.easyneedsaoop;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {
    @FXML
    private Hyperlink forgotButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordField;

    @FXML
    private Button sideCreateButton;

    @FXML
    private AnchorPane sidePanel;

    @FXML
    private AnchorPane signUpForm;

    @FXML
    private Button suButton;

    @FXML
    private Button sideAlreadyHave;

    @FXML
    private Button exitButton;

    @FXML
    private TextField suName;

    @FXML
    private TextField suPassword;

    @FXML
    private ComboBox<?> suQuestion;

    @FXML
    private TextField suUsername;

    @FXML
    private TextField usernameFiled;
    public void switchForm(ActionEvent event){
        TranslateTransition slider=new TranslateTransition();

        if(event.getSource()==sideCreateButton){
            slider.setNode(sidePanel);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));
            slider.setOnFinished((ActionEvent e)->{
                sideAlreadyHave.setVisible(true);
                sideCreateButton.setVisible(false);
            });
            slider.play();
        } else if (event.getSource()==sideAlreadyHave) {
            slider.setNode(sidePanel);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e)->{
                sideAlreadyHave.setVisible(false);
                sideCreateButton.setVisible(true);
            });
            slider.play();
        }
    }

    public void forgotPasswordAction(ActionEvent actionEvent) {
    }
    @FXML
    public void exitButtonAction(){
        Stage stage = (Stage)exitButton.getScene().getWindow();
        stage.close();
    }
}