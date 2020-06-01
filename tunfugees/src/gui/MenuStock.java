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
import services.ServiceTask;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fares
 */

public class MenuStock extends Form {

    Form current;
    
    private Resources res;
   // AccueilForm c = new AccueilForm(theme);
    public MenuStock(Resources res) {
        
         current = this;
        setTitle("Menu");
  setLayout(new FlowLayout(Component.CENTER, Component.CENTER));
   Container cnt = new Container(BoxLayout.y());
       Label l1 = new Label("Choisir une Option");
        Button btnAjoutStock = new Button("Ajouter un Stock");
       
        Button btnListTasks = new Button("Liste des Stock");
        btnAjoutStock.addActionListener(e -> new ListCatStock(current,res).show());
        
        btnListTasks.addActionListener(e -> new ListTasksForm(current,res).show());
        cnt.addAll(l1,btnAjoutStock,btnListTasks);
        addAll(cnt);
      

        //Formulaire Profile
        
//Toolbar ili howa el menu 
      /*
        current.getToolbar()
                .addCommandToLeftSideMenu("Liste des Livraisons", null, ev -> {
                    new ListTasksForm(current).show();
                });

*/
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            new AccueilForm(res).show();
        });

        
        
    }

   
}
