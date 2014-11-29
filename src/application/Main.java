package application;
	


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import application.view.RootLayoutController;

import com.buddha.game.Board;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Board board = new Board(10, 5);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			
			
			Scene scene = new Scene(rootLayout);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Game Of Life");
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			controller.setBoard(board);
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
