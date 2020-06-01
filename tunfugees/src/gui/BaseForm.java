/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.db.Database;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Utilisateur;

import java.io.IOException;

/**
 *
 * @author actar
 */
public class BaseForm extends Form {
Form r;

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

   
    
    
    
    //sidemenu
    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("ekher.png");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        Image placeholder = Image.createImage(45, 45, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Image userImage;
        if (Utilisateur.current_user.getPhoto() == null || Utilisateur.current_user.getPhoto().equals("null")) {
            userImage = res.getImage("default_profile_picture.png");
            tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                    sl,
                    FlowLayout.encloseCenterBottom(
                            new Label(userImage.scaled(200, 200)))
            ));
        } else {
            try{
                 userImage = Image.createImage("file:/C:/wamp64/www/tunfugees/web/uploads/user/photo/"+Utilisateur.current_user.getPhoto()).fill(200, 200);
            tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                    sl,
                    FlowLayout.encloseCenterBottom(
                            new Label(userImage.scaled(200, 200)))
                    
            ));
             } catch (IOException ex) {
                        ex.printStackTrace();
                    }
        }
    
       
      

       if(Utilisateur.current_user.getRoles().contains("ROLE_ADMIN"))
        {
             tb.addMaterialCommandToSideMenu("All Products", FontImage.MATERIAL_WORK, e -> new GestionProduit(res).show());
               tb.addMaterialCommandToSideMenu("Gestion Event", FontImage.MATERIAL_ADD_A_PHOTO, e ->   new AfficherBack(res).show());
                            tb.addMaterialCommandToSideMenu("Ajouter Refugies", FontImage.MATERIAL_WORK, e -> new afficherrefugies(res).show());
                                         tb.addMaterialCommandToSideMenu("Ajouter Camps", FontImage.MATERIAL_WORK, e -> new affichercamps(res).show());
 tb.addMaterialCommandToSideMenu("Gestion Stock", FontImage.MATERIAL_ACCOUNT_BALANCE, e -> new MenuStock(res).show());

        }
       else{
       
        tb.addMaterialCommandToSideMenu("Tufugees Store", FontImage.MATERIAL_ADD_A_PHOTO, e -> new AllProduitsForm(res).show());
      
        tb.addMaterialCommandToSideMenu("Commandes", FontImage.MATERIAL_STYLE, e -> new CommandeForm(res).show());
          tb.addMaterialCommandToSideMenu("Events", FontImage.MATERIAL_ADD_A_PHOTO, e ->  new EventF(res).show());
         tb.addMaterialCommandToSideMenu("Our Refugees", FontImage.MATERIAL_ADD_A_PHOTO, e ->  new OURrefugies(res).show());
         
         tb.addMaterialCommandToSideMenu("Contact us", FontImage.MATERIAL_ADD_A_PHOTO, e ->  new MenuReclamation(res).show());
               

       }
      
      
     
     
     
     
     
     
        

        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {
            try {
                Database db = Database.openOrCreate("tunfugees.db");
                db.execute("delete from appstates");
                Utilisateur.current_user = null;
                new SignInForm(res).show();
            } catch (IOException ex) {
            }
        });
    }
    
}
