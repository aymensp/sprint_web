/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.messaging.Message;
import com.codename1.push.PushCallback;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.util.Resources;
import com.sun.webkit.Disposer;
import entities.Produit;
import entities.Categorie_Produit;
import entities.Utilisateur;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import services.ProduitService;

import services.PanierService;
import entities.Panier;

/**
 *
 * @author anasc
 */
public class AllProduitsForm extends BaseForm implements PushCallback {

    Resources res;
    private ArrayList<Produit> annonces = new ArrayList<>();
    private ArrayList<Panier> annoncesp = new ArrayList<>();
    Container cnt, icp, ic;
    ArrayList<Categorie_Produit> listCat = new ArrayList<>();
  

    public AllProduitsForm(Resources res) {
        super("Produits", BoxLayout.y());
        ProduitService serviceAnnonce = new ProduitService();
         PanierService panierService = new PanierService();
        cnt = new Container(BoxLayout.y());
        this.res = res;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        getToolbar().addCommandToRightBar("Retour", FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_LEFT, getUnselectedStyle()), (evt) -> {
            new AccueilForm(res).show();

        });
        setUIID("SignUp");

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
        Container row = new Container(BoxLayout.x());
        Container row1 = new Container(BoxLayout.y());
        Container pa = FlowLayout.encloseRight();
        listCat = serviceAnnonce.getAllCategories();
        Button ajout = new Button(" + ");
        ComboBox Ccateg = new ComboBox<Categorie_Produit>();
        ComboBox Ctrier = new ComboBox<String>();
        
        Ctrier.addItem("Prix Bas");
        Ctrier.addItem("Prix Elevé");
        for (Categorie_Produit l : listCat) {
            Ccateg.addItem(l.getLibelle());
        } 
        annoncesp=panierService.getAllAnnoncesP();
                              int sizePanier=annoncesp.size();
        Button Tailler = new Button("("+sizePanier+")");
         Tailler.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                int x=1;
                                annoncesp=panierService.getAllAnnoncesP();
                                if(annoncesp.size()>0)
                                {
                                ShowPanier a=new ShowPanier(res);
                            a.show();
                                }
                                else
                                {
                                 Dialog.show("Information", "Votre Panier est Vide , Veuillez le remplir", "OK", "Cancel");
                                }

                            }
                        });
        
         Label y = new Label("                                                   ");
        Tailler.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SHOPPING_CART, Tailler.getUnselectedStyle()));
        Label c = new Label("(0)");
        annoncesp=panierService.getAllAnnoncesP();
                           
        
        //row.add(ajout);
        row1.add(Ccateg);
        row1.add(Ctrier);
       // row.add(row1);
       // row.add(y);
       // row.add(pa);
      //  row.add(c);
 pa.add(row1);
 pa.add(Tailler);
        //cnt.add(row);
        cnt.add(pa);
        ic = new InfiniteContainer() {
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
                ic.setLayout(new GridLayout((annonces.size()+1)/2,2));
                // System.out.println((annonces.size()+1)/2);
                Component[] elments = new Component[amount];
                int i = 0;
                for (Produit a : annonces) {
                    Container element = new Container(BoxLayout.y());
                    Container ls = new Container(BoxLayout.y());
                    try {
                        Image img = Image.createImage("file:/C:/wamp64/www/final/tunfugees/web/uploads/Produit/photo/" + a.getPhoto()).fill(555, 500);

                        ImageViewer v = new ImageViewer(img);
                        
                        Container ls1 = new Container(BoxLayout.y());
                      
                        
                        Label lbl_name = new Label(a.getNomProd(),("CenterLabel"));
                        Label lbl_desc = new Label(a.getDescription());
                        Label lbl_prix = new Label(a.getPrix().toString()+" Dt",("CenterLabel"));
                         Button details = new Button(); 
                         
                        ls.add(v);
                            
                        ls1.add(lbl_name);
                      
                        ls1.add(lbl_prix);
                        ls1.setLeadComponent(details);
                    
                        ls.add(ls1);
                    
                       
                        element.add(ls);
                        
                        
                     
                        
                        
                      
                        
                        details.addPointerPressedListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                try {
                                    //System.out.println(a.getId());
                                    new ShowProduitForm(res, a).show();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
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
      
        ic.setScrollableY(
                false);
        cnt.add(ic);

        ajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt
            ) {
                new AddProduitForm(res).show();
            }
        });

        Ccateg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int idc = listCat.get(Ccateg.getSelectedIndex()).getId();
                annonces.clear();
                cnt.removeComponent(ic);
                if (cnt.contains(icp) == true) {
                    cnt.removeComponent(icp);
                }
                icp = new InfiniteContainer() {
                    @Override
                    public Component[] fetchComponents(int index, int amount) {
                        if (index == 0) {
                            annonces = serviceAnnonce.getAnnoncesByCategorie(idc);
                            amount = annonces.size();

                        }
                        if (index + amount > annonces.size()) {

                            amount = annonces.size() - index;

                        }
                        if (amount <= 0) {
                            return null;
                        }
                        icp.setLayout(new GridLayout(2,2));
                        // System.out.println(amount);
                        Component[] elments = new Component[amount];
                        int i = 0;
                        for (Produit a : annonces) {
                            Container element = new Container(BoxLayout.y());
                            Container ls = new Container(BoxLayout.y());
                            try {
                                Image img = Image.createImage("file:/C:/wamp64/www/final/tunfugees/web/uploads/Produit/photo/" + a.getPhoto()).fill(555, 500);

                                ImageViewer v = new ImageViewer(img);
                                Container ls1 = new Container(BoxLayout.y());
                             
                                Label lbl_name = new Label(a.getNomProd());
                               
                                Label lbl_prix = new Label(a.getPrix().toString());
                           
                            
                                ls.add(v);
                                ls1.add(lbl_name);
                             
                                ls1.add(lbl_prix);
                           ls.add(ls1);
                                element.add(ls);
                              
                              lbl_name.addPointerPressedListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        try {
                                            new ShowProduitForm(res, a).show();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
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
                cnt.add(icp);
            }
        });
        Ctrier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                annonces.clear();
                cnt.removeComponent(ic);
                if (cnt.contains(icp) == true) {
                    cnt.removeComponent(icp);
                }
               
                if (Ctrier.getSelectedItem().equals("Prix Bas")) {
                    icp = new InfiniteContainer() {
                        @Override
                        public Component[] fetchComponents(int index, int amount) {
                            if (index == 0) {
                                annonces = serviceAnnonce.tirerAnnonces("PB");
                                amount = annonces.size();

                            }
                            if (index + amount > annonces.size()) {

                                amount = annonces.size() - index;

                            }
                            if (amount <= 0) {
                                return null;
                            }
                            icp.setLayout(new GridLayout(2,2));
                            // System.out.println(amount);
                            Component[] elments = new Component[amount];
                            int i = 0;
                            for (Produit a : annonces) {
                                Container element = new Container(BoxLayout.y());
                                Container ls = new Container(BoxLayout.y());
                                try {
                                    Image img = Image.createImage("file:/C:/wamp64/www/final/tunfugees/web/uploads/Produit/photo/" + a.getPhoto()).fill(555, 500);

                                    ImageViewer v = new ImageViewer(img);
                                    Container ls1 = new Container(BoxLayout.y());
                             
                                    Label lbl_name = new Label(a.getNomProd());
                        
                                    Label lbl_prix = new Label(a.getPrix().toString());
                                  
                              
                                    ls.add(v);
                                    ls1.add(lbl_name);
                           
                                    ls1.add(lbl_prix);
                                    ls1.setLeadComponent(lbl_name);
                                     ls.add(ls1);
                                    element.add(ls);
                             
                                 lbl_name.addPointerPressedListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent evt) {
                                            try {
                                                new ShowProduitForm(res, a).show();
                                            } catch (IOException ex) {
                                                ex.printStackTrace();
                                            }
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
                    cnt.add(icp);
                }
                if (Ctrier.getSelectedItem().equals("Prix Elevé")) {
                    icp = new InfiniteContainer() {
                        @Override
                        public Component[] fetchComponents(int index, int amount) {
                            if (index == 0) {
                                annonces = serviceAnnonce.tirerAnnonces("PE");
                                amount = annonces.size();

                            }
                            if (index + amount > annonces.size()) {

                                amount = annonces.size() - index;

                            }
                            if (amount <= 0) {
                                return null;
                            }
                            icp.setLayout(new GridLayout(2,2));
                            // System.out.println(amount);
                            Component[] elments = new Component[amount];
                            int i = 0;
                            for (Produit a : annonces) {
                                Container element = new Container(BoxLayout.y());
                                Container ls = new Container(BoxLayout.y());
                                try {
                                    Image img = Image.createImage("file:/C:/wamp64/www/final/tunfugees/web/uploads/Produit/photo/" + a.getPhoto()).fill(555, 500);

                                    ImageViewer v = new ImageViewer(img);
                                    Container ls1 = new Container(BoxLayout.y());
                                   
                                    Label lbl_name = new Label(a.getNomProd());
                                
                                    Label lbl_prix = new Label(a.getPrix().toString());
                                 
                                 
                                    ls.add(v);
                                    ls1.add(lbl_name);
                                 ls1.setLeadComponent(lbl_name);
                                    ls1.add(lbl_prix);
                                       ls.add(ls1);
                                    element.add(ls);
                                 
                                    lbl_name.addPointerPressedListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent evt) {
                                            try {
                                                new ShowProduitForm(res, a).show();
                                            } catch (IOException ex) {
                                                ex.printStackTrace();
                                            }
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
                    cnt.add(icp);
                }
            }
        });
        getToolbar().addSearchCommand((e) -> {
            String text = (String) e.getSource();
            annonces.clear();
            cnt.removeComponent(ic);
            if (cnt.contains(icp) == true) {
                cnt.removeComponent(icp);
            }
            if (text.equals("")) {
                icp = new InfiniteContainer() {
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
                        icp.setLayout(new GridLayout(2,2));
                        // System.out.println(amount);
                        Component[] elments = new Component[amount];
                        int i = 0;
                        for (Produit a : annonces) {
                            Container element = new Container(BoxLayout.y());
                            Container ls = new Container(BoxLayout.y());
                            try {
                                Image img = Image.createImage("file:/C:/wamp64/www/final/tunfugees/web/uploads/Produit/photo/" + a.getPhoto()).fill(555, 500);

                                ImageViewer v = new ImageViewer(img);
                                Container ls1 = new Container(BoxLayout.y());
                             
                                Label lbl_name = new Label(a.getNomProd());
                        
                                Label lbl_prix = new Label(a.getPrix().toString());
                             
                              
                                ls.add(v);
                                ls1.add(lbl_name);
                       
                                ls1.add(lbl_prix);
                               ls.add(ls1);
                                element.add(ls);
                               
                            lbl_name.addPointerPressedListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        try {
                                            new ShowProduitForm(res, a).show();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
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
            } else {
                icp = new InfiniteContainer() {
                    @Override
                    public Component[] fetchComponents(int index, int amount) {
                        if (index == 0) {
                            annonces = serviceAnnonce.recherchAnnonces(text);
                            amount = annonces.size();

                        }
                        if (index + amount > annonces.size()) {

                            amount = annonces.size() - index;

                        }
                        if (amount <= 0) {
                            return null;
                        }
                        // System.out.println(amount);
                          icp.setLayout(new GridLayout(2,2));
                        Component[] elments = new Component[amount];
                        int i = 0;
                        for (Produit a : annonces) {
                            Container element = new Container(BoxLayout.y());
                            Container ls = new Container(BoxLayout.y());
                            try {
                                Image img = Image.createImage("file:/C:/wamp64/www/final/tunfugees/web/uploads/Produit/photo/" + a.getPhoto()).fill(555, 500 );

                                ImageViewer v = new ImageViewer(img);
                                Container ls1 = new Container(BoxLayout.y());
                        
                                Label lbl_name = new Label(a.getNomProd());
                               
                                Label lbl_prix = new Label(a.getPrix().toString());
                              
                                ls.add(v);
                                ls1.add(lbl_name);
                            ls1.setLeadComponent(lbl_name);
                                ls1.add(lbl_prix);
                                   ls.add(ls1);
                                element.add(ls);
                           
                                lbl_name.addPointerPressedListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        try {
                                            new ShowProduitForm(res, a).show();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
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
            }

            cnt.add(icp);
        });
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
