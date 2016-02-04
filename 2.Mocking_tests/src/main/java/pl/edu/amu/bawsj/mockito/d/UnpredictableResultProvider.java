package pl.edu.amu.bawsj.mockito.d;


import java.util.Random;

public class UnpredictableResultProvider implements ResultProvider{

    @Override
    public double provide() {
        return 100.0 / new Random(0).nextInt(2);
    }

}
