package application.view;


import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import application.Main;

import com.buddha.game.Board;

public class RootLayoutController {
	private Main mainApp;
	private Board board;
	
	private int currentGeneration = 0;
	
	List<String> generations = new ArrayList<>();
	
	@FXML private Label boardLabel;
	@FXML private Label currentGenerationLabel;
	@FXML private TextField widthText;
	@FXML private TextField heightText;
	
	

	@FXML 
	private void handleNewBoard(){
		int width = Integer.parseInt(widthText.getText());
		int height = Integer.parseInt(heightText.getText());
		generations.clear();
		setBoard(new Board(width, height));
	}
	
	@FXML
	private void handleNextGen(){
		if(currentGeneration >= generations.size()-1)
			generateNext10Gens();
		currentGeneration++;
		setGeneration(currentGeneration);
		
	}
	
	private void setGeneration(int generation){
		boardLabel.setText(generations.get(currentGeneration));
		currentGenerationLabel.setText(""+generation);
	}
	
	@FXML
	private void handlePrevGen(){
		
		if(currentGeneration==0){
			return;
		}
		
		currentGeneration--;
		setGeneration(currentGeneration);
	}
	
	public void setMainApp(Main main) {
		this.mainApp = main;
		  widthText.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(2));
		    heightText.addEventFilter(KeyEvent.KEY_TYPED ,  numeric_Validation(2));
		
	}

	public void setBoard(Board board) {
		this.board = board;
		generations.add(board.toString());
		generateNext10Gens();
		setGeneration(0);
	}
	
	private void generateNext10Gens(){
		
		for(int i = 0; i < 10; i++){
			board.getNextGen();
			generations.add(board.toString());
			
		}
	}
	
	/* Numeric Validation Limit the  characters to maxLengh AND to ONLY DigitS *************************************/
	public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
	    return new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent e) {
	            TextField txt_TextField = (TextField) e.getSource();                
	            if (txt_TextField.getText().length() >= max_Lengh) {                    
	                e.consume();
	            }
	            if(e.getCharacter().matches("[0-9.]")){ 
	                if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
	                    e.consume();
	                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
	                    e.consume(); 
	                }
	            }else{
	                e.consume();
	            }
	        }
	    };
	}    

}
