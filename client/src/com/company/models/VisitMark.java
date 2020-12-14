package com.company.models;
import java.io.Serializable;
import java.util.Date;

public class VisitMark implements Serializable {
    private int id = 0;
    private Date datetime;
    private int visitMarkTypeId = 0;
    private String visitMarkTypeName = "";
    private int hours = 0;

    private int employeeId = 0;
    private String employeeSurname = "";
    private int departmentId = 0;
    private String departmentName = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public int getVisitMarkTypeId() {
        return visitMarkTypeId;
    }

    public void setVisitMarkTypeId(int visitMarkTypeId) {
        this.visitMarkTypeId = visitMarkTypeId;
    }

    public String getVisitMarkTypeName() {
        return visitMarkTypeName;
    }

    public void setVisitMarkTypeName(String visitMarkTypeName) {
        this.visitMarkTypeName = visitMarkTypeName;
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

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}