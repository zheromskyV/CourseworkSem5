package com.company.db.impl;

import com.company.db.ConnectionDB;
import com.company.db.DBSalary;
import com.company.models.Salary;
import com.company.utils.Parsers;

import java.util.ArrayList;

public class SQLSalary implements DBSalary {
    private static SQLSalary instance;
    private ConnectionDB dbConnection;

    private final String selectDataStrA = "SELECT id_salary, salary, id_empl, empl_surname, empl_est_accred, " +
            "empl_est_satisf, department_name, (select sum(hours) from mydb.visit_marks where mydb.visit_marks.employees_id_empl = id_empl) as hours FROM mydb.salaries INNER JOIN mydb.employees " +
            "INNER JOIN mydb.departments JOIN mydb.visit_marks INNER JOIN mydb.visit_mark_types " +
            "ON id_department = departments_id_department AND id_mark_type = visit_mark_types_id_mark_type " +
            "AND mydb.salaries.employees_id_empl = id_empl " +
            "WHERE mark_date >= date(NOW() - INTERVAL 1 MONTH) ";
    private final String selectDataStrB = "GROUP BY id_empl ORDER BY id_empl;";

    private SQLSalary() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLSalary getInstance() {
        if (instance == null) {
            instance = new SQLSalary();
        }
        return instance;
    }

    @Override
    public ArrayList<Salary> select() {
        ArrayList<String[]> res = dbConnection.getArrayResult(selectDataStrA + selectDataStrB);
        if (res.size() == 0) {
            return null;
        }

        ArrayList<Salary> salaries = new ArrayList<>();
        for (String[] result: res) {
            Salary salary = new Salary();
            loadIntoSalary(salary, result);
            salaries.add(salary);
        }

        return salaries;
    }

    @Override
    public Salary find(Salary salary) {
        String str = selectDataStrA + "AND id_empl= " + salary.getEmployeeId() + " " + selectDataStrB;

        ArrayList<String[]> res = dbConnection.getArrayResult(str);
        if (res.size() == 0) {
            return null;
        }

        loadIntoSalary(salary, res.get(0));

        return salary;
    }

    @Override
    public void update(Salary salary) {
        String str = "UPDATE salaries SET " +
                "salary = " + salary.getSalary() + " " +
                "WHERE id_salary = " + salary.getId();
        dbConnection.execute(str);
    }

    @Override
    public void loadIntoSalary(Salary salary, String[] result) {
        salary.setId(Parsers.integer(result[0]));
        salary.setSalary(Parsers.float_(result[1]));
        salary.setEmployeeId(Parsers.integer(result[2]));
        salary.setEmployeeSurname(result[3]);
        salary.setEmployeeEstAccred(Parsers.integer(result[4]));
        salary.setEmployeeEstSatisf(Parsers.integer(result[5]));
        salary.setDepartmentName(result[6]);
        salary.setWorkHours(Parsers.integer(result[7]));

        salary.calculateKoef();
        salary.calculateMonthSalary();
    }
}
