package com.example.easyneedsaoop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginsignup.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Image image = new Image("C:\\Users\\Administrator\\Desktop\\EasyNeedsAOOP\\EasyNeedsAOOP\\src\\main\\resources\\com\\example\\easyneedsaoop\\EasyNeed.png");
        ImageView imageView = new ImageView(image);
        Scene scene = new Scene(fxmlLoader.load(),600,470);
        stage.setTitle("EasyNeeds");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public void hello(){
        System.out.println("Hello");
    }

}