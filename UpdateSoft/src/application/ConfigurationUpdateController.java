package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class ConfigurationUpdateController implements Initializable{
	@FXML
	private Button updateChoiceFileButton;
	@FXML
	private Button updateSureUpdateButton;
	@FXML
	private TextArea updateConfiTextArea;
	@FXML
	private Label updateFileInfoLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	public void updateChoiceFile(){
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = 
       		 new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        String fileInfo = file.getAbsolutePath().toString();
        updateFileInfoLabel.setText(fileInfo);
	}
	public void updateSureUpdate(){
		
	}

}
