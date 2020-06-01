/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.push.PushCallback;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
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
public class OURrefugies extends BaseForm implements PushCallback{
   
    static  Form f;
   // SpanLabel lb;
     Label titre ; 
Container cnt;
     
    static Refugies event ;
    ImageViewer img ; 
    Label User ;
  
    
    Label nbVues;
    Resources res;
   
  
    public OURrefugies( Resources res)   {
       
       super("Refugees", BoxLayout.y());
        getTitleArea().setUIID("Container");
        setLayout(BoxLayout.y());
    
         this.res = res;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        //Tabs : toolbar
       
        
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            new AccueilForm(res).show();
        });
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
        RadioButton all = RadioButton.createToggle("Refugees", barGroup);
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
      cnt = new Container(BoxLayout.y());
        //creer un groupe 
        
         super.add(cnt);
        
        
        
        
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
            Container nomm = new Container(new BoxLayout(BoxLayout.X_AXIS));
             Container prenomm= new Container(new BoxLayout(BoxLayout.X_AXIS));

            img = new ImageViewer();
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
            URLImage urlImage = URLImage.createToStorage(placeholder, ev.getImage(), "http://127.0.0.1:8000/uploads/Produit/photo/"+ev.getImage());
            img.setImage(urlImage);
            eventContainer.add(img);
           
            titre= new Label();
           Label nom= new Label();
            nbVues = new Label();
                       Label prenom= new Label();    
           nom.setText("Nom:");
           titre.setText(ev.getNom());
            prenom.setText("Prenom:");
           nbVues.setText(ev.getPrenomn()+"");
           
          
          // nbVues.setIcon(FontImage.createMaterial(FontImage.MATERIAL_REMOVE_RED_EYE, nbVues.getUnselectedStyle()));
           /* f.getToolbar().addCommandToLeftBar("Retour", null, e ->{
            new AffichageCatEvts(res);
            AffichageCatEvts.f.showBack() ;});*/
           
           
            
           nomm.add(nom);
           nomm.add(titre);
   
           prenomm.add(prenom);
           prenomm.add(nbVues);
                      detailsContainer.add(nomm);
detailsContainer.add(prenomm);

           
           eventContainer.add(detailsContainer);
           
         
           Button detailsEvent = new Button(""); 
           detailsEvent.addActionListener((e)->
           {
               
           event = ev; 

           
           new DetailsRef(res);
           
           });
           eventContainer.setLeadComponent(detailsEvent);
           
           
           
           events.add(eventContainer);
           
       }
       
        this.add(events);
        this.show();
        // this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new Interface().show());

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

      
    public void push(String value) {
        Dialog.show(value, "ok", "", "");
    }

    public void registeredForPush(String deviceId) {
    }

  
    public void pushRegistrationError(String error, int errorCode) {
    }
}
