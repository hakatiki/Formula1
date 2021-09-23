package com.company;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;

public class DataBase {

    private List<Race> races = new ArrayList<Race>();
    private List<MyQuery> queries = new ArrayList<MyQuery>();

    public int getQueriesCount(){
        return queries.size();
    }
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
                queries.add(new MyQuery(list.get(i), list.get(i+1)));
                i+=1;
            }
        }
    }
    public void executeQuery(int index){
        HashMap<String, Double> drivers = new HashMap<>();
        HashMap<String, Double> teams = new HashMap<>();
        MyQuery current = queries.get(index);
        List<Integer> pointSystem = current.getPoints();


        for (int i = 0; i < races.size(); i++) {
            Race currentRace = races.get(i);
            if (!currentRace.getYear().equals(current.getYear()))
                continue;
            if (current.getRank() < 0 ||
                    current.getRank() == currentRace.getRank()) { // This means that no race were specified!!

                float multi = currentRace.getMultiplier();
                List<Result> results = currentRace.getResults();
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
        System.out.println("Driver results:");
        System.out.println(drivers);
        System.out.println("Team results:");
        System.out.println(teams);
    }
}
