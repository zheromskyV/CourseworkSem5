package com.company.controllers;

import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.company.client.Client;
import com.company.models.Employee;
import com.company.utils.Routes;
import com.company.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;

public class AdminChartsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();

    @FXML
    private LineChart<String, Number> chartAccred = new LineChart<String, Number>(xAxis, yAxis);

    @FXML
    private BarChart<String, Number> chartSatisf = new BarChart<String, Number>(xAxis, yAxis);

    @FXML
    void onBack(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.adminMenu, getClass());
    }

    private XYChart.Series getSeries(ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> data) {
        XYChart.Series series = new XYChart.Series();
        series.setName("Количество оценок");

        for (AbstractMap.SimpleEntry<Integer, Integer> point: data) {
            series.getData().add(new XYChart.Data(point.getKey().toString(), point.getValue()));
        }

        return series;
    }

    @FXML
    void initialize() {
        Client client = Client.getInstance();

        client.sendMessage("getChart");
        client.sendMessage("accred");
        chartAccred.getData().add(
                getSeries((ArrayList<AbstractMap.SimpleEntry<Integer, Integer>>) client.readObject()));

        client.sendMessage("getChart");
        client.sendMessage("satisf");
        chartSatisf.getData().add(
                getSeries((ArrayList<AbstractMap.SimpleEntry<Integer, Integer>>) client.readObject()));
    }
}
