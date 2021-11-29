package day01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberSequence {

    private List<Integer> numbers;

    public NumberSequence(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public NumberSequence(int numberOfElements, int minValue, int maxValue) {
        numbers = new ArrayList<>();
        for (int i = 0; i < numberOfElements; i++) {
            numbers.add(new Random().nextInt(minValue, (maxValue + 1)));
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public List<Integer> closeToAverage(int value) {
        List<Integer> numbersCloseToAverage = new ArrayList<>();
        double average = getAverage();
        for (int number : numbers) {
            if (Math.abs(number-average) <= value) {
                numbersCloseToAverage.add(number);
            }
        }
        return numbersCloseToAverage;
    }

    private double getAverage() {
        double sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum / numbers.size();
    }
}
