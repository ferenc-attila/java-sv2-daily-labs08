package day05;

import day04.FileReaderBetterSolution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReader {

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        System.out.println(fileReader.findSmallestTemperatureSpread("weather.dat"));
        System.out.println(fileReader.findSmallestDifference("football.dat"));
    }

    public String findSmallestDifference(String filename) {
        int[] columnBounds = {7, 23, 43, 45, 50, 52};
        List<String> contentOfFile = readFile(filename);
        List<String> cleanedTable = removeInvalidRows(contentOfFile, columnBounds, filename);
        List<String> strippedValues = createStrippedValues(cleanedTable, columnBounds);
        return getSmallestDifference(strippedValues);
    }

    public int findSmallestTemperatureSpread(String filename) {
        int[] columnBounds = {2, 4, 6, 8, 12, 14};
        List<String> contentOfFile = readFile(filename);
        List<String> cleanedTable = removeInvalidRows(contentOfFile, columnBounds, filename);
        List<String> strippedValues = createStrippedValues(cleanedTable, columnBounds);
        return Integer.parseInt(getSmallestDifference(strippedValues));
    }

    public String getSmallestDifference(List<String> strippedValues) {
        int minDifference = Integer.MAX_VALUE;
        String result = "";
        for (String row : strippedValues) {
            String value = row.split(",")[0];
            int max = Integer.parseInt(row.split(",")[1]);
            int min = Integer.parseInt(row.split(",")[2]);
            if (Math.abs(max - min) < minDifference) {
                minDifference = Math.abs(max - min);
                result = value;
            }
        }
        return result;
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

    public List<String> removeInvalidRows(List<String> contentOfFile, int[] columnBounds, String filename) {
        List<String> rowsToRemove = getRowsToRemove(contentOfFile, columnBounds, filename);
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

    private List<String> getRowsToRemove(List<String> contentOfFile, int[] columnBounds, String filename) {
        List<String> rowsToRemove = new ArrayList<>();
        for (String row : contentOfFile) {
            if (!isValidRow(row, columnBounds, filename)) {
                rowsToRemove.add(row);
            }
        }
        return rowsToRemove;
    }

    private boolean isValidRow(String row, int[] columnBounds, String filename) {
        if ("football.dat".equals(filename)) {
            return row.length() >= columnBounds[5] + 1 && Character.isDigit(row.charAt(columnBounds[2])) && Character.isDigit(row.charAt(columnBounds[4]));
        } else {
            return !row.isBlank() && Character.isDigit(row.charAt(columnBounds[0] + 1));
        }
    }
}
