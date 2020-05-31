/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author user
 */
public class Event_volontaire {
    private int evenement_id;
    private int volontaire_id;

    public Event_volontaire(int evenement_id, int volontaire_id) {
        this.evenement_id = evenement_id;
        this.volontaire_id = volontaire_id;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    public int getVolontaire_id() {
        return volontaire_id;
    }

    public void setVolontaire_id(int volontaire_id) {
        this.volontaire_id = volontaire_id;
    }

    @Override
    public String toString() {
        return "Event_volontaire{" + "evenement_id=" + evenement_id + ", volontaire_id=" + volontaire_id + '}';
    }

    public Event_volontaire() {
    }
    
    
}
