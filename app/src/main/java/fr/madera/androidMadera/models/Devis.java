package fr.madera.androidMadera.models;

import java.util.Date;

public class Devis {

    private int id;
    private String ref;
    private String nomProjet;
    private float remise;
    private String etat;
    private Date dateCreation;
    private Date dateModification;
    private Date dateValidation;
    private int modele_id;
    private int utilisateur_id;
    private int utilisateur_id_utilisateur;
    private int adresse_id;
    private int adresse_id_adresse;

    public Devis (String p_ref, int p_utilisateur_id){
        this.ref = p_ref;
        this.utilisateur_id = p_utilisateur_id;
    }

    public String getRef() {
        return ref;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }
}
