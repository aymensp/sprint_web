/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author hp
 */
public class Interface extends Form{
     Form current;
    public Interface() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnBack = new Button("Back");
        Button btnFront = new Button("Front");

        btnBack.addActionListener(e -> new back().show());
        btnFront.addActionListener(e -> 
                new OURrefugies().show());
        addAll(btnBack, btnFront);

    }

}
