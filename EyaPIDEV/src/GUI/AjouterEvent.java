/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import Entities.Event;

import java.util.ArrayList;
import Services.EventService;
import com.codename1.capture.Capture;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.notifications.LocalNotification;

import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;

    




/**
 *
 * @author syrine
 */
public class AjouterEvent extends com.codename1.ui.Form{
     private Resources theme;
     String pa="";
    private int id_user_actif = 9;
     public AjouterEvent() {
    //this(com.codename1.ui.util.Resources.getGlobalResources());
         setTitle("Mes Evenements");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("Nouveau evenement", cnt1);
        
        add(tab);
        //cnt1.add(new SpanLabel(new ServiceGroupeMembre().Afficher().toString()));
      
        ArrayList<Event> list_e = new EventService().getList();
         Picker p=new Picker();
         Picker pt=new Picker();
         
         p.setType(Display.PICKER_TYPE_DATE);
         p.setFormatter(new SimpleDateFormat("yyyy-MM-dd"));
      
        // pt.setFormatter(new SimpleDateFormat("HH:mm"));
         
         TextField nom = new TextField(null, "Nom");//textfield
        // AutoCompleteTextField lieu = new AutoCompleteTextField(null, "adresse");
      final DefaultListModel<String> options = new DefaultListModel<>();
      AutoCompleteTextField lieu = new AutoCompleteTextField(options) {
      @Override
      protected boolean filter(String text) {
          if(text.length() == 0) {
              return false;
          }
          String[] l = searchLocations(text);
          if(l == null || l.length == 0) {
              return false;
          }
  
          options.removeAll();
          for(String s : l) {
              options.addItem(s);
          }
          return true;
      }
  
  };
  lieu.setMinimumElementsShownInPopup(8);
 
       
         TextField description = new TextField(null, "Description");//textfield
         TextField nbplace = new TextField(null, "nbplace");
         TextField image = new TextField(null, "image");
         Button Cap = new Button ("Importer");
         Label Photo = new Label();
         Cap.addActionListener((evt) -> {
             String i =Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
             if(i!=null)
             {
               try {
                 //  String path = "file://Users/"
                  String pathToBeStored = "file://Users/user/AppData/Local/"+ System.currentTimeMillis() +  ".jpg";
                    Image img;
                     img = Image.createImage(i);
                     Photo.setIcon(img);
                     revalidate();
                     OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored );
                     ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_JPEG, 0.9f);
                  //  FileSystemStorage.getInstance().getAppHomePath(); 
                     pa="C:"+pathToBeStored.substring(5);
                os.close();
                System.out.println(pa);
                  //   file://C:/Users/user/AppData/Local/Temp/temp5546755409013724979s..jpg
                   // String path = i.substring(i.length()-6, i.length());
                     //String path = i.replace('/',',');  
                   //  C://Users/user/AppData/Local/1589595372982.jpg
                   //pa=pathToBeStored.substring(pathToBeStored.length()-14,pathToBeStored.length());

                     image.setText("04.jpg");
                     
                     
                     this.getComponentForm().revalidate();
                 } catch (Exception ex) {
                    ex.printStackTrace();
                 }

             }
                 
                     
         });
          Button save = new Button("Enregistrer");
           System.out.println(pt.getText());
          save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //ajouter groupe
                EventService sg = new EventService();  
              
                System.out.println(p.getText());
                try {
                    if (nom.getText()==""|| lieu.getText()==""||p.getText()==""||description.getText()==""||nbplace.getText()==""||image.getText()=="")
                   Dialog.show("ERREUR", "Champ(s) vide(nt)",null, "ok");
                    else { 
                        if(Integer.parseInt(nbplace.getText())>0)
                    {
                  //  System.out.println("eyaaaaaa!!");
                    sg.ajouter(new Event(nom.getText(), lieu.getText(),p.getText(), description.getText(),Integer.parseInt(nbplace.getText()), image.getText()));
                     Dialog.show("Ajout", "Ajouté avec succés",null,"ok");
                         new AfficherBack().show();
                    } else 
                         Dialog.show("ERREUR", "le nombre maximum est negatif ou null",null, "ok");
                    }  
                } catch (IOException ex) {
ex.printStackTrace();
                }
            
                
                System.out.println(pt.getText());
  
            
                
            }
        });
        
        
        cnt1.setLayout(BoxLayout.y());
        cnt1.addAll(nom,lieu,p,description,nbplace,image,Cap,save,Photo);
        
        
        
          this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
           // new AfficherBack().show();
        });
          
           
 /* this.add(new SpanLabel("This demo requires a valid google API key to be set below "
           + "you can get this key for the webservice (not the native key) by following the instructions here: "
           + "https://developers.google.com/places/web-service/get-api-key"));*/
  apiKey.setText("AIzaSyD8gVLOFa4zw9_WM4uVxOSvBSUzOj8KcrE");
  //this.add(apiKey);
 // this.getToolbar().addCommandToRightBar("Get Key", null, e -> Display.getInstance().execute("https://developers.google.com/places/web-service/get-api-key"));
  this.show();
          
          
          
          
          
          
          
          
          
          
          
     }
     
     
     TextField apiKey = new TextField();

     String[] searchLocations(String text) {        
    try {
        if(text.length() > 0) {
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json"); 

            r.addArgument("key", apiKey.getText());
            r.addArgument("input", text);
            NetworkManager.getInstance().addToQueueAndWait(r);
            Map<String,Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            String[] res = Result.fromContent(result).getAsStringArray("//description");
            return res;
        }
    } catch(Exception err) {
      err.printStackTrace();
    }
    return null;
}

}