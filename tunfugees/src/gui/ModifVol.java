/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Event;
import entities.Volontaire;
import services.EventService;
import services.VolontaireService;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;

import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
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
public class ModifVol extends BaseForm{
     private Resources res;
     Container cnt;

        public ModifVol(Volontaire evnt,Resources res) {
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
        RadioButton all = RadioButton.createToggle("Modify Volantaire", barGroup);
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
   
        
           ArrayList<Volontaire> list_e = new VolontaireService().getList2();
           
           Label m = new Label ("Veuillez modifier la pésence ");
           Container cn = new Container(new FlowLayout(CENTER));
           Label p = new Label ("Présence : ");
              ComboBox cmb=new ComboBox("Present","Absent");
              cn.addAll(p,cmb);
           
           
           
           
           
           
        
           
             Button save = new Button("Enregistrer");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                VolontaireService sg = new VolontaireService();                
                    Volontaire e;
                e = new Volontaire(evnt.getId_vol(), (String) cmb.getSelectedItem());
                    sg.modifier(e);
                new listVol(res).show();
            }
        });
        Container cn1=new Container(new FlowLayout(CENTER));
        cn1.add(m);   
        setLayout(BoxLayout.y());
           cnt.addAll(cn1,cn,save);
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
