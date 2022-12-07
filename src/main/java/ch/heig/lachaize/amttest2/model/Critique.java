package ch.heig.lachaize.amttest2.model;

public class Critique {
    private int codCri;
    private String nom;
    private String prenom;
    private Theme theme;

    public Critique() {

    }

    public Critique(int codCri, String nom, String prenom) {
        this.codCri = codCri;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCodCri() {
        return codCri;
    }

    public void setCodCri(int codCri) {
        this.codCri = codCri;
    }
}
