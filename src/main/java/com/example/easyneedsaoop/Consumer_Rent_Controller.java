package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Consumer_Rent_Controller implements Initializable {

    @FXML
    private Button backBtn;
    @FXML
    private GridPane gridPane;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private ObservableList<rentData> cardDetails= FXCollections.observableArrayList();


    public ObservableList<rentData> menuGetData(){
        String sql="SELECT * FROM `rentinfo`";
        ObservableList<rentData> listData= FXCollections.observableArrayList();

        connect=database.connectDB();
        try{
            prepare=connect.prepareStatement(sql);
            result= prepare.executeQuery();
            rentData houseData;
            while(result.next()){
                houseData=new rentData(result.getInt("id"),
                        result.getString("houseName"),
                        result.getInt("flatNo"),
                        result.getDouble("rent"),
                        result.getString("address"),
                        result.getString("image"));
                listData.add(houseData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }
    public void menuDisplayCard(){
        cardDetails.clear();
        cardDetails.addAll(menuGetData());
        int row=0;
        int column=0;
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().clear();
        for(int i=0;i<cardDetails.size();i++){
            try {
                System.out.println(cardDetails.get(i).toString());
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("RentCard.fxml"));
                AnchorPane pane=loader.load();
                RentCardDesign cardR= loader.getController();
                cardR.setData(cardDetails.get(i));
                pane.setOnMouseClicked(event -> handleCardClick(cardR));


                if(column==4){
                    column=0;
                    row+=1;
                }
                // Add margins to create space between cards
                Insets margin = new Insets(10);
                GridPane.setMargin(pane, margin);
                gridPane.add(pane,column++,row);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void handleCardClick(RentCardDesign card) {
        rentData clickedData = card.getData();
        try {
            rentData houseData = menuGetSpecificData(clickedData.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("booking_agreements.fxml"));
            AnchorPane pane = loader.load();

            BookingAgreements bookingController = loader.getController();
            bookingController.setData(houseData);


            // Create a new stage and set the scene
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(pane));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public rentData menuGetSpecificData(int id) {
        String sql = "SELECT * FROM `rentinfo` WHERE id=?";
        rentData houseData = null;

        connect = database.connectDB();
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    houseData = new rentData(
                            resultSet.getInt("id"),
                            resultSet.getString("ownerName"),
                            resultSet.getString("houseName"),
                            resultSet.getString("username"),
                            resultSet.getDouble("rent"),
                            resultSet.getString("address"),
                            resultSet.getString("einfo"),
                            resultSet.getString("image"),
                            resultSet.getBoolean("bachelor"),
                            resultSet.getBoolean("sublet"),
                            resultSet.getBoolean("dn_draw"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return houseData;
    }
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            menuDisplayCard();
        }
        public void backBtnAction() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FrontPage.fxml"));
            Stage stage=new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
            backBtn.getScene().getWindow().hide();
        }


    public void slideCartForm(ActionEvent actionEvent) {
    }
}



