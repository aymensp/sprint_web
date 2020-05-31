/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;

/**
 *
 * @author user
 */

public class Volontaire {
    private int cin;
    private String nom;
    private String prenom;
    private String mail;
    private int tel;
    private String nom_event;
    private String presence;
    private int id_vol;
    

    public Volontaire(int cin, String nom, String prenom, String mail, int tel) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.tel = tel;
        
        
        
      
    }

    public Volontaire( int id_vol,int cin, String nom, String prenom, String mail, int tel, String nom_event, String presence) {
        this.id_vol = id_vol;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.tel = tel;
        this.nom_event = nom_event;
        this.presence = presence;
       
    }
    

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }

    public Volontaire() {
    }

    public int getId_vol() {
        return id_vol;
    }

    public String getNom_event() {
        return nom_event;
    }

    public int getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public int getTel() {
        return tel;
    }

   

    public String getPresence() {
        return presence;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

   

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public Volontaire( int id_vol,String presence) {
        this.presence = presence;
        this.id_vol = id_vol;
    }

 

        public Volontaire(String string) {
            
        }
    

   

   
    
}
