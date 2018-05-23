package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ConfigurationUpdateUI extends BorderPane{
	
	String filePath;
	
	Label fileInfoShowLabel;
	Button delFileButton;
	TextArea confiUpdateTextArea;
	public ConfigurationUpdateUI(){
		super();
		ScrollPane confiUpdateScrollPane = new ScrollPane();
		confiUpdateTextArea = new TextArea();
		confiUpdateScrollPane.setContent(confiUpdateTextArea);
		TitledPane confiUpdateTitledPane = new TitledPane("配置修改",
				confiUpdateScrollPane);
		confiUpdateTitledPane.setPadding(new Insets(5));
		this.setTop(confiUpdateTitledPane);
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10));
		Button choiceFileButton = new Button("选择文件");
		HBox.setMargin(choiceFileButton, new Insets(0, 10, 0, 0));
		choiceFileButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FileChooser fileChooser = new FileChooser();
		        FileChooser.ExtensionFilter extFilter = 
		        		 new FileChooser.ExtensionFilter("PROPERTIES "
		        		 		+ "files (*.properties)", "*.properties");
		        fileChooser.getExtensionFilters().add(extFilter);
		        File file = fileChooser.showOpenDialog(null);
		        if(file.exists()){
		        	String fileName = file.getName().toString();
		        	filePath = file.getAbsolutePath().toString();
		        	
		        	fileInfoShowLabel.setText(fileName);
		        	delFileButton.setVisible(true);
		        	delFileButton.setDisable(false);
		        	try {
						FileReader fileReader = new FileReader(file);
						char byt[] = new char[2048];						
						int len = fileReader.read(byt);	
						confiUpdateTextArea.setText(new String(byt, 0, len));
						fileReader.close();
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	
		        }
			}
		});
		Button updateFileButton = new Button("确定更新");
		updateFileButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
		        if(filePath != null){
		        	File file = new File(filePath);
		        	try {
						FileWriter fileWriter = new FileWriter(file);
					    fileWriter.write(confiUpdateTextArea.getText());
					    fileWriter.close();						 
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		        	
		        	
		        }else{
		        	f_alert_informationDialog("", "文件路径为空");
		        }
			}
		});
		hBox.getChildren().add(choiceFileButton);
		hBox.getChildren().add(updateFileButton);
		this.setCenter(hBox);
		
		HBox hBox2 = new HBox();
		hBox2.setPadding(new Insets(10, 10, 100, 10));
		Label fileLabel = new Label("已选择文件：");
		fileInfoShowLabel = new Label("");
		delFileButton = new Button("清除");
		delFileButton.setStyle("-fx-background-color: red");
		HBox.setMargin(delFileButton, new Insets(0, 0, 0, 10));
		delFileButton.setVisible(false);
		delFileButton.setDisable(true);
		delFileButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				fileInfoShowLabel.setText("");
				confiUpdateTextArea.setText("");
				delFileButton.setVisible(false);
				delFileButton.setDisable(true);
			}
		});
		hBox2.getChildren().add(fileLabel);
		hBox2.getChildren().add(fileInfoShowLabel);
		hBox2.getChildren().add(delFileButton);
		this.setBottom(hBox2);
	}
	public void f_alert_informationDialog(String p_header, String p_message){
        Alert _alert = new Alert(Alert.AlertType.INFORMATION);
        _alert.setTitle("信息");
        _alert.setHeaderText(p_header);
        _alert.setContentText(p_message);
        _alert.initOwner(null);
        _alert.show();
    }

}
