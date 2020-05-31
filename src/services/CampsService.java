/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import entities.Camps;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class CampsService {
    
    ArrayList<Camps> listcamps = new ArrayList<>();
   //ArrayList<Categorie_Annonce> listCat = new ArrayList<>();
    public void ajoutercamp(Camps a) {
        String Url = "http://localhost/tunfugees/web/app_dev.php/new";
        //String url = Statics.BASE_URL + "/tasks/" + t.getName() + "/" + t.getStatus(); //création de l'URL
        MultipartRequest req = new MultipartRequest();
        try {
            req.setUrl(Url);
            req.setPost(true);
            req.addArgument("nbremax", a.getNomCamp());
            req.addArgument("nomCamp", a.getNomCamp());
            req.addArgument("adresse", a.getAdresse());
         
            

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

    
}
