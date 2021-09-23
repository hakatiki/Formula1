package com.company;

import java.util.List;

public class Fastest {
    private String racerName;
    private String teamName;

    public Fastest (List<String> list) {
        racerName = list.get(1);
        teamName = list.get(0);
    }
}
