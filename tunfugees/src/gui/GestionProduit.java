/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import entities.Produit;
import java.io.IOException;
import java.util.ArrayList;
import services.ProduitService;
import services.PanierService;
import entities.Panier;
import services.EventService;

/**
 *
 * @author anasc
 */
public class GestionProduit extends BaseForm {

    Resources res;
    private ArrayList<Produit> annonces = new ArrayList<>();
    private ArrayList<Panier> annoncesp = new ArrayList<>();
    Container cnt;

    public GestionProduit(Resources res) {
        super("Annonces", BoxLayout.y());
        ProduitService serviceAnnonce = new ProduitService();
        PanierService panierService = new PanierService();
        cnt = new Container();
        this.res = res;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
       getToolbar().addCommandToRightBar("Add +", null, (evt) -> {
            new AddProduitForm(res).show();
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
        RadioButton all = RadioButton.createToggle("All Products", barGroup);
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

      Container row = new Container(BoxLayout.x());
        Button ajout = new Button(" + ");

       
       
       
        
     
       
        
        
      
        
        cnt.add(row);
        
        Container ic = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    annonces = serviceAnnonce.getAllAnnonces();
                    amount = annonces.size();

                }
                if (index + amount > annonces.size()) {

                    amount = annonces.size() - index;

                }
                if (amount <= 0) {
                    return null;
                }
                // System.out.println(amount);
                Component[] elments = new Component[amount];
                int i = 0;
                for (Produit a : annonces) {
                    Container element = new Container(BoxLayout.y());
                    Container ls = new Container(BoxLayout.x());
                    try {
                        Image img = Image.createImage("file:/C:/wamp64/www/final/tunfugees/web/uploads/Produit/photo/" + a.getPhoto()).fill(380, 380);

                        ImageViewer v = new ImageViewer(img);
                        Container ls1 = new Container(BoxLayout.y());
                        Container buttons = new Container(BoxLayout.x());
                        Label lbl_name = new Label(a.getNomProd());
                        Label lbl_desc = new Label(a.getDescription());
                        Label lbl_prix = new Label(a.getPrix().toString());
                        
                        Button supprimer = new Button("Delete");
                        Button modifier = new Button("Modify");
                        int id_annonce=a.getId();
                        
                        
                        
                     
                   
                        buttons.add(supprimer);
                        buttons.add(modifier);
                        ls.add(v);
                        ls1.add(lbl_name);
                        ls1.add(lbl_desc);
                        ls1.add(lbl_prix);
                        ls1.add(buttons);
                        element.add(ls);
                        ls.add(ls1);
                        
                        
                        
  
                       supprimer.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                            
                                boolean ti;
                                
                   ti=  serviceAnnonce.supprimer(a);
                   if (ti==true)
                   {
                       push("Product have been deleted","succes");
                         new GestionProduit(res).show();
                   }
                   else
                           
                   {
                       push("Problem","errer");
                   }
             
                        
                            }
                        });   
                        
                        modifier.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                
                          new ModifyProduct(a,res).show();
                            }
                        });
                        
                        
                        elments[i] = element;
                        i++;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    // element.setLeadComponent(b);

                }
                return elments;
            }
        };
        ic.setScrollableY(false);
        cnt.add(ic);
       
        super.add(cnt);
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
    
    public void push(String value,String value2) {
        Dialog.show(value2, value, "", "ok");
    }
}
