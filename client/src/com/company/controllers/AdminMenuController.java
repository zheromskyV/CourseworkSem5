package com.company.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import com.company.utils.Utils;
import com.company.utils.Routes;

public class AdminMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private Button dataBtn;

    @FXML
    private Button salaryBtn;

    @FXML
    private Button manageUsersBtn;

    @FXML
    private Button diagrBtn;

    @FXML
    private Button reportBtn;

    @FXML
    private Button chartBtn;

    @FXML
    void onBack(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.authorization, getClass());
    }

    @FXML
    void onChart(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.adminCharts, getClass());
    }

    @FXML
    void onData(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.manageData, getClass());
    }

    @FXML
    void onDiagr(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.adminDiagr, getClass());
    }

    @FXML
    void onManageUsers(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.manageUsers, getClass());
    }

    @FXML
    void onReport(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.adminReports, getClass());
    }

    @FXML
    void onSalary(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.manageSalaries, getClass());
    }

    @FXML
    void initialize() {
    }
}
