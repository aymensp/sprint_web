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
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Camps;
import java.util.ArrayList;
import services.CampsService;
import services.PanierService;
import services.ProduitService;
import services.RefugiesService;

/**
 *
 * @author hp
 */
public class DetailsRef  extends BaseForm {
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
    Container cnt;
    Resources res;
     public DetailsRef(Resources res)  {
       
    
       // f = new Form("",new BoxLayout(BoxLayout.Y_AXIS));
      //  UserService serviceuser =new UserService();
       // l= new Label();
       
       
        super("Show Refugees", BoxLayout.y());
        this.res = res;
      
       
        cnt = new Container(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            new OURrefugies(res).show();
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
        RadioButton all = RadioButton.createToggle("Show Refugee", barGroup);
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
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(super.getWidth()/1,super.getHeight()/3),false);
        URLImage urlImage = URLImage.createToStorage(placeholder, OURrefugies.event.getImage(), "http://127.0.0.1:8000/uploads/Produit/photo/"+OURrefugies.event.getImage());
        //img.setOpaque(false);
        img.setImage(urlImage);
/*        f.getToolbar().addCommandToLeftBar("Retour", null, e ->{
            new AffichageEvents();
            AffichageEvents.f.showBack() ;});*/
       
        
        
      //  date.setText(sm.format(AffichageEvents.event.getDate()));
     
     


        super.add(titre);
        super.add(date);
        super.add(img);
        super.add(nom);
       
        super.add(prenom);
        super.add(age);
        super.add(pays);
        super.add(adresse);
        
        
      
     
        super.show();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new OURrefugies(res).show());

        
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
    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
    public void push(String value) {
        Dialog.show("", value, "ok", "cancel");
    }
    
}
