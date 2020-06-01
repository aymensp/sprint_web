/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Fares
 */
public class Reclamation {
//$id $title $category $description $photo $postdate
    private int id;
    private int cat_id;
    private int createur;
    private String title;
    private String description;
    private String photo;
    private Date postdate;

    

    public Reclamation(int id, int cat_id,int createur, String title,String description,String photo,Date postdate) {
        this.id = id;
        this.cat_id = cat_id;
        this.createur = createur;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.postdate = postdate;

    }

    
    public Reclamation() {

    }
    
    
    public Reclamation(int cat_id,int createur, String title,String description) {
        this.createur = createur;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.postdate = postdate;
    }
        public Reclamation(int id) {
                    this.id = id;

        }

    
    
    
    public Reclamation(int id,int cat_id,int createur, String title,String description) {
        this.id=id;
        this.createur = createur;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.postdate = postdate;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getCreateur() {
        return createur;
    }

    public void setCreateur(int createur) {
        this.createur = createur;
    }
    
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }
    
    
    /*

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", status=" + status + ", name=" + name + '}';
    }
*/
}

