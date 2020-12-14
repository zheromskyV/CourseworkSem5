package com.company.models;

import java.io.Serializable;

public class Department implements Serializable {
    private int id = 0;
    private String name = "";
    private int emplAmnt = 0;

    private int bankId = 0;
    private String bankName = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmplAmnt() {
        return emplAmnt;
    }

    public void setEmplAmnt(int emplAmnt) {
        this.emplAmnt = emplAmnt;
    }
}
