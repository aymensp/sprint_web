/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Event;
import entities.Volontaire;
import static gui.EventF.event;
import services.EventService;
import services.VolontaireService;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class listVol extends BaseForm {
    private Resources res;
     Container events;
      public listVol(Resources res){
        //this(com.codename1.ui.util.Resources.getGlobalResources());
      //  super("Liste des Volontaires", BoxLayout.y());
        super("Show Product", BoxLayout.y());
        this.res = res;
  
        events = new Container(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            new AfficherBack(res).show();
        });
        getContentPane().setScrollVisible(false);
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        super.addSideMenu(res);
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Volenteer List", barGroup);
        all.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, all),
                FlowLayout.encloseBottom(arrow)
        ));
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(false);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
         SimpleDateFormat sm = new SimpleDateFormat("yyyy/MM/dd");
       
       // lb = new SpanLabel("");
       // f.add(lb);
        VolontaireService service=new VolontaireService();
       // lb.setText(e.getList2().toString());
     ArrayList<Volontaire> a = service.getList(AfficherBack.event) ;
       
        for (Volontaire ev : a) 
       {
           
                Container eventContainer=new Container();
             Container detailsContainer=new Container(BoxLayout.y());
            ImageViewer imgv=new ImageViewer(res.getImage("ef.png"));
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
            
             Button Modifier = new Button("Modifier"); 
           Modifier.addActionListener((e)->
           {
           
         new ModifVol(ev,res).show();
           
           });
          // eventContainer.setLeadComponent(Modifier);
          detailsContainer.add(Modifier);
        eventContainer.add(imgv);
        eventContainer.add(detailsContainer);
         events.add(eventContainer);
       }
         
//      this.add(events);
super.add(events);
        
    
        
        
        
                
               
      
      }
      
private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
                
            }
        });
    }
   
    
    
}
