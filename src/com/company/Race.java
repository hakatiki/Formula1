package com.company;

import java.util.List;

public class Race {
    private String name;
    private String year; // Could be int this is easier to implement rn
    private float multiplier;
    private int rank;

    private List<Result> results;
    private Fastest fastest;

    public Race (List<String> race, List<List<String>> other){
        this.year = race.get(1);
        this.name = race.get(2);
        this.rank = Integer.getInteger(race.get(3));
        this.multiplier = Float.parseFloat(race.get(4));
        for (int i = 0; i < other.size();i++){
            List<String> current = other.get(i);
            if (current.get(0).equals("RESULT"))
                results.add(new Result(current));
            else
                fastest = new Fastest(current);
        }
    }

}
