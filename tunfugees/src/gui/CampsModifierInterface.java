/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.push.PushCallback;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
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
import entities.Camps;
import entities.Refugies;
import java.util.ArrayList;
import services.CampsService;
import services.RefugiesService;

/**
 *
 * @author hp
 */
public class CampsModifierInterface extends BaseForm implements PushCallback  {
          
      private Resources res;
   Container cnt1;
    public CampsModifierInterface(Resources res,Camps evnt) {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
         super("Refugees", BoxLayout.y());
        getTitleArea().setUIID("Container");
        setLayout(BoxLayout.y());
    
         this.res = res;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        //Tabs : toolbar
       
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
        RadioButton all = RadioButton.createToggle("Modify Camps", barGroup);
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
         cnt1 = new Container(BoxLayout.y());
        super.add(cnt1);
           ArrayList<Camps> list_e = new CampsService().getList2();
          
      //  Picker p = new Picker();
       // p.setType(Display.PICKER_TYPE_DATE);

       

        TextField NBRMAX = new TextField(String.valueOf(evnt.getNbrmax()), "NBRMAX");//textfield
         ComboBox<String> CATEGORIES = new ComboBox("Infancy(0-3)", "Childhood(4-8)", "Puberty(9-13)","Teenager(14-18)", "Adult(19-39)", "Middle age(40-59)", "old age(60-99)" );
  
        // TextField date = new TextField(evnt.getDate().toString(), "date");
    //TextField heure = new TextField(evnt.getHeure().toString(), "heure");
        
         TextField ADRESSE = new TextField(evnt.getAdresse(), "ADRESS");//textfield


        Button save = new Button("Modify");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 CampsService sg = new CampsService();                
                    Camps e= new Camps(evnt.getIdCamp(),Integer.parseInt(NBRMAX.getText()),CATEGORIES.getSelectedItem(),ADRESSE.getText());

                    sg.modifier(e);
                new affichercamps(res).show();
            }
        });
           setLayout(BoxLayout.y());
           addAll(NBRMAX,CATEGORIES,ADRESSE,save);
           
                  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new affichercamps(res).show());

        
        
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
