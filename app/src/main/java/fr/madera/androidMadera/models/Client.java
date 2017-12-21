package fr.madera.androidMadera.models;

import java.util.Date;

public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String commentaire;
    private  String type;
    private Date dateCreation;
    private Date dateModification;
	
	public Client(int p_id ,String p_nom, String p_prenom){
        this.id = p_id;
		this.nom = p_nom;
		this.prenom = p_prenom;
	}

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
}
