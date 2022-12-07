package ch.heig.lachaize.amttest2.model;

import java.io.Serializable;

public class Livre implements Serializable {

    private int codLiv;
    private String titre;
    private Theme theme;
    private int note;
    private boolean selection;


    public Livre() {

    }

    public Livre(int codLiv, String titre, int note, boolean selection) {
        this.codLiv = codLiv;
        this.titre = titre;
        this.note = note;
        this.selection = selection;
    }

    public int getCodLiv() {
        return codLiv;
    }

    public void setCodLiv(int codLiv) {
        this.codLiv = codLiv;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public boolean isSelection() {
        return selection;
    }

    public void setSelection(boolean selection) {
        this.selection = selection;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
