package day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReaderBetterSolution {

    public static void main(String[] args) {
        FileReaderBetterSolution fileReaderBetterSolution = new FileReaderBetterSolution();
        System.out.println(fileReaderBetterSolution.findSmallestTemperatureSpread("weather.dat"));
        System.out.println(fileReaderBetterSolution.findSmallestDifference("football.dat"));
    }

    public String findSmallestDifference(String filename) {
        int[] columnBounds = {7, 23, 43, 45, 50, 52};
        List<String> contentOfFile = readFile(filename);
        List<String> cleanedTable = cleanTable(contentOfFile, columnBounds);
        List<String> strippedValues = createStrippedValues(cleanedTable, columnBounds);
        return getSmallestDifference(strippedValues);
    }

    public int findSmallestTemperatureSpread(String filename) {
        int[] columnBounds = {2, 4, 6, 8, 12, 14};
        List<String> contentOfFile = readFile(filename);
        List<String> cleanedTable = cleanTable(contentOfFile, columnBounds);
        List<String> strippedValues = createStrippedValues(cleanedTable, columnBounds);
        return Integer.parseInt(getSmallestDifference(strippedValues));
    }

    public String getSmallestDifference(List<String> strippedValues) {
        int minDifference = Integer.MAX_VALUE;
        String value = "";
        for (String row : strippedValues) {
            String day = row.split(",")[0];
            int max = Integer.parseInt(row.split(",")[1]);
            int min = Integer.parseInt(row.split(",")[2]);
            if (max - min < minDifference) {
                minDifference = max - min;
                value = day;
            }
        }
        return value;
    }

    public List<String> createStrippedValues(List<String> cleanedTable, int[] columnBounds) {
        if (columnBounds.length < 6) {
            throw new IllegalArgumentException("Not enough indexes to select all three columns!" + Arrays.toString(columnBounds));
        }
        List<String> strippedValues = new ArrayList<>();
        for (String row : cleanedTable) {
            createRow(columnBounds, strippedValues, row);
        }
        return strippedValues;
    }

    public List<String> cleanTable(List<String> contentOfFile, int[] columnBounds) {
        List<String> rowsToRemove = getRowsToRemove(contentOfFile, columnBounds);
        if (!rowsToRemove.isEmpty()) {
            contentOfFile.removeAll(rowsToRemove);
        }
        return contentOfFile;
    }

    public List<String> readFile(String filename) {
        try {
            return Files.readAllLines(Paths.get("src/main/resources/" + filename));
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Unable to  read file", ioe);
        }
    }

    private void createRow(int[] columnBounds, List<String> strippedValues, String row) {
        String separator = ",";
        StringBuilder sb = new StringBuilder();
        sb.append(row.substring(columnBounds[0], columnBounds[1]).trim()).append(separator);
        sb.append(row.substring(columnBounds[2], columnBounds[3]).trim()).append(separator);
        sb.append(row.substring(columnBounds[4], columnBounds[5]).trim());
        strippedValues.add(sb.toString());
    }

    private List<String> getRowsToRemove(List<String> contentOfFile, int[] columnBounds) {
        List<String> rowsToRemove = new ArrayList<>();
        for (String row : contentOfFile) {
            if (!isValidRow(row, columnBounds)) {
                rowsToRemove.add(row);
            }
        }
        return rowsToRemove;
    }

    private boolean isValidRow(String row, int[] columnBounds) {
        return row.length() >= columnBounds[5] + 1 && Character.isDigit(row.charAt(columnBounds[2])) && Character.isDigit(row.charAt(columnBounds[4]));
    }
}
