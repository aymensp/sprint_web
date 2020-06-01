/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.push.PushCallback;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Refugies;
import java.util.ArrayList;
import services.PanierService;
import services.ProduitService;
import services.RefugiesService;

/**
 *
 * @author hp
 */
public class RefugiesModifierInterface extends BaseForm implements PushCallback {
         private Resources res;
Container cnt;
    
    public RefugiesModifierInterface(Resources res,Refugies evnt) {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
    super("Modiefier Refugies", BoxLayout.y());
        ProduitService serviceAnnonce = new ProduitService();
         PanierService panierService = new PanierService();
        cnt = new Container(BoxLayout.y());
        this.res = res;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        getToolbar().addCommandToRightBar("Retour", FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_LEFT, getUnselectedStyle()), (evt) -> {
            new afficherrefugies(res).show();

        });
       

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
        RadioButton all = RadioButton.createToggle("Produits", barGroup);
        all.setUIID("SelectBar");
//        TextField searchField = new TextField("", "Toolbar Search");
//        searchField.getHintLabel().setUIID("Title");
//        searchField.setUIID("Title");
//        searchField.getAllStyles().setAlignment(Component.LEFT);
//        getToolbar().setTitleComponent(searchField);
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
           ArrayList<Refugies> list_e = new RefugiesService().getList2();
          
      //  Picker p = new Picker();
       // p.setType(Display.PICKER_TYPE_DATE);

       // Picker pt = new Picker();
       // pt.setType(Display.PICKER_TYPE_TIME);
       // p.setText(evnt.getDate());
        //pt.setText(evnt.getHeure());
        
        TextField nom = new TextField(evnt.getNom(), "Nom");//textfield
         TextField prenom = new TextField(evnt.getPrenomn(), "prenom");
          
        // TextField date = new TextField(evnt.getDate().toString(), "date");
    //TextField heure = new TextField(evnt.getHeure().toString(), "heure");
        
         TextField pays = new TextField(evnt.getPays(), "pays");//textfield
     
         TextField Age;
             Age = new TextField(String.valueOf(evnt.getAge()), "Age");
         

        Button save = new Button("Modifier");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 RefugiesService sg = new RefugiesService();                
                    Refugies e= new Refugies(evnt.getIdref(),nom.getText(),prenom.getText(),pays.getText(),Integer.parseInt(Age.getText()));

                    sg.modifier(e);
                new afficherrefugies(res).show();
            }
        });
        
           super.addAll(nom,prenom,pays,Age,save);
           
          //;  this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new afficherrefugies(res).show());

        
        
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
