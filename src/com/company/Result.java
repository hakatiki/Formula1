package com.company;

import java.util.List;

public class Result {


    private final String racerName;
    private final String teamName;
    private final int placement;


    public Result (List<String> list){
        this.placement =  Integer.parseInt(list.get(1));
        this.racerName =  list.get(2);
        this.teamName =  list.get(3);
    }

    public String getRacerName() {
        return racerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPlacement() {
        return placement;
    }
}
