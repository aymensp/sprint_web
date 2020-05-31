/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Refugies;
import java.util.ArrayList;
import services.RefugiesService;

/**
 *
 * @author hp
 */
public class OURrefugies extends Form{
   
    static  Form f;
   // SpanLabel lb;
     Label titre ; 
    Label date ;
    //static Evenement event ;
    ImageViewer img ; 
    Label User ;
    Label lieu;
    Label description;
    Label nbVues;
    Resources res;
   
  
    public OURrefugies()   {
        
        super("Our Refugees");
          SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
         Container events = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       // lb = new SpanLabel("");
       // f.add(lb);
        RefugiesService service=new RefugiesService();
       // lb.setText(e.getList2().toString());
     ArrayList<Refugies> a = service.getList2() ;
        //System.out.println(AffichageCatEvts.cat);
        for (Refugies ev : a) 
       {
            Container eventContainer = new Container (new BoxLayout(BoxLayout.X_AXIS));
            Container detailsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            img = new ImageViewer();
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
            URLImage urlImage = URLImage.createToStorage(placeholder, ev.getImage(), "http://localhost/tunfugees/web/images/refugies/"+ev.getImage());
            img.setImage(urlImage);
            eventContainer.add(img);
           titre= new Label();
           lieu=new Label();
           description=new Label();
           date = new Label();
            nbVues = new Label();
           titre.setText(ev.getNom());
           lieu.setText(ev.getPays());
          // description.setText(ev.getDescription());
           date.setText(ev.getAge()+"");
           nbVues.setText(ev.getPrenomn()+"");
          // nbVues.setIcon(FontImage.createMaterial(FontImage.MATERIAL_REMOVE_RED_EYE, nbVues.getUnselectedStyle()));
           /* f.getToolbar().addCommandToLeftBar("Retour", null, e ->{
            new AffichageCatEvts(res);
            AffichageCatEvts.f.showBack() ;});*/
           
           
            
           
           detailsContainer.add(titre);
           detailsContainer.add(lieu);
           detailsContainer.add(description);
           detailsContainer.add(date);
           detailsContainer.add(nbVues);
           
           eventContainer.add(detailsContainer);
           
           events.add(eventContainer);
       }
       
        this.add(events);
        this.show();
         this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new Interface().show();
        });
    }
    
}
