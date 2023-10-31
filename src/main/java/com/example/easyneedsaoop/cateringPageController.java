package com.example.easyneedsaoop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class cateringPageController implements Initializable {

    @FXML
    private AnchorPane adminAnchorPane;

    @FXML
    private ComboBox<?> billPay;

    @FXML
    private TextField cateringOwnerID;

    @FXML
    private TextField cateringOwnerName;

    @FXML
    private TextField cateringOwnerPhone;

    @FXML
    private Button cateringPaymentBtn;

    @FXML
    private TextField cateringShopAddress;

    @FXML
    private TextField cateringShopBranch;

    @FXML
    private TextField cateringShopName;

    @FXML
    private TextField cateringShopPhone;

    @FXML
    private Button catering_ImportBtn1;

    @FXML
    private Button catering_UpdateBtn1;

    @FXML
    private Button catering_addBtn1;

    @FXML
    private Button catering_btn;

    @FXML
    private Button catering_clearBtn1;

    @FXML
    private TableColumn<?, ?> catering_col_Branch1;

    @FXML
    private TableColumn<?, ?> catering_col_Id1;

    @FXML
    private TableColumn<?, ?> catering_col_address1;

    @FXML
    private TableColumn<?, ?> catering_col_contact1;

    @FXML
    private TableColumn<?, ?> catering_col_contact11;

    @FXML
    private TableColumn<?, ?> catering_col_date1;

    @FXML
    private TableColumn<?, ?> catering_col_eInfo1;

    @FXML
    private TableColumn<?, ?> catering_col_mealType1;

    @FXML
    private TableColumn<?, ?> catering_col_owner1;

    @FXML
    private TableColumn<?, ?> catering_col_shopName1;

    @FXML
    private Button catering_deleteBtn1;

    @FXML
    private AnchorPane catering_form;

    @FXML
    private ImageView catering_imageView1;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TextField extraInfoText;

    @FXML
    private Label imgLbl11;

    @FXML
    private Label imgLbl21;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private ComboBox<?> mealDelivery;

    @FXML
    private ComboBox<?> mealType;

    @FXML
    private AnchorPane menuAnchorPane;

    @FXML
    private TableView<?> rentInTable1;

    @FXML
    private Label userName;

    private Alert alert ;
    @FXML
    private Label imgLbl1;
    @FXML
    private Label imgLbl2;

    private Image image;
    public String[] mealTypeOption={"Daily","Weekly","Monthly"};
    public String[] mealDeliveryOption={"Daily","Weekly","Monthly"};
    public String[] billOption={"Daily","Weekly","Monthly"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
        optionAdder();
    }
    public void optionAdder(){
        List<String> mtype = new ArrayList<>(Arrays.asList(mealTypeOption));
        List<String> delType = new ArrayList<>(Arrays.asList(mealDeliveryOption));
        List<String> billType = new ArrayList<>(Arrays.asList(billOption));
        ObservableList mealData= FXCollections.observableArrayList(mtype);
        ObservableList deliveryData= FXCollections.observableArrayList(delType);
        ObservableList billData= FXCollections.observableArrayList(billType);

        mealType.setItems(mealData);
        mealDelivery.setItems(deliveryData);
        billPay.setItems(billData);

    }
    public void InventoryImportBtn(){
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());

        if (file != null) {

            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 126, 120, false, true);
            catering_imageView1.setImage(image);
            imgLbl1.setVisible(false);
            imgLbl2.setVisible(false);

        }
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

    public void displayUsername(){
        String user=data.username;
        user=user.substring(0,1).toUpperCase()+ user.substring(1);

        userName.setText(user);
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
}
