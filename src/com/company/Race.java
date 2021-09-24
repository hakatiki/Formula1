package com.company;

import java.util.ArrayList;
import java.util.List;

public class Race {


    private String name;
    private String year;        // Could be int this is easier to implement rn
    private float multiplier;   // Score multiplier
    private int rank;           // This is the ..th race of the season

    private List<Result> results =  new ArrayList<Result>();
    private String fastestRacer;
    private String fastestTeam;


    public Race (List<String> race, List<List<String>> other){
        this.year = race.get(1);
        this.name = race.get(2);
        this.rank = Integer.valueOf(race.get(3));
        this.multiplier = Float.parseFloat(race.get(4));
        for (int i = 0; i < other.size();i++){
            List<String> current = other.get(i);
            if (current.get(0).equals("RESULT"))
                results.add(new Result(current));
            else if (current.get(0).equals("FASTEST")){
                fastestRacer = current.get(1);
                fastestTeam = current.get(2);
            }
        }
    }
    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public int getRank() {
        return rank;
    }

    public List<Result> getResults() {
        return results;
    }

    public String getFastestRacer() {
        return fastestRacer;
    }

    public String getFastestTeam() {
        return fastestTeam;
    }

}
