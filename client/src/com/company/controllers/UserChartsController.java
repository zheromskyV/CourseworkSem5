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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class UserChartsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();

    @FXML
    private LineChart<String, Number> chart = new LineChart<String, Number>(xAxis, yAxis);

    @FXML
    void onBack(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.userMenu, getClass());
    }

    private XYChart.Series getSeries(ArrayList<AbstractMap.SimpleEntry<String, Integer>> data) {
        XYChart.Series series = new XYChart.Series();
        series.setName("Отработанные часы");

        for (AbstractMap.SimpleEntry<String, Integer> point: data) {
            series.getData().add(new XYChart.Data(point.getKey(), point.getValue()));
        }

        return series;
    }

    @FXML
    void initialize() {
        Client client = Client.getInstance();

        client.sendMessage("getChart");
        client.sendMessage("visitMarks");
        client.sendObject(Client.self);

        chart.getData().add(
                getSeries((ArrayList<AbstractMap.SimpleEntry<String, Integer>>) client.readObject()));
    }
}
