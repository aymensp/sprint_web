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
public class LigneCommande {
    private int id;
    private int id_commande;
    private int idProd;
    private int id_utilisateur;
    private double prixProd;

    public LigneCommande(int id, int id_commande, int idProd, int id_utilisateur, double prixProd) {
        this.id = id;
        this.id_commande = id_commande;
        this.idProd = idProd;
        this.id_utilisateur = id_utilisateur;
        this.prixProd = prixProd;
    }

    public LigneCommande() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public double getPrixProd() {
        return prixProd;
    }

    public void setPrixProd(double prixProd) {
        this.prixProd = prixProd;
    }

    @Override
    public String toString() {
        return "LigneCommande{" + "id=" + id + ", id_commande=" + id_commande + ", idProd=" + idProd + ", id_utilisateur=" + id_utilisateur + ", prixProd=" + prixProd + '}';
    }

    
    
}
