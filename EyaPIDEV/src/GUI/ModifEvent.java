/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import Entities.Event;
import java.util.ArrayList;
import Services.EventService;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.Display;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Dialog;
import java.io.IOException;


/**
 *
 * @author syrine
 */
public class ModifEvent extends com.codename1.ui.Form {
     private Resources theme;
    private int id_user_actif = 9;
    
    public ModifEvent(Event evnt) {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
        setTitle("Mes Evenements");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
       
        Container cnt2 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("Mes Evenements", cnt2);
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
                        new AfficherBack().show();
                        Dialog.show("Modification", "Modifié avec succés",null,"ok");
                        new AfficherBack().show();
                    } else
                        Dialog.show("ERREUR", "le nombre maximum est negatif ou null",null, "ok");
                
            
            }
        });
           setLayout(BoxLayout.y());
           addAll(nom,lieu,p,description,nbplace,image,save);
           
            this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
          //  new AfficherBack().show();
        });
        
        
    }
}
