/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.Stock;
import entities.Reclamation;
import entities.Utilisateur;
import services.ServiceTask;
import java.util.ArrayList;
import java.util.List;
//import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Fares
 */

public class ListReclamations extends BaseForm {

    Form F2,previous;
   
     Resources res;
     

     MenuReclamation c = new  MenuReclamation(res);
     
    public ListReclamations(Form previous,Resources res) {
         this.res = res;
        setTitle("Liste des réclamations");
        setLayout(new FlowLayout(BoxLayout.Y_AXIS));
        Container cnt = new Container(BoxLayout.y());
        cnt.setScrollableY(true);
        List<Reclamation> list = new ArrayList();
        list = ServiceTask.getInstance().getAllRecs(Utilisateur.current_user.getId() );
        for (Reclamation p : list) {
            System.out.println(p);
        }
        for (Reclamation p : list) {
            cnt.add(addItem(p,previous,res));
        }
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        add(cnt);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());
    }

    public Container addItem(Reclamation p,Form previous,Resources res) {
 //res = UIManager.initFirstTheme("/theme");
        Container cnt2 = new Container(BoxLayout.x());
        MultiButton mb = new MultiButton("ID  :  " + p.getId());
        mb.setTextLine2("Titre  :  " + p.getTitle());

        FontImage.setMaterialIcon(mb, FontImage.MATERIAL_3D_ROTATION);
        cnt2.add(mb);
        //bouton Vers Détails
        mb.addActionListener((e) -> {
            System.out.println(p);
            F2 = new Form("Détails Réclamation", BoxLayout.y());

            Button valider = new Button("editer");
            Button supprimer = new Button("Supprimer");
            Label lbid = new Label("ID    :   " + p.getId());
            Label lbtitre = new Label("Titre   :   " + p.getTitle());
            Label lbetat = new Label("Description    :  " + p.getDescription());
            //Label lbdatecreation = new Label("Date Création    :   " + p.getPostdate());
   Toolbar tb = new Toolbar(true);
       F2. setToolbar(tb);
            F2.addAll(lbid, lbtitre, lbetat/*, lbdatecreation,*/, valider, supprimer);
            F2.getToolbar().addCommandToLeftBar("back", null, es -> new ListReclamations(previous, res).show() ); 
            F2.show();
            valider.addActionListener((ea) -> {
                Form F3 = new Form("Editer Réclamation", BoxLayout.y());
               
                Container cnt = new Container(BoxLayout.y());
                 ImageViewer img = new ImageViewer(res.getImage("edit.jpg"));
        TextField tfTitre = new TextField("", "Title");
        TextField tfSujet = new TextField("", "Description");
 //
        Button btnValider = new Button("Editer Réclamation");
   //      Button btnSupprimer = new Button("Supprimer Réclamation");
   
        cnt.addAll(img,tfTitre,tfSujet,btnValider/*,btnSupprimer*/);
                F3.add(cnt);
                Toolbar tb1 = new Toolbar(true);
       F3. setToolbar(tb1);
            F3.getToolbar().addCommandToLeftBar("back", null, es -> F2.show());

                F3.show();
              //  p.setTitle(tfTitre.getText());
             //   p.setDescription(tfSujet.getText());
               // System.out.println(p.getTitle());
           btnValider.addActionListener((eacaaadsa) -> {
                
                if ((tfTitre.getText().length() == 0) || (tfSujet.getText().length() == 0)   /* || (tfLivraison.getText().length() == 0) */) {
                    Dialog.show("Alert", "Svp Verfier tous les champs ", new Command("OK"));
                } else {

               boolean list = ServiceTask.getInstance().editRec(p.getId(),tfTitre.getText(),tfSujet.getText());
                Dialog.show("Success", "Reclamation Editée", new Command("OK"));
                new ListReclamations(previous, res).show();
                }
            });
           
            
          /*      
                  btnSupprimer.addActionListener((eac) -> {
                boolean list = ServiceTask.getInstance().deleteRec(p.getId());
                Dialog.show("Success", "Reclamation Supprimée", new Command("OK"));
                c.show();
            });
           */
            });
            

            supprimer.addActionListener((eac) -> {
                boolean list = ServiceTask.getInstance().deleteRec(p.getId());
                Dialog.show("Success", "Reclamation Supprimée", new Command("OK"));
              new ListReclamations(previous, res).show();
            });
            
             

          
            
            
        });

        /* Form F3 = new Form("", BoxLayout.y());
       Form F4 = new Form("", BoxLayout.y());
            F2.getToolbar().addCommandToOverflowMenu("ajouter", null, es -> F3.show());
             F2.getToolbar().addCommandToOverflowMenu("afficher", null, es -> F4.show());
              Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE);
              Slider sl = new Slider();
        sl.setEditable(true);
        sl.setMinValue(27);
        sl.setMaxValue(42);
        Button btn = new Button("Valider");
          
       //Request
        con.setUrl(url);
            NetworkManager.getInstance().addToQueueAndWait(con);

        //Response
        con.addResponseListener((eaa)->{
        String reponse=new String(con.getResponseData());
            System.out.println(reponse);
        });   
            F2.addAll(lb1,lbimage);
             F2.getToolbar().addCommandToLeftBar("back", null, es -> f2.show());
             F3.addAll(date,sl,btn);
            F2.show();
         */
        return cnt2;
    }

}
