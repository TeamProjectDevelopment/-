package application;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ManaPageController implements Initializable{
	@FXML
	private Button deleteButton;
	@FXML
	private Button updateButton;
	@FXML
	private Button eSaveButton;
	@FXML
	private Button choiceHisButton;
	@FXML
	private Button choiceFileButton;
	@FXML
	private Button sureUpdateButton;
	@FXML
	private Button fileInfoDeleteButton;
	@FXML
	private Button updatingButton;
	@FXML
	private TextArea hisConfigurationTextArea;
	@FXML
	private TextArea configurationTextArea;
	@FXML
	private Label fileInfoShowLabel;

	  private Main main; 
	   
	   public void setApp(Main main) {
		   this.main=main;
	   }
	   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
public void deleteConfiguration(ActionEvent event) { 
		
	}
	public void updateConfiguration(ActionEvent event) { 
        Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {										
					Parent son = FXMLLoader.load(getClass().getResource("/application/配置修改界面.fxml"));
					Stage stage = new Stage();
					stage.setScene(new Scene(son));
					stage.setResizable(false);
					stage.setTitle("配置修改界面");
					stage.show();
//					new Main().start(new Stage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	public void eSaveConfiguration(ActionEvent event) { 
		 FileChooser fileChooser = new FileChooser();
         FileChooser.ExtensionFilter extFilter = 
        		 new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
         fileChooser.getExtensionFilters().add(extFilter);
         File file = fileChooser.showOpenDialog(null);
	}	
	public void chioceConfiguration(ActionEvent event) { 
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {					
					Parent son = FXMLLoader.load(getClass()
					        .getResource("/application/配置文件选择界面.fxml"));
					Stage stage = new Stage();
					stage.setScene(new Scene(son));	
					stage.setMaximized(false);
					stage.setResizable(false);
					stage.setTitle("配置文件选择界面");
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
			
	}
	public void choiceFile(ActionEvent event) { 
		 FileChooser fileChooser = new FileChooser();
         FileChooser.ExtensionFilter extFilter = 
        		 new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
         fileChooser.getExtensionFilters().add(extFilter);
         File file = fileChooser.showOpenDialog(null);
         String fileInfoString = file.getAbsolutePath().toString();
         if(fileInfoString != null){
        	 Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub					
//					fileInfoShowLabel.setText(fileInfoString);					
//		            fileInfoDeleteButton.setVisible(true);
				}
			});
             
         }
//         System.out.println(file);
	}
	public void sureUpdate(ActionEvent event) { 
		
	}
	public void fileInfoDelete(){
		fileInfoShowLabel.setText("");
		fileInfoDeleteButton.setVisible(false);
	}
	public void updating(ActionEvent event) {
        Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {					
					@SuppressWarnings("restriction")
					ObservableList<Stage> stage = FXRobotHelper.getStages(); 
					Scene scene = new Scene(FXMLLoader.load(getClass()
							.getResource("/application/更新界面.fxml")));
					stage.get(0).setScene(scene);
					new Main().start((Stage) stage);
					((Component) stage).show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
	 public void showMana(ActionEvent event) {
	       System.out.println("showMana");
	       main.gotoMana();
	   }
	   
	   public void showUpdate(ActionEvent event) {
		   System.out.println("showUpdate");
	       main.gotoUpdate();
	   }
}
