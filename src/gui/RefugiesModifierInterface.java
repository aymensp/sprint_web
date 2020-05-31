/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Refugies;
import java.util.ArrayList;
import services.RefugiesService;

/**
 *
 * @author hp
 */
public class RefugiesModifierInterface extends com.codename1.ui.Form {
         private Resources theme;

    
    public RefugiesModifierInterface(Refugies evnt) {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
        setTitle("modifier un Refugie");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();

        Container cnt2 = ui.createContainer(theme, "GUI 2");//ajouter graphiquement un GUI element
        tab.addTab("Mes Evenements", cnt2);
           ArrayList<Refugies> list_e = new RefugiesService().getList2();
          
      //  Picker p = new Picker();
       // p.setType(Display.PICKER_TYPE_DATE);

       // Picker pt = new Picker();
       // pt.setType(Display.PICKER_TYPE_TIME);
       // p.setText(evnt.getDate());
        //pt.setText(evnt.getHeure());
        
        TextField nom = new TextField(evnt.getNom(), "Nom");//textfield
         TextField prenom = new TextField(evnt.getPrenomn(), "prenom");
          
        // TextField date = new TextField(evnt.getDate().toString(), "date");
    //TextField heure = new TextField(evnt.getHeure().toString(), "heure");
        
         TextField pays = new TextField(evnt.getPays(), "pays");//textfield
     
         TextField image = new TextField(evnt.getImage(), "image");
         

        Button save = new Button("Modifier");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 RefugiesService sg = new RefugiesService();                
                    Refugies e= new Refugies(evnt.getIdref(),nom.getText(),prenom.getText(),pays.getText(), image.getText());

                    sg.modifier(e);
                new afficherrefugies().show();
            }
        });
           setLayout(BoxLayout.y());
           addAll(nom,prenom,pays,image,save);
           
            this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new afficherrefugies().show();
        });
        
        
    }
    
}
