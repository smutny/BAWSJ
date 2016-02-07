package pl.edu.amu.bawsj.threads;

import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class SorterPresenter {

    private static final Logger LOG = LogManager.getLogger();

    private SorterView view;

    public SorterPresenter(SorterView view) {
        this.view = view;
    }

    public void sortClicked() {
        if (toString().isEmpty()) return;
        new Thread(() -> {
            Platform.runLater(() -> view.block());
            final StringJoiner joiner = new StringJoiner(" ");
            Arrays.stream(
                    view.getNums().split(" "))
                    .map(s -> Double.valueOf(s))
                    .sorted()
                    .map(aDouble -> String.valueOf(aDouble))
                    .forEach(s -> {
                        joiner.add(s);
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            LOG.error(e);
                        }
                    });
            Platform.runLater(() -> {
                view.setNums(joiner.toString());
                view.unBlock();
            });
        }).start();
    }

    public void seedClicked() {
        int seedCount;
        try {
            seedCount = Integer.parseInt(view.getSeedCount());
        } catch (Exception e) {
            LOG.error(e);
            return;
        }
        Random random = new Random(0);
        StringBuilder builer = new StringBuilder();
        for (int i = 0; i < seedCount; i++) {
            builer.append(random.nextInt()).append(" ");
        }
        view.setNums(builer.toString());
    }
}
