package com.company.models.table;

import com.company.models.Salary;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class TableSalary {
    private SimpleIntegerProperty num;
    private SimpleStringProperty surname;
    private SimpleStringProperty department;
    private SimpleIntegerProperty estAccred;
    private SimpleIntegerProperty estSatisf;
    private SimpleFloatProperty koef;
    private SimpleIntegerProperty hours;
    private SimpleFloatProperty salary;
    private SimpleFloatProperty monthSalary;

    public TableSalary(int num, String surname, String department, int estAccred, int estSatisf, float koef, int hours,
                       float salary, float monthSalary) {
        this.num = new SimpleIntegerProperty(num);
        this.surname = new SimpleStringProperty(surname);
        this.department = new SimpleStringProperty(department);
        this.estAccred = new SimpleIntegerProperty(estAccred);
        this.estSatisf = new SimpleIntegerProperty(estSatisf);
        this.koef = new SimpleFloatProperty(koef);
        this.hours = new SimpleIntegerProperty(hours);
        this.salary = new SimpleFloatProperty(salary);
        this.monthSalary = new SimpleFloatProperty(monthSalary);
    }

    public static ArrayList<TableSalary> convert(ArrayList<Salary> salaries) {
        ArrayList<TableSalary> result = new ArrayList<>();
        for (int i = 0, employeesSize = salaries.size(); i < employeesSize; i++) {
            Salary salary = salaries.get(i);
            TableSalary tableSalary = new TableSalary(
                    i + 1,
                    salary.getEmployeeSurname(),
                    salary.getDepartmentName(),
                    salary.getEmployeeEstAccred(),
                    salary.getEmployeeEstSatisf(),
                    salary.getKoef(),
                    salary.getWorkHours(),
                    salary.getSalary(),
                    salary.getMonthSalary()
            );
            result.add(tableSalary);
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

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
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

    public float getKoef() {
        return koef.get();
    }

    public SimpleFloatProperty koefProperty() {
        return koef;
    }

    public void setKoef(float koef) {
        this.koef.set(koef);
    }

    public int getHours() {
        return hours.get();
    }

    public SimpleIntegerProperty hoursProperty() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours.set(hours);
    }

    public float getSalary() {
        return salary.get();
    }

    public SimpleFloatProperty salaryProperty() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary.set(salary);
    }

    public float getMonthSalary() {
        return monthSalary.get();
    }

    public SimpleFloatProperty monthSalaryProperty() {
        return monthSalary;
    }

    public void setMonthSalary(float monthSalary) {
        this.monthSalary.set(monthSalary);
    }
}
