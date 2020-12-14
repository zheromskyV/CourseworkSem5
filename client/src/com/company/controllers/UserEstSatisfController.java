package com.company.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.company.client.Client;
import com.company.models.Employee;
import com.company.utils.AlertBox;
import com.company.utils.Routes;
import com.company.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class UserEstSatisfController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private RadioButton q1a;

    @FXML
    private ToggleGroup q1;

    @FXML
    private RadioButton q2a;

    @FXML
    private ToggleGroup q2;

    @FXML
    private RadioButton q3a;

    @FXML
    private ToggleGroup q3;

    @FXML
    private RadioButton q4a;

    @FXML
    private ToggleGroup q4;

    @FXML
    private RadioButton q5a;

    @FXML
    private ToggleGroup q5;

    @FXML
    private Button submitBtn;

    @FXML
    void onBack(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.userMenu, getClass());
    }

    private int getEst(RadioButton rb) {
        return rb.isSelected() ? 2 : 0;
    }

    @FXML
    void onSubmit(ActionEvent event) {
        int res = 0;
        res += getEst(q1a);
        res += getEst(q2a);
        res += getEst(q3a);
        res += getEst(q4a);
        res += getEst(q5a);

        Employee me = Client.self;
        me.setEstSatisf(res);

        Client client = Client.getInstance();
        client.sendMessage("updateUserData");
        client.sendObject(me);

        onBack(null);
        new AlertBox(AlertBox.SUCCESS, "Успешно").show();
    }

    @FXML
    void initialize() {
    }
}
