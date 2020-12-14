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

public class AdminReportsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private Button usersReport;

    @FXML
    private Button departmentsReport;

    @FXML
    private Button salariesReport;

    private Client client;

    @FXML
    void onBack(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.adminMenu, getClass());
    }

    @FXML
    void onDepartmentsReport(ActionEvent event) {
        client.sendMessage("getReport");
        client.sendMessage("departments");

        Utils.createReport(client.readMessage(), "Departments Report");
        new AlertBox(AlertBox.SUCCESS, "Отчет создан").show();
    }

    @FXML
    void onSalariesReport(ActionEvent event) {
        client.sendMessage("getReport");
        client.sendMessage("salaries");

        Utils.createReport(client.readMessage(), "Salaries Report");
        new AlertBox(AlertBox.SUCCESS, "Отчет создан").show();
    }

    @FXML
    void onUsersReport(ActionEvent event) {
        client.sendMessage("getReport");
        client.sendMessage("employees");

        Utils.createReport(client.readMessage(), "Employees Report");
        new AlertBox(AlertBox.SUCCESS, "Отчет создан").show();
    }

    @FXML
    void initialize() {
        client = Client.getInstance();
    }
}
