/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Aziz
 */
public class Commande {
        private int id;
    private Date date_emission ;
    private String etat_commande;
    private int id_user;
    private double prixTotal;

    public Commande() {
    }

    public Commande(String etat_commande, int id_user, double prixTotal) {
        
        
        this.etat_commande = etat_commande;
        this.id_user = id_user;
        this.prixTotal = prixTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_emission() {
        return date_emission;
    }

    public void setDate_emission(Date date_emission) {
        this.date_emission = date_emission;
    }

    public String getEtat_commande() {
        return etat_commande;
    }

    public void setEtat_commande(String etat_commande) {
        this.etat_commande = etat_commande;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", date_emission=" + date_emission + ", etat_commande=" + etat_commande + ", id_user=" + id_user + ", prixTotal=" + prixTotal + '}';
    }
    
    
}
