package application;

import java.awt.CheckboxGroup;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdateUI extends GridPane{
	CheckBox confiCheckBox1;
	CheckBox confiCheckBox2;
	TextArea textArea1;
	TextArea textArea2;
	BorderPane borderPane2;
	final ChoiceBox<String> choiceUpdateBox;
	public UpdateUI(){
		super();
		this.setHeight(600);
		this.setWidth(1000);
		this.setLayoutX(1);
		this.setLayoutY(2);
		this.setHgap(30);
		this.setVgap(10);

		BorderPane borderPane1 = new BorderPane();
		HBox hBox1 = new HBox();
		hBox1.setPadding(new Insets(15, 15, 15, 15));
		hBox1.setSpacing(10);
		Label updatelabel = new Label("更新选择");
		updatelabel.setStyle("-fx-font: 18 arial;");
		Button getNewConfiButton = new Button("获取最新配置");
		getNewConfiButton.setPadding(new Insets(8));
		getNewConfiButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int configurationNum = 1;
				String filePathString = "D:/data/";
				File confiFile = new File(filePathString + "configuration" 
					    + configurationNum + ".properties");
				while(confiFile.exists()){
					configurationNum++;
					confiFile = new File(filePathString + "configuration" 
					    + configurationNum + ".properties");
				}
				--configurationNum;
				confiFile = new File(filePathString + "configuration" 
				    + configurationNum + ".properties");
				if(confiFile.exists()){
//		        	   textArea1.setText(confiFile.getName().toString());
		        	   textArea1.setText("文件名：" + confiFile.getName() 
		        	           + "\n文件路径：" + confiFile.getAbsolutePath() 
		        	           + "\n文件大小：" + confiFile.length() + "\n"+ "文件修改日期：" 
		        			   + new Date(confiFile.lastModified()));
		        	   //提示获取最新配置成功
		        	   JOptionPane.showMessageDialog(null,
								"获取最新配置文件成功！","",
								JOptionPane.INFORMATION_MESSAGE);
		           }
				else
					JOptionPane.showMessageDialog(null,
							"暂时无配置文件！","",
							JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
//		getNewConfiButton.set
		hBox1.getChildren().add(updatelabel);
		hBox1.getChildren().add(getNewConfiButton);
		borderPane1.setTop(hBox1);
		
		ScrollPane scrollPane1 = new ScrollPane();
		textArea1 = new TextArea();
		scrollPane1.setContent(textArea1);
		TitledPane titledPane1 = new TitledPane("最新配置",scrollPane1);
		titledPane1.setPadding(new Insets(10));
		borderPane1.setCenter(titledPane1);
		
		ScrollPane scrollPane2 = new ScrollPane();
		textArea2 = new TextArea();
		scrollPane2.setContent(textArea2);
		TitledPane titledPane2 = new TitledPane("本地配置",scrollPane2);
		titledPane2.setPadding(new Insets(10));
		borderPane1.setBottom(titledPane2);
		this.add(borderPane1, 0, 0);
		
		borderPane2 = new BorderPane();		
		
		HBox hBox2 = new HBox();
		hBox2.setPadding(new Insets(10));
		Label choiceWaylabel = new Label("选择更新方式：");
		choiceUpdateBox = new ChoiceBox<String>(
			        FXCollections.observableArrayList("整体更新", "部分更新"));

		choiceUpdateBox.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
			            if(new_value.intValue()==1){
			            	Label conLabel = new Label("对更新文件选择");
			            	confiCheckBox1 = new CheckBox("configuration1");
			            	VBox.setMargin(confiCheckBox1, new Insets(10, 0, 0, 0));
			        		confiCheckBox2 = new CheckBox("configuration2");
			        	    VBox.setMargin(confiCheckBox2, new Insets(10, 0, 0, 0));
			        		VBox checkVBox = new VBox();
			        		checkVBox.setPadding(new Insets(250, 10, 10, 50));
			        		checkVBox.getChildren().addAll(conLabel,confiCheckBox1,confiCheckBox2);
			        		borderPane2.setCenter(checkVBox);
			            }else if(new_value.intValue() == 0){
			            	borderPane2.setCenter(null);
			            }
			          }
			        });

		choiceUpdateBox.setTooltip(new Tooltip("选择更新方式"));
		choiceUpdateBox.setValue("中文");
		Button updateButton = new Button("确定更新");
		HBox.setMargin(updateButton, new Insets(0, 0, 0, 20));
		updateButton.setPadding(new javafx.geometry.Insets(8));
		updateButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(choiceUpdateBox.getSelectionModel().getSelectedIndex()==0){
					//进行整体更新
					String fileNameString = "D:\\自动更新软件.exe";
					URL fileUrl = null;
					try {
						fileUrl = new URL("网址" + "/文件名");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					downloadFile(fileUrl, fileNameString);
				}else{
					//进行部分更新
					//更新配置文件1
					if(confiCheckBox1.isSelected()){
						try {
							String fileNameString = "configuration1.properties";
							int configurationNum = 1;
							String filePathString = "D:/data/";
							File confiFile = new File(filePathString + "configuration" 
								    + configurationNum + ".properties");
							while(confiFile.exists()){
								configurationNum++;
								confiFile = new File(filePathString + "configuration" 
								    + configurationNum + ".properties");
							}
							--configurationNum;
							String fileDiString = "configuration" 
							    + configurationNum + ".properties";
							URL fileUrl1 = new URL("file:///D:/data/" + fileDiString );
							downloadFile(fileUrl1,fileNameString);
							
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}
					//更新配置文件2
					if(confiCheckBox2.isSelected()){
						try {
							String fileNameString = "configuration2.properties";
							int configurationNum = 1;
							String filePathString = "D:/data/";
							File confiFile = new File(filePathString + "configuration" 
								    + configurationNum + ".properties");
							while(confiFile.exists()){
								configurationNum++;
								confiFile = new File(filePathString + "configuration" 
								    + configurationNum + ".properties");
							}
							--configurationNum;
							String fileDiString = "configuration" 
							    + configurationNum + ".properties";
							URL fileUrl2 = new URL("file:///D:/data/" + fileDiString );
							downloadFile(fileUrl2,fileNameString);
							
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}
				}
			}
		});
		hBox2.getChildren().addAll(choiceWaylabel,choiceUpdateBox,updateButton);
		borderPane2.setBottom(hBox2);
		this.add(borderPane2, 1, 0);
		
		//执行查找本地配置文件并显示
		queryLocalConfi();
	}
	public void queryLocalConfi(){
		int configurationNum = 1;
		File confiFile = new File("configuration" 
			    + configurationNum + ".properties");
		
		while(configurationNum <= 20){
			if(confiFile.exists()){
				textArea2.appendText(confiFile.getName()+"\n");								
			}
			confiFile = new File("configuration" 
				    + ++configurationNum + ".properties");
		}
	}
	//第一个参数为下载路径，第二个参数为文件名
	public void downloadFile(URL fileUrl,String fileName){
		InputStream is = null;
		OutputStream os = null;
		try {						
			URLConnection uric = fileUrl.openConnection();
			int fileLength = uric.getContentLength();
			if(fileLength >= 1){
				byte[] buffer = new byte[fileLength];
				is = uric.getInputStream();
				
//				//设置进度条显示下载更新进度
//				Stage progresStage = new Stage();
//				Group root = new Group();				
//				ProgressBar fileDownloadProgressBar = new ProgressBar(0);
//				fileDownloadProgressBar.setVisible(true);
//				fileDownloadProgressBar.setPadding(new Insets(10));
//				fileDownloadProgressBar.setLayoutX(100);
//				fileDownloadProgressBar.setLayoutY(20);
//				root.getChildren().add(fileDownloadProgressBar);
//				progresStage.setScene(new Scene(root));
//				progresStage.show();
				
				is.read(buffer, 0, fileLength);
				//下载并更新
				File file = new File(fileName);				
				if(file.exists()){
					file.delete();					
				}
				file.createNewFile();
				os = new FileOutputStream(file);
//				int ch;
//				int progressCount = 0;
//				while((ch = is.read()) != -1){
//					os.write(ch);
//					progressCount++;
//					fileDownloadProgressBar.setProgress(progressCount/fileLength);					
//				}
//				progresStage.close();
				os.write(buffer);
				os.close();
				//另保存下载的文件作以作版本比较
				String fileNameString = fileUrl.getFile();	
				fileNameString = fileNameString.substring(fileNameString.lastIndexOf('/') + 1);
				File file2 = new File(fileNameString);
				if(file2.exists()){
					file2.delete();
				}				
				file2.createNewFile();
				FileOutputStream fileOut = new FileOutputStream(file2);
				fileOut.write(buffer);
				fileOut.close();
				//提示更新成功并提示可重启使用系统
				int num = JOptionPane.showOptionDialog(null, "更新成功，是否重启？",
						"更新提示", JOptionPane.INFORMATION_MESSAGE, 
						JOptionPane.OK_CANCEL_OPTION, null, null,"OK" );
				if(num == 0){
					Runtime.getRuntime().addShutdownHook(new Thread() {
						public void run() {
		                try {
							Runtime.getRuntime().exec("java Main");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }    
		        });
		        System.exit(0);
				}
				
			}else{
				JOptionPane.showMessageDialog(null,"要下载的文件为空文件！","",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			System.out.println(e);
		}
		finally{
			try {
				if(is != null)
				    is.close();
				if(os != null)
					os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}	

}
