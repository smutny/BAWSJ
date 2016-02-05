package pl.edu.amu.bawsj.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SorterPresenter {
    private SorterView view;

    public SorterPresenter(SorterView view) {
        this.view = view;
    }

    public void sortClicked() {
        List<String> split = Arrays.asList(view.getNums().split(" "));

        List<Double> sortedNums = split.stream().map(new Function<String, Double>() {
            @Override
            public Double apply(String s) {
                return Double.valueOf(s);
            }
        }).sorted().collect(Collectors.<Double>toList());

        StringJoiner joiner = new StringJoiner(" ");
       sortedNums.stream().map(new Function<Double, String>() {
            @Override
            public String apply(Double aDouble) {
                return String.valueOf(aDouble);
            }
        }).reduce("aa");

        view.setNums();

    }
}
