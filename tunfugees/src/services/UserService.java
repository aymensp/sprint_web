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

import entities.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rania
 */
public class UserService {
      public ArrayList<Utilisateur> listeUsers(String json) { 

       ArrayList<Utilisateur> listUser = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

       
            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
          
            List<Map<String, Object>> users = (List<Map<String, Object>>) user.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : users) {
                //Création des tâches et récupération de leurs données
                Utilisateur u = new Utilisateur();

                float id = Float.parseFloat(obj.get("id").toString());

                u.setId( (int) id);
                
                u.setUsername(obj.get("username").toString());
              
                System.out.println(u);
                
                listUser.add(u);

            }

        } catch (IOException ex) {
        }
        
  
        System.out.println(listUser);
        return listUser;

    }
    
    
 /*   ArrayList<Utilisateur> listUsers = new ArrayList<>();
    
    public ArrayList<Utilisateur> getParticipantsEvent(Evenement e){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/front/jmsoneevent/"+e.getId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //ServiceTask ser = new ServiceTask();
                listUsers =(new UserService()).listeUsers(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUsers;
    }*/

    
}
