package com.company.db;

import com.company.models.Salary;

import java.util.ArrayList;

public interface DBSalary {
    ArrayList<Salary> select();

    Salary find(Salary salary);

    void update(Salary salary);

    void loadIntoSalary(Salary salary, String[] result);
}
