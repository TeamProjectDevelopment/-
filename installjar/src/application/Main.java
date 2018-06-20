package application;
	
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class Main extends Application {
	static String oldJar = null; 
	static String newJar = null; 
	@Override
	public void start(Stage primaryStage) {
		try {
				primaryStage.setTitle("完整更新");
				Text text =new Text(1,1,"更新中...");
           	
				String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
				System.out.println(path);
				path=path.substring(1, path.lastIndexOf("/"));
				

				//example:"src/test.txt"
            	 readFileByLines(path+"/conf.txt");
            	 System.out.println(oldJar);
            	 System.out.println(newJar);
		            				      	
            	//开始备份,先输入需要复制的目标文件或目标文件夹  
    	 		//example:"src/test.txt" C:/Users/likun/Desktop/git_project
                String pathname = oldJar;  
                File file = new File(pathname);  
                //复制到的位置  "src/data"
                String topathname =path+"/old" ;  
                File toFile = new File(topathname);  
                try {  
                    copy(file, toFile);  
                } catch (Exception e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
		                
                //备份之后删除
                deleteFile(file);
		        
                //开始更新
				String newpathname = newJar;  
				File newfile = new File(newpathname);  
				String newtopathname =path;  
				File newtoFile = new File(newtopathname);  
				try {  
				    copy(newfile, newtoFile);  
				} catch (Exception e) {  
				    // TODO Auto-generated catch block  
				    e.printStackTrace();  
				}  
		        
				
                String jar_name= newJar.substring(newJar.lastIndexOf("/")+1);
                System.out.println(jar_name);//获取jar包名字
                try {
                     Runtime.getRuntime().exec("java -jar " + jar_name);
                     System.exit(0);
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
		        
		        StackPane root = new StackPane();
		        root.getChildren().add(text);
		        primaryStage.setScene(new Scene(root, 300, 250));
		        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public String getPath(String path) {
		String newpath=path.substring(1, path.lastIndexOf("/"));
		return newpath;
		
	}
    public static void copy(File file, File toFile) throws Exception {  
        byte[] b = new byte[1024];  
        int a;  
        FileInputStream fis;  
        FileOutputStream fos;  
        if (file.isDirectory()) {  
            String filepath = file.getAbsolutePath();  
            filepath=filepath.replaceAll("\\\\", "/");  
            String toFilepath = toFile.getAbsolutePath();  
            toFilepath=toFilepath.replaceAll("\\\\", "/");  
            int lastIndexOf = filepath.lastIndexOf("/");  
            toFilepath = toFilepath + filepath.substring(lastIndexOf ,filepath.length());  
            File copy=new File(toFilepath);  
            //复制文件夹  
            if (!copy.exists()) {  
                copy.mkdir();  
            }  
            //遍历文件夹  
            for (File f : file.listFiles()) {  
                copy(f, copy);  
            }  
        } else {  
            if (toFile.isDirectory()) {  
                String filepath = file.getAbsolutePath();  
                filepath=filepath.replaceAll("\\\\", "/");  
                String toFilepath = toFile.getAbsolutePath();  
                toFilepath=toFilepath.replaceAll("\\\\", "/");  
                int lastIndexOf = filepath.lastIndexOf("/");  
                toFilepath = toFilepath + filepath.substring(lastIndexOf ,filepath.length());  
                  
                //写文件  
                File newFile = new File(toFilepath);  
                fis = new FileInputStream(file);  
                fos = new FileOutputStream(newFile);  
                while ((a = fis.read(b)) != -1) {  
                    fos.write(b, 0, a);  
                } 
                fis.close();
                fos.close();
            } else {  
                //写文件  
                fis = new FileInputStream(file);  
                fos = new FileOutputStream(toFile);  
                while ((a = fis.read(b)) != -1) {  
                    fos.write(b, 0, a);  
                }  
                fis.close();
                fos.close();
            }  
  
        }  
     
    }  
    
    public static boolean deleteFile(File file) {
        
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
//                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
//                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
//            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
    
	 public static void readFileByLines(String fileName) {  
	        File file = new File(fileName);  
	        BufferedReader reader = null;  
	        try {  
	            System.out.println("以行为单位读取文件内容，一次读一整行：");  
	            reader = new BufferedReader(new FileReader(file));  
	            oldJar = reader.readLine();  
	            newJar = reader.readLine(); 
//	            int line = 1;  
//	            // 一次读入一行，直到读入null为文件结束  
//	            while ((tempString = reader.readLine()) != null) {  
//	                // 显示行号  
//	                System.out.println("line " + line + ": " + tempString);  
//	                line++;  
//	            }  
	            reader.close(); 
	          
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (reader != null) {  
	                try {  
	                    reader.close();  
	                } catch (IOException e1) {  
	                }  
	            }  
	        }

	    }  
}
