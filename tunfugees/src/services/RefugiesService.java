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
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import entities.Camps;
import entities.Refugies;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import Utils.Statics;

/**
 *
 * @author hp
 */
public class RefugiesService {
    private Boolean responseResult;
    public ArrayList<Refugies> listeEvents(String json) throws ParseException {

        ArrayList<Refugies> listEvent = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> events = (List<Map<String, Object>>) event.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : events) {
                //Création des tâches et récupération de leurs données
                Refugies a = new Refugies();
             //   Camps c = new Camps();

                float id = Float.parseFloat(obj.get("idref").toString());
                a.setIdref((int) id);
                a.setNom(obj.get("nom").toString());
                a.setPrenomn(obj.get("prenom").toString());

                 float age = Float.parseFloat(obj.get("age").toString());
                a.setAge((int)age);
                //float camps_id = Float.parseFloat(obj.get("camps").toString());
                //a.setCamps_id((int)camps_id);
              
                a.setImage(obj.get("image").toString());
                a.setPays(obj.get("pays").toString());
                 a.setCamps_id(obj.get("camps").toString());
                 
                 
           
                 
                System.out.println(a);

                listEvent.add(a);

            }

        } catch (IOException ex) {
        }

        System.out.println(listEvent);
        return listEvent;

    }

    ArrayList<Refugies> listEvents = new ArrayList<>();

    public ArrayList<Refugies> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/front/Ref");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    //ServiceTask ser = new ServiceTask();
                    listEvents = (new RefugiesService()).listeEvents(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("erroe");
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;
    }
    public boolean supprimer(Refugies gm)
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/front/back/delete/"+gm.getIdref());
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
     public boolean ajouter(Refugies g) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/front/new/"+ g.getNom()+"/"+ g.getPrenomn()+"/"+ g.getAge()+"/"+ g.getPays()+"/"+ g.getImage());
           
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
    public boolean modifier(Refugies g)
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/front/viwes/"+ g.getIdref()+"/"+ g.getNom()+"/"+g.getPrenomn()+"/"
             +g.getPays()+"/"+g.getAge());
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
    
}
