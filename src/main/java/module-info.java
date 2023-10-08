module com.example.easyneedsaoop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.easyneedsaoop to javafx.fxml;
    exports com.example.easyneedsaoop;
}