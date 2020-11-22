package controller.gui;

import java.util.Arrays;

import controller.Parser;

import java.util.ArrayList;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.scene.text.Text;


import javafx.scene.control.Label;

public class MainController {
	
	@FXML
	public Label peakAltitudeLabel;
	public Label currentAltitudeLabel;
	public Label peakVelocityLabel;
	public Label currentVelocityLabel;
	public Label peakAccelerationLabel;
	public Label currentAccelerationLabel;
	public Label currentRSSILabel;
	PauseTransition transition = new PauseTransition(Duration.seconds(1));
	
	
	@FXML
	public void setPeakAltitudeLabel(String value){
		this.peakAltitudeLabel.setText(value);
	}
	
	@FXML
	public void setCurrentAltitudeLabel(String value){
		this.currentAltitudeLabel.setText(value);
	}
	@FXML
	public void setPeakVelocityLabel(String value){
		this.peakVelocityLabel.setText(value);
	}
	@FXML
	public void setCurrentVelocityLabel(String value){
		this.currentVelocityLabel.setText(value);
	}
	@FXML
	public void setPeakAccelerationLabel(String value) {
		this.peakAccelerationLabel.setText(value);
	}
	@FXML
	public void setCurrentAccelerationLabel(String value){
		this.currentAccelerationLabel.setText(value);
	}
	@FXML
	public void setCurrentRSSILabel(String value) {
		this.currentRSSILabel.setText(value);
	}
	

    int i = 0;
    
    public void startTimer(ArrayList<double[]> myDataList) {
    	Timeline timeline = new Timeline();
    	timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO,event ->{
    		System.out.println(myDataList.get(i)[5]);
    		//set Peak Altitude 
    		if (myDataList.get(i)[3] > Double.parseDouble(peakAltitudeLabel.getText())) 
    			setPeakAltitudeLabel(String.valueOf(myDataList.get(i)[3]));
    		//set Current Altitude 
    		setCurrentAltitudeLabel(String.valueOf(myDataList.get(i)[3]));
    		//set Peak Velocity 
    		if (myDataList.get(i)[4]>Double.parseDouble(peakVelocityLabel.getText())) 
    			setPeakVelocityLabel(String.valueOf(myDataList.get(i)[4]));
    		//set Current Velocity 
    		setCurrentVelocityLabel(String.valueOf(myDataList.get(i)[4]));
    		//set Peak Acceleration
    		if (myDataList.get(i)[4]>Double.parseDouble(peakAccelerationLabel.getText())) 
    			setPeakAccelerationLabel(String.valueOf(myDataList.get(i)[5]));
    		//set Current Acceleration
    		setCurrentAccelerationLabel(String.valueOf(myDataList.get(i)[5]));
    		//setCurrentRSSI
    		setCurrentRSSILabel(String.valueOf(myDataList.get(i)[9]));
    		if((i+1)<myDataList.size()) i++;
    	}));
    	timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1)));
    	timeline.setCycleCount(Timeline.INDEFINITE);
    	timeline.play();
    }

	public void initializeNumberDisplays() {
		
	}
	

}
	