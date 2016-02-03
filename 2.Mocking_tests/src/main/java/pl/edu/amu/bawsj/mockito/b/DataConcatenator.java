package pl.edu.amu.bawsj.mockito.b;

/**
 * Created by Bocian on 03.02.2016.
 */
public class DataConcatenator {
    private DataChanger changer;

    DataConcatenator(DataChanger changer){
        this.changer = changer;
    }

    public String concatenate(Data data){
        changer.change(data);
        return data.getA() + ":" + data.getB();
    }
}
