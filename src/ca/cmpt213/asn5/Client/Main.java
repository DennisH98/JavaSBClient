package ca.cmpt213.asn5.Client;

import javafx.application.Application;

import javafx.stage.Stage;



public class Main extends Application {

    /**
     * This is the main class where the application starts
     * @author Dennis Huynh
     * @author 3013279204
     */

    @Override
    public void start(Stage primaryStage) throws Exception{


        UserInterface ui = new UserInterface();

        primaryStage.setTitle("Tokimon Page");
        primaryStage.setScene(ui.mainScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
