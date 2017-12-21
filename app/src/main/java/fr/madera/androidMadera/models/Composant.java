package fr.madera.androidMadera.models;

import java.util.Date;

/**
 * Created by Guillaume on 13/11/2017.
 */
public class Composant {

    private int id;
    private String libelle;
    private String type;
    private String nature;
    private float longueur;
    private float largeur;
    private float epaisseur;
    private String unite; //cm , metre ect..
    private float prix;
    private Date dateCreation;
    private Date dateModification;
    private int famille_id;
    private int fournisseur_id;

}
