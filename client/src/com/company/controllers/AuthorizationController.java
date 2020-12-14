package com.company.controllers;

import com.company.utils.AlertBox;
import com.company.utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.company.client.Client;
import com.company.models.Employee;
import com.company.utils.Utils;
import com.company.utils.Routes;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button submit;

    @FXML
    void onSubmit(ActionEvent event) {
        String l = login.textProperty().getValue();
        String p = password.textProperty().getValue();

        Employee employee = new Employee();
        employee.setLogin(l);
        employee.setPassword(p);

        Client client = Client.getInstance();

        client.sendMessage("authorize");
        client.sendObject(employee);

        String answer = client.readMessage();

        if (answer.equals("no")) {
            Utils.resetTextField(login);
            login.requestFocus();
            Utils.resetTextField(password);

            new AlertBox(AlertBox.ERROR, "Неверный логин или пароль").show();
        } else {
            boolean isAdmin = answer.equals(Constants.Admin);
            String route = isAdmin ? Routes.adminMenu : Routes.userMenu;

            Employee current = (Employee) client.readObject();
            Client.self = current;

            Utils.closeWindow(submit);
            Utils.loadWindow(route, getClass());
        }
    }

    @FXML
    void initialize() {
    }
}
