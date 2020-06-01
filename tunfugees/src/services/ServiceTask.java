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
import com.codename1.l10n.DateFormat;
import com.codename1.ui.Calendar;
import com.codename1.ui.events.ActionListener;
import entities.CatStock;
import entities.Stock;
import entities.Reclamation;
import Utils.staticss;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceTask {

    public ArrayList<Stock> tasks;
        public ArrayList<CatStock> CategStock;

    public ArrayList<Reclamation> recs;
    public static ServiceTask instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTask() {
         req = new ConnectionRequest();
    }

    public static ServiceTask getInstance() {
        if (instance == null) {
            instance = new ServiceTask();
        }
        return instance;
    }
// add Reclamation
    public boolean addTask(Reclamation t) {
        String url = staticss.BASE_URL + "/tasks/new?category=1&createur="+t.getCreateur()+"&title=" + t.getTitle() + "&description=" + t.getDescription()+ "&photo=NULL&postdate=" ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    public boolean addStock(Stock t) {
                System.out.println(t.getCat_id());

        String url = staticss.BASE_URL + "/stock/new?category="+t.getCat_id()+"&createur=1&title=" + t.getTitle() + "&description=" + t.getDescription()+ "&photo=NULL&postdate=" ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
// stock Json
    public ArrayList<Stock> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Stock t = new Stock();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);

               // float cat_id = Float.parseFloat(obj.get("cat_id").toString());
                
              //  t.setPrix((Float.parseFloat(obj.get("prix").toString())));
                t.setTitle(obj.get("title").toString());
               // t.setEtat(obj.get("etat").toString());
                t.setDescription(obj.get("description").toString());
               // t.setTel(obj.get("tel").toString());
               // t.setPostdate(obj.get("postdate").);
              //  t.setClient( obj.get("client").toString());
              
                tasks.add(t);
            }
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    
    
    // Catstock Json
    public ArrayList<CatStock> parseCatStock(String jsonText){
        try {
            CategStock=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                CatStock t = new CatStock();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
              //  t.setPrix((Float.parseFloat(obj.get("prix").toString())));
                t.setNomcateg(obj.get("nomcateg").toString());
              //  t.setClient( obj.get("client").toString());
              
                CategStock.add(t);
            }
            
        } catch (IOException ex) {
            
        }
        return CategStock;
    }
  
     //Catstock get All 
    public ArrayList<CatStock> getAllCatStock(){
        String url = staticss.BASE_URL+"/Catstock/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CategStock = parseCatStock(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return CategStock;
    }
   
    
    
    
    
    
    public ArrayList<Reclamation> parseRecs(String jsonText){
        try {
            recs =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Reclamation t = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setTitle(obj.get("title").toString());
                t.setDescription(obj.get("description").toString());
                             //   t.setCreateur(obj.get("createur").toString());
/*
                                               float Cat_id = Float.parseFloat(obj.get("createur").toString());
                                              t.setCat_id((int)Cat_id);

                */
                
                System.out.println(t.getCreateur());
/*
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
                Object date = t.getPostdate();
                String strDate = dateFormat.format(date);  
                t.setPostdate(obj.dateFormat.format(date);.toString());*/
               // t.setLivraisonId(obj.get("livraison").toString());
             
                recs.add(t);
            }
            
        } catch (IOException ex) {
            
        }
        return recs;
    }
    //stock get All 
    public ArrayList<Stock> getAllTasks(){
        String url = staticss.BASE_URL+"/stock/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
     public boolean deleteTask(int id){
        String url = staticss.BASE_URL+"/stock/delete/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
     
     public boolean editTask(int id, String title, String description){
        String url = staticss.BASE_URL+"/stock/update/"+id+"/"+title+"/"+description;
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
      public boolean deleteRec(int id){
        String url = staticss.BASE_URL+"/tasks/delete/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
     
     public boolean editRec(int id, String title, String description){
        String url = staticss.BASE_URL+"/tasks/update/"+id+"/"+title+"/"+description;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
    
     public ArrayList<Reclamation> getAllRecs(int id){
        String url = staticss.BASE_URL+"/reclamation1/all/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                recs = parseRecs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return recs;
    }
}
