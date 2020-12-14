package com.company.server;

import com.company.db.impl.*;
import com.company.models.*;
import com.company.utils.Report;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;

public class Worker implements Runnable {
    protected Socket clientSocket;
    ObjectInputStream sois;
    ObjectOutputStream soos;

    public Worker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            soos = new ObjectOutputStream(clientSocket.getOutputStream());
            sois = new ObjectInputStream(clientSocket.getInputStream());

            boolean isRunning = true;
            while (isRunning) {
                String choice = sois.readObject().toString();
                System.out.println(choice);
                switch (choice) {
                    case "authorize" -> {
                        Employee employee = (Employee) sois.readObject();

                        SQLEmployee sqlEmployee = SQLEmployee.getInstance();
                        Employee res = sqlEmployee.find(employee);

                        if (res == null) {
                            soos.writeObject("no");
                        } else {
                            boolean isAdmin = res.getAccessRightName().equals(AccessRight.ADMIN);
                            soos.writeObject(isAdmin ? AccessRight.ADMIN : AccessRight.USER);
                            soos.writeObject(res);
                        }
                    }
                    case "getUser" -> {
                        Employee employee = (Employee) sois.readObject();

                        SQLEmployee sqlEmployee = SQLEmployee.getInstance();
                        Employee res = sqlEmployee.find(employee);

                        soos.writeObject(res);
                    }
                    case "getUsers" -> {
                        SQLEmployee sqlEmployee = SQLEmployee.getInstance();
                        ArrayList<Employee> employees = sqlEmployee.select();

                        soos.writeObject(employees);
                    }
                    case "addUser" -> {
                        Employee employee = (Employee) sois.readObject();

                        SQLEmployee sqlEmployee = SQLEmployee.getInstance();
                        sqlEmployee.insert(employee);
                    }
                    case "deleteUser" -> {
                        Employee employee = (Employee) sois.readObject();

                        SQLEmployee sqlEmployee = SQLEmployee.getInstance();
                        sqlEmployee.delete(employee);
                    }
                    case "updateUserManage" -> {
                        Employee employee = (Employee) sois.readObject();

                        SQLEmployee sqlEmployee = SQLEmployee.getInstance();
                        sqlEmployee.updateManage(employee);
                    }
                    case "updateUserData" -> {
                        Employee employee = (Employee) sois.readObject();

                        SQLEmployee sqlEmployee = SQLEmployee.getInstance();
                        sqlEmployee.update(employee);
                    }
                    case "getSalaries" -> {
                        SQLSalary sqlSalary = SQLSalary.getInstance();
                        ArrayList<Salary> salaries = sqlSalary.select();

                        soos.writeObject(salaries);
                    }
                    case "getSalary" -> {
                        Salary salary = (Salary) sois.readObject();

                        SQLSalary sqlSalary = SQLSalary.getInstance();
                        Salary res = sqlSalary.find(salary);

                        soos.writeObject(res);
                    }
                    case "updateSalary" -> {
                        Salary salary = (Salary) sois.readObject();

                        SQLSalary sqlSalary = SQLSalary.getInstance();
                        sqlSalary.update(salary);
                    }
                    case "getReport" -> {
                        String type = sois.readObject().toString();
                        System.out.println("  " + type);
                        switch (type) {
                            case "employees" -> {
                                SQLEmployee sqlEmployee = SQLEmployee.getInstance();
                                soos.writeObject(Report.getEmployeesReport(sqlEmployee.select()));
                            }
                            case "salaries" -> {
                                SQLSalary sqlSalary = SQLSalary.getInstance();
                                soos.writeObject(Report.getSalariesReport(sqlSalary.select()));
                            }
                            case "departments" -> {
                                SQLDepartment sqlDepartment = SQLDepartment.getInstance();
                                soos.writeObject(Report.getDepartmentsReport(sqlDepartment.select()));
                            }
                            case "visitMarks" -> {
                                Employee employee = (Employee) sois.readObject();

                                SQLVisitMark sqlVisitMark = SQLVisitMark.getInstance();
                                soos.writeObject(Report.getVisitMarksPerort(sqlVisitMark.select(employee.getId())));
                            }
                            case "salary" -> {
                                Employee employee = (Employee) sois.readObject();
                                Salary s = new Salary();
                                s.setEmployeeId(employee.getId());

                                SQLSalary sqlSalary = SQLSalary.getInstance();
                                soos.writeObject(Report.getSalaryReport(sqlSalary.find(s)));
                            }
                        }
                    }
                    case "getChart" -> {
                        String type = sois.readObject().toString();
                        System.out.println("  " + type);
                        switch (type) {
                            case "accred" -> {
                                SQLEmployee sqlEmployee = SQLEmployee.getInstance();
                                soos.writeObject(sqlEmployee.getEstAccred());
                            }
                            case "satisf" -> {
                                SQLEmployee sqlEmployee = SQLEmployee.getInstance();
                                soos.writeObject(sqlEmployee.getEstSatisf());
                            }
                            case "departments" -> {
                                SQLDepartment sqlDepartment = SQLDepartment.getInstance();
                                ArrayList<Department> departments = sqlDepartment.select();

                                ArrayList<AbstractMap.SimpleEntry<String, Integer>> data = new ArrayList<>();
                                for (Department d: departments) {
                                    data.add(new AbstractMap.SimpleEntry<String, Integer>(
                                            d.getName(), d.getEmplAmnt()));
                                }

                                soos.writeObject(data);
                            }
                            case "visitMarks" -> {
                                Employee employee = (Employee) sois.readObject();

                                SQLVisitMark sqlVisitMark = SQLVisitMark.getInstance();
                                ArrayList<VisitMark> visitMarks = sqlVisitMark.select(employee.getId());

                                if (visitMarks.size() != 0) {
                                    ArrayList<AbstractMap.SimpleEntry<String, Integer>> data = new ArrayList<>();
                                    for (VisitMark vm: visitMarks) {
                                        if (vm.getVisitMarkTypeId() == 2) {
                                            Date datetime = vm.getDatetime();
                                            data.add(new AbstractMap.SimpleEntry<String, Integer>(
                                                    datetime.getDate() + "-" + (datetime.getMonth() + 1) +
                                                            "-" + (1900 + datetime.getYear()),
                                                    vm.getHours()
                                            ));
                                        }
                                    }

                                    soos.writeObject(data);
                                }
                            }
                        }
                    }
                    case "addMark" -> {
                        SQLVisitMark sqlVisitMark = SQLVisitMark.getInstance();
                        VisitMark visitMark = (VisitMark) sois.readObject();

                        sqlVisitMark.insert(visitMark);
                    }
                    case "exit" -> {
                        soos.close();
                        sois.close();
                        System.out.println("Клиент " + clientSocket.getInetAddress().toString() + "отключен");
                        isRunning = false;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Клиент отключен");
        }
    }
}