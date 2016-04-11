package pl.edu.amu.bawsj.domain;

/**
 * Created by rafal on 3/17/16.
 */
public class Note {
    private int value;

    public Note(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || ! Note.class.isAssignableFrom(obj.getClass()))
            return false;
        final Note other = (Note) obj;

        return this.value == other.getValue();
    }
}
