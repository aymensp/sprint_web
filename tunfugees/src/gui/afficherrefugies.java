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
import entities.Refugies;
import java.util.ArrayList;
import services.RefugiesService;

/**
 *
 * @author hp
 */
public class afficherrefugies extends BaseForm implements PushCallback{
        private Resources res;
  Container cnt;
    public afficherrefugies(Resources res) {
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
        RadioButton all = RadioButton.createToggle("Refugees", barGroup);
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
        
        ArrayList<Refugies> list_e = new RefugiesService().getList2();
      //  ArrayList<ListeParticipant> list_p = new ServiceEvenement().Afficher();
        
    
        for(Refugies gmi :  list_e )
         
        { cnt.add(addItemEvent(gmi));}
      
       
        this.show();
        
        setToolbar(tb);
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e ->    new AccueilForm(res).show());

        getToolbar().addMaterialCommandToRightBar("",FontImage.MATERIAL_ADD, e-> new RefugiesAjouterInterface(res).show());
        getToolbar().addMaterialCommandToRightBar("Statique",FontImage.MATERIAL_SHOW_CHART, e -> new PieChart(res).show());
        
       
       
    }

      public Container addItemEvent(Refugies gm){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3=new Container(BoxLayout.x());
        Container cn4=new Container(BoxLayout.x());
        Container cn5=new Container(BoxLayout.x());
        Container cn9=new Container(BoxLayout.x());
        

        Label nom=new Label("Nom : ");
        Label lab=new Label(gm.getNom());  
        cn3.add(nom).add(lab);
        
        
       Label prenom=new Label("Prenom : ");
       Label lab2=new Label(gm.getPrenomn());
       cn4.add(prenom).add(lab2);


       Label age=new Label("Age : ");
       Label lab3=new Label(Integer.toString (gm.getAge()));
       cn5.add(age).add(lab3);
       
       Label pays=new Label("Pays : ");
       Label lab4=new Label(gm.getPays());
       cn9.add(pays).add(lab4);
       // Label lab2=new Label(gm.getDesciption());
        
        Button btnSupp=new Button("supprimer");
        btnSupp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                RefugiesService sr = new RefugiesService();
                sr.supprimer(gm);
                new afficherrefugies(res).show();
            }
        });
        Button btnmodifier=new Button("modifier");
       btnmodifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             
                new RefugiesModifierInterface(res,gm).show();
            }
        });
        Container cn8=new Container(BoxLayout.x());
        cn8.add(btnSupp).add(btnmodifier);
        
       ImageViewer img = new ImageViewer();
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
            URLImage urlImage = URLImage.createToStorage(placeholder, gm.getImage(), "http://127.0.0.1:8000/uploads/Produit/photo/"+gm.getImage());
            img.setImage(urlImage);
        cn2.add(cn3).add(cn4).add(cn5).add(cn9).add(cn8);
        cn1.add(BorderLayout.WEST,img );
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
