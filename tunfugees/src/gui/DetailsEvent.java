    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
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


import entities.Event;
import entities.Volontaire;
//import entities.ParticiperEvent;


import services.EventService;
import services.VolontaireService;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.Utilisateur;

//import doryan.windowsnotificationapi.fr.Notification;
import java.util.ArrayList;
import static javafx.scene.paint.Color.color;


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
    Resources res;
    Image Ima;
    MultiButton finishLandingPage;
      public int i =0;
    
     public DetailsEvent(Resources res){
       
     //   SimpleDateFormat sm = new SimpleDateFormat("EEE, MMM d, yyyy");
        super("Details",new BoxLayout(BoxLayout.Y_AXIS));
        
        
         //theme = UIManager.initFirstTheme("/theme");
        this.header("Details");
        this.res=res;
        
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
          
          
    Toolbar tb = new Toolbar(true);
        setToolbar(tb);
    
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
         new EventF(res).show();
        });
          /*
       getToolbar().addCommandToLeftBar("Retour", null, e ->{
          
          });*/
       txtp=new Label();
       txtnop=new Label();
       txtp.setVisible(false);
        txtnop.setVisible(false);
        participer.setVisible(true);
        noparticiper.setVisible(false);
        
        VolontaireService es=new VolontaireService();
        //ParticiperEvent join=new ParticiperEvent(AfficherBack.event,Utilisateur.current_user); 
        Volontaire v = new Volontaire();
        String mail=Utilisateur.current_user.getEmail();//mail useeeeeeeeeer
    String S="";
    
     ArrayList<Volontaire> a = es.getList(EventF.event) ;
        
      for (Volontaire ev : a) 
       {   i =i+1;
           if (ev.getNom_event().equals(EventF.event.getNomEvent())&&ev.getMail().equals(mail))
           { S="true";}
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
        participants.getFontIconSize();
         participants.getAllStyles().setFgColor(ColorUtil.rgb(0,128,0));  
        if(i<EventF.event.getNbrMax())
            {
              // int s=  EventF.event.getNbrMax()-i;
           finishLandingPage = new MultiButton("On a besoin de : "+(EventF.event.getNbrMax()-i));
      //  finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(0xff000, finishLandingPage.getPreferredH(),true));
        finishLandingPage.setIconUIID("Container");
                
                
         //  finishLandingPage.setIcon(createCircleLine(0xff000, finishLandingPage.getPreferredH(),true));
              //  Ima=createCircleLine(0xff000, RIGHT, focusScrolling);
               participants.setText("Il ya "+i+" qui (a/ont) participé à cet événement");
            }
           else      {  
            finishLandingPage = new MultiButton("Evenement Complet ");
   //  FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
           //   finishLandingPage.setEmblem(arrowDown);
       
      finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(0xF70C04, finishLandingPage.getPreferredH(),true));
        finishLandingPage.setIconUIID("Container");
       // participer.setVisible(false);
       // participerimg.setVisible(true);
        
      //  noparticiper.setVisible(false);
         participants.setText("On a atteint le nombre maximum");
        }
       
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
        URLImage urlImage = URLImage.createToStorage(placeholder, EventF.event.getImage(), "http://127.0.0.1:8000/uploads/Produit/photo/"+EventF.event.getImage());
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
                
                ve.setNom(Utilisateur.current_user.getNom());
                ve.setPrenom(Utilisateur.current_user.getPrenom());
                ve.setMail(Utilisateur.current_user.getEmail());
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
                else  
                     Dialog.show("Evénement Complet ", "L'évenement "+EventF.event.getNomEvent()+" est Complet ", "Ok", "Annuler");
            });
            
        
        
         //   EncodedImage placeholder2 = EncodedImage.createFromImage(Image.createImage(this.getWidth()/1, this.getWidth()/5),false);
         //   URLImage urlImage2 = URLImage.createToStorage(placeholder2,"noparticiper", "http://localhost/sprint_web-eyaaa/tunfugees/web");
          //  noparticiperimg.setImage(urlImage2);
            
            noparticiper.addActionListener(e->{
              String Mail =Utilisateur.current_user.getEmail();
                
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
        
       this.add(participer);
        this.add(noparticiper);
        
        this.add(finishLandingPage);
        this.add(participants);
        
        //this.add(participerimg);
        //this.add(noparticiperimg);
          this.add(sb);       
      
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
        
        Label arrow = new Label("Container");

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
     private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        /*       g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);*/
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }
     
    
}
