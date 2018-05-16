package application;

import  java.io.InputStream;

import com.sun.glass.ui.Window.Level;
import com.sun.javafx.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 1000.0;  
    private final double MINIMUM_WINDOW_HEIGHT = 600.0;
    @Override
    public void start(Stage primaryStage) {
        try {
            // Read file fxml and draw interface.
//            Parent root = FXMLLoader.load(getClass().getResource("UpdatePage.fxml"));
//            System.out.println(root);
//            primaryStage.setTitle("更新配置管理软件");
//            primaryStage.setScene(new Scene(root));
//            primaryStage.show();
        	 stage = primaryStage;  
             stage.setTitle("更新配置软件");  
             stage.setMinWidth(MINIMUM_WINDOW_WIDTH);  
             stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);  
             gotoUpdate();  
             stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void gotoUpdate() {
    	try {
    		UpdatePageController update = (UpdatePageController)replaceSceneContent("UpdatePage.fxml");
    		update.setApp(this);
    	} catch (Exception ex) {  
    		ex.printStackTrace();
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);  
        } 
    }
    
    public void gotoMana() {
    	try {
    		ManaPageController mana = (ManaPageController)replaceSceneContent("ManaPage.fxml");
    		mana.setApp(this);
    	} catch (Exception ex) {  
    		ex.printStackTrace();
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);  
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
        Scene scene = new Scene(page, 1000, 600);  
        stage.setScene(scene);  
        stage.sizeToScene();  
    	
        return (Initializable) loader.getController();  
    }  
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void hideMain() {
//    	Main main =new Main();
//    	main.primaryStage.hide();
    }
}