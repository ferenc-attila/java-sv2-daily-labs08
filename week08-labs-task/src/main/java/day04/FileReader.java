package day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        System.out.println(fileReader.findSmallestTemperatureSpread(Path.of("src/main/resources/weather.dat")));
        System.out.println(fileReader.findSmallestDifference(Path.of("src/main/resources/football.dat")));
    }

    public String findSmallestDifference(Path path) {
        List<String> contentOfFile = readFile(path);
        List<Integer> differences = getDifferences(contentOfFile);
        int index = getDayWithSmallestSpread(differences);
        return contentOfFile.get(index+1).substring(7,23).trim();
    }

    public int findSmallestTemperatureSpread(Path path) {
        List<String> contentOfFile = readFile(path);
        List<Integer> spreads = getSpreads(contentOfFile);
        return getDayWithSmallestSpread(spreads) + 1;
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

    private List<Integer> getDifferences(List<String> contentOfFile) {
        int givenGoals;
        int receivedGoals;
        List<Integer> differences = new ArrayList<>();
        for (int i = 1; i < contentOfFile.size() - 1; i++) {
            try {
            givenGoals = (Integer.parseInt(contentOfFile.get(i).substring(43, 45).trim()));
            receivedGoals = (Integer.parseInt(contentOfFile.get(i).substring(50, 52).trim()));
            differences.add(givenGoals - receivedGoals);
            } catch (NumberFormatException nfe) {
                continue;
            }
        }
        return differences;
    }

    private List<String> readFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Unable to  read file", ioe);
        }
    }
}
