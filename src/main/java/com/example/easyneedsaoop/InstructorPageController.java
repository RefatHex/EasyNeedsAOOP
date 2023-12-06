package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class InstructorPageController implements Initializable {


    @FXML
    private AnchorPane instructor_form;

    @FXML
    private AnchorPane adminAnchorPane;

    @FXML
    private ComboBox<?> courseCategory;

    @FXML
    private TextField courseDescription;

    @FXML
    private TextField courseExtraInfo;

    @FXML
    private TextField courseID;

    @FXML
    private TextField courseName;

    @FXML
    private TextField coursePrice;

    @FXML
    private ComboBox<?> course_type;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TextField instructName;

    @FXML
    private TableColumn<?, ?> instructorIn_col_courseCategory;

    @FXML
    private TableColumn<?, ?> instructorIn_col_courseEInfo;

    @FXML
    private TableColumn<?, ?> instructorIn_col_courseID;

    @FXML
    private TableColumn<?, ?> instructorIn_col_courseName;

    @FXML
    private TableColumn<?, ?> instructorIn_col_coursePrice;

    @FXML
    private TableColumn<?, ?> instructorIn_col_courseType;

    @FXML
    private TableColumn<?, ?> instructorIn_col_date;

    @FXML
    private TableView<CourseData> instructorTable;

    @FXML
    private Button instructor_addbtn;

    @FXML
    private Button instructor_cleatbtn;

    @FXML
    private Button instructor_deletebtn;

    @FXML
    private ImageView rentIn_imageView;

    @FXML
    private Button instructor_importbtn;

    @FXML
    private Button instructor_updatebtn;

    @FXML
    private Button inventory_btn;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private AnchorPane menuAnchorPane;

    @FXML
    private Button payment_btn;

    @FXML
    private AnchorPane rentAnchor1;

    @FXML
    private Label userName;
    @FXML
    private Label imgLbl1;

    @FXML
    private Label imgLbl2;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Alert alert;
    private Image image;
    @FXML
    private GridPane messageGridPane;
    @FXML
    private Button order_btn;
    @FXML
    private Button chat_btn;
    @FXML
    private AnchorPane order_form;
    @FXML
    private AnchorPane chat_form;

    @FXML
    private GridPane gridPane;

    private ObservableList<rentOrderData> orderDetails= FXCollections.observableArrayList();


    private String[] courseCategoryOption ={"Education","Tech","Programming","Study Material","Motivational"};
    private String[] courseTypeOption ={"Weekly","Yearly","Semester","Trimester"};

    public void courseInventoryAddBtn() {
        if (courseID.getText().isEmpty() ||
                courseName.getText().isEmpty() ||
                coursePrice.getText().isEmpty() ||
                instructName.getText().isEmpty() ||
                courseCategory.getSelectionModel().getSelectedItem() == null ||
                course_type.getSelectionModel().getSelectedItem() == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            String insertData = "INSERT INTO courseInfo " +
                    "(courseID, courseName, price, instructorName, userName, description, image, info, category, type, date)" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            try {
                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, courseID.getText());
                prepare.setString(2, courseName.getText());
                prepare.setString(3, coursePrice.getText());
                prepare.setString(4, instructName.getText());
                prepare.setString(5, data.username);
                prepare.setString(6, courseDescription.getText());
                prepare.setString(7, data.path.replace("\\", "\\\\"));
                prepare.setString(8, courseExtraInfo.getText());
                prepare.setString(9, String.valueOf(courseCategory.getSelectionModel().getSelectedItem()));
                prepare.setString(10, String.valueOf(course_type.getSelectionModel().getSelectedItem()));

                java.util.Date date = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                prepare.setString(11, String.valueOf(sqlDate));

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();


                 courseInventoryShowData();
                 courseInventoryClearBtn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void courseInventoryUpdateBtn() {
        if (courseID.getText().isEmpty() ||
                courseName.getText().isEmpty() ||
                coursePrice.getText().isEmpty() ||
                instructName.getText().isEmpty() ||
                courseCategory.getSelectionModel().getSelectedItem() == null ||
                course_type.getSelectionModel().getSelectedItem() == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
            return;
        }

        String updateData = "UPDATE courseInfo SET " +
                "courseName=?, price=?, instructorName=?, userName=?, description=?, image=?, info=?, category=?, type=?, date=? " +
                "WHERE courseID=?";

        try {
            prepare = connect.prepareStatement(updateData);
            prepare.setString(1, courseName.getText());
            prepare.setDouble(2, Double.parseDouble(coursePrice.getText()));
            prepare.setString(3, instructName.getText());
            prepare.setString(4, data.username);
            prepare.setString(5, courseDescription.getText());
            prepare.setString(6, data.path.replace("\\", "\\\\"));
            prepare.setString(7, courseExtraInfo.getText());
            prepare.setString(8, String.valueOf(courseCategory.getSelectionModel().getSelectedItem()));
            prepare.setString(9, String.valueOf(course_type.getSelectionModel().getSelectedItem()));

            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            prepare.setDate(10, sqlDate);

            prepare.setString(11, courseID.getText());

            int affectedRows = prepare.executeUpdate();

            if (affectedRows > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                courseInventoryShowData();
                courseInventoryClearBtn();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Update Error");
                alert.setHeaderText(null);
                alert.setContentText("No records updated. Please check the ID.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void courseInventoryClearBtn() {
        courseID.clear();
        courseName.clear();
        coursePrice.clear();
        instructName.clear();
        courseDescription.clear();
        courseExtraInfo.clear();
        courseCategory.getSelectionModel().clearSelection();
        course_type.getSelectionModel().clearSelection();
    }
    public void courseInventoryDeleteBtn() {
        if (courseID.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please provide the ID of the record to be deleted.");
            alert.showAndWait();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this record?", ButtonType.YES, ButtonType.NO);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText(null);

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            String deleteQuery = "DELETE FROM courseInfo WHERE courseID=?";

            try {
                prepare = connect.prepareStatement(deleteQuery);
                prepare.setString(1, courseID.getText());

                int affectedRows = prepare.executeUpdate();

                if (affectedRows > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delete Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    courseInventoryShowData();
                    courseInventoryClearBtn();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Delete Error");
                    alert.setHeaderText(null);
                    alert.setContentText("No records deleted. Please check the ID.");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //copy this to other classes
    private Map<String, StringBuilder> getMessagesByReceiver() {
        Map<String, StringBuilder> messagesMap = new HashMap<>();
        String sql = "SELECT senderUsername, message FROM messages WHERE receiverUsername = ?";
        try (
             PreparedStatement preparedStatement = connect.prepareStatement(sql)) {

            preparedStatement.setString(1, data.username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String senderUsername = resultSet.getString("senderUsername");
                    String message = resultSet.getString("message");

                    messagesMap.computeIfAbsent(senderUsername, k -> new StringBuilder()).append(message).append("\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messagesMap;
    }
    //copy this to other classes
    public void showMessageList() {
        int row = 0;
        int column = 0;
        messageGridPane.getChildren().clear();
        messageGridPane.getRowConstraints().clear();
        messageGridPane.getColumnConstraints().clear();
        Map<String, StringBuilder> messagesMap=getMessagesByReceiver();

        for (Map.Entry<String, StringBuilder> entry : messagesMap.entrySet()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Message.fxml"));
                AnchorPane pane = loader.load();
                Message card = loader.getController();

                card.setData(new messageData(entry.getKey(), entry.getValue().toString()));


                // Add margins to create space between cards
                Insets margin = new Insets(10);
                messageGridPane.setMargin(pane, margin);
                messageGridPane.add(pane, column, row++);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
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
    //MERGE DATA ON TABLE
    public ObservableList<CourseData> getCourseListData() {
        ObservableList<CourseData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM courseInfo";
        connect = database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            CourseData data;
            while (result.next()) {
                data = new CourseData(
                        result.getInt("courseID"),
                        result.getString("courseName"),
                        result.getDouble("price"),
                        result.getString("instructorName"),
                        result.getString("userName"),
                        result.getString("description"),
                        result.getString("image"),
                        result.getString("info"),
                        result.getString("category"),
                        result.getString("type"),
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
    private ObservableList<CourseData> courseInventoryList;
    public void courseInventoryShowData() {
        courseInventoryList = getCourseListData();

        instructorIn_col_courseID.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        instructorIn_col_courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        instructorIn_col_coursePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        instructorIn_col_courseType.setCellValueFactory(new PropertyValueFactory<>("type"));
        instructorIn_col_courseCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        instructorIn_col_courseEInfo.setCellValueFactory(new PropertyValueFactory<>("info"));
        instructorIn_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        instructorTable.setItems(courseInventoryList);
    }

    //OTHER DATA
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
                //stage.initStyle(StageStyle.UNDECORATED);
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
        List<String> courseCatgoryType=new ArrayList<>(Arrays.asList(courseCategoryOption));
        List<String> CourseType=new ArrayList<>(Arrays.asList(courseTypeOption));
        ObservableList courseCategoryData= FXCollections.observableArrayList(courseCatgoryType);
        ObservableList courseTypeData=FXCollections.observableArrayList(CourseType);
        courseCategory.setItems(courseTypeData);
        course_type.setItems(courseCategoryData);
        course_type.setEditable(true);
    }

    public void handleEvent(ActionEvent e){
        if(e.getSource()==dashboard_btn){
            dashboard_form.setVisible(true);
            instructor_form.setVisible(false);
            chat_form.setVisible(false);
            order_form.setVisible(false);
        }else if(e.getSource()==inventory_btn){
            dashboard_form.setVisible(false);
            instructor_form.setVisible(true);
            chat_form.setVisible(false);
            order_form.setVisible(false);
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
        }else if(e.getSource()==chat_btn){
            dashboard_form.setVisible(false);
            instructor_form.setVisible(false);
            order_form.setVisible(false);
            chat_form.setVisible(true);
            showMessageList();
        }else if (e.getSource() == order_btn){
            dashboard_form.setVisible(false);
            instructor_form.setVisible(false);
            order_form.setVisible(true);
            chat_form.setVisible(false);
        }
    }
    public ObservableList<rentOrderData> menuGetOrderData() {
        String sql = "SELECT * FROM `rentorder` WHERE ownerUserName = ?";
        ObservableList<rentOrderData> listOrderData = FXCollections.observableArrayList();

        connect = database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, data.username);
            result = prepare.executeQuery();
            rentOrderData orderData;
            while (result.next()) {
                orderData = new rentOrderData(result.getInt("id"),
                        result.getString("ownerName"),
                        result.getString("houseName"),
                        result.getString("ownerUserName"),
                        result.getString("tanentUserName"),
                        result.getDouble("rent"),
                        result.getString("address"),
                        result.getString("nidImage"),
                        result.getDate("date"));
                listOrderData.add(orderData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOrderData;
    }

    //copy this
    public void menuDisplayOrderCard() {
        orderDetails.clear();
        orderDetails.addAll(menuGetOrderData());
        int row = 0;
        int column = 0;
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().clear();
        for (rentOrderData orderDetail : orderDetails) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("order_show.fxml"));
                AnchorPane pane = loader.load();
                OrderShow cardR = loader.getController();
                cardR.setData(orderDetail);
//                pane.setOnMouseClicked(event -> handleOrderCardClick(cardR));
                // Add margins to create space between cards
                Insets margin = new Insets(10);
                GridPane.setMargin(pane, margin);
                gridPane.add(pane, column, row++);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        optionAdder();
        connect = database.connectDB();
        courseInventoryShowData();
        showMessageList();
        menuDisplayOrderCard ();
    }
}