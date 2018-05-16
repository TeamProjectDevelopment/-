package application;

import java.io.IOException;
import  java.io.InputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Alert extends Application {
	
	private Stage stage;

	@Override
	public void start(Stage primaryStage) {
        try {
            // Read file fxml and draw interface.
       	 	stage = primaryStage;  
         	stage.setTitle("ÌבÊ¾");  
        	AlertController alert = (AlertController)replaceSceneContent("AlertPage.fxml");
    		alert.setAlert(this);
//          Parent root = FXMLLoader.load(getClass().getResource("Alert.fxml"));
//             stage.setScene(new Scene(root));
             stage.showAndWait();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
 

	    
    private Initializable replaceSceneContent(String fxml) throws Exception {  
        FXMLLoader loader = new FXMLLoader();  
        InputStream in = (InputStream) Main.class.getResourceAsStream(fxml);  
        loader.setBuilderFactory(new JavaFXBuilderFactory());  
        loader.setLocation(Main.class.getResource(fxml));  
        AnchorPane page;  
        try {  
            page = (AnchorPane) loader.load(in);  
        } finally {  
            in.close();  
        }   
        Scene scene = new Scene(page);  
        stage.setScene(scene);  
        stage.sizeToScene();  
    	
        return (Initializable) loader.getController();  
    } 
}