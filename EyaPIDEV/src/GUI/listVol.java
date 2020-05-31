/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Event;
import Entities.Volontaire;
import static GUI.EventF.event;
import Services.EventService;
import Services.VolontaireService;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class listVol extends Form {
    private Resources theme;
    
      public listVol(){
        //this(com.codename1.ui.util.Resources.getGlobalResources());
      //  super("Liste des Volontaires", BoxLayout.y());
        setLayout(BoxLayout.y()); 
      Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        
       theme = UIManager.initFirstTheme("/theme");
         SimpleDateFormat sm = new SimpleDateFormat("yyyy/MM/dd");
          Container events = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
  
          tab.addTab("Liste des volontaires", events);
          this.add(tab);
          this.show();
       // lb = new SpanLabel("");
       // f.add(lb);
        VolontaireService service=new VolontaireService();
       // lb.setText(e.getList2().toString());
     ArrayList<Volontaire> a = service.getList(AfficherBack.event) ;
       
        for (Volontaire ev : a) 
       {
           
                Container eventContainer=ui.createContainer(theme, "GUI 2");
             Container detailsContainer=new Container(BoxLayout.y());
            ImageViewer imgv=new ImageViewer(theme.getImage("ef.png"));
           /* img = new ImageViewer();
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
            URLImage urlImage = URLImage.createToStorage(placeholder, ev.getImage(), );
            img.setImage(urlImage);
           */ 
         //  eventContainer.add(imgv);
           Label nom= new Label();
           Label prenom=new Label();
           Label mail =new Label();
           Label tel =new Label();
          
           
           Label presence = new Label();
          
           nom.setText("Nom :"+ev.getNom());
           prenom.setText("Prénom :"+ev.getPrenom());
           tel.setText("Tel: "+String.valueOf(ev.getTel()));
           //description.setText(ev.getDescription());
           mail.setText("Email: "+ev.getMail());
           presence.setText("Présence: "+ev.getPresence());
           
           /* f.getToolbar().addCommandToLeftBar("Retour", null, e ->{
            new AffichageCatEvts(res);
            AffichageCatEvts.f.showBack() ;});*/
           
           
            
           
           detailsContainer.add(nom);
           detailsContainer.add(prenom);
           detailsContainer.add(tel);
           detailsContainer.add(mail);
           detailsContainer.add(presence);
           
           //eventContainer.add(detailsContainer);
           Button sep = new Button(); 
             Button Modifier = new Button("Modifier"); 
           Modifier.addActionListener((e)->
           {
           
         new ModifVol(ev).show();
           
           });
          // eventContainer.setLeadComponent(Modifier);
          detailsContainer.add(Modifier).add(sep);
        eventContainer.add(imgv);
        eventContainer.add(detailsContainer);
         events.add(eventContainer);
       }
         
//      this.add(events);
        this.show();
    
        
        
        
     
              this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new AfficherBack().show();
        });              
               
      
      }
      

   
    
    
}
