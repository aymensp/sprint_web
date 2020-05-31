    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;


import Entities.Event;
import Entities.Volontaire;
//import entities.ParticiperEvent;


import Services.EventService;
import Services.VolontaireService;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.RadioButton;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import doryan.windowsnotificationapi.fr.Notification;
import java.util.ArrayList;


//import services.UserService;

/**
 *
 * @author Rania
 */
public class DetailsEvent extends Form {
    
    Label titre ; 
    Label date ;
    // Form f;
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
    private Resources theme;
      public int i =0;
    
     public DetailsEvent(){
       
     //   SimpleDateFormat sm = new SimpleDateFormat("EEE, MMM d, yyyy");
        super("Details",new BoxLayout(BoxLayout.Y_AXIS));
           theme = UIManager.initFirstTheme("/theme");
        this.header("Details");
      //  UserService serviceuser =new UserService();
       // l= new Label();
        titre = new Label();
        date = new Label ();
        lieu = new Label();
        description = new Label();
       participants= new Label();
        participerimg = new ImageViewer();
         noparticiperimg = new ImageViewer();
          participer=new Button("Participer");
          noparticiper=new Button("Ne Plus participer");
       
       txtp=new Label();
       txtnop=new Label();
       txtp.setVisible(false);
        txtnop.setVisible(false);
        participer.setVisible(false);
        noparticiper.setVisible(false);
        
        VolontaireService es=new VolontaireService();
        //ParticiperEvent join=new ParticiperEvent(AfficherBack.event,Utilisateur.current_user); 
        Volontaire v = new Volontaire();
        String mail="eya.bellllil";//mail useeeeeeeeeer
    String S="";
     ArrayList<Volontaire> a = es.getList(EventF.event) ;
      
      for (Volontaire ev : a) 
       {
         i =i+1;
           if (ev.getNom_event().equals(EventF.event.getNomEvent())&&ev.getMail().equals(mail))
           { S="true";
           
           }
           else S="false";
       }
       //System.out.println(EventF.event.getId_event());
    if(S.equals("false"))
             
     {
          // System.out.println(es.verifParticiper(EventF.event,mail));  
        participer.setVisible(true);
       // participerimg.setVisible(true);
        
        noparticiper.setVisible(false);
       // noparticiperimg.setVisible(false);
     }
    if(S.equals("true"))
       {
        participer.setVisible(false);
       //  participerimg.setVisible(false);
         
        noparticiper.setVisible(true);
      //  noparticiperimg.setVisible(true);
        }
       
       titre.setText("Découvrez l'évènement "+EventF.event.getNomEvent());
        titre.getAllStyles().setFgColor(ColorUtil.rgb(0,128,0));
        lieu.setText("Qui se tiendra à : "+EventF.event.getAdresse());
        date.setText("Le : " +EventF.event.getDate());
      //  participants.setText("les volontaires sont :"+EventF.event.getVolontaires().size());
         //System.out.println(EventF.event.getVolontaires());
        txtp.setText("participer!");
        txtnop.setText("Ne plus participer");
        
        //**********************
        ShareButton sb=new ShareButton();
        sb.setText("Invitez vos amis");
        sb.setTextToShare("Bonjour, je t’invite à\n" +
        "télécharger et à installer l’application Tunfugees. C’est une association pour aider les réfugiés");
        //**********************
        
        
        SpanLabel description  = new SpanLabel("Description : \n"+EventF.event.getDescription());
        description.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        img = new ImageViewer();
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/1,this.getHeight()/3),false);
        URLImage urlImage = URLImage.createToStorage(placeholder, EventF.event.getImage(), "http://localhost/sprint_web-eyaaa/tunfugees/web/uploads/image/"+EventF.event.getImage());
        img.setOpaque(false);
        img.setImage(urlImage);
/*        f.getToolbar().addCommandToLeftBar("Retour", null, e ->{
            new AffichageEvents();
            AffichageEvents.f.showBack() ;});*/
       
        
        
      //  date.setText(sm.format(AffichageEvents.event.getDate()));
     
     
           // EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(this.getWidth()/5, this.getWidth()/8),false);
           // URLImage urlImage1 = URLImage.createToStorage(placeholder1,"participer", "http://localhost/sprint_web-eyaaa/tunfugees/web");
           // participerimg.setImage(urlImage1);
            
            participer.addActionListener(e->{ 
            if(i<EventF.event.getNbrMax())
            {
                
            
            try {
                Volontaire ve = new Volontaire();// mail o nom o prenom o tel mtaa l useeeeeeer
                ve.setNom("hhhh");
                ve.setPrenom("hhhhh");
                ve.setMail("eya.bellllil");
                ve.setNom_event(EventF.event.getNomEvent());
                ve.setTel(111111);
                
                VolontaireService sv = new VolontaireService();
                sv.ajouter(ve, EventF.event);
               
               
                //  System.out.println(sv.ajouter(ve, EventF.event));
                participer.setVisible(false);
                //  participerimg.setVisible(false);
                noparticiper.setVisible(true);
                //   noparticiperimg.setVisible(true);
                txtnop.setVisible(true);
                Dialog.show("Nouvelle participation!", "Vous venez de participer a l'évènement "+EventF.event.getNomEvent(), "Ok", "Annuler");
            } catch (Exception ex) {
               ex.printStackTrace();
            }}
            else   Dialog.show("Evénement Complet ", "L'évenement "+EventF.event.getNomEvent()+" est Complet ", "Ok", "Annuler");
                
            });
            
        
        
         //   EncodedImage placeholder2 = EncodedImage.createFromImage(Image.createImage(this.getWidth()/1, this.getWidth()/5),false);
         //   URLImage urlImage2 = URLImage.createToStorage(placeholder2,"noparticiper", "http://localhost/sprint_web-eyaaa/tunfugees/web");
          //  noparticiperimg.setImage(urlImage2);
            
            noparticiper.addActionListener(e->{
              String Mail ="eya.bellllil";
                
                VolontaireService sv = new VolontaireService();
                sv.noparticiper( EventF.event.getId_event(),Mail);
                //(new EventService()).NoParticiper(new ParticiperEvent(Utilisateur.current_user.getId(),EventF.event.getId()));
                noparticiper.setVisible(false);
               // noparticiperimg.setVisible(false);
                participer.setVisible(true);
               // participerimg.setVisible(true);
                Dialog.show("Annulation de participation", "Etes-vous sur de ne plus vouloir y participer?", "Oui", "Annuler");
                 txtp.setVisible(true);
                
            });
       
        this.add(titre);
        this.add(date);
        this.add(lieu); 
        this.add(img);
        this.add(description);
        this.add(participants);
       
        this.add(participer);
        this.add(noparticiper);
        //this.add(participerimg);
        //this.add(noparticiperimg);
          this.add(sb);       
         this.getToolbar().addCommandToLeftBar("Retour", null, e ->{
            new EventF().show();
          });
        this.show();
        
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
