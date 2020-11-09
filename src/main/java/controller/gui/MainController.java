package controller.gui;

import java.util.Arrays;
import java.util.ArrayList;
import javafx.animation.*;
import javafx.util.Duration;
import sun.nio.cs.Surrogate.Parser;
import javafx.fxml.FXML;
import javafx.scene.text.Text;


import javafx.scene.control.Label;

public class MainController {
	
	Parser parser = new Parser(11);
	ArrayList<String> myData = parser.storedata("src/main/resources/Data_last_year.txt");
	ArrayList<double[]> myDataList = new Arraylist<double[]>();
	
//	for (String str:myData) {
//		myDataList.add(parser.parse(str)); //add double array to myDataList
//	}
	//double[] myDataList = parser.parse(myData);
	//double altitude = myData[3];
	
	
	ArrayList<String> strings = new ArrayList<String>(
			Arrays.asList("1","2","3","4","5","1","2","3","4","5","1","2","3","4","30","1","2","3","4","5"));
	
	
	@FXML
	public Label peakAltitudeLabel;
	
	@FXML
	public void setPeakAltitudeLabel(String value){
		this.peakAltitudeLabel.setText(value);
	}
	
    int i = 0;
	
	public void startTimer() {
		
		for (String str:myData) {
			myDataList.add(parser.parse(str)); //add double array to myDataList
		}
		
	    Timeline timeline = new Timeline();
	    timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, event -> {
	    	
	    	if (myDataList.get(i)[3]) > peakAltitudeLabel.getText()) setPeakAltitudeLabel(myDataList.get(i)[3]));
	     //   if (Integer.parseInt(strings.get(i)) > Integer.parseInt(peakAltitudeLabel.getText())) setPeakAltitudeLabel(strings.get(i).toString());
	        i++;
	    }));
	    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1)));
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
	}
	
	public void initializeNumberDisplays() {
		
	}

}
	