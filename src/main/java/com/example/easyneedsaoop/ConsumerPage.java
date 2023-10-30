package com.example.easyneedsaoop;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConsumerPage {
    @FXML
    private Button bookBtn;

    @FXML
    private ImageView DoctorIcon;

    @FXML
    private ImageView DoctorIcon1;

    @FXML
    private ImageView HospitalIcon1;

    @FXML
    private Button cartButton;

    @FXML
    private ImageView clothingIcon;

    @FXML
    private ImageView clothingIcon1;

    @FXML
    private ImageView eduationIcon;

    @FXML
    private ImageView eduationIcon1;

    @FXML
    private Button exitBtn;

    @FXML
    private ImageView foodIcon;

    @FXML
    private ImageView foodIcon1;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView homeImage;

    @FXML
    private ImageView homeImage1;

    @FXML
    private Button menuBtn;

    @FXML
    private Hyperlink myAccountButton;

    @FXML
    private Hyperlink rewardsLink;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ImageView shoppingIcon;

    @FXML
    private AnchorPane sidePanel;
    private boolean slided = false;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private ObservableList<rentData> cardDetails= FXCollections.observableArrayList();


    public ObservableList<rentData> menuGetData(){
        String sql="SELECT * FROM `rentinfo`";
        ObservableList<rentData> listData=FXCollections.observableArrayList();

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
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("RentCardDesign.fxml"));
                AnchorPane pane=loader.load();
                RentCardDesign cardR= loader.getController();
                cardR.setData(cardDetails.get(i));

                if(column==3){
                    column=0;
                    row+=1;
                }
                gridPane.add(pane,column++,row);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void initialize() {
        sidePanel.setVisible(false);
        menuDisplayCard();
    }
    public void menuBtn(){

        TranslateTransition slider = new TranslateTransition();
        if (slided) {
            slider.setToX(-100);
            slider.setOnFinished((ActionEvent e) -> {
                sidePanel.setVisible(false);
            });
        } else {
            sidePanel.setVisible(true);
            slider.setToX(214);
        }

        slider.setNode(sidePanel);
        slider.setDuration(Duration.seconds(.5));
        slider.play();

        slided = !slided;
    }


    @FXML
    void exitHandleEvent(ActionEvent event) {
        System.exit(0);
    }
}
