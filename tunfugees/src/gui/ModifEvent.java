/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.UIBuilder;
import entities.Event;
import java.util.ArrayList;
import services.EventService;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.Display;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;


/**
 *
 * @author syrine
 */
public class ModifEvent extends BaseForm {
     private Resources res;
    private int id_user_actif = 9;
     Container cnt;
    public ModifEvent(Event evnt,Resources res) {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
          super("Show Product", BoxLayout.y());
        this.res = res;
  
        cnt = new Container(BoxLayout.y());
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
        RadioButton all = RadioButton.createToggle("Modify Event", barGroup);
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
       
        
        //Tabs : toolbar
       
    
           ArrayList<Event> list_e = new EventService().getList();
          
        Picker p = new Picker();
        p.setType(Display.PICKER_TYPE_DATE);

       
        p.setText(evnt.getDate());
        //.setText(evnt.getHeure());

         TextField nom = new TextField(evnt.getNomEvent(), "Nom");//textfield
         TextField lieu = new TextField(evnt.getAdresse(), "Adresse");
          
        // TextField date = new TextField(evnt.getDate().toString(), "date");
         //TextField siteweb = new TextField(evnt.getSiteWeb(), "siteweb");
         //TextField heure = new TextField(evnt.getHeure().toString(), "heure");
        
         TextField description = new TextField(evnt.getDescription(), "Description");//textfield
         TextField nbplace = new TextField(String.valueOf(evnt.getNbrMax()), "Nombre Max");
        // System.out.println(String.valueOf(evnt.getNbParticipant()));
         // System.out.println(evnt.getNbPlace());
         TextField image = new TextField(evnt.getImage(), "image");
        
        Button save = new Button("Enregistrer");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EventService sg = new EventService();                
                    Event e;
                e = new Event(evnt.getId_event(),nom.getText(), lieu.getText(), p.getText(), description.getText(),image.getText(),Integer.parseInt(nbplace.getText()));
                 if(Integer.parseInt(nbplace.getText())>0)
                    {    
                sg.modifier(e);
                new AfficherBack(res).show();
            } else
                        Dialog.show("ERREUR", "le nombre maximum est negatif ou null",null, "ok");
                
            
            }
        });
           setLayout(BoxLayout.y());
           cnt.addAll(nom,lieu,p,description,nbplace,image,save);
           super.add(cnt);
            this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new AfficherBack(res).show();
        });
        
        
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
