package com.company.controllers;

import com.company.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import com.company.utils.Routes;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private Button markBtn;

    @FXML
    private Button accred;

    @FXML
    private Button estimBtn;

    @FXML
    private Button dataBtn;

    @FXML
    private Button reportBtn;

    @FXML
    private Button chartBtn;

    @FXML
    void onAccred(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.userEstAccred, getClass());
    }

    @FXML
    void onBack(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.authorization, getClass());
    }

    @FXML
    void onChart(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.userCharts, getClass());
    }

    @FXML
    void onEstim(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.userEstSatisf, getClass());
    }

    @FXML
    void onMark(ActionEvent event) {
        Utils.closeWindow(markBtn);
        Utils.loadWindow(Routes.userMakeMark, getClass());
    }

    @FXML
    void onReport(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.userReports, getClass());
    }

    @FXML
    void initialize() {

    }
}

