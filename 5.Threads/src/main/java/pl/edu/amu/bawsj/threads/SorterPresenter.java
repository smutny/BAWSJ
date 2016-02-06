package pl.edu.amu.bawsj.threads;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class SorterPresenter {
    private SorterView view;

    public SorterPresenter(SorterView view) {
        this.view = view;
    }

    public void sortClicked() {
        List<String> split = Arrays.asList();
        final StringJoiner joiner = new StringJoiner(" ");
        Arrays.stream(
                view.getNums().split(" "))
                .map(s -> Double.valueOf(s))
                .sorted()
                .map(aDouble -> String.valueOf(aDouble))
                .forEach(s -> joiner.add(s));
        view.setNums(joiner.toString());
    }
}
