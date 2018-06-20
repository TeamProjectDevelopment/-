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
				primaryStage.setTitle("��������");
				Text text =new Text(1,1,"������...");
           	
				String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
				System.out.println(path);
				path=path.substring(1, path.lastIndexOf("/"));
				

				//example:"src/test.txt"
            	 readFileByLines(path+"/conf.txt");
            	 System.out.println(oldJar);
            	 System.out.println(newJar);
		            				      	
            	//��ʼ����,��������Ҫ���Ƶ�Ŀ���ļ���Ŀ���ļ���  
    	 		//example:"src/test.txt" C:/Users/likun/Desktop/git_project
                String pathname = oldJar;  
                File file = new File(pathname);  
                //���Ƶ���λ��  "src/data"
                String topathname =path+"/old" ;  
                File toFile = new File(topathname);  
                try {  
                    copy(file, toFile);  
                } catch (Exception e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
		                
                //����֮��ɾ��
                deleteFile(file);
		        
                //��ʼ����
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
                System.out.println(jar_name);//��ȡjar������
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
            //�����ļ���  
            if (!copy.exists()) {  
                copy.mkdir();  
            }  
            //�����ļ���  
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
                  
                //д�ļ�  
                File newFile = new File(toFilepath);  
                fis = new FileInputStream(file);  
                fos = new FileOutputStream(newFile);  
                while ((a = fis.read(b)) != -1) {  
                    fos.write(b, 0, a);  
                } 
                fis.close();
                fos.close();
            } else {  
                //д�ļ�  
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
        
        // ����ļ�·������Ӧ���ļ����ڣ�������һ���ļ�����ֱ��ɾ��
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
//                System.out.println("ɾ�������ļ�" + fileName + "�ɹ���");
                return true;
            } else {
//                System.out.println("ɾ�������ļ�" + fileName + "ʧ�ܣ�");
                return false;
            }
        } else {
//            System.out.println("ɾ�������ļ�ʧ�ܣ�" + fileName + "�����ڣ�");
            return false;
        }
    }
    
	 public static void readFileByLines(String fileName) {  
	        File file = new File(fileName);  
	        BufferedReader reader = null;  
	        try {  
	            System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");  
	            reader = new BufferedReader(new FileReader(file));  
	            oldJar = reader.readLine();  
	            newJar = reader.readLine(); 
//	            int line = 1;  
//	            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����  
//	            while ((tempString = reader.readLine()) != null) {  
//	                // ��ʾ�к�  
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
