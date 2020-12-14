package com.company.utils;

import com.company.models.Department;
import com.company.models.Employee;
import com.company.models.Salary;
import com.company.models.VisitMark;

import java.util.ArrayList;

public class Report {
    public static String getSalariesReport(ArrayList<Salary> salaries) {
        String report = "";

        for (Salary s: salaries) {
            report += "Фамилия: " + s.getEmployeeSurname() + ", ";
            report += "Отдел: " + s.getDepartmentName() + ", ";
            report += "Ставка: " + s.getSalary() + ", ";
            report += "Часы: " + s.getWorkHours() + ", ";
            report += "Коэф.: " + s.getKoef() + ", ";
            report += "З/П: " + s.getMonthSalary() + ".\n";
        }

        return report;
    }

    public static String getEmployeesReport(ArrayList<Employee> employees) {
        String report = "";

        for (Employee e: employees) {
            report += "Имя: " + e.getName() + ", ";
            report += "Фамилия: " + e.getSurname() + ", ";
            report += "Паспорт: " + e.getPassportSer() + e.getPassportNum() + ", ";
            report += "Отдел: " + e.getDepartmentName() + ", ";
            report += "Аккред.: " + e.getEstAccred() + ", ";
            report += "Удовл.: " + e.getEstSatisf() + ".\n";
        }

        return report;
    }

    public static String getDepartmentsReport(ArrayList<Department> departments) {
        String report = "";

        for (Department d: departments) {
            report += "Название: " + d.getName() + ", ";
            report += "Кол-во сотрудников: " + d.getEmplAmnt() + ".\n";
        }

        return report;
    }

    public static String getVisitMarksPerort(ArrayList<VisitMark> visitMarks) {
        String report = "";

        for (VisitMark vm: visitMarks) {
            report += "[ " + vm.getVisitMarkTypeName() + " ] ";
            report += "Дата/время: " + vm.getDatetime().toString() + "\n";
        }

        return report;
    }

    public static String getSalaryReport(Salary salary) {
        String report = "";

        report += "Ставка: " + salary.getSalary() + ", ";
        report += "Коэф.: " + salary.getKoef() + ", ";
        report += "Часы: " + salary.getWorkHours() + ", ";
        report += "З/П: " + salary.getMonthSalary() + ".\n";

        return report;
    }
}
