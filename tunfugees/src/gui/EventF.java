/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Event;
import services.EventService;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import services.PanierService;
import services.ProduitService;

/**
 *
 * @author user
 */
public class EventF extends BaseForm{
    // static  Form f;
   // SpanLabel lb;
     Label titre ; 
    Label date ;
    static Event event ;
    ImageViewer img ; 
    Container events;
    Label lieu;
    Label description;
  
    Resources res;
    
    public EventF( Resources res)
    {  
         super("Mes Evenements", BoxLayout.y());
        // super("Details",new BoxLayout(BoxLayout.Y_AXIS));
          this.res = res;
       
         events = new Container(new FlowLayout(CENTER));
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
        RadioButton all = RadioButton.createToggle("Details", barGroup);
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
         // this.header("Mes Evénements");
        
       
      
        //  this.header("Nos évènements");
         SimpleDateFormat sm = new SimpleDateFormat("yyyy/MM/dd");
        
       // lb = new SpanLabel("");
       // f.add(lb);
        EventService service=new EventService();
       // lb.setText(e.getList2().toString());
     ArrayList<Event> a = service.getList() ;
       
        for (Event ev : a) 
       {
            Container eventContainer = new Container(new FlowLayout(CENTER));
            Container detailsContainer = new Container(new FlowLayout(CENTER));
            img = new ImageViewer();
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
            URLImage urlImage = URLImage.createToStorage(placeholder, ev.getImage(), "http://127.0.0.1:8000/uploads/Produit/photo/"+ev.getImage());
            img.setImage(urlImage);
            eventContainer.add(img);
           titre= new Label();
           lieu=new Label();
           description=new Label();
           date = new Label();
          
           titre.setText("Nom "+ev.getNomEvent());
           lieu.setText("à :"+ev.getAdresse());
           //description.setText(ev.getDescription());
           date.setText("Cree le " +ev.getDate());
        
         
           /* f.getToolbar().addCommandToLeftBar("Retour", null, e ->{
            new AffichageCatEvts(res);
            AffichageCatEvts.f.showBack() ;});*/
           
           
            
           
           detailsContainer.add(titre);
           detailsContainer.add(lieu);
           detailsContainer.add(description);
           detailsContainer.add(date);
 
           
           eventContainer.add(detailsContainer);
           Button detailsEvent = new Button(); 
           detailsEvent.addActionListener((e)->
           {
               
          event = ev; 
         
           
           new DetailsEvent(res);
           
           });
           eventContainer.setLeadComponent(detailsEvent);
           events.add(eventContainer);
       }
        
       
        super.add(events);
        super.show();
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
