package com.company.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.company.client.Client;
import com.company.utils.AlertBox;
import com.company.utils.Routes;
import com.company.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserReportsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private Button visitMarksReport;

    @FXML
    private Button salaryReport;

    private Client client;

    @FXML
    void onBack(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.userMenu, getClass());
    }

    @FXML
    void onSalaryReport(ActionEvent event) {
        client.sendMessage("getReport");
        client.sendMessage("salaries");
        client.sendObject(Client.self);

        Utils.createReport(client.readMessage(), "Salary Report");
        new AlertBox(AlertBox.SUCCESS, "Отчет создан").show();
    }

    @FXML
    void onVisitMarksReport(ActionEvent event) {
        client.sendMessage("getReport");
        client.sendMessage("visitMarks");
        client.sendObject(Client.self);

        Utils.createReport(client.readMessage(), "Visit Marks Report");
        new AlertBox(AlertBox.SUCCESS, "Отчет создан").show();
    }

    @FXML
    void initialize() {
        client = Client.getInstance();
    }
}
