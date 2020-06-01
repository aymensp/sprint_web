/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.Stock;
import entities.Reclamation;
import entities.Utilisateur;
import services.PanierService;
import services.ProduitService;
import services.ServiceTask;
/**
 *
 * @author bhk
 */
public class AddTaskForm extends BaseForm {
Container cnt;
    Resources res;
Form current;
    public AddTaskForm(Resources res) {
         super("Refugees",BoxLayout.y());
       
         this.res = res;
        setTitle("Ajouter une nouvelle Réclamation");
        cnt = new Container(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            new MenuReclamation(res).show();
        });

        getContentPane().setScrollVisible(false);
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        super.addSideMenu(res);
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Ajouter une nouvelle Réclamation", barGroup);
        all.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, all),
                FlowLayout.encloseBottom(arrow)
        ));
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(false);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
       // res = UIManager.initFirstTheme("/theme");
        Container cnt = new Container(BoxLayout.y());
           Container cnt1= new Container (new FlowLayout(Component.CENTER, Component.CENTER));
           cnt1.add(cnt);
          ImageViewer img = new ImageViewer(res.getImage("livraison.jpg"));
        TextField tfTitre = new TextField("", "Titre");
        TextField tfSujet = new TextField("", "Description");
    //    TextField tfLivraison = new TextField("", "Numéro Livraison");
        Button btnValider = new Button("Ajouter Réclamation");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitre.getText().length() == 0) || (tfSujet.getText().length() == 0) /* || (tfLivraison.getText().length() == 0) */) {
                    Dialog.show("Alert", "Svp Remplissez tous les champs", new Command("OK"));
                } else {
                    try {
                        Reclamation t = new Reclamation();
                        t.setTitle(tfTitre.getText());
                        t.setDescription(tfSujet.getText());
                                             t.setCreateur(Utilisateur.current_user.getId()); /* User.getId() lehna yemn bech t7ot el fct eli bech recuperi el id user */

                        if (ServiceTask.getInstance().addTask(t)) {
                            Dialog.show("Success", "Réclamation Ajoutée", new Command("OK"));
                        //   new ListReclamations(current).show();

                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "ID must be a number", new Command("OK"));
                    }
                }
            }
        });

        cnt.addAll(img,tfTitre, tfSujet/*, tfLivraison*/, btnValider);
  super.add(cnt1);
    
       
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
}
