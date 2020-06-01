package gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.push.PushCallback;
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

public class MenuReclamation extends BaseForm implements PushCallback {

    Form current;
   // AccueilForm c = new AccueilForm();
    private Resources res;
    
    public MenuReclamation(Resources res) {
        this.res = res;
         current = this;
        setTitle("Menu");
  setLayout(new FlowLayout(Component.CENTER, Component.CENTER));
   Container cnt = new Container(BoxLayout.y());
       Label l1 = new Label("Choisir une Option");
       
        Button btnListRecs = new Button("Liste des Réclamations");
        Button btnAddRecs = new Button("Ajouter une réclamation");
        
        btnListRecs.addActionListener(e -> new ListReclamations(current,res).show());
        btnAddRecs.addActionListener(e -> new AddTaskForm(res).show());
        //btnAjoutTasks.addActionListener(e -> new AddStockForm(current).show());
        cnt.addAll(l1, btnListRecs, btnAddRecs);
        //addAll(cnt);
      super.add(cnt);

        //Formulaire Profile
        
//Toolbar ili howa el menu 
      Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            new AccueilForm(res).show();
        });




        
        
    }

     private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }

      @Override
    public void push(String value) {
        Dialog.show(value, "ok", "", "");
    }

    @Override
    public void registeredForPush(String deviceId) {
    }

    @Override
    public void pushRegistrationError(String error, int errorCode) {
    }
}
