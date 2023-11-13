package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class ClothShopPageController implements Initializable {
    @FXML
    private Label imgLbl1;
    @FXML
    private Label imgLbl2;

    @FXML
    private AnchorPane Clothing_Form;

    @FXML
    private AnchorPane adminAnchorPane;
    @FXML
    private Button inventory_btn;
    @FXML
    private Button payment_btn;
    @FXML
    private TableColumn<?, ?> clothIn_col_ProdID;

    @FXML
    private TableColumn<?, ?> clothIn_col_ProdInfo;

    @FXML
    private TableColumn<?, ?> clothIn_col_ProdName;

    @FXML
    private TableColumn<?, ?> clothIn_col_ProdPrice;

    @FXML
    private TableColumn<?, ?> clothIn_col_ProdType;

    @FXML
    private TableColumn<?, ?> clothIn_col_category;

    @FXML
    private TableColumn<?, ?> clothIn_col_date;

    @FXML
    private TableView<ClothShopData> clothingTable;

    @FXML
    private Button customer_btn;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label imgLbl12;

    @FXML
    private Label imgLbl22;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private AnchorPane menuAnchorPane;

    @FXML
    private ComboBox<?> prodCat;

    @FXML
    private TextField prodID;

    @FXML
    private TextField prodInfo;

    @FXML
    private TextField prodName;

    @FXML
    private TextField prodPrice;

    @FXML
    private ComboBox<?> prodType;

    @FXML
    private AnchorPane rentAnchor1;

    @FXML
    private ImageView rentIn_imageView;

    @FXML
    private Button rent_btn;

    @FXML
    private Label userName;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Alert alert;
    private Image image;

    private String[] categoryOption={"kids","Man","Women"};
    private String[] prodTypeOption={"T-Shirt","Shirt","Panjabi","Shari","Skirt"};


    //Clothing Data

    public void clothShopInventoryAddBtn(){
        if(prodID.getText().isEmpty() ||
                prodName.getText().isEmpty() ||
                prodPrice.getText().isEmpty() ||
                prodCat.getSelectionModel().getSelectedItem() == null ||
                prodType.getSelectionModel().getSelectedItem() == null ||
                prodInfo.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            String insertData = "INSERT INTO clothinginfo " +
                    "(prodID, username, shopName, prodName, price,image, category, type, einfo, date)" +
                    "VALUES(?, ?,?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, prodID.getText());
                prepare.setString(2, data.username);
                prepare.setString(3, data.name);
                prepare.setString(4, prodName.getText());
                prepare.setString(5, prodPrice.getText());
                String path = data.path;
                path = path.replace("\\", "\\\\");
                prepare.setString(6,path);
                prepare.setString(7, String.valueOf(prodCat.getSelectionModel().getSelectedItem()));
                prepare.setString(8, String.valueOf(prodType.getSelectionModel().getSelectedItem()));
                prepare.setString(9, prodInfo.getText());

                java.util.Date date = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                prepare.setString(10, String.valueOf(sqlDate));

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                clothShopInventoryShowData();
                clothShopInventoryClearBtn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public void clothShopInventoryClearBtn() {
        prodID.clear();
        prodName.clear();
        prodPrice.clear();
        prodCat.getSelectionModel().clearSelection();
        prodType.getSelectionModel().clearSelection();
        prodInfo.clear();
    }

    public void clothShopInventoryUpdateBtn(){
        if(prodID.getText().isEmpty() ||
                prodName.getText().isEmpty() ||
                prodPrice.getText().isEmpty() ||
                prodCat.getSelectionModel().getSelectedItem() == null ||
                prodType.getSelectionModel().getSelectedItem() == null ||
                prodInfo.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
            return;
        }

        String updateData = "UPDATE clothinginfo SET " +
                "username=?, shopName=?, prodName=?, price=?,image=?, category=?, " +
                "type=?, einfo=?, date=? " +
                "WHERE prodID=?";

        try {
            prepare = connect.prepareStatement(updateData);
            prepare.setString(1, data.username);
            prepare.setString(2, data.name);
            prepare.setString(3, prodName.getText());
            prepare.setString(4, prodPrice.getText());
            String path = data.path;
            path = path.replace("\\", "\\\\");
            prepare.setString(5,path);
            prepare.setString(6, String.valueOf(prodCat.getSelectionModel().getSelectedItem()));
            prepare.setString(7, String.valueOf(prodType.getSelectionModel().getSelectedItem()));
            prepare.setString(8, prodInfo.getText());


            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            prepare.setString(9, String.valueOf(sqlDate));

            prepare.setString(10, prodID.getText());

            int affectedRows = prepare.executeUpdate();

            if (affectedRows > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                clothShopInventoryShowData();
                clothShopInventoryClearBtn();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Update Error");
                alert.setHeaderText(null);
                alert.setContentText("No records updated. Please check the product ID.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void clothShopInventoryDeleteBtn() {
        if (prodID.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please provide both the Product ID and Username to delete a product.");
            alert.showAndWait();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product?", ButtonType.YES, ButtonType.NO);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText(null);

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            String deleteQuery = "DELETE FROM clothinginfo WHERE prodID=? AND username=?";

            try {
                prepare = connect.prepareStatement(deleteQuery);
                prepare.setString(1, prodID.getText());
                prepare.setString(2, data.username);

                int affectedRows = prepare.executeUpdate();

                if (affectedRows > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delete Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    clothShopInventoryShowData();
                    clothShopInventoryClearBtn();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Delete Error");
                    alert.setHeaderText(null);
                    alert.setContentText("No records deleted. Please check the Product ID and Username.");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public ObservableList<ClothShopData> getClothShopListData() {
        ObservableList<ClothShopData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM clothinginfo";
        connect = database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ClothShopData data;
            while (result.next()) {
                data = new ClothShopData(
                        result.getInt("prodID"),
                        result.getString("username"),
                        result.getString("shopName"),
                        result.getString("prodName"),
                        result.getDouble("price"),
                        result.getString("image"),
                        result.getString("category"),
                        result.getString("type"),
                        result.getString("einfo"),
                        result.getDate("date")
                );
                listData.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    // Show Data on table
    private ObservableList<ClothShopData> clothShopInventoryList;
    public void clothShopInventoryShowData() {
        clothShopInventoryList = getClothShopListData();
        clothIn_col_ProdID.setCellValueFactory(new PropertyValueFactory<>("prodID"));
        clothIn_col_ProdInfo.setCellValueFactory(new PropertyValueFactory<>("einfo"));
        clothIn_col_ProdName.setCellValueFactory(new PropertyValueFactory<>("prodName"));
        clothIn_col_ProdPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clothIn_col_ProdType.setCellValueFactory(new PropertyValueFactory<>("type"));
        clothIn_col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        clothIn_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        clothingTable.setItems(clothShopInventoryList);
    }




//Static options
public void InventoryImportBtn(){
    FileChooser openFile = new FileChooser();
    openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

    File file = openFile.showOpenDialog(mainForm.getScene().getWindow());

    if (file != null) {

        data.path = file.getAbsolutePath();
        image = new Image(file.toURI().toString(), 126, 120, false, true);
        rentIn_imageView.setImage(image);
        imgLbl1.setVisible(false);
        imgLbl2.setVisible(false);

    }
}
    public void logout() {
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logoutBtn.getScene().getWindow().hide();
                Stage stage=new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginsignup.fxml"));
                stage.initStyle(StageStyle.UNDECORATED);
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
    public void optionAdder(){
        List<String> catgoryType=new ArrayList<>(Arrays.asList(categoryOption));
        List<String> prodTyp=new ArrayList<>(Arrays.asList(prodTypeOption));
        ObservableList categoryData=FXCollections.observableArrayList(catgoryType);
        ObservableList prodTypeData=FXCollections.observableArrayList(prodTyp);
        prodType.setItems(prodTypeData);
        prodCat.setItems(categoryData);
    }

    public void handleEvent(ActionEvent e){
        if(e.getSource()==dashboard_btn){
            dashboard_form.setVisible(true);
            Clothing_Form.setVisible(false);
        }else if(e.getSource()==inventory_btn){
            dashboard_form.setVisible(false);
            Clothing_Form.setVisible(true);
        }else if(e.getSource()==payment_btn){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoneyTransferStage.fxml"));
            Stage stage=new Stage();
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            stage.setScene(scene);
            stage.setTitle("Easy Pay");
            stage.show();
            payment_btn.getScene().getWindow().hide();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        optionAdder();
        dashboard_form.setVisible(true);
        Clothing_Form.setVisible(false);
        clothShopInventoryShowData();
    }
}
