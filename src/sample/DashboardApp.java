package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Model.Dashboard;

public class DashboardApp extends Application {

    private static Dashboard dashboard = new Dashboard();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/sample.fxml"));
        primaryStage.setTitle("Hello World");

        Scene start_window = new Scene(root, 600, 800);
        primaryStage.setScene(start_window);
        primaryStage.show();
        dashboard.setInitialStage(primaryStage);
    }

    public static Dashboard getDashboard() {
        return dashboard;
    }


    public static void main(String[] args) {
        launch(args);
    }
}



