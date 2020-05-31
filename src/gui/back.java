/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author hp
 */
public class back extends  com.codename1.ui.Form{
         Form current;
    public back() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("back");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btngestionrefugies = new Button("gestion Refugies");
        Button btngestioncamps = new Button("gestion Camps");

        btngestionrefugies.addActionListener(e -> new afficherrefugies().show());
       // btngestioncamps.addActionListener(e -> 
            //    new affichercamps().show());
        addAll(btngestionrefugies, btngestioncamps);
         this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new Interface().show();
        });

    }
    
}
