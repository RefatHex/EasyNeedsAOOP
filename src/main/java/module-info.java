module com.example.easyneedsaoop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.easyneedsaoop to javafx.fxml;
    exports com.example.easyneedsaoop;
}