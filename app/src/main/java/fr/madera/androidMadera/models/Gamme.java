package fr.madera.androidMadera.models;

import java.util.Date;

/**
 * Created by Louis on 22/11/2017.
 */
public class Gamme {

    private int id;
    private String libelle;
    private Date dateCreation;
    private Date dateModification;

    public Gamme(String p_libelle){
        this.libelle = p_libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
