package pl.edu.amu.bawsj.atmjpa.model;

public enum Note {
    ZL_10, ZL_20, ZL_50, ZL_100, ZL_200;

    public int getValue() {
        switch (this) {
            case ZL_10:
                return 10;
            case ZL_20:
                return 20;
            case ZL_50:
                return 50;
            case ZL_100:
                return 100;
            case ZL_200:
                return 200;
            default:
                throw new IllegalStateException("No such value");
        }
    }
}
