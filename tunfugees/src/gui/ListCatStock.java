package gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.CatStock;
import entities.Stock;
import services.ServiceTask;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Fares
 */

public class ListCatStock extends Form {

    Form F2,previous;
   
     Resources res;
     MenuStock c = new MenuStock(res);
    public ListCatStock(Form previous,Resources res) {
        this.res=res;
        setTitle("Choisir le type de Stock");
        setLayout(new FlowLayout(BoxLayout.Y_AXIS));
        Container cnt = new Container(BoxLayout.y());
        cnt.setScrollableY(true);
        List<CatStock> list = new ArrayList();
        list = ServiceTask.getInstance().getAllCatStock();
        for (CatStock p : list) {
            System.out.println(p);
        }
        for (CatStock p : list) {
            cnt.add(addItem(p,previous,res));
        }
         Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        add(cnt);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public Container addItem(CatStock p,Form previous,Resources res) {
// theme = UIManager.initFirstTheme("/theme");
        Container cnt2 = new Container(BoxLayout.x());
        MultiButton mb = new MultiButton("ID  :  " + p.getId());
        mb.setTextLine2("Titre  :  " + p.getNomcateg());

        FontImage.setMaterialIcon(mb, FontImage.MATERIAL_ADJUST);
        cnt2.add(mb);
        //bouton Vers Détails
        mb.addActionListener((e) -> {
            System.out.println(p);
            F2 = new Form("Détails Categorie", BoxLayout.y());

            Button valider = new Button("Ajouter Stock");
 //           Button supprimer = new Button("Supprimer");
            Label lbid = new Label("ID    :   " + p.getId());
            Label lbtitre = new Label("Titre   :   " + p.getNomcateg());
           // Label lbetat = new Label("Description    :  " + p.getDescription());
          //  Label lbdatecreation = new Label("Date Création    :   " + p.getPostdate());

            F2.addAll(lbid, lbtitre/*, lbetat, lbdatecreation*/, valider/*, supprimer*/);
            
              Toolbar tb = new Toolbar(true);
       F2. setToolbar(tb);
            F2.getToolbar().addCommandToLeftBar("back", null, es -> new ListCatStock(previous, res).show());
            F2.show();
            valider.addActionListener((ea) -> {
                Form F3 = new Form("Ajouter Stock", BoxLayout.y());
                     Toolbar tb1 = new Toolbar(true);
       F3. setToolbar(tb1);
                                                   F3.getToolbar().addCommandToLeftBar("back", null, es -> F2.show());

                Container cnt = new Container(BoxLayout.y());
                 ImageViewer img = new ImageViewer(res.getImage("livraison.jpg"));

        TextField tfTitre = new TextField("", "Titre");
        TextField tfSujet = new TextField("", "Quantite");
        TextField tfcat = new TextField("", "Category");
        


//
        Button btnValider = new Button("Ajouter Stock");
   //      Button btnSupprimer = new Button("Supprimer Réclamation");
        cnt.addAll(img,tfTitre,tfSujet,btnValider/*,btnSupprimer*/);

                F3.add(cnt);

                F3.show();
              //  p.setTitle(tfTitre.getText());
             //   p.setDescription(tfSujet.getText());
               // System.out.println(p.getTitle());
           btnValider.addActionListener(new ActionListener() {
 @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitre.getText().length() == 0) || (tfSujet.getText().length() == 0) || !StringUtils.isNumeric(tfSujet.getText()) /* || (tfLivraison.getText().length() == 0) */) {
                    Dialog.show("Alert", "Svp Verfier tous les champs ", new Command("OK"));
                } else {
                    try {
                        Stock t = new Stock();
                
                        t.setCat_id(p.getId());
                                                System.out.println(t.getCat_id());

                        t.setTitle(tfTitre.getText());
                        t.setDescription(tfSujet.getText());
                        System.out.println(p.getId());
                        
          //             int()tfcat.getText();
//                         float id = Float.parseFloat(obj.get("id").toString());
             //   t.setId((int)id);
                        //  t.setCreateur(User.getId());

                        if (ServiceTask.getInstance().addStock(t)) {
                            Dialog.show("Success", "Stock Ajoutée", new Command("OK"));
                            /* Message m = new Message("Body of message");
                            String textAttachmentUri = null;
m.getAttachments().put(textAttachmentUri, "text/plain");
                            String imageAttachmentUri = null;
m.getAttachments().put(imageAttachmentUri, "image/png");
Display.getInstance().sendMessage(new String[] {"mohamed.chtioui@esprit.tn"}, "Subject of message", m);*/
                            
                          new  ListTasksForm(previous,res).show();
                            
                            
                            
                            } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "ID must be a number", new Command("OK"));
                    }
                }
            }
          //      c.show();
            });
          /*      
                  btnSupprimer.addActionListener((eac) -> {
                boolean list = ServiceTask.getInstance().deleteRec(p.getId());
                Dialog.show("Success", "Reclamation Supprimée", new Command("OK"));
                c.show();
            });
           */
            });
            

            
             

          
            
            
        });
        
            //    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> F2.showBack());

        return cnt2;
    
}
}