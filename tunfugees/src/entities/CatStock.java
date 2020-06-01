package entities;

import java.util.Date;
//import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author bhk
 */
public class CatStock {

    
    private int id;
    private String nomcateg;
  
    /**
     * No args constructor for use in serialization
     *
     */
    public CatStock() {
    }

    
    public CatStock(String nomcateg ) {
        this.nomcateg = nomcateg;
       
    }

   
     public CatStock(int id, String nomcateg) {
        this.id = id;
        this.nomcateg=nomcateg;
    }

    
    
   

    

    public int getId() {
        return id;
    }
    
    
    public void setId(int id) {
        this.id = id;
    }

    
    
    
  
    public String getNomcateg() {
        return nomcateg;    
}
    
    
public void setNomcateg(String nomcateg) {
        this.nomcateg = nomcateg;
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
