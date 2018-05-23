package application;
	
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	Stage stage;
	BorderPane rootPane; 
	UpdateUI updateUI;
	ConfigurationUI configurationUI;
	@Override
	public void start(Stage primaryStage) {
		try {
//			 BorderPane root = new UpdateUI();
			rootPane = new BorderPane();
			MenuBar menuBar = new MenuBar();
		    menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		    rootPane.setTop(menuBar);
		    Menu updateMenu = new Menu("����");
		    MenuItem updateMenuItem = new MenuItem("������½���");
		    updateMenuItem.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					updateUI = new UpdateUI();
					rootPane.setCenter(updateUI);
					stage.setTitle("���½���");
				}
			});
		    updateMenu.getItems().add(updateMenuItem);
		    Menu confiMenu = new Menu("����");
		    MenuItem confiMenuItem = new MenuItem("����������");
		    confiMenuItem.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					configurationUI = new ConfigurationUI();
					rootPane.setCenter(configurationUI);
					stage.setTitle("���ù������");
				}
			});
		    confiMenu.getItems().add(confiMenuItem);
		    menuBar.getMenus().addAll(updateMenu,confiMenu);
		    rootPane.setTop(menuBar);
		    
			stage = primaryStage;
			GridPane rootGridPane = new UpdateUI();
			rootPane.setCenter(rootGridPane);
			stage.setScene(new Scene(rootPane,1000,600));
			stage.setTitle("���½���");
			stage.show();
			
			Properties properties = new Properties();
			properties.load(new FileInputStream("configuration1.properties"));
			String keyString1 = properties.getProperty("softname");
			String keyString2 = properties.getProperty("version");
         
			//��ʾ����ʾ���������ļ��ɸ���
			JOptionPane.showMessageDialog(null,"������ƣ�" + keyString1 + "\n�汾��" + keyString2
					+ "\n" + "�������ļ��ɸ��£�","",
					JOptionPane.INFORMATION_MESSAGE);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}	
	
}
