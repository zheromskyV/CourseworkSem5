package com.company.db;

import com.company.models.VisitMark;
import java.util.ArrayList;

public interface DBVisitMark {
    ArrayList<VisitMark> select(int employeeId);

    void insert(VisitMark visitMark);

    void loadIntoVisitMark(VisitMark visitMark, String[] result);
}
