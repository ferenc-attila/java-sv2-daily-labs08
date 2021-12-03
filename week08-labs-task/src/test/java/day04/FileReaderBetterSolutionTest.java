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
    void createStrippedWeatherValuesTest() {
        int[] columnBounds = {2, 4, 6, 8, 12, 14};
        List<String> cleanedTable = new ArrayList<>(Arrays.asList("   1  88    59    74          53.8       0.00 F       280  9.6 270  17  1.6  93 23 1004.5",
                "   2  79    63    71          46.5       0.00         330  8.7 340  23  3.3  70 28 1004.5",
                "   3  77    55    66          39.6       0.00         350  5.0 350   9  2.8  59 24 1016.8",
                "   9  86    32*   59       6  61.5       0.00         240  7.6 220  12  6.0  78 46 1018.6",
                "  19  81    61    71          58.9       0.00 H       250  5.2 230  12  5.3  87 44 1028.5"));
        List<String> strippedValues = new ArrayList<>(Arrays.asList("1,88,59", "2,79,63", "3,77,55", "9,86,32","19,81,61"));
        assertEquals(strippedValues, fileReaderBetterSolution.createStrippedValues(cleanedTable, columnBounds));
    }

    @Test
    void createStrippedFootballValuesTest() {
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
    void removeInvalidRowsInWeatherTest() {
        String filename = "weather.dat";
        int[] columnBounds = {2, 4, 6, 8, 12, 14};
        List<String> contentOfFile = new ArrayList<>(Arrays.asList("  Dy MxT   MnT   AvT   HDDay  AvDP 1HrP TPcpn WxType PDir AvSp Dir MxS SkyC MxR MnR AvSLP",
                "",
                "   1  88    59    74          53.8       0.00 F       280  9.6 270  17  1.6  93 23 1004.5",
                "   2  79    63    71          46.5       0.00         330  8.7 340  23  3.3  70 28 1004.5",
                "   3  77    55    66          39.6       0.00         350  5.0 350   9  2.8  59 24 1016.8",
                "   9  86    32*   59       6  61.5       0.00         240  7.6 220  12  6.0  78 46 1018.6",
                "  19  81    61    71          58.9       0.00 H       250  5.2 230  12  5.3  87 44 1028.5",
                "  mo  82.9  60.5  71.7    16  58.8       0.00              6.9          5.3"));
        List<String> cleanedTable = new ArrayList<>(Arrays.asList("   1  88    59    74          53.8       0.00 F       280  9.6 270  17  1.6  93 23 1004.5",
                "   2  79    63    71          46.5       0.00         330  8.7 340  23  3.3  70 28 1004.5",
                "   3  77    55    66          39.6       0.00         350  5.0 350   9  2.8  59 24 1016.8",
                "   9  86    32*   59       6  61.5       0.00         240  7.6 220  12  6.0  78 46 1018.6",
                "  19  81    61    71          58.9       0.00 H       250  5.2 230  12  5.3  87 44 1028.5"));
        assertEquals(cleanedTable, fileReaderBetterSolution.removeInvalidRows(contentOfFile, columnBounds, filename));
    }

    @Test
    void removeInvalidRowsInFootballTest() {
        String filename = "football.dat";
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
        assertEquals(cleanedTable, fileReaderBetterSolution.removeInvalidRows(contentOfFile, columnBounds, filename));
    }
}