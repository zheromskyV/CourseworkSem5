package com.company;

import com.company.utils.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import com.company.client.Client;
import com.company.utils.Constants;
import com.company.utils.Routes;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Utils.setUpStage(primaryStage, FXMLLoader.load(getClass().getResource(Routes.authorization)));
    }

    public static void main(String[] args) {
        Client.setInitValues(Constants.ipAdress, Constants.port);
        launch(args);
    }
}
