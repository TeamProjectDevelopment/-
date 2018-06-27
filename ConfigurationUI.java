package application;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ConfigurationUI extends GridPane{
	
	ChoiceBox<String> hisChoiceBox;
	TextArea textArea1;
	TextArea textArea2;
	TextArea textArea3;
	Label fileInfoLabel;
	Button clearFileInfoButton;
	
	Stage updateConfiStage;
	public ConfigurationUI(){
		super();
		this.setHeight(600);
		this.setWidth(1000);
		this.setLayoutX(1);
		this.setLayoutY(2);
		this.setHgap(15);
		this.setVgap(10);
		
		BorderPane borderPane1 = new BorderPane();
		VBox vBox1 = new VBox();
		Label hisLabel = new Label("历史配置");
		hisLabel.setStyle("-fx-font: 25 arial;");
		hisLabel.setPadding(new javafx.geometry.Insets(10));
//		hisLabel.setFont(null);
		vBox1.getChildren().add(hisLabel);
		vBox1.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
		HBox hisChoiceHBox = new HBox();
		hisChoiceHBox.setPadding(new javafx.geometry.Insets(10));
		Label hisChoiceLabel = new Label("历史配置选择：");
		hisChoiceBox = new ChoiceBox<String>(
		        FXCollections.observableArrayList("configuration1", "configuration2"));

		hisChoiceBox.getSelectionModel().selectedIndexProperty()
	    .addListener(new ChangeListener<Number>() {
		    public void changed(ObservableValue ov, Number value, Number new_value) {
		         int newValue = new_value.intValue()+1;
		    	 String confiFileString = "configuration" 
			               + newValue + ".properties";
			           File confiFile = new File(confiFileString);
			           if(confiFile.exists()){
			        	   textArea1.setText(confiFile.getName().toString());
			        	   textArea2.setText("文件名：" + confiFile.getName() 
			        	           + "\n文件路径：" + confiFile.getAbsolutePath() 
			        	           + "\n文件大小：" + confiFile.length() + "\n"+ "文件修改日期：" 
			        			   + new Date(confiFile.lastModified()));
			           }
		           }
	    });
		hisChoiceBox.setTooltip(new Tooltip("选择历史配置文件"));
		hisChoiceBox.setValue("English");
		hisChoiceHBox.getChildren().addAll(hisChoiceLabel,hisChoiceBox);
		vBox1.getChildren().add(hisChoiceHBox);
		ScrollPane scrollPane1 = new ScrollPane();
		textArea1 = new TextArea();
		scrollPane1.setContent(textArea1);
		TitledPane titledPane1 = new TitledPane("历史配置",scrollPane1);
		vBox1.getChildren().add(titledPane1);
		borderPane1.setTop(vBox1);
		
		HBox hBox1 = new HBox();
		hBox1.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
		Button deleteButton = new Button("删除");
		deleteButton.setPadding(new javafx.geometry.Insets(8));
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int num = JOptionPane.showOptionDialog(null, "该操作可能影响系统的正常运行，确定删除吗？",
						"删除警告", JOptionPane.WARNING_MESSAGE, 
						JOptionPane.OK_CANCEL_OPTION, null, null,"OK" );
				if(num != 0){
					return;
				}
				int curValue = hisChoiceBox.getSelectionModel().getSelectedIndex()+1;
				String selectedFileString = "configuration" + curValue + ".properties";
				File selectedFile = new File(selectedFileString);
				if(selectedFile.exists()){
					if(selectedFile.delete()){
						textArea1.setText("");
						textArea2.setText("");
					}else{
						JOptionPane.showMessageDialog(null,
								"删除配置文件失败","",
								JOptionPane.INFORMATION_MESSAGE);
					}
					
				}else{
					JOptionPane.showMessageDialog(null,
							"文件不存在！","",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		Button updateButton = new Button("修改");
		HBox.setMargin(updateButton, new javafx.geometry.Insets(0, 10, 0, 10));
		updateButton.setPadding(new javafx.geometry.Insets(8));
		updateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				updateConfiStage = new Stage();
				Scene scene = new Scene(new ConfigurationUpdateUI(),500,400);
				updateConfiStage.setScene(scene);
				updateConfiStage.setTitle("配置修改界面");
				updateConfiStage.show();
			}
		});
		Button eSaveButton = new Button("另存为");
		eSaveButton.setPadding(new javafx.geometry.Insets(8));
		eSaveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				FileChooser fileChooser = new FileChooser();
		        FileChooser.ExtensionFilter extFilter = 
		        		 new FileChooser.ExtensionFilter("PROPERTIES "
		        		 		+ "files (*.properties)", "*.properties");
		        fileChooser.getExtensionFilters().add(extFilter);
		        File efile = fileChooser.showSaveDialog(null);
		        int curValue = hisChoiceBox.getSelectionModel().getSelectedIndex()+1;
				String selectedFileString = "configuration" + curValue + ".properties";
				File selectedFile = new File(selectedFileString);
		        try {
					FileWriter fileWriter = new FileWriter(efile);
					FileReader selectedFileReader = new FileReader(selectedFile);
					int c;
					while((c = selectedFileReader.read())!= -1){
						fileWriter.write(c);
					}
					selectedFileReader.close();
					fileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
			}
		});
		hBox1.getChildren().add(deleteButton);
		hBox1.getChildren().add(updateButton);
		hBox1.getChildren().add(eSaveButton);
		borderPane1.setCenter(hBox1);
		
		VBox vBox2 = new VBox();
		vBox2.setPadding(new javafx.geometry.Insets(10));
		ScrollPane scrollPane2 = new ScrollPane();
		textArea2 = new TextArea();
		scrollPane2.setContent(textArea2);
		TitledPane titledPane2 = new TitledPane("配置详情",scrollPane2);
		vBox2.getChildren().addAll(titledPane2);
		borderPane1.setBottom(vBox2);
		this.add(borderPane1,0,0);
		
		BorderPane borderPane2 = new BorderPane();
		VBox vBox3 = new VBox();
		vBox3.setPadding(new javafx.geometry.Insets(10));
		Label label2 = new Label("生成新配置");
		label2.setStyle("-fx-font: 25 arial;");
		label2.setPadding(new javafx.geometry.Insets(10));
		
		ScrollPane scrollPane3 = new ScrollPane();
		textArea3 = new TextArea();
		scrollPane3.setContent(textArea3);
		TitledPane titledPane3 = new TitledPane("编写配置",scrollPane3);
		vBox3.getChildren().add(label2);
		vBox3.getChildren().add(titledPane3);
		borderPane2.setTop(vBox3);
		
		HBox hBox2 = new HBox();
		hBox2.setPadding(new javafx.geometry.Insets(10));
		Button choiceFileButton = new Button("选择文件");
		choiceFileButton.setPadding(new javafx.geometry.Insets(8));
//		choiceFileButton.addEventHandler(MouseEvent.MOUSE_ENTERED, 
//			    new EventHandler<MouseEvent>() {
//	        @Override public void handle(MouseEvent e) {
//	            
//	        }
//	    });
		choiceFileButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				choiceFile();
				// TODO Auto-generated method stub
//				FileChooser fileChooser = new FileChooser();
//		        FileChooser.ExtensionFilter extFilter = 
//		        		 new FileChooser.ExtensionFilter("PROPERTIES "
//		        		 		+ "files (*.properties)", "*.properties");
//		        fileChooser.getExtensionFilters().add(extFilter);
//		        File file = fileChooser.showOpenDialog(null);
//		        if(file.exists()){
//		        	String filePath = file.getName().toString();
//		        	fileInfoLabel.setText(filePath);
//		        	clearFileInfoButton.setVisible(true);
//		        	clearFileInfoButton.setDisable(false);
//		        	try {
//						FileReader fileReader = new FileReader(file);
//						char byt[] = new char[2048];						
//						int len = fileReader.read(byt);	
//						textArea3.setText(new String(byt, 0, len));
//						fileReader.close();
//						
//					} catch (FileNotFoundException e) {
//						// TODO Auto-generated catch block
//						System.out.println(e);
//					}catch (IOException e) {
//						// TODO Auto-generated catch block
//						System.out.println(e);
//					}
//		        	
//		        }
			}
		});
		Button newConfiButton = new Button("确定生成");
		HBox.setMargin(newConfiButton, new javafx.geometry.Insets(0, 10, 0, 10));
		newConfiButton.setPadding(new javafx.geometry.Insets(8));
		newConfiButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				generateConfi();
			}
		});
		hBox2.getChildren().add(choiceFileButton);
		hBox2.getChildren().add(newConfiButton);
		borderPane2.setCenter(hBox2);
		
		HBox hBox3 = new HBox();
		hBox3.setPadding(new javafx.geometry.Insets(10, 10, 200, 10));
		Label label3 = new Label("已选择的文件：");
		fileInfoLabel = new Label("");
		clearFileInfoButton = new Button("清除");
		clearFileInfoButton.setStyle("-fx-background-color: red;");
		HBox.setMargin(clearFileInfoButton, new javafx.geometry.Insets(0, 0, 0, 10));
		clearFileInfoButton.setPadding(new javafx.geometry.Insets(5));
		clearFileInfoButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				fileInfoLabel.setText("");
				textArea3.setText("");
				clearFileInfoButton.setVisible(false);
	            clearFileInfoButton.setDisable(true);
			}
		});
		clearFileInfoButton.setVisible(false);
		hBox3.getChildren().addAll(label3,fileInfoLabel,clearFileInfoButton);
		borderPane2.setBottom(hBox3);
		this.add(borderPane2, 1, 0);
	}
	
	public void choiceFile() {
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = 
        		 new FileChooser.ExtensionFilter("PROPERTIES "
        		 		+ "files (*.properties)", "*.properties");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        if(file.exists()){
        	String filePath = file.getName().toString();
        	fileInfoLabel.setText(filePath);
        	clearFileInfoButton.setVisible(true);
        	clearFileInfoButton.setDisable(false);
        	try {
				FileReader fileReader = new FileReader(file);
				char byt[] = new char[2048];						
				int len = fileReader.read(byt);	
				textArea3.setText(new String(byt, 0, len));
				fileReader.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
        	
        }
	}
	
	public boolean generateConfi() {
		if(textArea3.getText() == null){
			JOptionPane.showMessageDialog(null,
					"文本域值为空，不能生成新配置","",
					JOptionPane.INFORMATION_MESSAGE);
			return false ;
		}
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = 
        		 new FileChooser.ExtensionFilter("PROPERTIES "
        		 		+ "files (*.properties)", "*.properties");
        fileChooser.getExtensionFilters().add(extFilter);
        File efile = fileChooser.showSaveDialog(null);		       			
        try {
			FileWriter fileWriter = new FileWriter(efile);					
			fileWriter.write(textArea3.getText());				
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		JOptionPane.showMessageDialog(null,
				"生成新配置成功！","",
				JOptionPane.INFORMATION_MESSAGE);
		return true;
	}
	

}

