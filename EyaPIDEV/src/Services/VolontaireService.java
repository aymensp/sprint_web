/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Event;
import Entities.Volontaire;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class VolontaireService {
        private boolean responseResult;
        private int i ;
        private boolean test;
        public Volontaire U;
        String s; 

    public ArrayList<Volontaire> listeVolons(String json) throws ParseException {

        ArrayList<Volontaire> listVolon = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> events = (List<Map<String, Object>>) event.get("root");
                   
            //Parcourir la liste des tâches Json
           for (Map<String, Object> obj : events) {
                
                //Création des tâches et récupération de leurs données
                 Volontaire a = new Volontaire();

                float id = Float.parseFloat(obj.get("idVol").toString());
                a.setId_vol((int) id);
                System.out.println(a.getId_vol());
//                a.setCin(Integer.parseInt(obj.get("cin").toString()));
                a.setNom(obj.get("nom").toString());
                //System.out.println(a.getNomEvnet());
                
                a.setPrenom(obj.get("prenom").toString());
                a.setMail(obj.get("mail").toString());
                 float tel=(Float.parseFloat(obj.get("tel").toString()));
                a.setTel((int) tel);
                a.setNom_event(obj.get("nomEvent").toString());
                a.setPresence(obj.get("presence").toString());
                
                System.out.println(a);

                listVolon.add(a);

            }

        } catch (IOException ex) {
        }
        
        System.out.println(listVolon);
        return listVolon;

    }
        public Volontaire getUserEntity(String json) {
        Volontaire a= null;
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));

            if (obj.size() == 0) {
                return null;
            }
            a = new Volontaire();
            float id = Float.parseFloat(obj.get("idVol").toString());
                a.setId_vol((int) id);
                //System.out.println(a.getId_vol());
                a.setCin(Integer.parseInt(obj.get("cin").toString()));
                a.setNom(obj.get("nom").toString());
                //System.out.println(a.getNomEvnet());
                
                a.setPrenom(obj.get("prenom").toString());
                a.setMail(obj.get("mail").toString());
                 float tel=(Float.parseFloat(obj.get("tel").toString()));
                a.setTel((int) tel);
                a.setNom_event(obj.get("nomEvent").toString());
                a.setPresence(obj.get("presence").toString());

            //u.setProfilepicture("" + obj.get("profilePicture")); Au cas ou
        } catch (IOException ex) {
        }
        return a;
    }

    ArrayList<Volontaire> listVolons = new ArrayList<>();
    Volontaire Volontaire = new Volontaire();

    public ArrayList<Volontaire> getList(Event v) {//LISTE DES VOLONTAIRES FIL EVENT
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/sprint_web-eyaaa/tunfugees/web/app_dev.php/events/jsonlist/"+v.getId_event());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    //ServiceTask ser = new ServiceTask();
                    listVolons = (new VolontaireService()).listeVolons(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("error");
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listVolons;
    }
    public ArrayList<Volontaire> getList2() {//VolontaireeeeeeeSSSS
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/sprint_web-eyaaa/tunfugees/web/app_dev.php/events/jsonlist2");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    //ServiceTask ser = new ServiceTask();
                    listVolons = (new VolontaireService()).listeVolons(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("error");
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listVolons;
    }
       public boolean modifier(Volontaire g)
    {  ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/sprint_web-eyaaa/tunfugees/web/app_dev.php/volontaire/jsonedit2/"+g.getId_vol()+"/" + g.getPresence();
        con.setUrl(url);// Insertion de l'URL de notre demande de connexion
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
       public boolean ajouter(Volontaire g, Event e) {
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/sprint_web-eyaaa/tunfugees/web/app_dev.php/volontaire/jsonadd2/" 
            +e.getId_event()+"/"+ g.getNom()+"/"+g.getPrenom()+"/"+g.getMail()+"/"   
            +g.getNom_event()+"/"+g.getTel(); //création de l'URL                                                       
        con.setUrl(url);// Insertion de l'URL de notre demande de connexion
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this); //Sup     primer cet actionListener
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
       
       
       
       
       
       
       
       public String verifParticiper (Event ev,String mail)
    { 
        
         ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/tunfugees/web/app_dev.php/verif/"+ev.getId_event()+"/"+mail; // création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
 
              con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              //  VolontaireService ser = new VolontaireService();
              //  System.out.println(new String(con.getResponseData()));
               s =new String(con.getResponseData());

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
      
    return s;
     
    
    
}  

       
        public boolean noparticiper(int id, String mail){
            ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/sprint_web-eyaaa/tunfugees/web/app_dev.php/volontaire/jmscancel/"+id+"/"+mail; //création de l'URL                                                       
        con.setUrl(url);// Insertion de l'URL de notre demande de connexion
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);  //Sup     primer cet actionListener
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
    

