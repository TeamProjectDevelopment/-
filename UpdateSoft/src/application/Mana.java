package application;

import java.io.IOException;

import javax.xml.crypto.Data;

import com.sun.javafx.robot.impl.FXRobotHelper;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class Mana extends AnchorPane{
	private static Mana mana;
	
	private Data data ; //���⽻�����ʹ�õ��Զ������ݽṹ
	private Control controller;
	private Stage primaryStage;
	
	private Mana(){
		try{
			
			ObservableList<Stage> stage = FXRobotHelper.getStages();
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/application/ManaPage.fxml")));
			stage.get(0).setScene(scene);
			
			
//			Parent root = FXMLLoader.load(getClass()
//                    .getResource("/application/ManaPage.fxml"));
//			primaryStage = new Stage();
//			
//			primaryStage.setTitle("�������ù������");
//            primaryStage.setScene(new Scene(root));
			
//            primaryStage.show(); 	
//            Control controller = FXMLLoader.getController();
//            controller.start(mana); //����Popupʵ������ͬʱ����Controller�İ�ť������
            }catch(IOException ex){
//			ex.printStackTrace();
			}
		}
	
	public static Data showMana() {
		mana = new Mana();
//		mana.primaryStage.show(); 
		return mana.getData();
	}
	
	public void hide(){
		if(primaryStage != null)
		primaryStage.hide();
	}
		//ʵ����������ȡ��������
	public Data getData(){
	return data;
	}
		//���⽻�����ʹ�õ��Զ������ݽṹ
	public static class Data{
	private String id;
	private String name;
	private String city;
	public Data(){
	}
	public Data(String id,String name,String city){
	this.id = id;
	this.name = name;
	this.city = city;
	}
}
	}