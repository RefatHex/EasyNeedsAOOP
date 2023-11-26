package com.example.easyneedsaoop;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Cloth_ConsumerPage implements Initializable {

    @FXML
    private AnchorPane cart_form;
//
//    @FXML
//    private TableView<?> cartTable;
//
//    @FXML
//    private TableColumn<?, ?> col_price;
//
//    @FXML
//    private TableColumn<?, ?> col_productName;
//
//    @FXML
//    private TableColumn<?, ?> col_quantity;
//
//    @FXML
//    private TableColumn<?, ?> col_size;

    @FXML
    private GridPane gridPane1;

    @FXML
    private GridPane gridPane2;

    @FXML
    private Button cartBtn;
    @FXML
    private Button closeBtn;


    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private ObservableList<ClothShopData> cardDetails= FXCollections.observableArrayList();


    public ObservableList<ClothShopData> menuGetData(){
        String sql="SELECT * FROM clothinginfo";
        ObservableList<ClothShopData> listData=FXCollections.observableArrayList();

        connect=database.connectDB();
        try{
            prepare=connect.prepareStatement(sql);
            result= prepare.executeQuery();
            ClothShopData prodData;
            while(result.next()){
                prodData=new ClothShopData(result.getInt("productID"),
                        result.getString("username"),
                        result.getString("shopName"),
                        result.getString("prodName"),
                        result.getDouble("price"),
                        result.getString("image")
                        );
                listData.add(prodData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }



    public void menuDisplayRecentCard(){
        cardDetails.clear();
        cardDetails.addAll(menuGetData());
        int row=0;
        int column=0;
        gridPane1.getRowConstraints().clear();
        gridPane1.getColumnConstraints().clear();
        for(int i=0;i<cardDetails.size();i++){
            try {
                System.out.println(cardDetails.get(i).toString());
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("Cloth_RecentCard.fxml"));
                AnchorPane pane=loader.load();
                Cloth_RecentCard cardR= loader.getController();
                cardR.setData(cardDetails.get(i));
                Insets margin = new Insets(10);
                GridPane.setMargin(pane, margin);
                gridPane1.add(pane,column++,row);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void menuDisplayMainCard(){
        cardDetails.clear();
        cardDetails.addAll(menuGetData());
        int row=0;
        int column=0;
        gridPane2.getRowConstraints().clear();
        gridPane2.getColumnConstraints().clear();
        for(int i=0;i<cardDetails.size();i++){
            try {
                System.out.println(cardDetails.get(i).toString());
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("Cloth_MainCard.fxml"));
                AnchorPane pane=loader.load();
                Cloth_MainCard cardR= loader.getController();
                cardR.setData(cardDetails.get(i));
                Insets margin = new Insets(10);
                GridPane.setMargin(pane, margin);
                gridPane2.add(pane,column++,row);
                if(column==4){
                    column=0;
                    row+=1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void slideCartForm(ActionEvent event){
        TranslateTransition slider=new TranslateTransition();

        if(event.getSource()==cartBtn){
            slider.setNode(cart_form);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));
            slider.play();
        }else if(event.getSource()==closeBtn){
            slider.setNode(cart_form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));
            slider.play();
        }
            slider.play();
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuDisplayMainCard();
        menuDisplayRecentCard();
    }
}