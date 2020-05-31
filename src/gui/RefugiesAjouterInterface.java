/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.l10n.SimpleDateFormat;
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
public class RefugiesAjouterInterface extends com.codename1.ui.Form{
    private Resources theme;
  
     public RefugiesAjouterInterface() {
    //this(com.codename1.ui.util.Resources.getGlobalResources());
         setTitle("refugies");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
        Container cnt1 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
tab.addTab("", cnt1);        
        add(tab);
        //cnt1.add(new SpanLabel(new ServiceGroupeMembre().Afficher().toString()));
      
        ArrayList<Refugies> list_e = new RefugiesService().getList2();
        // Picker p=new Picker();
         //Picker pt=new Picker();
         
        // p.setType(Display.PICKER_TYPE_DATE);
         //p.setFormatter(new SimpleDateFormat("yyyy-MM-dd"));
         //pt.setType(Display.PICKER_TYPE_TIME);
        // pt.setFormatter(new SimpleDateFormat("HH:mm"));
         
         TextField nom = new TextField(null, "Nom");//textfield
         TextField prenom = new TextField(null, "prenom");
         
         //TextField date = new TextField(null, "date");
         TextField age = new TextField(null, "age");
         //TextField heure = new TextField(null, "heure");
         TextField pays = new TextField(null, "pays");//textfield
         TextField image = new TextField(null, "image");
         
          Button save = new Button("ajouter");
          // System.out.println(pt.getText());
          save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //ajouter groupe
                RefugiesService sg = new RefugiesService();  
              
                //System.out.println(p.getText());
                sg.ajouter(new Refugies(nom.getText(), prenom.getText(),Integer.parseInt(age.getText()),pays.getText(), image.getText()));
             //  System.out.println(pt.getText());
                //ajouter membre
               
               
                new afficherrefugies().show();
                
            }
        });
        
        
        cnt1.setLayout(BoxLayout.y());
        cnt1.addAll(nom,prenom,age,pays,image,save);
        
        
        
          this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new afficherrefugies().show();
        });
     }
    
}
