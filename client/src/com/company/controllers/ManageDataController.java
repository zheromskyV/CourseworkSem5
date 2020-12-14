package com.company.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.company.client.Client;
import com.company.models.Employee;
import com.company.models.table.TableManageData;
import com.company.utils.AlertBox;
import com.company.utils.Parsers;
import com.company.utils.Utils;
import com.company.utils.Routes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageDataController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<TableManageData> table;

    @FXML
    private TableColumn<TableManageData, ?> cIdNum;

    @FXML
    private TableColumn<TableManageData, ?> cIdName;

    @FXML
    private TableColumn<TableManageData, ?> cIdSurname;

    @FXML
    private TableColumn<TableManageData, ?> cIdPassport;

    @FXML
    private TableColumn<TableManageData, ?> cIdDepartment;

    @FXML
    private TableColumn<TableManageData, ?> cIdBank;

    @FXML
    private TableColumn<TableManageData, ?> cIdAccred;

    @FXML
    private TableColumn<TableManageData, ?> cIdSatisf;

    @FXML
    private Button updateBtn;

    @FXML
    private Button btnSave;

    @FXML
    private TextField saveName;

    @FXML
    private TextField saveNum;

    @FXML
    private Button btnLoad;

    @FXML
    private TextField savePassportNum;

    @FXML
    private TextField savePassportSer;

    @FXML
    private TextField saveSurname;

    private Client client;

    private ObservableList<TableManageData> tableData;
    private ArrayList<Employee> data;
    private Employee singleData;

    private Employee getNumEmployee(String number) {
        int num = Parsers.integer(number);

        return num < 1 || num > data.size() ? null : data.get(num - 1);
    }

    private void resetFields() {
        Utils.resetTextField(saveNum);
        Utils.resetTextField(saveName);
        Utils.resetTextField(saveSurname);
        Utils.resetTextField(savePassportSer);
        Utils.resetTextField(savePassportNum);
    }

    @FXML
    void onBack(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.adminMenu, getClass());
    }

    @FXML
    void onLoad(ActionEvent event) {
        Employee numE = getNumEmployee(saveNum.textProperty().getValue());

        if (numE != null) {
            client.sendMessage("getUser");
            client.sendObject(numE);

            singleData = (Employee) client.readObject();
        } else {
            singleData = null;
        }

        if (singleData != null) {
            saveName.textProperty().setValue(singleData.getName());
            saveSurname.textProperty().setValue(singleData.getSurname());
            savePassportSer.textProperty().setValue(singleData.getPassportSer());
            savePassportNum.textProperty().setValue(String.valueOf(singleData.getPassportNum()));
        } else {
            new AlertBox(AlertBox.ERROR, "Ошибка").show();
            resetFields();
        }
    }

    @FXML
    void onSave(ActionEvent event) {
        String name = saveName.textProperty().getValue();
        String surname = saveSurname.textProperty().getValue();
        String passportSer = savePassportSer.textProperty().getValue();
        int passportNum = Parsers.passportNum(savePassportNum.textProperty().getValue());

        if (singleData != null) {
            singleData.setName(name);
            singleData.setSurname(surname);
            singleData.setPassportSer(passportSer);
            singleData.setPassportNum(passportNum);

            client.sendMessage("updateUserData");
            client.sendObject(singleData);

            resetFields();
            new AlertBox(AlertBox.SUCCESS, "Успешно").show();
        }
    }

    @FXML
    void onUpdate(ActionEvent event) {
        client.sendMessage("getUsers");
        data = (ArrayList<Employee>) client.readObject();
        if (data != null) {
            tableData = FXCollections.observableArrayList(TableManageData.convert(data));
            table.setItems(tableData);
        }
    }

    @FXML
    void initialize() {
        client = Client.getInstance();

        cIdNum.setCellValueFactory(new PropertyValueFactory("num"));
        cIdName.setCellValueFactory(new PropertyValueFactory("name"));
        cIdSurname.setCellValueFactory(new PropertyValueFactory("surname"));
        cIdPassport.setCellValueFactory(new PropertyValueFactory("passport"));
        cIdDepartment.setCellValueFactory(new PropertyValueFactory("department"));
        cIdBank.setCellValueFactory(new PropertyValueFactory("bank"));
        cIdAccred.setCellValueFactory(new PropertyValueFactory("estAccred"));
        cIdSatisf.setCellValueFactory(new PropertyValueFactory("estSatisf"));

        onUpdate(null);
    }
}
