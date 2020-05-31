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
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class EventService {
    private boolean responseResult;
    private boolean test;
    
    public ArrayList<Event> listeEvents(String json) throws ParseException {

        ArrayList<Event> listEvent = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> events = (List<Map<String, Object>>) event.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : events) {
                //Création des tâches et récupération de leurs données
                Event a = new Event();

                float id = Float.parseFloat(obj.get("id").toString());
                a.setId_event((int) id);
                System.out.println(a.getId_event());
                a.setNomEvent(obj.get("nomevent").toString());
                //System.out.println(a.getNomEvnet());
                
                a.setDescription(obj.get("description").toString());
                a.setAdresse(obj.get("adresse").toString());
                a.setImage(obj.get("image").toString());
                a.setDate(obj.get("date").toString());
                float nbrmax = Float.parseFloat(obj.get("nbrmax").toString());
                a.setNbrMax((int) nbrmax);
               // a.setNbrMax(obj.get("nbrmax").toString());
              
                
                System.out.println(a);

                listEvent.add(a);

            }

        } catch (IOException ex) {
        }

        System.out.println(listEvent);
        return listEvent;

    }

    ArrayList<Event> listEvents = new ArrayList<>();

    public ArrayList<Event> getList() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/sprint_web-eyaaa/tunfugees/web/app_dev.php/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    //ServiceTask ser = new ServiceTask();
                    listEvents = (new EventService()).listeEvents(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("error");
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;
    }
        public boolean modifier(Event g)
    {  ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/sprint_web-eyaaa/tunfugees/web/app_dev.php/events/jsonedit/" 
                + g.getId_event()+ "/" + g.getNomEvent()+"/"+g.getAdresse()+"/"+g.getDate()
             +"/"+g.getDescription()+"/"+g.getNbrMax()+"/"+g.getImage();  
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
    public boolean ajouter(Event g) throws IOException {
        MultipartRequest request = new MultipartRequest();

        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/sprint_web-eyaaa/tunfugees/web/app_dev.php/events/jsonadd/" 
            + g.getNomEvent()+"/"+g.getAdresse()+"/"+g.getDate()+"/"   
            +g.getDescription()+"/"+g.getNbrMax()+"/"+g.getImage(); //création de l'URL                                                       
       // request.setUrl(url);// Insertion de l'URL de notre demande de connexion
        request.setUrl(url);
      //  FileSystemStorage.getInstance().getAppHomePath();
      //  request.addData("", g.getImage(), "text/plain");
      
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; //Code HTTP 200 OK
                request.removeResponseListener(this); //Sup     primer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseResult;
    }
        public boolean supprimer(Event gm)
    { ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/sprint_web-eyaaa/tunfugees/web/app_dev.php/events/jsondelete/"+ gm.getId_event(); 
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

   
        
}
