package com.company;


import javafx.util.Pair;

import java.util.*;

public class DataBase {

    private List<Race> races = new ArrayList<Race>();
    private List<MyQuery> queries = new ArrayList<MyQuery>();

    public DataBase(String path){
        Input input = new Input();
        List<List<String>> list = input.readCSV(path);
        if (input.validate(list)){
            System.out.println("Input is valid :))\n");

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
                    queries.add(new MyQuery(list.get(i), list.get(i+1)));
                    i+=1;
                }
            }
        }
        else{
            System.out.println("Pretty please fix your mistakes :((\n");
        }

    }
    public void executeQuery(int index){
        HashMap<String, Double> drivers = new HashMap<>();
        HashMap<String, Double> teams = new HashMap<>();
        MyQuery current = queries.get(index);
        List<Integer> pointSystem = current.getPoints();


        for (int i = 0; i < races.size(); i++) {
            final Race currentRace = races.get(i);
            final String currentYear = current.getYear();

            if (!currentRace.getYear().equals(currentYear))
                continue;
            if (current.getYearQuery() || current.getRank() == currentRace.getRank()) { // This means that no race were specified!!

                float multi = currentRace.getMultiplier();
                final List<Result> results = currentRace.getResults();
                for (Result r : results) {
                    if (!drivers.containsKey(r.getRacerName()))
                        drivers.put(r.getRacerName(), 0.0);
                    if (!teams.containsKey(r.getTeamName()))
                        teams.put(r.getTeamName(), 0.0);

                    Double score = (double) (r.getPlacement() - 1 < pointSystem.size()
                            ? pointSystem.get(r.getPlacement() - 1) * multi : 0);
                    Double driverScore = score + drivers.get(r.getRacerName());
                    Double teamScore = score + teams.get(r.getTeamName());

                    drivers.put(r.getRacerName(), driverScore);
                    teams.put(r.getTeamName(), teamScore);
                }
            }
        }
        prettyPrintResults(drivers, teams);
    }


    // Sort a hashmap by value and return them in pairs of <value, key>, in  ascending order by value
    private List<Pair<Integer, String>> sortMap(HashMap<String, Double> map){
        ArrayList<Pair<Integer, String>> sorted = new ArrayList<>();
        map.forEach((k,v) -> sorted.add(new Pair((int)Math.round(v),
                k)));
        Collections.sort(sorted, new Comparator<Pair<Integer, String>>() {
            @Override
            public int compare(Pair<Integer, String> lhs, Pair<Integer, String> rhs) {
                return lhs.getKey() > rhs.getKey()?-1:1;
            }
        });
        return sorted;
    }
    // Printing stuff...
    private void prettyPrintResults(HashMap<String, Double> drivers, HashMap<String, Double> teams ){
        if (drivers.size() == 0)
            System.out.println("No results for the query:(");
        else{
            System.out.println("Driver results:");
            prettyPrint(sortMap(drivers));
        }
        if (teams.size() == 0)
            System.out.println("No results for the query:(");
        else{
            System.out.println("Team results:");
            prettyPrint(sortMap(teams));
        }
        System.out.println("\n");
    }
    private void prettyPrint(List<Pair<Integer, String>> list){
        for (int i = 1 ; i < list.size()+1;i++){
            System.out.println(i + ": " + list.get(i-1).getValue() + "\tscore: " + list.get(i-1).getKey());
        }
    }
    // Pointless func
    public int getQueriesCount(){
        return queries.size();
    }
}