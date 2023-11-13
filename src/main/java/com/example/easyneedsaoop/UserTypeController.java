package com.example.easyneedsaoop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserTypeController  {

    @FXML
    private Button firstBtn;
    @FXML
    private Button secondBtn;
    @FXML
    private Button thirdBtn;
    @FXML
    private Button fourthBtn;
    @FXML
    private Button fifthBtn;
    @FXML
    private Button sixthBtn;
    @FXML
    private Label welcomeLbl;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private ArrayList<String> userTypeList = new ArrayList<>();
    private List<Button> buttonsList;
    private String selectedOption;
    private String[] Type = {"Consumer", "Health Assistant", "Home owner", "Catering owner", "Instructor", "Clothing Shops"};

    @FXML
    private void initialize() {
        buttonsList = Arrays.asList(firstBtn, secondBtn, thirdBtn, fourthBtn, fifthBtn, sixthBtn);
        buttonsList.forEach(btn -> {
            btn.setVisible(false);
            btn.setOnAction(event -> handleButtonAction(btn));
        });
        nextPage();
    }

    private void updateButtons() {
        buttonsList.forEach(btn -> btn.setVisible(false));


        for (int i = 0; i < userTypeList.size(); i++) {
            Button currentButton = buttonsList.get(i);
            currentButton.setText(userTypeList.get(i));
            currentButton.setVisible(true);
        }
    }

    private void handleButtonAction(Button btn) {
        selectedOption = btn.getText();
        showPage(selectedOption);
    }

    private void showPage(String selectedOption) {
        String location="";
        if (selectedOption.equals(Type[0])) {
            location="consumerPage.fxml";
        } else if (selectedOption.equals(Type[1])) {
            location="healthAssistantPage.fxml";
        } else if (selectedOption.equals(Type[2])) {
            location="rentPage.fxml";
        } else if (selectedOption.equals(Type[3])) {
            location="cateringPage.fxml";
        } else if (selectedOption.equals(Type[4])) {
            location="InstructorPage.fxml";
        } else if (selectedOption.equals(Type[5])) {
            location="ClothShopPage.fxml";
        } else {
            location="AdminControlPage.fxml";
        }
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(location));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("EasyNeeds");
            stage.setScene(scene);
            stage.show();
            firstBtn.getScene().getWindow().hide();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void nextPage(){
        String checkUsername = "SELECT username FROM user WHERE username = ?";
        connect =database.connectDB();

        try {
            prepare = connect.prepareStatement(checkUsername);
            prepare.setString(1, data.username);
            result = prepare.executeQuery();

            // If user exists
            if (result.next()) {
                // Fetch the occupation of that user
                String getOccupation = "SELECT occupation FROM user WHERE username = ?";
                prepare = connect.prepareStatement(getOccupation);
                prepare.setString(1, data.username);
                result = prepare.executeQuery();
                while (result.next()) {
                    userTypeList.add(result.getString("occupation"));
                }
                updateButtons();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
