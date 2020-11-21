

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.Animation.*;
import controller.Parser;
import controller.gui.MainController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.util.Arrays;
import java.util.ArrayList;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.scene.text.Text;


import javafx.scene.control.Label;

public class MainApp extends Application {
	
    @Override
    public void start(Stage stage) throws Exception {
    	
    	
        Label l = new Label("McGill Rocket Team Ground Station");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/MainApp.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.<MainController>getController();
        
        Scene mainApp = new Scene(root, 1920,1080);

        stage.setTitle("McGill Rocket Team Ground Station");
        stage.setScene(mainApp);
        stage.show();
        
        ArrayList<double[]> myDataList = new ArrayList<double[]>();
        Parser parser = new Parser(11);
    	ArrayList<String> myData = (ArrayList<String>) Parser.storeData("src/main/resources/Data_last_year.txt");
    	
    	for (String str:myData) {
    		try {
    		//	System.out.println(parser.parse(str));
    			myDataList.add(parser.parse((str)));
    		} catch (IllegalArgumentException e) {			
    	//		System.out.println("Line of data does not start with S and/or does not end with E and/or ','");
    		}
    	}
    	
    		mainController.startTimer(myDataList);	
    }

    public static void main(String[] args) {
        launch();
    }

}
