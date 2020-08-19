package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.DashboardApp;
import sample.Model.Dashboard;

import java.io.IOException;

public class Controller {

    @FXML
    Button startButton;

    /**
     * After clicking the main start button, close the main menu and open 4 new windows
     * @param actionEvent
     * @throws IOException
     */

    @FXML
    protected void startWindows(ActionEvent actionEvent) throws IOException {

        //procedure list window
        Parent procedureFXML = FXMLLoader.load(getClass().getResource("FXML/procedure.fxml"));
        Scene procedure = new Scene(procedureFXML, 600, 800);
        Stage procedureStage = new Stage();
        procedureStage.setTitle("Procedures List");
        procedureStage.setScene(procedure);
        procedureStage.show();

        //Containers with the labview window
        Parent containersFXML = FXMLLoader.load(getClass().getResource("FXML/containers.fxml"));
        Scene containers = new Scene(containersFXML, 600, 800);
        Stage containersStage = new Stage();
        containersStage.setTitle("LabView Containers");
        containersStage.setScene(containers);
        containersStage.show();

        //Cameras window
        Parent camerasFXML = FXMLLoader.load(getClass().getResource("FXML/cameras.fxml"));
        Scene cameras = new Scene(camerasFXML, 600, 800);
        Stage camerasStage = new Stage();
        camerasStage.setTitle("Cameras");
        camerasStage.setScene(cameras);
        camerasStage.show();

        //Graphs window
        Parent graphsFXML = FXMLLoader.load(getClass().getResource("FXML/graphs.fxml"));
        Scene graphs = new Scene(graphsFXML, 600, 800);
        Stage graphsStage = new Stage();
        graphsStage.setTitle("Graphs");
        graphsStage.setScene(graphs);
        graphsStage.show();

        //Create new Application with the stages above
        DashboardApp.getDashboard().setFourStages(procedureStage, containersStage, camerasStage, graphsStage);

        Stage primaryStage = DashboardApp.getDashboard().getInitialStage();
        primaryStage.close();
    }
}
