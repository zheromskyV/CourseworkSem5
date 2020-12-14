package com.company.models;

import java.io.Serializable;

public class VisitMarkType implements Serializable {
    public static final String IN = "Пришел";
    public static final String OUT = "Ушел";

    private int id = 0;
    private boolean typeIsIN = true;
    private String typeName = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return typeIsIN ? IN : OUT;
    }

    public void setType(String type) {
        typeIsIN = type.equals(IN);
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
