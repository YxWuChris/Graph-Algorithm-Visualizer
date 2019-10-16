package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
			primaryStage.setTitle("Menu");
			primaryStage.setScene(new Scene(root));
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
	public static void recover() {
		try { 
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("View.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setResizable(false);
			dialogStage.setTitle("Menu");
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			dialogStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Show Search method, Load the Search algorithm page
	 */
	

	public static void showSearch() {
		try { 
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Search.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setResizable(false);
			dialogStage.setTitle("InnerRoot");
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			dialogStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Show SP method, Load the Sp algorithm page
	 */
	public static void showSP() {
		try { 
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ShortestPath.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setResizable(false);
			dialogStage.setTitle("InnerRoot");
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			dialogStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
