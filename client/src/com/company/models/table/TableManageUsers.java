package com.company.models.table;

import com.company.models.Employee;
import javafx.beans.property.*;

import java.util.ArrayList;

public class TableManageUsers {
    private SimpleIntegerProperty num;
    private SimpleStringProperty login;
    private SimpleStringProperty password;
    private SimpleStringProperty accessRight;

    public TableManageUsers(int num, String login, String password, String accessRight) {
        this.num = new SimpleIntegerProperty(num);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        this.accessRight = new SimpleStringProperty(accessRight);
    }

    public static ArrayList<TableManageUsers> convert(ArrayList<Employee> employees) {
        ArrayList<TableManageUsers> result = new ArrayList<>();
        for (int i = 0, employeesSize = employees.size(); i < employeesSize; i++) {
            Employee employee = employees.get(i);
            TableManageUsers tableManageUsers = new TableManageUsers(
                    i + 1,
                    employee.getLogin(),
                    employee.getPassword(),
                    employee.getAccessRightName()
            );
            result.add(tableManageUsers);
        }
        return result;
    }

    public int getNum() {
        return num.get();
    }

    public void setNum(int num) {
        this.num.set(num);
    }

    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getAccessRight() {
        return accessRight.get();
    }

    public void setAccessRight(String accessRight) {
        this.accessRight.set(accessRight);
    }
}
