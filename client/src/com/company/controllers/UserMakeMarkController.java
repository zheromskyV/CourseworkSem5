package com.company.controllers;

import com.company.client.Client;
import com.company.models.VisitMark;
import com.company.utils.AlertBox;
import com.company.utils.Utils;
import com.company.utils.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.company.utils.Routes;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class UserMakeMarkController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private Button submit;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField timePicker;

    @FXML
    private RadioButton radioIn;

    @FXML
    private ToggleGroup radioInOut;

    @FXML
    private RadioButton radioOut;

    Client client;

    @FXML
    void onBack(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.userMenu, getClass());
    }

    @FXML
    void onSubmit(ActionEvent event) {
        LocalDate localDate = datePicker.getValue();
        String time = timePicker.textProperty().getValue();
        boolean isIN = radioIn.isSelected();

        if (Validator.validateTime(time)) {
            Date datetime = new Date();
            datetime.setYear(localDate.getYear() - 1900);
            datetime.setMonth(localDate.getMonthValue() - 1);
            datetime.setDate(localDate.getDayOfMonth());
            datetime.setHours(Integer.parseInt(time.split("\\:")[0]));
            datetime.setMinutes(Integer.parseInt(time.split("\\:")[1]));

            VisitMark mark = new VisitMark();
            mark.setDatetime(datetime);
            mark.setVisitMarkTypeId(isIN ? 1 : 2);
            mark.setEmployeeId(Client.self.getId());

            client.sendMessage("addMark");
            client.sendObject(mark);

            new AlertBox(AlertBox.SUCCESS, "Отметка добавлена").show();
        } else {
            new AlertBox(AlertBox.ERROR, "Ошибка").show();
        }

        onBack(null);
    }

    @FXML
    void initialize() {
        client = Client.getInstance();
    }
}
