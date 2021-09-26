package com.company;

import java.io.*;
import java.util.*;

public class Input {
    private static final HashMap<String, List<String>> transitions = new HashMap<String, List<String>>(){{
        put("START_STATE",  Arrays.asList("RACE"));
        put("RACE",         Arrays.asList("RESULT", "FASTEST", "FINISH"));
        put("RESULT",       Arrays.asList("RESULT", "FINISH", "FASTEST"));
        put("FASTEST",      Arrays.asList("FINISH"));
        put("FINISH",       Arrays.asList("RACE", "QUERY"));
        put("QUERY",        Arrays.asList("POINT"));
        put("POINT",        Arrays.asList("QUERY", "EXIT"));
        put("EXIT",         Arrays.asList());

    }};
    private static final HashMap<String, List<Integer>> argCount = new HashMap<String, List<Integer>>(){{
        put("START_STATE",  Arrays.asList(1));
        put("RACE",         Arrays.asList(5));
        put("RESULT",       Arrays.asList(4));
        put("FASTEST",      Arrays.asList(3));
        put("FINISH",       Arrays.asList(1));
        put("QUERY",        Arrays.asList(2,3));
        put("POINT",        Arrays.asList(2));
        put("EXIT",         Arrays.asList(1));

    }};
    private static final List<String> multipliers = new ArrayList<String>() {
        {
            add("0");
            add("1");
            add("0.5");
            add("2");
        }
    };
    public List<List<String>> readCSV(String path){
        List<List<String>> list = new LinkedList<>(); // Since we will add a lot of stuff -> fester
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(path));
            String row;
            while ((row = csvReader.readLine()) != null) {
                if (row.equals(""))
                    continue;
                // I could use an array but with a hashmap its more readable imo
                String seperator = ";";
                String[] data = row.split(seperator);
                list.add(new LinkedList<String>(Arrays.asList(data)));
            }
            csvReader.close();
        } catch (IOException e) {
            System.out.println("Please give me a valid path:(\n");
        }
        return list;
    }
    public Boolean validate(List<List<String>> list){
        String state = "START_STATE";
        int resultCounter = 0;
        for (int i = 0; i < list.size(); i++) {
            List<String> row = list.get(i);
            ///////////////EMPTY ROW///////////////////////
            if (row.get(0).equals(""))
                continue;
            ///////////////TRANSITIONS/////////////////////
            String command = row.get(0);
            List<String> validCommands = transitions.get(state);
            if (!validCommands.contains(command)) {
                System.out.println("ERROR Invalid command transition. These commands can never follow each other:");
                System.out.println(state + " -> " + command + " is invalid!");
                System.out.println("The error is on line: " + (i + 1));
                return false;
            }

            ///////////////FINNISH///////////////////////
            if (command.equals("FINISH")) {
                if (command.equals(state)) {
                    System.out.println("ERROR more then one FINISH");
                    System.out.println("The error is on line: " + (i + 1));
                    return false;
                }
                if (resultCounter < 10) {
                    System.out.println("ERROR not enough RESULTs before FINNISH command");
                    System.out.println("The error is on line: " + (i + 1));
                    return false;
                } else
                    resultCounter = 0;
            }
            ///////////////10 RESULT///////////////////////
            resultCounter += command.equals("RESULT") ? 1 : 0;

            ///////////////ARG COUNT///////////////////////
            if (!argCount.get(command).contains(row.size())) {
                System.out.println("ERROR not enough arguments");
                System.out.println("The error is on line: " + (i + 1));
                return false;
            }
            ///////////////MULTIPLIER VALIDATION//////////
            if (row.get(0).equals("RACE")){
                String multiplier = row.get(4);
                if (!multipliers.contains(multiplier)){
                    System.out.println("ERROR invalid multiplier");
                    System.out.println("The error is on line: " + (i + 1));
                    return false;
                }
            }
            state = command;
        }
        return true;
    }


}
