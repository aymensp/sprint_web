/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Event;
import entities.Event_volontaire;
import static gui.EventF.event;
import java.util.ArrayList;
import services.EventService;

import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;



/**
 *
 * @author syrine
 */
public class AfficherBack extends BaseForm{
  Resources res;
    Container cnt;
    private int id_user_actif = 9;
      static Event event ;
    
    public AfficherBack(Resources res){
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
        super("Show Event", BoxLayout.y());
        this.res = res;
         
        cnt = new Container(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            new AccueilForm(res).show();
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
        RadioButton all = RadioButton.createToggle("All Events", barGroup);
       
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
        //creer un groupe 
        Button ajouterBtn = new Button("");
        ajouterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              //  new AjouterEvent().show();
            }
        });
        super.add(cnt).add(ajouterBtn);
         this.show();
        
        ArrayList<Event> list_e = new EventService().getList();
      //  ArrayList<ListeParticipant> list_p = new ServiceEvenement().Afficher();
       
       getToolbar().addCommandToOverflowMenu("ajouter", res.getImage("back-command.png"), (et) -> {
             new AjouterEvent(res).show();});
             
         cnt.setLayout(BoxLayout.y());
        for(Event gmi :  list_e )
          
        {  
            Button btnvol=new Button("Liste des Volontaires");
            cnt.add(addItemEvent(gmi)).add(btnvol);
             
           btnvol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             
                 event= gmi ;
                //new ModifEvent(gm).show();
                new listVol(res).show();
                
            }
        });
          
        }
        this.show();
     
   
                  
                                     
                               
       
       
    }
    
      public Container addItemEvent(Event gm){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Label lab=new Label(gm.getNomEvent());
        Label lab2=new Label("A "+gm.getAdresse());
         Label lab3=new Label("Le: "+gm.getDate());
          Label lab4= new Label();
                  lab4.setText("Il supporte "+String.valueOf(gm.getNbrMax())+" de volontaires");
        
        Button btnSupp=new Button("supprimer");
        btnSupp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new EventService().supprimer(gm);
               new AfficherBack(res).show();
            }
        });
        Button btnmodifier=new Button("modifier");
       btnmodifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             
                new ModifEvent(gm,res).show();
            }
        });
        
        Container cn3=new Container(BoxLayout.x());
      
        cn3.add(btnSupp).add(btnmodifier);
        
        ImageViewer imgv = new ImageViewer();
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
        URLImage urlImage = URLImage.createToStorage(placeholder, gm.getImage(), "http://127.0.0.1:8000/uploads/Produit/photo/"+gm.getImage());
         imgv.setImage(urlImage);
        cn2.add(lab).add(lab2).add(lab3).add(lab4).add(cn3);
        cn1.add(BorderLayout.WEST,imgv);
        cn1.add(BorderLayout.CENTER,cn2);
        
        return cn1;
        
                
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
