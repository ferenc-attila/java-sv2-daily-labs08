package day05;

import day04.FileReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

day04.FileReader fileReader = new FileReader();

    @Test
    void findSmallestTemperatureSpreadTest() {
        assertEquals("Aston_Villa", fileReader.findSmallestDifference("football.dat"));
    }

    @Test
    void findSmallestDifferenceTest() {
        assertEquals(14, fileReader.findSmallestTemperatureSpread("weather.dat"));
    }

}