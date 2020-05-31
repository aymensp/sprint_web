/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author user
 */
public class Home extends Form{
     public Home() {
         super("Home", BoxLayout.y());
         Container cn = new Container(new FlowLayout(CENTER,CENTER));
         Button Front = new Button("Front");
        Button Back = new Button("Back");
         Front.addActionListener((ActionEvent evt) -> {
             new EventF().show();
        });
          Back.addActionListener((evt) -> {
          new AfficherBack().show();
        });
        
        cn.addAll(Front,Back);
        this.add(cn);
     }
    
}
