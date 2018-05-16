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
	
	private Data data ; //对外交换输出使用的自定义数据结构
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
//			primaryStage.setTitle("更新配置管理软件");
//            primaryStage.setScene(new Scene(root));
			
//            primaryStage.show(); 	
//            Control controller = FXMLLoader.getController();
//            controller.start(mana); //传递Popup实例对象，同时启动Controller的按钮动作绑定
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
		//实例方法：获取交换数据
	public Data getData(){
	return data;
	}
		//对外交换输出使用的自定义数据结构
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