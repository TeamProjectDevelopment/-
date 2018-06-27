package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Update {
	
	public static boolean partUpdate(URL fileUrl,String filePath) throws MalformedURLException {
		List<String> oldFileList = new ArrayList<String>();
		List<String> serverFileList = new ArrayList<String>();
		File localFile = new File(filePath+"/cfile.txt");	
		oldFileList = searchAllFile(localFile);
		URL temfileUrl = new URL(fileUrl + "/tem.txt");
		downloadFile(temfileUrl,filePath + "/temFile.txt");
		File serverFile = new File(filePath  + "/temFile.txt");
		serverFileList = searchAllFile(serverFile);
		for(String sFile:serverFileList) {
			if(!oldFileList.contains(sFile)){
				URL subfileUrl = new URL(fileUrl + "/" + sFile);
				if(sFile.lastIndexOf(".") == -1) {
					 downloadUpdate(filePath + "/" + sFile);
				}else
				    downloadFile(subfileUrl,filePath + "/" + sFile );
			}
		}
		for(String oldfile:oldFileList) {
			if(!serverFileList.contains(oldfile)) {
				File delFile = new File(filePath + "/" + oldfile);
				delFile.delete();
			}
		}
		return true;
	}
	
	
	public static List<String> searchAllFile(File file){
		List<String> fileInfoList = new ArrayList<String>();
		try{			
			FileReader fReader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(fReader);
			//将日志文件中的文件日志读出
			String s;
			while((s = bReader.readLine()) != null){				
				fileInfoList.add(s);
			}
			bReader.close();
			fReader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return fileInfoList;
	}
	
	public static void downloadFile(URL fileUrl,String fileName){
		InputStream is = null;
		OutputStream os = null;
		try {						
			URLConnection uric = fileUrl.openConnection();
			int fileLength = uric.getContentLength();
			if(fileLength >= 0){
				byte[] buffer = new byte[fileLength];
				is = uric.getInputStream();			
				
				is.read(buffer, 0, fileLength);
				//下载并更新
				File file = new File(fileName);				
				if(file.exists()){
					file.delete();					
				}
				file.createNewFile();
				os = new FileOutputStream(file);

				os.write(buffer);
				os.close();										
			}else{
				JOptionPane.showMessageDialog(null,"要下载的文件为空文件！","",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
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
				System.out.println(e);
			}
			
		}
	}
	public static boolean downloadUpdate(String directory) throws MalformedURLException{
		
		//创建文件夹
		File temFilePath = new File(directory);
		if(temFilePath.exists()){
			temFilePath.delete();
		}
		temFilePath.mkdir();
		String dir = directory.substring(directory.lastIndexOf("/"), directory.length());				
	    File serverFile = new File("D:/data/" + dir);
	    File [] serverFiles = serverFile.listFiles();
		for(int i = 0;i < serverFiles.length;i++){	
			URL fileUrl1 = new URL("file:///" + serverFiles[i].toString() );
			downloadFile(fileUrl1,directory + "/" + serverFiles[i].getName().toString());
		}
		return true;
	}

}
