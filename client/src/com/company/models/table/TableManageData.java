package com.company.models.table;

import com.company.models.Employee;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class TableManageData {
    private SimpleIntegerProperty num;
    private SimpleStringProperty name;
    private SimpleStringProperty surname;
    private SimpleStringProperty passport;
    private SimpleStringProperty department;
    private SimpleStringProperty bank;
    private SimpleIntegerProperty estAccred;
    private SimpleIntegerProperty estSatisf;

    public TableManageData(int num, String name, String surname, String passport, String department, String bank,
                           int estAccred, int estSatisf) {
        this.num = new SimpleIntegerProperty(num);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.passport = new SimpleStringProperty(passport);
        this.department = new SimpleStringProperty(department);
        this.bank = new SimpleStringProperty(bank);
        this.estAccred = new SimpleIntegerProperty(estAccred);
        this.estSatisf = new SimpleIntegerProperty(estSatisf);
    }

    public static ArrayList<TableManageData> convert(ArrayList<Employee> employees) {
        ArrayList<TableManageData> result = new ArrayList<>();
        for (int i = 0, employeesSize = employees.size(); i < employeesSize; i++) {
            Employee employee = employees.get(i);
            TableManageData tableManageData = new TableManageData(
                    i + 1,
                    employee.getName(),
                    employee.getSurname(),
                    employee.getPassportSer() + String.valueOf(employee.getPassportNum()),
                    employee.getDepartmentName(),
                    employee.getBankName(),
                    employee.getEstAccred(),
                    employee.getEstSatisf()
            );
            result.add(tableManageData);
        }
        return result;
    }

    public int getNum() {
        return num.get();
    }

    public SimpleIntegerProperty numProperty() {
        return num;
    }

    public void setNum(int num) {
        this.num.set(num);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getPassport() {
        return passport.get();
    }

    public SimpleStringProperty passportProperty() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport.set(passport);
    }

    public String getDepartment() {
        return department.get();
    }

    public SimpleStringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public String getBank() {
        return bank.get();
    }

    public SimpleStringProperty bankProperty() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank.set(bank);
    }

    public int getEstAccred() {
        return estAccred.get();
    }

    public SimpleIntegerProperty estAccredProperty() {
        return estAccred;
    }

    public void setEstAccred(int estAccred) {
        this.estAccred.set(estAccred);
    }

    public int getEstSatisf() {
        return estSatisf.get();
    }

    public SimpleIntegerProperty estSatisfProperty() {
        return estSatisf;
    }

    public void setEstSatisf(int estSatisf) {
        this.estSatisf.set(estSatisf);
    }
}
