/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Camps;
import entities.Refugies;
import java.util.ArrayList;
import services.CampsService;
import services.RefugiesService;

/**
 *
 * @author hp
 */
public class CampsModifierInterface extends com.codename1.ui.Form  {
             private Resources theme;

    
    public CampsModifierInterface(Camps evnt) {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
        setTitle("modifier un camp");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();

        Container cnt2 = ui.createContainer(theme, "GUI 2");//ajouter graphiquement un GUI element
        tab.addTab("", cnt2);
           ArrayList<Camps> list_e = new CampsService().getList2();
          
      //  Picker p = new Picker();
       // p.setType(Display.PICKER_TYPE_DATE);

       

        TextField NBRMAX = new TextField(String.valueOf(evnt.getNbrmax()), "NBRMAX");//textfield
         ComboBox<String> CATEGORIES = new ComboBox("Infancy(0-3)", "Childhood(4-8)", "Puberty(9-13)","Teenager(14-18)", "Adult(19-39)", "Middle age(40-59)", "old age(60-99)" );
  
        // TextField date = new TextField(evnt.getDate().toString(), "date");
    //TextField heure = new TextField(evnt.getHeure().toString(), "heure");
        
         TextField ADRESSE = new TextField(evnt.getAdresse(), "ADRESSE");//textfield


        Button save = new Button("Modifier");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 CampsService sg = new CampsService();                
                    Camps e= new Camps(evnt.getIdCamp(),Integer.parseInt(NBRMAX.getText()),CATEGORIES.getSelectedItem(),ADRESSE.getText());

                    sg.modifier(e);
                new affichercamps().show();
            }
        });
           setLayout(BoxLayout.y());
           addAll(NBRMAX,CATEGORIES,ADRESSE,save);
           
                  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new affichercamps().show());

        
        
    }
    
    
}
