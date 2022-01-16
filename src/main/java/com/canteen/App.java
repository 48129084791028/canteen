package com.canteen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;

public class App extends Application {

    public static String dbHost = "localhost",
            dbName = "canteen",
            dbLogin = "root",
            dbPassword = "1234";
    public static Scene scene;
    static Stage prevStage;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("signin"), 700, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        App.prevStage = stage;
        stage.getIcons().add(new Image("com/canteen/icon.png"));
        
    }

    public static void switchToAdmin(Stage stage) throws IOException {
        scene = new Scene(loadFXML("admin"), 915, 575);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        prevStage.close();
        App.prevStage = stage;
        stage.getIcons().add(new Image("com/canteen/icon.png"));
    }

    public static void switchToMain(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"), 700, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        prevStage.close();
        App.prevStage = stage;
        stage.getIcons().add(new Image("com/canteen/icon.png"));
    }

    public static void backToLogin(Stage stage) throws IOException {
        scene = new Scene(loadFXML("signin"), 700, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        prevStage.close();
        App.prevStage = stage;
        stage.getIcons().add(new Image("com/canteen/icon.png"));
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
