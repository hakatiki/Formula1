package com.company;

import java.util.List;

public class Result {
    private String racerName;
    private String teamName;
    private int placement;


    public Result (List<String> list){
        this.placement =  Integer.getInteger(list.get(1));
        this.racerName =  list.get(2);
        this.teamName =  list.get(3);
    }
}
