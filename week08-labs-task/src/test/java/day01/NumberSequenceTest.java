package day01;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSequenceTest {

    @Test
    void createWithListTest() {
        List<Integer> numbers = Arrays.asList(2, -5, 10, 4, 8, 79, 3, 4, 88, -49, -54);
        NumberSequence numberSequence = new NumberSequence(numbers);
        assertEquals(11, numberSequence.getNumbers().size());
        assertEquals(-5, numberSequence.getNumbers().get(1));
        assertTrue(isRealRandom(numbers));
    }

    @Test
    void createWithRandomTest() {
        NumberSequence numberSequence = new NumberSequence(12, -50, 50);
        List<Integer> numbers = numberSequence.getNumbers();
        assertEquals(12, numberSequence.getNumbers().size());
        assertTrue(isWithinLimits(numbers));
        assertTrue(isRealRandom(numbers));
    }

    @Test
    void getNumbersTest() {
        List<Integer> numbers = Arrays.asList(2, -5, 10, 4, 8, 79, 3, 4, 88, -49, -54);
        NumberSequence numberSequence = new NumberSequence(numbers);
        assertEquals(11, numberSequence.getNumbers().size());
        assertEquals(-54, numberSequence.getNumbers().get(10));
        assertEquals(2, numberSequence.getNumbers().get(0));
    }

    @Test
    void closeToAverageTest() {
        List<Integer> numbers = Arrays.asList(2, -5, 10, 4, 8, 79, 3, 4, 88, -49, -54);
        NumberSequence numberSequence = new NumberSequence(numbers);
        List<Integer> result = numberSequence.closeToAverage(3);
        assertEquals(2, result.size());
        assertEquals(10, result.get(0));
    }

    private boolean isWithinLimits(List<Integer> numbers) {
        for (int number : numbers) {
            if (Math.abs(number) > 50) {
                return false;
            }
        }
        return true;
    }

    private boolean isRealRandom(List<Integer> numbers) {
        int count = 0;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(i) == numbers.get(j)) {
                    count++;
                }
            }
        }
        return count < numbers.size() / 10;
    }
}