package sample.Model;

import javafx.stage.Stage;

public class Dashboard {

    private Stage primaryStage;
    private Stage procedureStage;
    private Stage containersStage;
    private Stage camerasStage;
    private Stage graphsStage;

    public Dashboard() {
    }

    public void setInitialStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setFourStages(Stage procedureStage, Stage containersStage, Stage camerasStage, Stage graphsStage) {
        this.procedureStage = procedureStage;
        this.containersStage = containersStage;
        this.camerasStage = camerasStage;
        this.graphsStage = graphsStage;
    }

    public Stage getInitialStage() {
        return primaryStage;
    }

}
