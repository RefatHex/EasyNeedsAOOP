package com.example.easyneedsaoop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ClothShopPage.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);

//        Image image = new Image("D:\\Study Materials\\Trimester-4\\AOOP\\EasyNeedsAOOP\\src\\main\\resources\\com\\example\\easyneedsaoop\\EasyNeed.png");
//
//
//        ImageView imageView = new ImageView(image);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("EasyNeeds");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}