package com.company.db;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionDB {
    private static ConnectionDB instance;

    protected Connection connect;
    protected Statement statement;
//    private ResultSet resultSet;
//    ArrayList<String[]> masResult;

    public ConnectionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb?useUnicode=true&serverTimezone=Europe/Minsk",
                    "root",
                    "root");
            statement = connect.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Problem with JDBC Driver");
            e.printStackTrace();
        }
    }

//    public void setResultSet(String str) {
//        try {
//            String select = str;
//            resultSet = statement.executeQuery(select);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void execute(String query) {
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionDB getInstance() {
        if (instance == null) {
            instance = new ConnectionDB();
        }

        return instance;
    }

    public ArrayList<String[]> getArrayResult(String str) {
        ArrayList<String[]> masResult = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(str);
            int count = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                String[] arrayString = new String[count];
                for (int i = 1; i <= count; i++) {
                    arrayString[i - 1] = resultSet.getString(i);
                }

                masResult.add(arrayString);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return masResult;
    }

    public void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
}
