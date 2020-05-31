/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import Entities.Event;
import Entities.Event_volontaire;
import static GUI.EventF.event;
import java.util.ArrayList;
import Services.EventService;

import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.RadioButton;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;



/**
 *
 * @author syrine
 */
public class AfficherBack extends Form{
    private Resources theme;
    private int id_user_actif = 9;
      static Event event ;
    
    public AfficherBack(){
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
        
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        //this.header("Mes Evénements");
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        
          Container cnt2 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("Mes Evenements", cnt2);
        //creer un groupe 
        Button ajouterBtn = new Button("");
        ajouterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              //  new AjouterEvent().show();
            }
        });
        this.add(tab).add(ajouterBtn);
         this.show();
        
        ArrayList<Event> list_e = new EventService().getList();
      //  ArrayList<ListeParticipant> list_p = new ServiceEvenement().Afficher();
        
       this.getToolbar().addCommandToOverflowMenu("ajouter", theme.getImage("back-command.png"), (et) -> {
             new AjouterEvent().show();});
             
         cnt2.setLayout(BoxLayout.y());
        for(Event gmi :  list_e )
          
        {  
            Button btnvol=new Button("Liste des Volontaires");
            cnt2.add(addItemEvent(gmi)).add(btnvol);
             
           btnvol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             
                 event= gmi ;
                //new ModifEvent(gm).show();
                new listVol().show();
                
            }
        });
          
        }
        this.show();
     
   
                this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new Home().show();
        });              
                                     
                               
       
       
    }
    /* public Container addItemGroupe(Evennement g){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Label lab=new Label(g.getNom());
        Label lab2=new Label(g.getDesciption());
        ServiceParticipant sv=new ServiceParticipant();
        Button btnacc=new Button("rejoindre");
        Button btnreff=new Button("disjoindre");
        Button att=new Button("envoyee");
            btnacc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                new ServiceEvenement().Rejoindre(id_user_actif, g.getIdE());
                new EvenementAfficherInterface().show();
            }
        });
             btnreff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                new ServiceEvenement().disjoindre(id_user_actif, g.getIdE());
                new EvenementAfficherInterface().show();
            }
        });
           
        
       
        ImageViewer imgv=new ImageViewer(theme.getImage("groupes.png"));
        cn2.add(lab).add(lab2);
         if(sv.chercherp(g.getIdE(), id_user_actif).isEmpty())
        {
            cn2.add(btnacc);
        }
        else
         { 
             ArrayList<ListeParticipant> evt = sv.chercherp(g.getIdE(), id_user_actif);
              for(ListeParticipant pr: evt)
              {    if(pr.getEtatp()==0)
                        cn2.add(att);
              
                   if(pr.getEtatp()==1)
                        cn2.add(btnreff);
              }
         }
        cn1.add(BorderLayout.WEST,imgv );
        cn1.add(BorderLayout.CENTER,cn2);
        
  
        return cn1;
    
                
    }*/
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
               new AfficherBack().show();
            }
        });
        Button btnmodifier=new Button("modifier");
       btnmodifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             
                new ModifEvent(gm).show();
            }
        });
        
        Container cn3=new Container(BoxLayout.x());
      
        cn3.add(btnSupp).add(btnmodifier);
        
        ImageViewer imgv = new ImageViewer();
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
        URLImage urlImage = URLImage.createToStorage(placeholder, gm.getImage(), "http://localhost/sprint_web-eyaaa/tunfugees/web/uploads/image/"+gm.getImage());
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
    public void header(String titre){
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
       // super.addSideMenu(res);

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle(titre, barGroup);
        all.setUIID("SelectBar");
        
        Label arrow = new Label(theme.getImage("news-tab-down-arrow.png"), "Container");

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
    

  
}
      

    
}
