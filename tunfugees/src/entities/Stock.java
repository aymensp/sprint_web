/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
//import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author bhk
 */
public class Stock {

    
    private int id;
    private int cat_id;
    private int createur;

    private String title;
    private String description;
    private String photo;
    private Date postdate;

    /**
     * No args constructor for use in serialization
     *
     */
    public Stock() {
    }

    
    public Stock(int cat_id,int createur,String title,String description,String photo,Date postdate ) {
        this.cat_id = cat_id;
        this.createur=createur;
        this.title = title;
        this.description=description;
        this.photo=photo;
        this.postdate=postdate;

    }


        
    public Stock(int cat_id,String title,String description ) {
        this.cat_id = cat_id;
       
        this.title = title;
        this.description=description;
       
    }

  /*  
    
     public Stock(String title,String description) {
        this.title = title;
        this.description=description;
    }
*/
    
    
    public Stock(String title,String description,String photo,Date postdate ) {
        this.title = title;
        this.description=description;
        this.photo=photo;
        this.postdate=postdate;

    }


    public int getCat_id() {
        return cat_id;
    }

 
    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getId() {
        return id;
    }
    
    
    public void setId(int id) {
        this.id = id;
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
    /**
     * @return the client
     */
 //   public String getClient() {
   //     return client;
  //  }

    /**
     * @param client the client to set
     */
 //   public void setClient(String client) {
   //     this.client = client;
   // }
/*
    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", titre=" + titre + ", etat=" + etat + ", dateCreation=" + dateCreation + ", dateLivraisonn=" + dateLivraisonn + ", adresse=" + adresse + ", prix=" + prix + ", tel=" + tel + ", agent=" + agent + ", client=" + client + '}';
    }

    
*/
}
