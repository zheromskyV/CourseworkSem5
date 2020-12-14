package com.company.models;

import java.io.Serializable;

public class Salary implements Serializable {
    private int id = 0;
    private float salary = 0;
    private float monthSalary = 0;

    private int employeeId = 0;
    private String employeeSurname = "";
    private int employeeEstAccred = 0;
    private int employeeEstSatisf = 0;
    private float koef = 0;
    private String departmentName = "";
    private int workHours = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getEmployeeEstAccred() {
        return employeeEstAccred;
    }

    public void setEmployeeEstAccred(int employeeEstAccred) {
        this.employeeEstAccred = employeeEstAccred;
    }

    public int getEmployeeEstSatisf() {
        return employeeEstSatisf;
    }

    public void setEmployeeEstSatisf(int employeeEstSatisf) {
        this.employeeEstSatisf = employeeEstSatisf;
    }

    public float getKoef() {
        return koef;
    }

    public void setKoef(float koef) {
        this.koef = koef;
    }

    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }

    public float getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(float monthSalary) {
        this.monthSalary = monthSalary;
    }

    public void calculateKoef() {
        koef = 1f + ((Float.valueOf(employeeEstAccred) / 2f) + (Float.valueOf(employeeEstSatisf) / 4f)) / 10f;
    }

    public void calculateMonthSalary() {
        monthSalary = salary * koef * Float.valueOf(workHours);
    }
}
