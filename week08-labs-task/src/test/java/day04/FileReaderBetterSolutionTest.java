package day04;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderBetterSolutionTest {

    FileReaderBetterSolution fileReaderBetterSolution = new FileReaderBetterSolution();

    @Test
    void findSmallestDifferenceTest() {
        assertEquals("Aston_Villa", fileReaderBetterSolution.findSmallestDifference("football.dat"));
    }

    @Test
    void findSmallestTemperatureSpreadTest() {
        assertEquals(14, fileReaderBetterSolution.findSmallestTemperatureSpread("weather.dat"));
    }

    @Test
    void getSmallestDifferenceTest() {
        List<String> strippedValues = new ArrayList<>(Arrays.asList("Monday,10,8", "Tuesday,12,5", "Wednesday,5,7", "Thursday,4,12", "Friday,5,3"));
        assertEquals("Monday", fileReaderBetterSolution.getSmallestDifference(strippedValues));
    }

    @Test
    void createStrippedValuesTest() {
        int[] columnBounds = {7, 23, 43, 45, 50, 52};
        List<String> cleanedTable = new ArrayList<>(Arrays.asList("    1. Arsenal         38    26   9   3    79  -  36    87",
                "    2. Liverpool       38    24   8   6    67  -  30    80",
                "    3. Manchester_U    38    24   5   9    87  -  45    77",
                "   18. Ipswich         38     9   9  20    41  -  64    36",
                "   19. Derby           38     8   6  24    33  -  63    30"));
        List<String> strippedValues = new ArrayList<>(Arrays.asList("Arsenal,79,36", "Liverpool,67,30", "Manchester_U,87,45", "Ipswich,41,64", "Derby,33,63"));
        assertEquals(strippedValues, fileReaderBetterSolution.createStrippedValues(cleanedTable, columnBounds));
    }

    @Test
    void cleanTableTest() {
        int[] columnBounds = {7, 23, 43, 45, 50, 52};
        List<String> contentOfFile = new ArrayList<>(Arrays.asList("", "       Team            P     W    L   D    F      A     Pts",
                "    1. Arsenal         38    26   9   3    79  -  36    87",
                "    2. Liverpool       38    24   8   6    67  -  30    80",
                "    3. Manchester_U    38    24   5   9    87  -  45    77",
                "   18. Ipswich         38     9   9  20    41  -  64    36",
                "----------------------------------------------------------",
                "   19. Derby           38     8   6  24    33  -  63    30"));
        List<String> cleanedTable = new ArrayList<>(Arrays.asList("    1. Arsenal         38    26   9   3    79  -  36    87",
                "    2. Liverpool       38    24   8   6    67  -  30    80",
                "    3. Manchester_U    38    24   5   9    87  -  45    77",
                "   18. Ipswich         38     9   9  20    41  -  64    36",
                "   19. Derby           38     8   6  24    33  -  63    30"));
        assertEquals(cleanedTable, fileReaderBetterSolution.cleanTable(contentOfFile, columnBounds));
    }
}