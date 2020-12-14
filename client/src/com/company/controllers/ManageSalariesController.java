package com.company.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.company.client.Client;
import com.company.models.Employee;
import com.company.models.Salary;
import com.company.models.table.TableManageData;
import com.company.models.table.TableSalary;
import com.company.utils.AlertBox;
import com.company.utils.Parsers;
import com.company.utils.Routes;
import com.company.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageSalariesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<TableSalary> tableF;

    @FXML
    private TableColumn<TableSalary, ?> cIdNumF;

    @FXML
    private TableColumn<TableSalary, ?> cIdSurnameF;

    @FXML
    private TableColumn<TableSalary, ?> cIdDepartmentF;

    @FXML
    private TableColumn<TableSalary, ?> cIdSalaryF;

    @FXML
    private TableColumn<TableSalary, ?> cIdAccredF;

    @FXML
    private TableColumn<TableSalary, ?> cIdSatisfF;

    @FXML
    private TableColumn<TableSalary, ?> cIdKoefF;

    @FXML
    private Button updateBtnF;

    @FXML
    private TableView<TableSalary> tableS;

    @FXML
    private TableColumn<TableSalary, ?> cIdNumS;

    @FXML
    private TableColumn<TableSalary, ?> cIdSurnameS;

    @FXML
    private TableColumn<TableSalary, ?> cIdDepartmentS;

    @FXML
    private TableColumn<TableSalary, ?> cIdSalaryS;

    @FXML
    private TableColumn<TableSalary, ?> cIdKoefS;

    @FXML
    private TableColumn<TableSalary, ?> cIdHoursS;

    @FXML
    private TableColumn<TableSalary, ?> cIdMonthSalaryS;

    @FXML
    private Button updateBtnS;

    @FXML
    private Button btnSave;

    @FXML
    private TextField saveSalary;

    @FXML
    private TextField saveNum;

    @FXML
    private Button btnLoad;

    private Client client;

    private ObservableList<TableSalary> tableData;
    private ArrayList<Salary> data;
    private Salary singleData;

    private Salary getNumSalary(String number) {
        int num = Parsers.integer(number);

        return num < 1 || num > data.size() ? null : data.get(num - 1);
    }

    private void resetFields() {
        Utils.resetTextField(saveNum);
        Utils.resetTextField(saveSalary);
    }

    @FXML
    void onBack(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.adminMenu, getClass());
    }

    @FXML
    void onUpdate(ActionEvent event) {
        client.sendMessage("getSalaries");
        data = (ArrayList<Salary>) client.readObject();
        if (data != null) {
            tableData = FXCollections.observableArrayList(TableSalary.convert(data));
            tableS.setItems(tableData);
            tableF.setItems(tableData);
        }
    }

    @FXML
    void onLoad(ActionEvent event) {
        Salary numS = getNumSalary(saveNum.textProperty().getValue());

        if (numS != null) {
            client.sendMessage("getSalary");
            client.sendObject(numS);

            singleData = (Salary) client.readObject();
        } else {
            singleData = null;
        }

        if (singleData != null) {
            saveSalary.textProperty().setValue(String.valueOf(singleData.getSalary()));
        } else {
            new AlertBox(AlertBox.ERROR, "Ошибка").show();
            resetFields();
        }
    }

    @FXML
    void onSave(ActionEvent event) {
        float salary = Parsers.float_(saveSalary.textProperty().getValue());

        if (singleData != null) {
            singleData.setSalary(salary);

            client.sendMessage("updateSalary");
            client.sendObject(singleData);

            resetFields();
            new AlertBox(AlertBox.SUCCESS, "Успешно").show();
        }
    }

    @FXML
    void initialize() {
        client = Client.getInstance();

        cIdNumF.setCellValueFactory(new PropertyValueFactory("num"));
        cIdNumS.setCellValueFactory(new PropertyValueFactory("num"));
        cIdSurnameF.setCellValueFactory(new PropertyValueFactory("surname"));
        cIdSurnameS.setCellValueFactory(new PropertyValueFactory("surname"));
        cIdDepartmentF.setCellValueFactory(new PropertyValueFactory("department"));
        cIdDepartmentS.setCellValueFactory(new PropertyValueFactory("department"));
        cIdSalaryF.setCellValueFactory(new PropertyValueFactory("salary"));
        cIdSalaryS.setCellValueFactory(new PropertyValueFactory("salary"));
        cIdKoefF.setCellValueFactory(new PropertyValueFactory("koef"));
        cIdKoefS.setCellValueFactory(new PropertyValueFactory("koef"));
        cIdAccredF.setCellValueFactory(new PropertyValueFactory("estAccred"));
        cIdSatisfF.setCellValueFactory(new PropertyValueFactory("estSatisf"));
        cIdHoursS.setCellValueFactory(new PropertyValueFactory("hours"));
        cIdMonthSalaryS.setCellValueFactory(new PropertyValueFactory("monthSalary"));

        onUpdate(null);
    }
}
