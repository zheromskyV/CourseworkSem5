package com.company.models;

import java.io.Serializable;

public class Employee implements Serializable {
    private int id = 0;
    private String login = "";
    private String password = "";
    private String name = "";
    private String surname = "";
    private String passportSer = "";
    private int passportNum = 0;
    private int estAccred = 0;
    private int estSatisf = 0;

    private int bankId = 0;
    private String bankName = "";
    private int departmentId = 0;
    private String departmentName = "";
    private int accessRightId = 0;
    private String accessRightName = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassportSer() {
        return passportSer;
    }

    public void setPassportSer(String passportSer) {
        this.passportSer = passportSer;
    }

    public int getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(int passportNum) {
        this.passportNum = passportNum;
    }

    public int getEstAccred() {
        return estAccred;
    }

    public void setEstAccred(int estAccred) {
        this.estAccred = estAccred;
    }

    public int getEstSatisf() {
        return estSatisf;
    }

    public void setEstSatisf(int estSatisf) {
        this.estSatisf = estSatisf;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public int getAccessRightId() {
        return accessRightId;
    }

    public void setAccessRightId(int accessRightId) {
        this.accessRightId = accessRightId;
    }

    public String getAccessRightName() {
        return accessRightName;
    }

    public void setAccessRightName(String accessRightName) {
        this.accessRightName = accessRightName;
    }
}
