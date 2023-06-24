package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/main/views/main.fxml"));
            URL url = loader.getLocation();
//            System.out.println("FXMLLoader is looking at: " + url.getPath());
            Parent root = loader.load();
//            SceneController sceneController = loader.getController();
//            System.out.println("t");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("we fialed");
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}

