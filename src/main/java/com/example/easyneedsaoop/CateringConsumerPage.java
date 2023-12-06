package com.example.easyneedsaoop;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CateringConsumerPage implements Initializable {

    @FXML
    private Button Book;

    @FXML
    private AnchorPane cartForm;

    @FXML
    private Button cartBtn;
    @FXML
    private Button backBtn;

    @FXML
    private ImageView cartImage;

    @FXML
    private Button chatBtn;

    @FXML
    private Label foodName;

    @FXML
    private GridPane gridPane;

    @FXML
    private TextField location;

    @FXML
    private ComboBox<?> mealType;

    @FXML
    private Button payBtn;

    @FXML
    private ComboBox<?> plans;

    @FXML
    private Label price;

    @FXML
    private AnchorPane slider;

    @FXML
    private ComboBox<?> sortBtn;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private ObservableList<CateringData> cardDetails= FXCollections.observableArrayList();



    public String[] sortOption={"Relevance","A to Z","Z to A","Newest First","Oldest First"};

    public ObservableList<CateringData> menuGetData(){
        String sql="SELECT * FROM `cateringinfo`";
        ObservableList<CateringData> listData= FXCollections.observableArrayList();

        connect=database.connectDB();
        try{
            prepare=connect.prepareStatement(sql);
            result= prepare.executeQuery();
            CateringData mealDetails;
            while(result.next()){
                mealDetails=new CateringData(
                        result.getInt("id"),
                        result.getString("shopName"),
                        result.getString("userName"),
                        result.getString("address"),
                        result.getString("image"),
                        result.getDouble("price"),
                        result.getString("mealType"),
                        result.getString("mealDelivery")
                );
                listData.add(mealDetails);
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
        for (CateringData cardDetail : cardDetails) {
            try {
                System.out.println(cardDetail.toString());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("CateringCard.fxml"));
                AnchorPane pane = loader.load();
                Catering_card cardR = loader.getController();
                cardR.setData(cardDetail);
                pane.setOnMouseClicked(event -> handleCardClick(cardR));


                if (column == 4) {
                    column = 0;
                    row += 1;
                }
                // Add margins to create space between cards
                Insets margin = new Insets(10);
                GridPane.setMargin(pane, margin);
                gridPane.add(pane, column++, row);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private Image image;
    private void handleCardClick(Catering_card card) {
        CateringData clickedData = card.getData();
        try {
            System.out.println(clickedData.toString());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("catringCart.fxml"));
            AnchorPane pane = loader.load();
            CatringCart cardR = loader.getController();
            cardR.setData(clickedData);
            Stage stage = new Stage();
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(pane));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public void optionAdder(){
        List<String> sortType = new ArrayList<>(Arrays.asList(sortOption));
        ObservableList sortData= FXCollections.observableArrayList(sortType);
        sortBtn.setItems(sortData);
    }
    public void backBtnAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FrontPage.fxml"));
        Stage stage=new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        backBtn.getScene().getWindow().hide();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        optionAdder();
        menuDisplayCard();
    }
}
