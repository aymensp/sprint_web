/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Produit;
import entities.Categorie_Produit;
import entities.Event;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anasc
 */
public class ProduitService {

    ArrayList<Produit> listAnnonce = new ArrayList<>();
    ArrayList<Categorie_Produit> listCat = new ArrayList<>();
private boolean responseResult;

public void ajouterAnnonce(Produit a) {
        String Url = "http://127.0.0.1:8000/annonceApi/new";
        MultipartRequest req = new MultipartRequest();
        try {
            req.setUrl(Url);
            req.setPost(true);
            req.addArgument("nomProd", a.getNomProd());
       
            req.addArgument("description", a.getDescription());
            req.addArgument("prix", a.getPrix().toString());
            req.addArgument("dispo", a.getDispo());
           
                  req.addArgument("nomRef", a.getNomRef());
                req.addArgument("img", a.getPhoto());
            req.addArgument("categorie", String.valueOf(a.getCategorie_id()));
            
           

            req.addResponseListener((response) -> {
                byte[] data = (byte[]) response.getMetaData();
                String s = new String(data);
                // System.out.println("data : " + s);
            });

            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
public void modifierAnnonce(Produit a) {
        String Url = "http://127.0.0.1:8000/annonceApi/modifier";
        MultipartRequest req = new MultipartRequest();
        try {
            req.setUrl(Url);
            req.setPost(true);
            req.addArgument("idProd",String.valueOf(a.getId()) );
            req.addArgument("nomProd", a.getNomProd());
       
            req.addArgument("description", a.getDescription());
            req.addArgument("prix", a.getPrix().toString());
            req.addArgument("dispo", a.getDispo());
           
                  req.addArgument("nomRef", a.getNomRef());
                req.addArgument("img", a.getPhoto());
            req.addArgument("categorie", String.valueOf(a.getCategorie_id()));
            
           

            req.addResponseListener((response) -> {
                byte[] data = (byte[]) response.getMetaData();
                String s = new String(data);
                // System.out.println("data : " + s);
            });

            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public boolean supprimer(Produit p)
    { ConnectionRequest con = new ConnectionRequest();
        String url = "http://127.0.0.1:8000/annonceApi/delete/"+ p.getId(); 
        con.setUrl(url);// Insertion de l'URL de notre demande de connexion
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
    public void UpdateLikesAnnonce(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/annonceApi/likes/" + id;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void supprimerAnnonce(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/annonceApi/delete/" + id;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Produit> parseListTaskJsonAnnonce(String json) throws ParseException {

        ArrayList<Produit> listAnnonce = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {

                Produit e = new Produit();
                Map<String, Object> category = (Map<String, Object>) obj.get("categorie");
                System.out.println(category);
                
                
                float idc = Float.parseFloat(category.get("id").toString());
       
                float id = Float.parseFloat(obj.get("idprod").toString());
                
                e.setId((int) id);
                e.setNomProd(obj.get("nomprod").toString());
                e.setDescription(obj.get("description").toString());
                e.setPrix(Double.parseDouble(obj.get("prix").toString()));
                e.setNomRef(obj.get("nomref").toString());
               
                e.setCategorie_id((int) idc);
           
                e.setDispo(obj.get("dispo").toString());
                e.setPhoto(obj.get("img").toString());
                e.setViews((int) Float.parseFloat(obj.get("views").toString()));
                e.setLikes((int) Float.parseFloat(obj.get("likes").toString()));
                e.setNote( Float.parseFloat(obj.get("note").toString()));
                System.out.println(e);
                listAnnonce.add(e);

            }

        } catch (IOException ex) {
        }

        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        // System.out.println(listAnnonce);
        return listAnnonce;

    }

    public ArrayList<Categorie_Produit> parseListTaskJsonCat(String json) throws ParseException {

        ArrayList<Categorie_Produit> ListCat = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {

                Categorie_Produit e = new Categorie_Produit();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setLibelle(obj.get("libelle").toString());
                //System.out.println(e);

                ListCat.add(e);

            }

        } catch (IOException ex) {
        }

        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        //System.out.println(ListCat);
        return ListCat;

    }


    public ArrayList<Produit> getAllAnnonces() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/annonceApi/");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
                try {
                    listAnnonce = ser.parseListTaskJsonAnnonce(new String(con.getResponseData()));
                     System.out.println(listAnnonce);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnnonce;
    }

    public ArrayList<Categorie_Produit> getAllCategories() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/annonceApi/categories");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
                try {
                    listCat = ser.parseListTaskJsonCat(new String(con.getResponseData()));
                    //  System.out.println(listCat);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCat;
    }

    public ArrayList<Produit> getAnnonceById(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/annonceApi/annonce/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
                try {
                    listAnnonce = ser.parseListTaskJsonAnnonce(new String(con.getResponseData()));
                    //System.out.println(listTasks);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnnonce;
    }

    public ArrayList<Produit> getAnnoncesByCategorie(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/annonceApi/categorie/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
                try {
                    listAnnonce = ser.parseListTaskJsonAnnonce(new String(con.getResponseData()));
                    //System.out.println(listTasks);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnnonce;
    }

    public void UpdateViwesAnnonce(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/annonceApi/viwes/"+id;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
     public void UpdateNoteAnnonce(int id,float note) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/annonceApi/notes/"+id+"/"+note;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

  
    public ArrayList<Produit> tirerAnnonces(String tr) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/annonceApi/trier/" + tr);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
                try {
                    listAnnonce = ser.parseListTaskJsonAnnonce(new String(con.getResponseData()));
                    //System.out.println(listTasks);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnnonce;
    }
    
    public ArrayList<Produit> recherchAnnonces(String key) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/annonceApi/recherche?keyWord=" + key);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
                try {
                    listAnnonce = ser.parseListTaskJsonAnnonce(new String(con.getResponseData()));
                    //System.out.println(listTasks);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnnonce;
    }
       
    
    
}
