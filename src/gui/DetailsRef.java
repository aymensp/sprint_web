/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import entities.Camps;
import java.util.ArrayList;
import services.CampsService;
import services.RefugiesService;

/**
 *
 * @author hp
 */
public class DetailsRef {
       Label titre ; 
    Label date ;
    static Form f;
    ImageViewer img ; 
    Label User ;
    Label lieu;
    Label description;
    Label txtp;
    Label txtnop;
    ImageViewer participerimg ;
    ImageViewer noparticiperimg;
    ShareButton sb;
    Button participer;
    Button noparticiper;
    Label participants;
    
     public DetailsRef(){
       
    
        f= new Form("",new BoxLayout(BoxLayout.Y_AXIS));
      //  UserService serviceuser =new UserService();
       // l= new Label();
        titre = new Label();
        date = new Label ();
        lieu = new Label();
        description = new Label();
     
         
         
       
       txtp=new Label();
       txtnop=new Label();
       txtp.setVisible(false);
     
        
        RefugiesService es=new RefugiesService();
         CampsService cs = new CampsService();
          Camps a = cs.getList(OURrefugies.event) ;
       
       
       titre.setText("Profil de  "+OURrefugies.event.getNom());
        titre.getAllStyles().setFgColor(ColorUtil.rgb(0,128,0));
       // lieu.setText("Qui se tiendra à : "+OURrefugies.event.getPrenomn());
        
       // participants.setText("les participants sont :"+AffichageEvents.event.getParticipants());
       
        
        //**********************
       
        //**********************
        
        
        SpanLabel nom  = new SpanLabel("Nom : "+OURrefugies.event.getNom());
        
        
        
        
        SpanLabel prenom  = new SpanLabel("Prenom : "+OURrefugies.event.getPrenomn());
        
        
        
        
        SpanLabel age  = new SpanLabel("Age : "+Integer.toString (OURrefugies.event.getAge()));
        
        
        
        SpanLabel pays  = new SpanLabel("Pays : "+ OURrefugies.event.getPays());
        SpanLabel adresse  = new SpanLabel();
  
       
        adresse.setText("Adresse Camp : "+a.getAdresse());
     
      
        
        
        
        
        
        
        nom.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        prenom.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        age.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        pays.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
          adresse.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));

        img = new ImageViewer();
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(f.getWidth()/1,f.getHeight()/3),false);
        URLImage urlImage = URLImage.createToStorage(placeholder, OURrefugies.event.getImage(), "http://localhost/tunfugees/web/images/refugies/"+OURrefugies.event.getImage());
        //img.setOpaque(false);
        img.setImage(urlImage);
/*        f.getToolbar().addCommandToLeftBar("Retour", null, e ->{
            new AffichageEvents();
            AffichageEvents.f.showBack() ;});*/
       
        
        
      //  date.setText(sm.format(AffichageEvents.event.getDate()));
     
     


        f.add(titre);
        f.add(date);
        f.add(img);
        f.add(nom);
       
        f.add(prenom);
        f.add(age);
        f.add(pays);
        f.add(adresse);
        
        
      
     
        f.show();
        f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new OURrefugies().show());

        
    }
    
    
}
