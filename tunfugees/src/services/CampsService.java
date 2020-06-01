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
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import entities.Camps;
import entities.Refugies;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hp
 */
public class CampsService {
     private Boolean responseResult;
     public Camps c;
    public ArrayList<Camps> listeEvents(String json) throws ParseException {

        ArrayList<Camps> listEvent = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> events = (List<Map<String, Object>>) event.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : events) {
                //Création des tâches et récupération de leurs données
                Camps a = new Camps();

                float id = Float.parseFloat(obj.get("idcamp").toString());
                a.setIdCamp((int) id);
                a.setNomCamp(obj.get("Categories").toString());
                a.setAdresse(obj.get("adresse").toString());

                 float nbrmax = Float.parseFloat(obj.get("nbrmax").toString());
                a.setNbrmax((int)nbrmax);

   
                 
                 
           
                 
                System.out.println(a);

                listEvent.add(a);

            }

        } catch (IOException ex) {
        }

        System.out.println(listEvent);
        return listEvent;

    }
     public Camps getUserEntity(String json) {
        Camps a= null;
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));

            if (obj.size() == 0) {
                return null;
            }
            a = new Camps();
            float id = Float.parseFloat(obj.get("idcamp").toString());
                a.setIdCamp((int) id);
                a.setNomCamp(obj.get("categories").toString());
                a.setAdresse(obj.get("adresse").toString());

                 float nbrmax = Float.parseFloat(obj.get("nbrmax").toString());
                a.setNbrmax((int)nbrmax);

   
            //u.setProfilepicture("" + obj.get("profilePicture")); Au cas ou
        } catch (IOException ex) {
        }
        return a;
    }

    ArrayList<Camps> listEvents = new ArrayList<>();

    public ArrayList<Camps> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/Ca/afficher");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    //ServiceTask ser = new ServiceTask();
                    listEvents = (new CampsService()).listeEvents(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("erroe");
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;
    }
    public boolean ajouter(Camps g) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/Ca/new/"+ g.getNbrmax()+"/"+ g.getNomCamp()+"/"+ g.getAdresse());
           
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return responseResult;
    }
    public boolean supprimer(Camps gm)
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/Ca/back/delete/"+gm.getIdCamp());
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
     public boolean modifier(Camps g)
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/Ca/viwes/"+ g.getIdCamp()+"/"+ g.getNbrmax()+"/"+g.getNomCamp()+"/"
             +g.getAdresse());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return responseResult;
    }
     Camps cp = new Camps();
      public Camps getList(Refugies r) {//VolontaireeeeeeeSSSS
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/front/Camps/"+r.getIdref());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //ServiceTask ser = new ServiceTask();
                c = (new CampsService()).getUserEntity(new String(con.getResponseData()));
                System.out.println(c);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return c;
    }

    
    
}
