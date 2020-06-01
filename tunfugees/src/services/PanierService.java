/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Produit;
import entities.Panier;
import entities.Commande;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aziz
 */
public class PanierService {
    ArrayList<Panier> listAnnoncep = new ArrayList<>();
    private boolean responseResult;
     public void ajouterAnnonce(Panier a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/panierApi/new?idProd="+ a.getIdProd()+"&nomProd="+a.getNomProd()+"&nomRef=" + a.getNomRef()+"&prix="+ a.getPrix() + "&img=" + a.getPhoto();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    }
     
     public ArrayList<Panier> parseListTaskJsonAnnoncePanier(String json) throws ParseException {

        ArrayList<Panier> listAnnoncep = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {

                Panier e = new Panier();
                
                
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setIdProd(obj.get("idprod").toString());
                e.setNomProd(obj.get("nomprod").toString());
                e.setNomRef(obj.get("nomref").toString());
                e.setPrix(Double.parseDouble(obj.get("prix").toString()));
              
                e.setPhoto(obj.get("img").toString());
               
                System.out.println(e);

                listAnnoncep.add(e);

            }

        } catch (IOException ex) {
        }

      
        System.out.println(listAnnoncep);
        return listAnnoncep;

    }
     
     public ArrayList<Panier> getAllAnnoncesP() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/panierApi/");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PanierService ser = new PanierService();
                try {
                    listAnnoncep = ser.parseListTaskJsonAnnoncePanier(new String(con.getResponseData()));
                    System.out.println(listAnnoncep);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnnoncep;
    }
     
      public boolean viderPanier() {
          ConnectionRequest con = new ConnectionRequest();
        String Url =("http://127.0.0.1:8000/panierApi/vider");
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this); //Supprimer cet actionListener
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         return responseResult;
    }
      
      public boolean supprimerAnnonce(int a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/panierApi/delete_a/"+a;
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this); //Supprimer cet actionListener
            } 
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         return responseResult;
    }
      
      public void ajouterCommande(Commande c) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/panierApi/newcmd?etat_commande="+c.getEtat_commande()+"&id_utilisateur="+c.getId_user()+"&prix_total="+c.getPrixTotal();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
        public void payerCommande(Commande c) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/commande/pay/"+c.getId();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
