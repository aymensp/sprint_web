/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author anasc
 */
public class Produit {

    private int id;

    private String nomProd;

    private String description;

    
    
    private Double prix;

    private String nomRef;

    private String dispo;

    private String photo;

    private Date photo_updated_at;

    private int likes;

    private int views;

    private int categorie_id;

   

    private String lib;

    private float note;
    

    public Produit() {
    }

    
     public Produit(int id,String nomProd,String nomRef, String description, Double prix, String dispo, String photo, int categorie_id) {
        this.id=id;
         this.nomProd = nomProd;
        this.nomRef=nomRef;
        this.description = description;
        this.prix = prix;
        this.dispo = dispo;
        this.photo = photo;
        this.categorie_id = categorie_id;
        
    }

   public Produit(String nomProd,String nomRef, String description, Double prix, String dispo, String photo, int categorie_id) {
        this.nomProd = nomProd;
        this.nomRef=nomRef;
        this.description = description;
        this.prix = prix;
        this.dispo = dispo;
        this.photo = photo;
        this.categorie_id = categorie_id;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  
    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

   

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getPhoto_updated_at() {
        return photo_updated_at;
    }

    public void setPhoto_updated_at(Date photo_updated_at) {
        this.photo_updated_at = photo_updated_at;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

 
    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getNomRef() {
        return nomRef;
    }

    public void setNomRef(String nomRef) {
        this.nomRef = nomRef;
    }

    public String getDispo() {
        return dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nomProd=" + nomProd + ", description=" + description + ", prix=" + prix + ", nomRef=" + nomRef + ", dispo=" + dispo + ", photo=" + photo + ", photo_updated_at=" + photo_updated_at + ", likes=" + likes + ", views=" + views + ", categorie_id=" + categorie_id + ", lib=" + lib + ", note=" + note + '}';
    }
   
}
