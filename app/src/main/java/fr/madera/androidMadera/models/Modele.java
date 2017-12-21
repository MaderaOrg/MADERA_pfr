package fr.madera.androidMadera.models;

import java.util.Date;

public class Modele {
    private int id;
    private String libelle;
    private Date dateCreation;
    private Date dateModification;

    public Modele(String p_libelle){
        this.libelle = p_libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
