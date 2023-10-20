package com.example.easyneedsaoop;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
    private TextField usernameField;


    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private String[] Type = {"Consumer", "Hospital", "Home owner", "Catering owner", "Instructor", "Clothing Shops"};

    private Alert alert;

    public void loginBtn(){
        if(usernameField.getText().isEmpty()||passwordField.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Fill all the blank");
            alert.showAndWait();
        }else{
            String selectData="SELECT username, password FROM user WHERE username=? and password=?";

            connect =database.connectDB();

            try {
                prepare=connect.prepareStatement(selectData);
                prepare.setString(1, usernameField.getText().trim());
                prepare.setString(2, passwordField.getText().trim());

                result=prepare.executeQuery();

                if(result.next()){
                    data.username=usernameField.getText().trim();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Congratulation");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully login");
                    alert.showAndWait();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminControlPage.fxml"));
                    Stage stage=new Stage();
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setTitle("EasyNeeds");
                    stage.setScene(scene);
                    stage.show();
                    loginButton.getScene().getWindow().hide();
                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect username/Password");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public void regBtn() {
        if (suUsername.getText().isEmpty() ||
                suPassword.getText().isEmpty() ||
                suQuestion.getSelectionModel().getSelectedItem() == null) {
//        alert.show("error",,);
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Massage");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the blank");
            alert.showAndWait();
        } else {
            String regData = "INSERT INTO user (name,username,password,occupation,date)" + "VALUES(?,?,?,?,?)";
            connect = database.connectDB();
            try {
                String checkUsername = ("SELECT username FROM user WHERE username = '" + suUsername.getText() + "'");
                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();
                if (result.next()) {
                    String checkType = ("SELECT occupation  FROM user WHERE occupation = '" + (String) suQuestion.getSelectionModel().getSelectedItem() + "'");
                    prepare = connect.prepareStatement(checkType);
                    result = prepare.executeQuery();
                    if (result.next()) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Already registered");
                        alert.setHeaderText(null);
                        alert.setContentText("Already registered as a" + (String) suQuestion.getSelectionModel().getSelectedItem());
                        alert.showAndWait();
                    }
                }else{
            prepare = connect.prepareStatement(regData);
            prepare.setString(1, suName.getText());
            prepare.setString(2, suUsername.getText());
            prepare.setString(3, suPassword.getText());
            prepare.setString(4, (String) suQuestion.getSelectionModel().getSelectedItem());
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            prepare.setString(5, String.valueOf(sqlDate));
            prepare.executeUpdate();
//                alert.show("information",,);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Massage");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Registered Account");
            alert.showAndWait();

            suName.setText("");
            suUsername.setText("");
            suPassword.setText("");
            suQuestion.getSelectionModel().clearSelection();

            TranslateTransition slider = new TranslateTransition();
            slider.setNode(sidePanel);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                sideAlreadyHave.setVisible(false);
                sideCreateButton.setVisible(true);
            });
            slider.play();}
        }
     catch(Exception e){
        e.printStackTrace();
    }
}

}

    public void regQuesList(){
        List<String> listQ=new ArrayList<>();
        Collections.addAll(listQ, Type);
        ObservableList listData = FXCollections.observableArrayList(listQ);
        suQuestion.setItems(listData);
    }
    public void switchForm(ActionEvent event){
        TranslateTransition slider=new TranslateTransition();

        if(event.getSource()==sideCreateButton){
            slider.setNode(sidePanel);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));
            slider.setOnFinished((ActionEvent e)->{
                sideAlreadyHave.setVisible(true);
                sideCreateButton.setVisible(false);
                regQuesList();
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