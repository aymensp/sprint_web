/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author user
 */
public class Event {
    private int id_event;
    private String nomEvent;
    private String adresse;
    private String date;
    private String description;
    private String image;
    private List<Volontaire>volontaires=new ArrayList();
    
    private int nbrMax;
    
 public List<Volontaire> getVolontaires() {
        return volontaires;
    }

    public void setVolontaires(List<Volontaire> volontaires) {
        this.volontaires = volontaires;
    }

    public Event(String nomEvent, String adresse, String date, String description,int nbrMax,String image) {
     
        this.nomEvent = nomEvent;
        this.adresse = adresse;
        this.date = date;
        this.description = description;
       
        this.nbrMax=nbrMax;
        this.image =image;  
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Event() {
    }

    public int getNbrMax() {
        return nbrMax;
    }

    public int getId_event() {
        return id_event;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

   

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

   

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

    public void setNbrMax(int nbrMax) {
        this.nbrMax = nbrMax;
    }

    public Event(int id_event, String nomEvent, String adresse, String date, String description, String image, int nbrMax) {
        this.id_event = id_event;
        this.nomEvent = nomEvent;
        this.adresse = adresse;
        this.date = date;
        this.description = description;
        this.image = image;
        this.nbrMax = nbrMax;
    }

   

    
    
   
    
}
