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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
import java.util.*;


public class Consumer_Rent_Controller implements Initializable {

    @FXML
    private Button backBtn;
    @FXML
    private GridPane gridPane;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    @FXML
    private TextField searchLocationBox;

    @FXML
    private Button searchLocationBtn;
    @FXML
    private ComboBox<?> sortOption;
    public String[] sortOptions={"Relevance","A to Z","Z to A","Newest First","Oldest First"};

    StringBuilder queryBuilder = new StringBuilder("SELECT * FROM `rentinfo` WHERE 1");



    private ObservableList<rentData> cardDetails= FXCollections.observableArrayList();
    private ObservableList<rentData> executeQuery(String sql) {
        ObservableList<rentData> listData = FXCollections.observableArrayList();

        Connection connect = null;
        PreparedStatement prepare = null;
        ResultSet result = null;

        try {
            connect = database.connectDB();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            rentData houseData;
            while (result.next()) {
                houseData = new rentData(
                        result.getInt("id"),
                        result.getString("houseName"),
                        result.getInt("flatNo"),
                        result.getDouble("rent"),
                        result.getString("address"),
                        result.getString("image"),
                        result.getInt("room")
                );
                listData.add(houseData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources in a finally block to ensure they are closed even if an exception occurs
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return listData;
    }

    public void menuDisplayCard(ObservableList<rentData> displayData) {
        int row = 0;
        int column = 0;
        gridPane.getChildren().clear();
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().clear();

        for (int i = 0; i < displayData.size(); i++) {
            try {
                System.out.println(displayData.get(i).toString());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("RentCard.fxml"));
                AnchorPane pane = loader.load();
                RentCardDesign cardR = loader.getController();
                cardR.setData(displayData.get(i));
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

    private ObservableList<rentData> updateSearchAndSortQuery() {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM `rentinfo` WHERE 1");

        String locationFilter = searchLocationBox.getText();
        if (!locationFilter.isEmpty()) {
            queryBuilder.append(" AND address LIKE '%").append(locationFilter).append("%'");
        }

        ObservableList<rentData> searchData = executeQuery(queryBuilder.toString());

        String selectedSortOption = String.valueOf(sortOption.getValue());
        if (selectedSortOption != null) {
            switch (selectedSortOption) {
                case "A to Z":
                    searchData.sort(Comparator.comparing(rentData::getHouseName));
                    break;
                case "Z to A":
                    searchData.sort(Comparator.comparing(rentData::getHouseName).reversed());
                    break;
                case "Newest First":
                    searchData.sort(Comparator.comparing(rentData::getId).reversed());
                    break;
                case "Oldest First":
                    searchData.sort(Comparator.comparing(rentData::getId));
                    break;
                // Add more cases as needed
                default:
                    // Default case: Do nothing (no sorting)
                    break;
            }
        }

        return searchData;
    }

    public void handleSearchAndSort() {
        cardDetails.clear();
        cardDetails.addAll(updateSearchAndSortQuery());

        menuDisplayCard(cardDetails);
    }

    private void handleCardClick(RentCardDesign card) {
        rentData clickedData = card.getData();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("booking_agreements.fxml"));
            AnchorPane pane = loader.load();

            BookingAgreements bookingController = loader.getController();
            bookingController.setData(clickedData);


            // Create a new stage and set the scene
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(pane));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            menuDisplayCard(executeQuery("SELECT * FROM `rentinfo`"));
            optionAdder();
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
    public void optionAdder(){
        List<String> sortType = new ArrayList<>(Arrays.asList(sortOptions));
        ObservableList sortData= FXCollections.observableArrayList(sortType);
        sortOption.setItems(sortData);
    }
}



