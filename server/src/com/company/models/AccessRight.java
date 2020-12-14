package com.company.models;

import java.io.Serializable;

public class AccessRight implements Serializable {
    public static final String USER = "Пользовтаель";
    public static final String ADMIN = "Администратор";

    private int id = 0;
    private String name = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
