
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.db.Cursor;
import com.codename1.db.Row;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import entities.CatStock;
import entities.Stock;
import services.ServiceTask;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author bhk
 */
public class ListTasksForm extends Form {

    Form F2,previous;

   // Form current;
                 Resources res;

        MenuStock c = new MenuStock(res);
    public ListTasksForm(Form previous,Resources res) {
        
         this.res = res;
       // current = new MenuForm();
        setTitle("Liste des Stocks");
        setLayout(new FlowLayout(BoxLayout.Y_AXIS));
        Container cnt = new Container(BoxLayout.y());
        cnt.setScrollableY(true);
        List<Stock> list = new ArrayList();
        list = ServiceTask.getInstance().getAllTasks();
        for (Stock p : list) {
            System.out.println(p);
        }
        for (Stock p : list) {
            cnt.add(addItem(p,previous,res));
        }
        
         Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        add(cnt);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
    public Container addItem(Stock p,Form previous,Resources res) {
        // theme = UIManager.initFirstTheme("/theme");
CatStock h =new CatStock();

        Container cnt2 = new Container(BoxLayout.x());
        MultiButton mb = new MultiButton("ID  :  " + p.getId());
        mb.setTextLine2("Titre  :  " + p.getTitle());
        
        FontImage.setMaterialIcon(mb, FontImage.MATERIAL_ADJUST);
        cnt2.add(mb);
        //bouton Vers Détails
        mb.addActionListener((e) -> {
            System.out.println(p);
            F2 = new Form("Détails Stock", BoxLayout.y());
            Stock l = new Stock();
            Button valider = new Button("editer");
            Button supprimer = new Button("Supprimer");
            Button contactfr = new Button("Contacter Fourniseur");
            Label lbid = new Label("ID    :   " + p.getId());
            
            Label lbetat = new Label("category    :   Alimentaire ");
            Label lbtitre = new Label("Title   :   " + p.getTitle());
            Label lbprix = new Label("Quantite    :  " + p.getDescription());
          //  Label lbadresse = new Label("Adresse    :  " + p.getAdresse());
           // Label lbetel = new Label("Tel    :  " + p.getTel());
         //   Label lbdatecreation = new Label("Date Création    :   " + p.getPostdate());
         //client //  Label cl1 = new Label("Détails Client    :   " + p.getClient());
            F2.addAll(lbid, lbtitre, lbetat, lbprix,/* lbadresse, lbetel, lbdatecreation,/* cl1,*/ valider,supprimer ,contactfr);
          if (Integer.parseInt(p.getDescription()) < 100 )
          {
          
          ToastBar.Status status = ToastBar.getInstance().createStatus();
            status.setMessage("Stock "+p.getTitle()+" Insuffisant Penser a Contacter le fournisseur ");
            status.setShowProgressIndicator(true);
            status.showDelayed(300); 
          }
             Toolbar tb = new Toolbar(true);
       F2. setToolbar(tb);
            F2.getToolbar().addCommandToLeftBar("back", null, es ->  new ListTasksForm(previous, res).show());
            F2.show();
            valider.addActionListener((ea) -> {   
                
                                Form F3 = new Form("Editer Stock", BoxLayout.y());
                                Container cnt = new Container(BoxLayout.y());
                                ImageViewer img = new ImageViewer(res.getImage("edit.jpg"));
                                TextField tfTitre = new TextField("", "Title");
                                TextField tfSujet = new TextField("", "Quantite");
        Button btnValider = new Button("Editer Stock");
      //  Button btnSupprimer = new Button("Supprimer Réclamation");
            cnt.addAll(img,tfTitre,tfSujet,btnValider/*,btnSupprimer*/);
                F3.add(cnt);
                       Toolbar tb1 = new Toolbar(true);
       F3. setToolbar(tb1);
                            F3.getToolbar().addCommandToLeftBar("back", null, es -> F2.show());

                F3.show();
                           btnValider.addActionListener((eacaaadsa) -> {
   if ((tfTitre.getText().length() == 0) || (tfSujet.getText().length() == 0) || !StringUtils.isNumeric(tfSujet.getText()) /* || (tfLivraison.getText().length() == 0) */) {
                    Dialog.show("Alert", "Svp Verfier tous les champs ", new Command("OK"));
                } else {

                boolean list = ServiceTask.getInstance().editTask(p.getId(),tfTitre.getText(),tfSujet.getText());
                Dialog.show("Success", "Stock Changé", new Command("OK"));
                
//  lbetat.setText("Livrée");
                        //  F3.getToolbar().addCommandToLeftBar("back", null, es -> c.show());

                 new ListTasksForm(previous, res).show();
   }
            });
                          
               
                           
            });
            
            
            
            
             contactfr.addActionListener((ea) -> {   
                
                                Form F3 = new Form("Contacter Fourniseur", BoxLayout.y());
                                Container cnt = new Container(BoxLayout.y());
                                //ImageViewer img = new ImageViewer(theme.getImage("edit.jpg"));
                                TextField tfTitre = new TextField("", "Produit");
                                TextField tfSujet = new TextField("", "Quantite");
        Button btnValider = new Button("Envoyer Mail");
      //  Button btnSupprimer = new Button("Supprimer Réclamation");
            cnt.addAll(tfTitre,tfSujet,btnValider/*,btnSupprimer*/);
                F3.add(cnt);
                   Toolbar tb1 = new Toolbar(true);
       F3. setToolbar(tb1);
                                          F3.getToolbar().addCommandToLeftBar("back", null, es -> F2.show());

                F3.show();
                           btnValider.addActionListener((eacaaadsa) -> {
      if ((tfTitre.getText().length() == 0) || (tfSujet.getText().length() == 0) || !StringUtils.isNumeric(tfSujet.getText()) /* || (tfLivraison.getText().length() == 0) */) {
                    Dialog.show("Alert", "Svp Verfier tous les champs ", new Command("OK"));
                } else {

   
                 Message m = new Message("Bonjour , Vous voulez me livré avec "+tfSujet.getText()+" unite de : "+tfTitre.getText()+" le plut bref possible Merci !");
                            String textAttachmentUri = null;
m.getAttachments().put(textAttachmentUri, "text/plain");
                            String imageAttachmentUri = null;
m.getAttachments().put(imageAttachmentUri, "image/png");
Display.getInstance().sendMessage(new String[] {"tunfegees@gmail.com"}, "Livraison", m);
                Dialog.show("Success", "Requete  Mail reussie  ", new Command("OK"));
              //  lbetat.setText("Livrée");

                F2.show();
      }
            });
                          
               
                           
            });
            
            
            
            
            
            
            
            
            
            
            
            
     
           
            
            
            
            
             supprimer.addActionListener((eac) -> {                
                boolean list = ServiceTask.getInstance().deleteTask(p.getId());
                Dialog.show("Success", "Stock Supprimée", new Command("OK"));
              //  lbetat.setText("Livrée");
                c.show();
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
