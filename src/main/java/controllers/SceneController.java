package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public SceneController(Stage stage) {
        this.stage = stage;
    }
 
    
    public SceneController() {
		// TODO Auto-generated constructor stub
	}


	public void switchToScene1() throws IOException {
        // 
//   	     URL url = getClass().
		
		
   	    System.out.println("   =../"+Constants.LOGIN_FXML);
   	     // get
   	    Class<?> cls = SceneController.class;
   	    String path = cls.getResource(cls.getSimpleName() + ".class").toString();
        System.out.println("Path: " + path);
        
        
        // class path 
        // get resource path 
        // constant path 
        // fxml loader checks 
   	    root = FXMLLoader.load(getClass().getResource(Constants.LOGIN_FXML));
//   	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   	    
   	    
   	    System.out.println("we got here");
   	    
   	    // scene = fxml file 
   	    
   	    scene = new Scene(root);
   	    
   	    // stage has scenes / fxml files 
   	    stage.setScene(scene);
   	    stage.show();
   }
    public void switchToScene1(ActionEvent event) throws IOException {
     // 
//	     URL url = getClass().getResource("../view/Scene2.fxml");
//	     System.out.println(url.getPath());
	    root = FXMLLoader.load(getClass().getResource(Constants.LOGIN_FXML));
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
    }
    
    public void switchToScene2(ActionEvent event) throws IOException {
        System.out.println("we are here");
//     URL url = getClass().getResource("../view/Scene2.fxml");
//     System.out.println(url.getPath());
     root = FXMLLoader.load(getClass().getResource("/main/views/loginclient.fxml"));
     stage = (Stage)((Node)event.getSource()).getScene().getWindow();
     scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }

    public void setStage(Stage primaryStage) {


    }
}
	

