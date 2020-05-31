/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author hp
 */
public class Refugies {
     private int idref;
     private int camps_id;

    private String nom;

    private String prenomn;

    private int age;

    private String pays;
    
    private String image;

    public int getIdref() {
        return idref;
    }

    public void setIdref(int idref) {
        this.idref = idref;
    }

    public int getCamps_id() {
        return camps_id;
    }

    public void setCamps_id(int camps_id) {
        this.camps_id = camps_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenomn() {
        return prenomn;
    }

    public void setPrenomn(String prenomn) {
        this.prenomn = prenomn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Refugies(int idref, int camps_id, String nom, String prenomn, int age, String pays, String image) {
        this.idref = idref;
        this.camps_id = camps_id;
        this.nom = nom;
        this.prenomn = prenomn;
        this.age = age;
        this.pays = pays;
        this.image = image;
    }
    public Refugies(){}

    public Refugies(String nom, String prenomn, int age, String pays, String image) {
        this.nom = nom;
        this.prenomn = prenomn;
        this.age = age;
        this.pays = pays;
        this.image = image;
    }

    public Refugies( String nom, String prenomn, String pays, String image) {
 
        this.nom = nom;
        this.prenomn = prenomn;
        this.pays = pays;
        this.image = image;
    }

    public Refugies(int idref, String nom, String prenomn, String pays, String image) {
        this.idref = idref;
        this.nom = nom;
        this.prenomn = prenomn;
        this.pays = pays;
        this.image = image;
    }
    
    

}
