package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MyQuery {


    private final String year;
    private int rank = invalidMonth; // Something invalid but easy to spot



    private Boolean yearQuery = false;
    private final String system;
    private List<Integer> points = new ArrayList<Integer>();

    private static final int invalidMonth = -1;
    private static final HashMap<String, List<Integer>> pointSystems = new HashMap<String, List<Integer>>(){{
        put("CLASSIC", Arrays.asList(10, 6, 4, 3, 2, 1));
        put("MODERN", Arrays.asList(10, 8, 6, 5, 4, 3, 2, 1));
        put("NEW", Arrays.asList(25, 18, 15, 12, 10, 8, 6, 4, 2, 1));
        put("PRESENT", Arrays.asList(25, 18, 15, 12, 10, 8, 6, 4, 2, 1));
    }};

    public MyQuery(List<String> query, List<String> point){
        this.year = query.get(1);
        this.yearQuery = query.size() != 3;
        this.rank = !this.yearQuery? Integer.parseInt(query.get(2)): invalidMonth;
        this.system = point.get(1);
        this.points = pointSystems.get(this.system);
    }


    public String getYear() {
        return year;
    }

    public int getRank() {
        return rank;
    }

    public String getSystem() {
        return system;
    }

    public List<Integer> getPoints() {
        return points;
    }

    public Boolean getYearQuery() {
        return yearQuery;
    }
}
