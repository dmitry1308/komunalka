package ru.dshepin.komunalka;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.logging.Logger;

public class App extends Application {
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		Logger.getLogger(Application.class.getName());
		Logger.getLogger("Привет");
		Logger.getLogger(String.valueOf(this.getClass()));


		FXMLLoader loader = new FXMLLoader();
		URL xmlUrl = getClass().getResource("/window.fxml");
		loader.setLocation(xmlUrl);
		Parent root = loader.load();
		stage.setTitle("Коммунальный калькулятор.");

		stage.setScene(new Scene(root));
		stage.show();
	}
}
