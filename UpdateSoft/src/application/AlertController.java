package application;

import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.sun.javafx.robot.impl.FXRobotHelper;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertController implements Initializable {


   private Alert alert; 
   private Stage stage;
   
   @FXML
   private Text text_id;
   
   public void setAlert(Alert alert) {
	   this.alert=alert;
   }
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {

       // TODO (don't really need to do anything here).

   }
   public void showAlert(ActionEvent event) { 
	   
       Platform.runLater(new Runnable() {
    	   
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {										
					Parent son = FXMLLoader.load(getClass()
					        .getResource("Alert.fxml"));
					stage = new Stage();
					stage.setScene(new Scene(son));
					stage.setResizable(false);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("提示");	
					stage.show();
					
//					new Main().start(new Stage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
   
 public void showAlertgengxin(ActionEvent event) { 
	   
       Platform.runLater(new Runnable() {
    	   
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {										
					Parent son = FXMLLoader.load(getClass()
					        .getResource("Alertgengxin.fxml"));
					stage = new Stage();
					stage.setScene(new Scene(son));
					stage.setResizable(false);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("提示");	
					stage.show();
					
//					new Main().start(new Stage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
 
   public void settext(ActionEvent event) {
		text_id.setText("huoqu");
   }
   
   public void hideAlert(ActionEvent event) { 
	   Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {				
					@SuppressWarnings("restriction")
					ObservableList<Stage> stage = FXRobotHelper.getStages();
					((Stage) stage).close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
   // When user click on myButton
   // this method will be called.
   
}