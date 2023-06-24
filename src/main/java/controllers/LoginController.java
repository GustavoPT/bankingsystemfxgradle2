package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToScene1(ActionEvent event) throws IOException {
        System.out.println("we are here");
//     URL url = getClass().getResource("../view/Scene2.fxml");
//     System.out.println(url.getPath());
        root = FXMLLoader.load(getClass().getResource("/main/views/main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
