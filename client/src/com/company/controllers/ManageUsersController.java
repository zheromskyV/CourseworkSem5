package com.company.controllers;

import com.company.client.Client;
import com.company.models.Employee;
import com.company.models.table.TableManageUsers;
import com.company.utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageUsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<TableManageUsers> table;

    @FXML
    private TableColumn<TableManageUsers, ?> cIdNum;

    @FXML
    private TableColumn<TableManageUsers, ?> cIdLogin;

    @FXML
    private TableColumn<TableManageUsers, ?> cIdPassword;

    @FXML
    private TableColumn<TableManageUsers, ?> cIdAR;

    @FXML
    private Button btnAddUser;

    @FXML
    private TextField addLogin;

    @FXML
    private PasswordField addPassword;

    @FXML
    private PasswordField addPasswordConfirm;

    @FXML
    private CheckBox addIsAdmin;

    @FXML
    private Button btnSave;

    @FXML
    private TextField saveLogin;

    @FXML
    private PasswordField savePassword;

    @FXML
    private PasswordField savePasswordConfirm;

    @FXML
    private CheckBox saveIsAdmin;

    @FXML
    private TextField saveNum;

    @FXML
    private Button btnLoad;

    @FXML
    private Button updateBtn;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField deleteNum;

    private Client client;

    private ObservableList<TableManageUsers> tableData;
    private ArrayList<Employee> data;
    private Employee singleData;

    private Employee getNumEmployee(String number) {
        int num = Parsers.integer(number);
        System.out.println(num + " " + data.size());
        return num < 1 || num > data.size() ? null : data.get(num - 1);
    }

    private void resetFields() {
        Utils.resetTextField(addLogin);
        Utils.resetTextField(addPassword);
        Utils.resetTextField(addPasswordConfirm);
        Utils.resetCheckBox(addIsAdmin);

        Utils.resetTextField(saveNum);
        Utils.resetTextField(saveLogin);
        Utils.resetTextField(savePassword);
        Utils.resetTextField(savePasswordConfirm);
        Utils.resetCheckBox(saveIsAdmin);

        Utils.resetTextField(deleteNum);
    }

    @FXML
    void onBack(ActionEvent event) {
        Utils.closeWindow(backBtn);
        Utils.loadWindow(Routes.adminMenu, getClass());
    }

    @FXML
    void onUpdate(ActionEvent event) {
        client.sendMessage("getUsers");
        data = (ArrayList<Employee>) client.readObject();
        if (data != null) {
            tableData = FXCollections.observableArrayList(TableManageUsers.convert(data));
            table.setItems(tableData);
        }
    }

    @FXML
    void onAddUser(ActionEvent event) {
        String login = addLogin.textProperty().getValue();
        String password = addPassword.textProperty().getValue();
        String passwordConfirm = addPasswordConfirm.textProperty().getValue();
        boolean isAdmin = addIsAdmin.isSelected();

        if (password.equals(passwordConfirm)) {
            Employee e = new Employee();
            e.setLogin(login);
            e.setPassword(password);
            e.setAccessRightId(Utils.getEmployeeAccessRightsId(isAdmin));
            e.setAccessRightName(Utils.getEmployeeAccessRights(isAdmin));

            client.sendMessage("addUser");
            client.sendObject(e);

            resetFields();
            new AlertBox(AlertBox.SUCCESS, "Успешно").show();
        } else {
            new AlertBox(AlertBox.ERROR, "Ошибка").show();
        }
    }

    @FXML
    void onDelete(ActionEvent event) {
        Employee numE = getNumEmployee(deleteNum.textProperty().getValue());

        if (numE != null) {
            client.sendMessage("deleteUser");
            client.sendObject(numE);
            new AlertBox(AlertBox.SUCCESS, "Успешно").show();
        } else {
            new AlertBox(AlertBox.ERROR, "Ошибка").show();
        }

        resetFields();
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
            saveLogin.textProperty().setValue(singleData.getLogin());
            savePassword.textProperty().setValue(singleData.getPassword());
            savePasswordConfirm.textProperty().setValue(singleData.getPassword());
            saveIsAdmin.setSelected(singleData.getAccessRightName().equals(Constants.Admin));
        } else {
            resetFields();
            new AlertBox(AlertBox.ERROR, "Ошибка").show();
        }
    }

    @FXML
    void onSave(ActionEvent event) {
        String login = saveLogin.textProperty().getValue();
        String password = savePassword.textProperty().getValue();
        String passwordConfirm = savePasswordConfirm.textProperty().getValue();
        boolean isAdmin = saveIsAdmin.isSelected();

        if (password.equals(passwordConfirm) && singleData != null) {
            singleData.setLogin(login);
            singleData.setPassword(password);
            singleData.setAccessRightId(Utils.getEmployeeAccessRightsId(isAdmin));
            singleData.setAccessRightName(Utils.getEmployeeAccessRights(isAdmin));

            client.sendMessage("updateUserManage");
            client.sendObject(singleData);

            resetFields();
            new AlertBox(AlertBox.SUCCESS, "Успешно").show();
        } else {
            new AlertBox(AlertBox.ERROR, "Ошибка").show();
        }
    }

    @FXML
    void initialize() {
        client = Client.getInstance();

        cIdNum.setCellValueFactory(new PropertyValueFactory("num"));
        cIdLogin.setCellValueFactory(new PropertyValueFactory("login"));
        cIdPassword.setCellValueFactory(new PropertyValueFactory("password"));
        cIdAR.setCellValueFactory(new PropertyValueFactory("accessRight"));

        onUpdate(null);
    }
}
