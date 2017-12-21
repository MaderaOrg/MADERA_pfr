package fr.madera.androidMadera.dbLoad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import fr.madera.androidMadera.models.Client;
import fr.madera.androidMadera.models.Devis;
import fr.madera.androidMadera.models.Gamme;
import fr.madera.androidMadera.models.Modele;

public class dbLoad extends SQLiteOpenHelper  {
    private SQLiteDatabase db;

    private String CREATE_DEVIS = "CREATE TABLE Devis(devis_id INTEGER PRIMARY KEY AUTOINCREMENT, ref TEXT ,nomProjet TEXT ,remise REAL ,etat TEXT ,dateCreation NUMERIC ,dateValidation NUMERIC ,dateModification NUMERIC ,modele_id INTEGER ,utilisateur_id INTEGER ,utilisateur_id_Utilisateur INTEGER ,adresse_id INTEGER ,adresse_id_Adresse INTEGER);";

    private String CREATE_CLIENT = "CREATE TABLE Client(client_id INTEGER PRIMARY KEY AUTOINCREMENT,nom TEXT ,prenom TEXT ,commentaire TEXT ,type TEXT ,dateCreation NUMERIC ,dateModification NUMERIC);";

    private String CREATE_UTILISATEUR = "CREATE TABLE Utilisateur(utilisateur_id INTEGER PRIMARY KEY AUTOINCREMENT, identifiant TEXT NOT NULL ,motPasse TEXT ,nom TEXT ,prenom TEXT ,menuBe INTEGER ,menuCom INTEGER ,menuMag INTEGER ,menuAdmin INTEGER ,tableauBord INTEGER ,dateCreation NUMERIC ,dateModifcation NUMERIC);";

    private String CREATE_COMPOSANT = "CREATE TABLE Composant(composant__id INTEGER PRIMARY KEY AUTOINCREMENT, libelle TEXT ,type TEXT ,nature TEXT ,longueur REAL ,largeur REAL ,epaisseur REAL ,unite TEXT ,prix REAL ,dateCreation NUMERIC ,dateModification NUMERIC ,famille_id INTEGER ,fournisseur_id INTEGER);";

    private String CREATE_GAMME = "CREATE TABLE Gamme(gamme_id INTEGER PRIMARY KEY AUTOINCREMENT, libelle TEXT NOT NULL ,dateCreation NUMERIC ,dateModification NUMERIC);";

    private String CREATE_MODULE = "CREATE TABLE Module(module_id INTEGER PRIMARY KEY AUTOINCREMENT, libelle TEXT ,nature TEXT ,dimension REAL ,unit TEXT ,angleEntrant REAL ,angleSortant REAL ,coupePrincipe TEXT ,dateCreation NUMERIC ,dateModification NUMERIC);";

    private String CREATE_FAMILLE_COMPOSANT = "CREATE TABLE FamilleComposant(famille_id INTEGER PRIMARY KEY AUTOINCREMENT, libelle TEXT);";

    private String CREATE_MODELE = "CREATE TABLE Modele(modele_id INTEGER PRIMARY KEY AUTOINCREMENT, libelle TEXT ,dateCreation NUMERIC ,dateModification NUMERIC);";

    private String CREATE_FOURNISSEUR = "CREATE TABLE Fournisseur(fournisseur_id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT ,siret TEXT ,coordonneeBancaire TEXT ,delaiAppro INTEGER ,tvaInterCom TEXT ,adresse_id INTEGER);";

    private String CREATE_STOCKAGE_COMPOSANT = "CREATE TABLE StockageComposant(stockComposant_id INTEGER PRIMARY KEY AUTOINCREMENT, quantite INTEGER ,statut TEXT ,dateStatut NUMERIC ,composant__id INTEGER ,adresse_id INTEGER);";

    private String CREATE_ADRESSE = "CREATE TABLE Adresse(adresse_id INTEGER PRIMARY KEY AUTOINCREMENT, pays TEXT ,ville TEXT ,rue TEXT ,codePostal TEXT ,complement1 TEXT ,complement2 TEXT ,complement3 TEXT ,tel TEXT ,fax TEXT ,email TEXT ,dateCreation NUMERIC ,DateModification NUMERIC);";

    private String CREATE_NIVEAU_STOCK = "CREATE TABLE NiveauStok(niveauStock_id INTEGER PRIMARY KEY AUTOINCREMENT, niveauAvertissement INTEGER ,niveauCritique INTEGER ,niveauRupture INTEGER ,adresse_id INTEGER ,composant__id INTEGER);";

    private String CREATE_APPARTIENT_GAMME ="CREATE TABLE AppartientGamme(gamme_id   INTEGER PRIMARY KEY ,module_id  INTEGER);";

    private String CREATE_COMPOSE_MODULE = "CREATE TABLE ComposeModule(quantite INTEGER ,module_id INTEGER PRIMARY KEY,composant__id INTEGER);";

    private String CREATE_NECESSITE ="CREATE TABLE Necessite(quantite INTEGER ,composant__id INTEGER, composant__id_Composant INTEGER PRIMARY KEY);";

    private String CREATE_REGROUPE ="CREATE TABLE Regroupe(gamme_id INTEGER PRIMARY KEY ,modele_id INTEGER);";

    private String CREATE_COMPOSE_MODELE = "CREATE TABLE ComposeModele(modele_id INTEGER PRIMARY KEY ,module_id INTEGER);";

    private String CREATE_POSSEDE_DEVIS = "CREATE TABLE PossedeDevis(client_id INTEGER PRIMARY KEY,devis_id INTEGER);";

    private String CREATE_COMPOSE_DEVIS = "CREATE TABLE ComposeDevis(libelle TEXT ,quantite INTEGER ,prix REAL ,module_id INTEGER ,devis_id INTEGER, composant__id INTEGER PRIMARY KEY);";

    private String CREATE_ADRESSE_CONTACT = "CREATE TABLE AdresseContact(adresse_id INTEGER PRIMARY KEY,client_id INTEGER NOT NULL);";


    public dbLoad(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        Log.i("bdd","debut creation table");
        this.db.execSQL(CREATE_DEVIS);
        this.db.execSQL(CREATE_CLIENT);
        this.db.execSQL(CREATE_UTILISATEUR);
        this.db.execSQL(CREATE_COMPOSANT);
        this.db.execSQL(CREATE_GAMME);
        this.db.execSQL(CREATE_MODULE);
        this.db.execSQL(CREATE_FAMILLE_COMPOSANT);
        this.db.execSQL(CREATE_MODELE);
        this.db.execSQL(CREATE_FOURNISSEUR);
        this.db.execSQL(CREATE_STOCKAGE_COMPOSANT);
        this.db.execSQL(CREATE_ADRESSE);
        this.db.execSQL(CREATE_NIVEAU_STOCK);
        this.db.execSQL(CREATE_APPARTIENT_GAMME);
        this.db.execSQL(CREATE_COMPOSE_MODULE);
        this.db.execSQL(CREATE_NECESSITE);
        this.db.execSQL(CREATE_REGROUPE);
        this.db.execSQL(CREATE_COMPOSE_MODELE);
        this.db.execSQL(CREATE_POSSEDE_DEVIS);
        this.db.execSQL(CREATE_COMPOSE_DEVIS);
        this.db.execSQL(CREATE_ADRESSE_CONTACT);
        Log.i("bdd", "fin creation table");
		initDataTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean open() {
        db = getWritableDatabase();
        return true;
    }

    public void close() {
        db.close();
    }
	
	public ArrayList<Client> loadAllClients(){
        String query = "SELECT * FROM Client";
        open();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Client> listClient = new ArrayList<Client>();
        if (cursor.moveToFirst()) {
            do {
                listClient.add(new Client(cursor.getInt(cursor.getColumnIndex("client_id")),cursor.getString(cursor.getColumnIndex("nom")), cursor.getString(cursor.getColumnIndex("prenom"))));
            }while (cursor.moveToNext()) ;
        }
        cursor.close();
        close();
        return  listClient;
    }

    public ArrayList<Gamme> loadAllGammes(){
        String query = "SELECT * FROM Gamme";
        open();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Gamme> listGamme = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                listGamme.add(new Gamme(cursor.getString(cursor.getColumnIndex("libelle"))));
            }while (cursor.moveToNext()) ;
        }
        cursor.close();
        close();
        return  listGamme;
    }

    public ArrayList<Modele> loadAllModeles(){
        String query = "SELECT * FROM Modele";
        open();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Modele> listModele = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                listModele.add(new Modele(cursor.getString(cursor.getColumnIndex("libelle"))));
            }while (cursor.moveToNext()) ;
        }
        cursor.close();
        close();
        return  listModele;
    }

    public ArrayList<Devis> loadDevisOf(int id_utilisateur){
        String query = "SELECT * FROM Devis WHERE utilisateur_id = "+id_utilisateur;
        open();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Devis> listDevis = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                listDevis.add(new Devis(cursor.getString(cursor.getColumnIndex("ref")),cursor.getInt(cursor.getColumnIndex("utilisateur_id"))));
            }while (cursor.moveToNext()) ;
        }
        cursor.close();
        close();
        return  listDevis;
    }

	private void initDataTable() {
        //pas besoin d'open la base car la fonction est appelé dans le create
        //INIT TABLE CLIENT
        Log.i("FRONTEND", "init data table");
        ContentValues values = new ContentValues();
        values.put("nom", "DUPONT");
        values.put("prenom", "Pierre");
        db.insert("Client", null, values);
        values.put("nom", "BAILLY");
        values.put("prenom", "Denis");
        db.insert("Client", null, values);
        Log.i("FRONTEND", "fin init data table");

        //INIT TABLE GAME
        values = new ContentValues();
        values.put("libelle", "Haut de gamme");
        db.insert("Gamme", null, values);
        values.put("libelle", "Standard");
        db.insert("Gamme", null, values);
        values.put("libelle", "Minimum");
        db.insert("Gamme", null, values);

        //INIT TABLE MODELE
        values.put("libelle", "Cube plein pied");
        db.insert("Modele", null, values);
        values.put("libelle", "Cube avec étage");
        db.insert("Modele", null, values);
        values.put("libelle", "Chalet mezzanine");
        db.insert("Modele", null, values);

        //INIT TABLE DEVIS
        values = new ContentValues();
        values.put("ref", "DEV364522");
        values.put("utilisateur_id", "1");
        db.insert("Devis", null, values);
        values.put("ref", "DEV435676");
        values.put("utilisateur_id", "1");
        db.insert("Devis", null, values);
    }
}