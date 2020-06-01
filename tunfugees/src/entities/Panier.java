/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Aziz
 */
public class Panier {
    private int id ;
    private String idProd;
    private String nomProd;
    
    private double prix;
    private String nomRef ;
    private String photo;

    public Panier() {
    }

    public Panier( String idProd, String nomProd, double prix, String nomRef, String photo) {
        
        this.idProd = idProd;
        this.nomProd = nomProd;
      
        this.prix = prix;
        this.nomRef = nomRef;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdProd() {
        return idProd;
    }

    public void setIdProd(String idProd) {
        this.idProd = idProd;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getNomRef() {
        return nomRef;
    }

    public void setNomRef(String nomRef) {
        this.nomRef = nomRef;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", idProd=" + idProd + ", nomProd=" + nomProd + ", prix=" + prix + ", nomRef=" + nomRef + ", photo=" + photo + '}';
    }

  
    
   

   
   
    
    
}
