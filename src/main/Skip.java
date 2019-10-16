package main;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import main.Main;

/*
 * From main page to different different page
 */

public class Skip {

	@FXML
	private AnchorPane mainPage;
	

	
	public void skipToSearch() {
		mainPage.getScene().getWindow().hide();
		Main.showSearch();
	}
	
	public void skipToSP() {
		mainPage.getScene().getWindow().hide();
		Main.showSP();
	}
	

}
