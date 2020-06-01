/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author anasc
 */
public class Utilisateur {
    
    public static Utilisateur current_user = null;
    
    private int id;
    
    private int group_id;
 
    private String username;
   
    private String usernameCanonical;
  
    private String email;
   
    private String emailCanonical;
   
    private boolean enabled;

    private String salt;
 
   
    private String password;
  
    private Date lastLogin;
 
    private String confirmationToken;

    private Calendar passwordRequestedAt;
   
    private String roles;
   
    private String nom;
   
    private String prenom;
    
    private String discr ;
    
    private String photo ;
    
    private Calendar photo_updated_at;
    
    private String rue;
    
    private String ville;
    
    private int numtel;
    
    private String nomPropriete;
    
    

    public Utilisateur(String username, String usernameCanonical, String email, String emailCanonical, String password, String nom, String prenom, String nomPropriete) {
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.nomPropriete = nomPropriete;
    }

    public Utilisateur(int id, String username) {
        this.id = id;
        this.username = username;
    }
    
    



    public Utilisateur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Calendar getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Calendar passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDiscr() {
        return discr;
    }

    public void setDiscr(String discr) {
        this.discr = discr;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Calendar getPhoto_updated_at() {
        return photo_updated_at;
    }

    public void setPhoto_updated_at(Calendar photo_updated_at) {
        this.photo_updated_at = photo_updated_at;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public String getNomPropriete() {
        return nomPropriete;
    }

    public void setNomPropriete(String nomPropriete) {
        this.nomPropriete = nomPropriete;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", group_id=" + group_id + ", username=" + username + ", usernameCanonical=" + usernameCanonical + ", email=" + email + ", emailCanonical=" + emailCanonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", lastLogin=" + lastLogin + ", confirmationToken=" + confirmationToken + ", passwordRequestedAt=" + passwordRequestedAt + ", roles=" + roles + ", nom=" + nom + ", prenom=" + prenom + ", discr=" + discr + ", photo=" + photo + ", photo_updated_at=" + photo_updated_at + ", rue=" + rue + ", ville=" + ville + ", numtel=" + numtel + ", nomPropriete=" + nomPropriete +  '}';
    }


}
