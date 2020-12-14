package com.company.db.impl;

import com.company.db.ConnectionDB;
import com.company.db.DBEmployee;
import com.company.models.Employee;
import com.company.utils.Parsers;
import com.mysql.cj.conf.ConnectionUrlParser;

import java.util.AbstractMap;
import java.util.ArrayList;

public class SQLEmployee implements DBEmployee {
    private static SQLEmployee instance;
    private ConnectionDB dbConnection;

    private final String selectDataStr = "SELECT id_empl, empl_login, empl_password, empl_name, empl_surname, " +
            "empl_passport_ser, empl_passport_num, empl_est_accred, empl_est_satisf, " +
            "id_access_right, access_right_name, id_bank, bank_name, id_department, department_name " +
            "FROM mydb.employees INNER JOIN mydb.access_rights INNER JOIN mydb.departments " +
            "INNER JOIN mydb.banks ON id_access_right = access_rights_id_access_right " +
            "AND id_department = departments_id_department AND id_bank = banks_id_bank ";

    private SQLEmployee() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLEmployee getInstance() {
        if (instance == null) {
            instance = new SQLEmployee();
        }
        return instance;
    }

    @Override
    public Employee find(Employee employee) {
        String str = selectDataStr + "WHERE empl_login='" + employee.getLogin() +
                "' AND empl_password='" + employee.getPassword() + "'";

        ArrayList<String[]> res = dbConnection.getArrayResult(str);
        if (res.size() == 0) {
            return null;
        }

        loadIntoEmployee(employee, res.get(0));

        return employee;
    }

    @Override
    public ArrayList<Employee> select() {
        ArrayList<String[]> res = dbConnection.getArrayResult(selectDataStr);
        if (res.size() == 0) {
            return null;
        }

        ArrayList<Employee> employees = new ArrayList<>();

        for (String[] result: res) {
            Employee employee = new Employee();
            loadIntoEmployee(employee, result);
            employees.add(employee);
        }

        return employees;
    }

    @Override
    public void insert(Employee employee) {
        String str = "INSERT INTO employees (empl_login, empl_password, empl_name, empl_surname, " +
                "empl_passport_ser, empl_passport_num, empl_est_accred, empl_est_satisf, " +
                "departments_id_department, access_rights_id_access_right) VALUES ('" +
                employee.getLogin() + "', '" +
                employee.getPassword() + "', '" +
                (employee.getName().equals("") ? "-" : employee.getName()) + "', '" +
                (employee.getSurname().equals("") ? "-" : employee.getSurname()) + "', '" +
                (employee.getPassportSer().equals("") ? "-" : employee.getPassportSer()) + "', " +
                employee.getPassportNum() + ", " +
                employee.getEstAccred() + ", " +
                employee.getEstSatisf() + ", " +
                (employee.getDepartmentId() == 0 ? 7 : employee.getDepartmentId()) + ", " +
                (employee.getAccessRightId() == 0 ? 2 : employee.getAccessRightId()) + ")";
        dbConnection.execute(str);
    }

    @Override
    public void delete(Employee employee) {
        String str = "DELETE FROM employees WHERE id_empl = " + employee.getId();
        dbConnection.execute(str);
    }

    @Override
    public void updateManage(Employee employee) {
        String str = "UPDATE employees SET " +
                "empl_login = '" + employee.getLogin() + "', " +
                "empl_password = '" + employee.getPassword() + "', " +
                "access_rights_id_access_right = " + employee.getAccessRightId() + " " +
                "WHERE id_empl = " + employee.getId();
        dbConnection.execute(str);
    }

    @Override
    public void update(Employee employee) {
        String str = "UPDATE employees SET " +
                "empl_name = '" + employee.getName() + "', " +
                "empl_surname = '" + employee.getSurname() + "', " +
                "empl_passport_ser = '" + employee.getPassportSer() + "', " +
                "empl_passport_num = " + employee.getPassportNum() + ", " +
                "empl_est_accred = " + employee.getEstAccred() + ", " +
                "empl_est_satisf = " + employee.getEstSatisf() + " " +
                "WHERE id_empl = " + employee.getId();
        dbConnection.execute(str);
    }

    @Override
    public void loadIntoEmployee(Employee employee, String[] result) {
        employee.setId(Parsers.integer(result[0]));
        employee.setLogin(result[1]);
        employee.setPassword(result[2]);
        employee.setName(result[3]);
        employee.setSurname(result[4]);
        employee.setPassportSer(result[5]);
        employee.setPassportNum(Parsers.integer(result[6]));
        employee.setEstAccred(Parsers.integer(result[7]));
        employee.setEstSatisf(Parsers.integer(result[8]));
        employee.setAccessRightId(Parsers.integer(result[9]));
        employee.setAccessRightName(result[10]);
        employee.setBankId(Parsers.integer(result[11]));
        employee.setBankName(result[12]);
        employee.setDepartmentId(Parsers.integer(result[13]));
        employee.setDepartmentName(result[14]);
    }

    @Override
    public ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> getEstAccred() {
        String str = "SELECT empl_est_accred, count(empl_est_accred) as count " +
                "FROM mydb.employees GROUP BY empl_est_accred;";

        ArrayList<String[]> resData = dbConnection.getArrayResult(str);
        if (resData.size() == 0) {
            return null;
        }

        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> res = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            res.add(new AbstractMap.SimpleEntry<>(i, 0));
        }

        for (String[] result: resData) {
            res.get(Parsers.integer(result[0])).setValue(Parsers.integer(result[1]));
        }

        return res;
    }

    @Override
    public ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> getEstSatisf() {
        String str = "SELECT empl_est_satisf, count(empl_est_satisf) as count " +
                "FROM mydb.employees GROUP BY empl_est_satisf;";

        ArrayList<String[]> resData = dbConnection.getArrayResult(str);
        if (resData.size() == 0) {
            return null;
        }

        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> res = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            res.add(new AbstractMap.SimpleEntry<>(i, 0));
        }

        for (String[] result: resData) {
            res.get(Parsers.integer(result[0])).setValue(Parsers.integer(result[1]));
        }

        return res;
    }
}
