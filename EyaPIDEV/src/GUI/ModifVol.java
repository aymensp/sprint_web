/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Event;
import Entities.Volontaire;
import Services.EventService;
import Services.VolontaireService;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class ModifVol extends Form{
     private Resources theme;
    

        public ModifVol(Volontaire evnt) {

     setTitle("Mes Evenements");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
       
        Container cnt2 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("Mes Evenements", cnt2);
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
                new listVol().show();
            }
        });
        Container cn1=new Container(new FlowLayout(CENTER));
        cn1.add(m);   
        setLayout(BoxLayout.y());
           addAll(cn1,cn,save);
           
            this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new AfficherBack().show();
        });
          
    
}}
