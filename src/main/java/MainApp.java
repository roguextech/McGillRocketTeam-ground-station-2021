

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.Animation.*;
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

	ArrayList<String> strings = new ArrayList<String>(
			Arrays.asList("1","2","3","4","5"));
	
	
	@FXML
	public Label peakAltitudeLabel;
	
	@FXML
	public void setPeakAltitudeLabel(String value){
		peakAltitudeLabel.setText(value);
	}
	
	private void startTimer() {
//	    Timeline timeline = new Timeline();
//	    timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, event -> {
//	        int i = 0;
//	        setPeakAltitudeLabel(strings.get(i).toString());
//	        i++;
//	    }));
//	    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1)));
//	    timeline.setCycleCount(Timeline.INDEFINITE);
//	    timeline.play();
		setPeakAltitudeLabel("1");
	}
	
	public void initializeNumberDisplays() {
		this.startTimer();
	}
	
    @Override
    public void start(Stage stage) throws Exception {
        Label l = new Label("McGill Rocket Team Ground Station");
        Parent root = FXMLLoader.load(getClass().getResource("fxml/MainApp.fxml"));
        Scene mainApp = new Scene(root, 1920,1080);

        stage.setTitle("McGill Rocket Team Ground Station");
        stage.setScene(mainApp);
        stage.show();
        this.startTimer();

    }

    public static void main(String[] args) {
        launch();
    }

}
