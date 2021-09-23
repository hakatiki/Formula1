package com.company;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private List<Race> races = new ArrayList<Race>();
    private List<MyQuery> queries = new ArrayList<MyQuery>();


    public DataBase(String path){
        Input input = new Input();
        List<List<String>> list = input.readCSV(path);
        if (input.validate(list)){
            System.out.println("Input is valid :))");
        }
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).get(0).equals("RACE")) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(j).get(0).equals("FINISH")) {
                        races.add(new Race(list.get(i), list.subList(i + 1, j)));
                        i = j;
                        break;
                    }
                }
            }
            else if (list.get(i).get(0).equals("QUERY")){

            }
        }
    }
}
