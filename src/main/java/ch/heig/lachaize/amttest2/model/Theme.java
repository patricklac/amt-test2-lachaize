package ch.heig.lachaize.amttest2.model;

import java.io.Serializable;

public class Theme implements Serializable {
    private String codTh;
    private String nom;


    public Theme() {

    }

    public Theme(String codTh, String nom) {
        this.codTh = codTh;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodTh() {
        return codTh;
    }

    public void setCodTh(String codTh) {
        this.codTh = codTh;
    }
}
