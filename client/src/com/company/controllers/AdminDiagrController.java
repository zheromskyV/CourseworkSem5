package com.company.controllers;

import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.company.client.Client;
import com.company.utils.Routes;
import com.company.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

public class AdminDiagrController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private PieChart departmentsDiagr;

    @FXML
    void onBack(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.adminMenu, getClass());
    }

    @FXML
    void initialize() {
        Client client = Client.getInstance();

        client.sendMessage("getChart");
        client.sendMessage("departments");

        ArrayList<AbstractMap.SimpleEntry<String, Integer>> data
                = (ArrayList<AbstractMap.SimpleEntry<String, Integer>>) client.readObject();

        for (AbstractMap.SimpleEntry<String, Integer> point: data) {
            departmentsDiagr.getData().add(new PieChart.Data(point.getKey(), point.getValue()));
        }

        departmentsDiagr.setLegendSide(Side.LEFT);
    }
}
