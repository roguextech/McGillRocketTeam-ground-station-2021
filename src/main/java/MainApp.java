

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
        Parser parser = new Parser(10);
        //Parse through data from file and store it in String ArrayList myData
    	ArrayList<String> myData = (ArrayList<String>) Parser.storeData("src/main/resources/Data_last_year.txt");
    	
    	//store arrays of doubles (data from myData) in myDataList
    	for (String str:myData) {
    	//for (int i = 2; i<myData.size();i++) {
    		try {
//    			System.out.println(parser.parse(str).length);
    			myDataList.add(parser.parse((str)));
    		} catch (IllegalArgumentException e) {
    			String subStr = str.substring(1, str.length()-2);
    			String[] sub = subStr.split(",");
    			if (sub.length != 11) { //if the number of data values does not equal number of parameters, ignore
    				System.out.println(sub.length);
    			} else if (str.charAt(0) != 'S' && str.charAt(str.length()-1) != 'E' && str.charAt(str.length()-2) != ',') {
    				//if string does not follow data format, create new edited string 
    				//add edited string to data list 
    				String newstr1 = "S"+str+",E";
    				myDataList.add(parser.parse(newstr1));
    			} else if (str.charAt(0) != 'S') {
    				//if string does not follow data format (first character not S), create new edited string 
    				//add edited string to data list 
    				String newstr2 = "S"+str;
    				myDataList.add(parser.parse(newstr2));
    			} else if (str.charAt(str.length()-1) != 'E') {
    				//if string does not follow data format (last character not E), create new edited string 
    				//add edited string to data list 
    				String newstr3 = str + "E";
    				myDataList.add(parser.parse(newstr3));
    			} else if (str.charAt(str.length()-2) != ',') {
    				//if string does not follow data format, create new edited string 
    				//add edited string to data list 
    				String newstr4 = str.substring(0, str.length()-2) + ",E";
    				myDataList.add(parser.parse(newstr4));
    			} 
    		}
    	}
//    	System.out.println(myData.size());
//    	System.out.println(myDataList.size());
    	mainController.startTimer(myDataList);	//start timer animation display
    }

    public static void main(String[] args) {
        launch();
    }

}
