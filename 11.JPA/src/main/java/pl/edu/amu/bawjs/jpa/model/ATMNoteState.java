package pl.edu.amu.bawjs.jpa.model;

import pl.edu.amu.bawsj.atmjpa.model.Note;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class ATMNoteState implements Serializable {
    @Id
    @Enumerated
    private Note noteId;

    @Column
    private int quantity;

    public ATMNoteState() {
    }

    public ATMNoteState(Note noteId, int quantity) {
        this.noteId = noteId;
        this.quantity = quantity;
    }

    public Note getNoteId() {
        return noteId;
    }

    public void setNoteId(Note noteId) {
        this.noteId = noteId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
