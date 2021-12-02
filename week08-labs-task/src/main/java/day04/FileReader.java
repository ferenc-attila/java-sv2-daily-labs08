package day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        System.out.println(fileReader.findSmallestTemperatureSpread("weather.dat"));
        System.out.println(fileReader.findSmallestDifference("football.dat"));
    }

    public int findSmallestTemperatureSpread(String filename) {
        List<String> contentOfFile = readFile(filename);
        List<Integer> spreads = getSpreads(contentOfFile);
        return getDayWithSmallestSpread(spreads) + 1;
    }

    public String findSmallestDifference(String filename) {
        List<String> contentOfFile = readFile(filename);
        return getTeam(contentOfFile);
    }

    private int getDayWithSmallestSpread(List<Integer> spreads) {
        int spread = Integer.MAX_VALUE;
        int day = 0;
        for (int i = 0; i < spreads.size(); i++) {
            if (spreads.get(i) < spread) {
                spread = spreads.get(i);
                day = i;
            }
        }
        return day;
    }

    private List<Integer> getSpreads(List<String> contentOfFile) {
        int minTemp;
        int maxTemp;
        List<Integer> spreads = new ArrayList<>();
        for (int i = 2; i < contentOfFile.size() - 1; i++) {
            maxTemp = (Integer.parseInt(contentOfFile.get(i).substring(6, 8).trim()));
            minTemp = (Integer.parseInt(contentOfFile.get(i).substring(12, 14).trim()));
            spreads.add(maxTemp - minTemp);
        }
        return spreads;
    }

    private String getTeam(List<String> contentOfFile) {
        int givenGoal;
        int receivedGoal;
        List<String> teams = new ArrayList<>();
        List<Integer> differences = new ArrayList<>();
        for (int i = 1; i < contentOfFile.size(); i++) {
            if (i == 18) {
                continue;
            }
            givenGoal = (Integer.parseInt(contentOfFile.get(i).substring(43, 45)));
            receivedGoal = (Integer.parseInt(contentOfFile.get(i).substring(50, 52)));
            teams.add(contentOfFile.get(i).substring(7, 23).trim());
            differences.add(Math.abs(givenGoal - receivedGoal));
        }
        int minDifference = Integer.MAX_VALUE;
        int teamIndex = -1;
        for (int i = 0; i < differences.size(); i++) {
            if (differences.get(i) < minDifference) {
                minDifference = differences.get(i);
                teamIndex = i;
            }
        }
        return teams.get(teamIndex);
    }

    private List<String> readFile(String filename) {
        try {
            return Files.readAllLines(Paths.get("src/main/resources/" + filename));
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Unable to  read file", ioe);
        }
    }
}
