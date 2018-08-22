package com.skyfoxdigital.mepos;
	
import com.uniquesecure.meposconnect.MePOS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	static MePOS mepos;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		
		try {

			stage.getIcons().add(new Image("/com/skyfoxdigital/mepos/assets/mepos_logo_50x50.png"));
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			stage.setTitle("MePOS Java SDK Demo");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() {
	
		if (mepos != null) mepos.getConnectionManager().disconnect();
	}
}
