/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.push.PushCallback;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
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
public class affichercamps extends BaseForm implements PushCallback {
        private Resources res;
  Container cnt;
    public affichercamps( Resources res) {
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
        RadioButton all = RadioButton.createToggle("Camps", barGroup);
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
      cnt = new Container(BoxLayout.y());
        //creer un groupe 
        
         super.add(cnt);
        
        ArrayList<Camps> list_e = new CampsService().getList2();
      //  ArrayList<ListeParticipant> list_p = new ServiceEvenement().Afficher();
        
    
        for(Camps gmi :  list_e )
         
        { cnt.add(addItemEvent(gmi));}
        //this.add(cnt2);
       
        this.show();
         this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e ->  new AccueilForm(res).show());

     this.getToolbar().addMaterialCommandToRightBar("",FontImage.MATERIAL_ADD, e-> new addCamp(res).show());

        
        
       
       
    }

      public Container addItemEvent(Camps gm){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn8=new Container(BoxLayout.x());
        Container cn4=new Container(BoxLayout.x());
        Container cn5=new Container(BoxLayout.x());
        
        
        Label CATEGORIES=new Label("CATEGORIES : ");       
        Label lab=new Label(gm.getNomCamp());
        cn8.add(CATEGORIES).add(lab);
        
        
        Label ADRESSE=new Label("ADRESSE : ");
        Label lab2=new Label(gm.getAdresse());
        cn4.add(ADRESSE).add(lab2);
        
        
        Label NBRMAX=new Label("NBRMAX : ");
        Label lab3=new Label( Integer.toString (gm.getNbrmax()));
        cn5.add(NBRMAX).add(lab3);
       
        
        
        Button btnSupp=new Button("supprimer");
        btnSupp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CampsService sr = new CampsService();
                sr.supprimer(gm);
                new affichercamps(res).show();
            }
        });
        Button btnmodifier=new Button("modifier");
       btnmodifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            new CampsModifierInterface(res,gm).show();
            }
        });
        Container cn3=new Container(BoxLayout.x());
        cn3.add(btnSupp).add(btnmodifier);
        
       
        cn2.add(cn8).add(cn4).add(cn5).add(cn3);
        
        cn1.add(BorderLayout.CENTER,cn2);
        
        return cn1;
                
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
