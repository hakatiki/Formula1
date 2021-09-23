package com.company;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Input {
    // I could use an array but with a hashmap its more readable imo
    private static final HashMap<String, List<String>> transitions = new HashMap<String, List<String>>(){{
        put("START_STATE", Arrays.asList("RACE"));
        put("RACE", Arrays.asList("RESULT", "FASTEST", "FINISH"));
        put("RESULT", Arrays.asList("RESULT", "FINISH", "FASTEST"));
        put("FASTEST", Arrays.asList("FINISH"));
        put("FINISH", Arrays.asList("RACE", "QUERY"));
        put("QUERY", Arrays.asList("POINT"));
        put("POINT", Arrays.asList("QUERY", "EXIT"));
        put("EXIT", Arrays.asList());

    }};
    private static final HashMap<String, Integer> argCount = new HashMap<String, Integer>(){{
        put("START_STATE", 1);
        put("RACE", 5);
        put("RESULT", 4);
        put("FASTEST", 3);
        put("FINISH", 1);
        put("QUERY",2);
        put("POINT", 2);
        put("EXIT", 1);

    }};

    public List<List<String>> readCSV(String path){
        List<List<String>> list = new LinkedList<>(); // Since we will add a lot of stuff -> fester
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(path));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(";");
                list.add(new LinkedList<String>(Arrays.asList(data)));
            }
            csvReader.close();
        } catch (FileNotFoundException e) {
            // TODO: ADD OUTPUT FOR THIS
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Boolean validate(List<List<String>> list){
        String state = "START_STATE";
        int resultCounter = 0;
        for (int i = 0; i < list.size(); i++){
            List<String> row = list.get(i);
            ///////////////EMPTY ROW///////////////////////
            if (row.get(0).equals(""))
                continue;
            ///////////////TRANSITIONS/////////////////////
            String command = row.get(0);
            List<String> validCommands = transitions.get(state);
            if (!validCommands.contains(command)){
                // TODO: ERROR Invalid command transition
                System.out.println("ERROR Invalid command transition");
                System.out.println(state +" -> "+ command + " is invalid!");
                System.out.println( "The error is on line: " + String.valueOf(i+1));
                return false;
            }

            ///////////////FINNISH///////////////////////
            if (command.equals("FINISH")){
                if (command.equals(state)){
                    // TODO: ERROR more then one finnished
                    System.out.println("ERROR more then one FINISH");
                    System.out.println( "The error is on line: " + String.valueOf(i+1));
                    return false;
                }
                if (resultCounter < 10){
                    // TODO: ERROR not enough results
                    System.out.println("ERROR not enough RESULTs before FINNISH command");
                    System.out.println( "The error is on line: " + String.valueOf(i+1));
                    return false;
                }
                else
                    resultCounter = 0;
            }
            ///////////////10 RESULT///////////////////////
            resultCounter += command.equals("RESULT")?1:0;

            ///////////////ARG COUNT///////////////////////
            if (argCount.get(command)!= row.size()){
                if (!(command.equals("QUERY")&& row.size()==3)){
                    // TODO: ERROR not enough arguments
                    System.out.println("ERROR not enough arguments");
                    System.out.println( "The error is on line: " + String.valueOf(i+1));
                    return false;
                }

            }

            state = command;
        }
        return true;
    }


}
