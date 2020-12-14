package com.company.db.impl;

import com.company.db.ConnectionDB;
import com.company.db.DBVisitMark;
import com.company.models.VisitMark;
import com.company.utils.Parsers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class SQLVisitMark implements DBVisitMark {
    private static SQLVisitMark instance;
    private ConnectionDB dbConnection;

    private final String selectDataStrA = "SELECT id_mark, mark_date, mark_time, id_mark_type, mark_type, employees_id_empl, hours " +
            "FROM mydb.visit_marks INNER JOIN mydb.visit_mark_types ON visit_mark_types_id_mark_type = id_mark_type " +
            "WHERE employees_id_empl = ";
    private final String selectDataStrB = " ORDER BY mark_date;";

    private SQLVisitMark() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLVisitMark getInstance() {
        if (instance == null) {
            instance = new SQLVisitMark();
        }
        return instance;
    }

    @Override
    public ArrayList<VisitMark> select(int employeeId) {
        ArrayList<String[]> res = dbConnection.getArrayResult(
                selectDataStrA + employeeId + selectDataStrB);
        if (res.size() == 0) {
            return null;
        }

        ArrayList<VisitMark> visitMarks = new ArrayList<>();

        for (String[] result: res) {
            VisitMark visitMark = new VisitMark();
            loadIntoVisitMark(visitMark, result);
            visitMarks.add(visitMark);
        }

        return visitMarks;
    }

    @Override
    public void insert(VisitMark visitMark) {
        if (visitMark.getVisitMarkTypeId() == 2) {
            visitMark.setHours(8);
        }
        String str = "INSERT INTO visit_marks (mark_date, mark_time, visit_mark_types_id_mark_type, employees_id_empl, hours) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = dbConnection.getConnect().prepareStatement(str);
            statement.setDate(1, new java.sql.Date(visitMark.getDatetime().getTime()));
            statement.setTime(2, new Time(visitMark.getDatetime().getTime()));
            statement.setInt(3, visitMark.getVisitMarkTypeId());
            statement.setInt(4, visitMark.getEmployeeId());
            statement.setInt(5, visitMark.getHours());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void loadIntoVisitMark(VisitMark visitMark, String[] result) {
        visitMark.setId(Parsers.integer(result[0]));

        Date datetime = new Date();
        datetime.setYear(Parsers.integer(result[1].split("\\-")[0]) - 1900);
        datetime.setMonth(Parsers.integer(result[1].split("\\-")[1]) - 1);
        datetime.setDate(Parsers.integer(result[1].split("\\-")[2]));
        datetime.setHours(Integer.parseInt(result[2].split("\\:")[0]));
        datetime.setMinutes(Integer.parseInt(result[2].split("\\:")[1]));
        visitMark.setDatetime(datetime);

        visitMark.setVisitMarkTypeId(Parsers.integer(result[3]));
        visitMark.setVisitMarkTypeName(result[4]);
        visitMark.setEmployeeId(Parsers.integer(result[5]));
        visitMark.setHours(Parsers.integer(result[6]));
    }
}
