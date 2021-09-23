package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataBase database = new DataBase("src/Data/input-hf.csv");

        for (int i = 0; i < database.getQueriesCount(); i++)
            database.executeQuery(i);
    }
}
