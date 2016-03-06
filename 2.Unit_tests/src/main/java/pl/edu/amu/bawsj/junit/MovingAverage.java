package pl.edu.amu.bawsj.junit;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class MovingAverage
{
    List<Double> numbers;
    int windowSize;
    MovingAverage( int windowSize ) {
        if (windowSize < 0) {
            throw new IllegalStateException();
        }
        this.windowSize = windowSize;
        numbers = new ArrayList<Double>();
    }

    public void push( double val ) {
        numbers.add(val);
        if (numbers.size() > windowSize) {
            numbers.remove(0);
        }
    }

    public double getAvg() {
        if (numbers.size() ==0) {
            throw new IllegalStateException();
        }

        Double average = numbers.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .getAsDouble();

        return average;
    }
}
