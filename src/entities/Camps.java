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
public class Camps {
    private int idCamp;
    private int nbrmax;
    private String nomCamp;
    private String adresse;

    public int getIdCamp() {
        return idCamp;
    }

    public void setIdCamp(int idCamp) {
        this.idCamp = idCamp;
    }

    public int getNbrmax() {
        return nbrmax;
    }

    public void setNbrmax(int nbrmax) {
        this.nbrmax = nbrmax;
    }

    public String getNomCamp() {
        return nomCamp;
    }

    public void setNomCamp(String nomCamp) {
        this.nomCamp = nomCamp;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Camps(int idCamp, int nbrmax, String nomCamp, String adresse) {
        this.idCamp = idCamp;
        this.nbrmax = nbrmax;
        this.nomCamp = nomCamp;
        this.adresse = adresse;
    }
    public Camps(){}
    
    
}
