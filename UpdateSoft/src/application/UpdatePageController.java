package application;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.controlsfx.dialog.Dialogs;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UpdatePageController implements Initializable {

   @FXML
   private Button myButton;

   @FXML
   private TextField myTextField;

   private Main main; 
   private Alert alert; 
   
   public void setAlert(Alert alert) {
	   this.alert=alert;
   }
   public void setApp(Main main) {
	   this.main=main;
   }
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {

       // TODO (don't really need to do anything here).

   }

   // When user click on myButton
   // this method will be called.
   
   public void showDateTime(ActionEvent event) {
       System.out.println("Button Clicked!");

       Date now= new Date();

       DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
       String dateTimeString = df.format(now);
        // Show in VIEW
        myTextField.setText(dateTimeString);

   }
   
   public void showMana(ActionEvent event) {
       System.out.println("showMana");
       main.gotoMana();
   }
   
   public void showUpdate(ActionEvent event) {
	   System.out.println("showUpdate");
       main.gotoUpdate();
   }
   
   public void showAlert(ActionEvent event) { 
       Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {										
					AlertController alert= new AlertController();
					alert.showAlert(event);
//					alert.settext(event);

				} catch (Exception e) {
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
					AlertController alert= new AlertController();
					alert.showAlertgengxin(event);
//					alert.settext(event);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}