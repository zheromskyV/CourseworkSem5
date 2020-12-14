package com.company.db.impl;

import com.company.db.ConnectionDB;
import com.company.models.Department;
import com.company.utils.Parsers;

import java.util.ArrayList;

public class SQLDepartment {
    private static SQLDepartment instance;
    private ConnectionDB dbConnection;

    private final String selectDataStr = "SELECT id_department, department_name, COUNT(id_empl) AS amnt " +
            "FROM mydb.departments INNER JOIN mydb.employees ON id_department = departments_id_department " +
            "GROUP BY id_department ORDER BY amnt;";

    private SQLDepartment() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLDepartment getInstance() {
        if (instance == null) {
            instance = new SQLDepartment();
        }
        return instance;
    }

    public ArrayList<Department> select() {
        ArrayList<String[]> res = dbConnection.getArrayResult(selectDataStr);
        if (res.size() == 0) {
            return null;
        }

        ArrayList<Department> departments = new ArrayList<>();

        for (String[] result: res) {
            Department department = new Department();
            loadIntoDepartment(department, result);
            departments.add(department);
        }

        return departments;
    }

    public void loadIntoDepartment(Department department, String[] result) {
        department.setId(Parsers.integer(result[0]));
        department.setName(result[1]);
        department.setEmplAmnt(Parsers.integer(result[2]));
    }
}
