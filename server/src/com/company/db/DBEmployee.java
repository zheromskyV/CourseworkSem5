package com.company.db;

import com.company.models.Employee;

import java.util.AbstractMap;
import java.util.ArrayList;

public interface DBEmployee {
    Employee find(Employee employee);

    ArrayList<Employee> select();

    void insert(Employee employee);

    void delete(Employee employee);

    void updateManage(Employee employee);

    void update(Employee employee);

    void loadIntoEmployee(Employee employee, String[] result);

    ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> getEstAccred();

    ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> getEstSatisf();
}
