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
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.wefeel.QRMaker.QRMaker;
import entities.Produit;
import entities.Panier;
import entities.Commande;
import entities.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import services.ProduitService;
import services.CommandeService;
import services.PanierService;
import services.TwilioSMS;

/**
 *
 * @author Aziz
 */
public class ShowPanier extends BaseForm{
private ArrayList<Panier> annoncesp = new ArrayList<>();
    Container cnt;
     Resources res;
     int k2=0;
     double prixtot;

    public ShowPanier(Resources res) {
        super("Panier", BoxLayout.y());
      
        PanierService panierService = new PanierService();
          CommandeService commandeService = new CommandeService();
        cnt = new Container();
        this.res = res;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setUIID("SignUp");
        getContentPane().setScrollVisible(false);

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0x29a02d);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0x29a02d);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        super.addSideMenu(res);

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Articles Panier", barGroup);
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
        
Button j = new Button("");
      Container row = new Container(BoxLayout.x());
      Label pt=new Label("                                     ");
      Button Back=new Button();
      Back.setIcon(FontImage.createMaterial(FontImage.MATERIAL_REPLAY, j.getUnselectedStyle()));
      row.add(Back);
      Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              AllProduitsForm a=new AllProduitsForm(res);
                            a.show();
            }
        });
       row.add(pt);
       
        cnt.add(row);
      
        Container ic = new InfiniteContainer() {
              int k=0;
            double prix_total=0;
            
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    annoncesp=panierService.getAllAnnoncesP();
                    amount = annoncesp.size();

                }
                if (index + amount > annoncesp.size()) {

                    amount = annoncesp.size() - index;

                }
                if (amount <= 0) {
                    return null;
                }
                
                Component[] elments = new Component[amount];
                int i = 0;
                int i2=1;
                for (Panier a : annoncesp) {
                    Container element = new Container(BoxLayout.y());
                    Container ls = new Container(BoxLayout.x());
                    try {
                        k=commandeService.verifLigne(a.getIdProd());
                             System.out.println(k);
                        k2=k;
                        
                        Image img = Image.createImage("file:/C:/wamp64/www/final/tunfugees/web/uploads/Produit/photo/" + a.getPhoto()).fill(300, 300);
Button j = new Button("");
                        ImageViewer v = new ImageViewer(img);
                        Container ls1 = new Container(BoxLayout.y());
                        Container buttons = new Container(BoxLayout.x());
                          Label lbl_name1 = new Label("Nom :");
 Label lbl_desc1 = new Label("Description :");
  Label lbl_prix1 = new Label("Prix :");
                        Label lbl_name = new Label(a.getNomProd());
                  //    Label lbl_name=new Label(a.get)
                        Label lbl_prix = new Label(Double.toString(a.getPrix()));
                        Button delete=new Button();
                        delete.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, j.getUnselectedStyle()));
                       prix_total=prix_total+a.getPrix();
                        lbl_name.getStyle().setFgColor(0x220000);
                        
                        lbl_prix.getStyle().setFgColor(0x220000);
                       
                        lbl_name1.getStyle().setFgColor(0xE54040);
                        lbl_desc1.getStyle().setFgColor(0xE54040);
                        lbl_prix1.getStyle().setFgColor(0xE54040);
                        
                        Label Article=new Label("Article "+i2);
                         Article.getStyle().setFgColor(0x220000);
                         //Article.getStyle().setFont(Font.STYLE_ITALIC);
                        ls.add(Article);
                        ls.add(v);
                        ls1.add(lbl_name1);
                        ls1.add(lbl_name);

                        ls1.add(lbl_prix1);
                        ls1.add(lbl_prix);
                        
                        ls1.add(buttons);
                        buttons.add(delete);
                        element.add(ls);
                        ls.add(ls1);

                        elments[i] = element;
                        i++;
                        i2++;
                        
                        delete.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                panierService.supprimerAnnonce(a.getId());
                                
                                Dialog.show("Information", "Article Supprimé du Panier", "OK", "Cancel");
                             //ls.setHidden(true);
                             ShowPanier r=new ShowPanier(res);
                            r.show();

                            }
                        });
                        
                      
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    // element.setLeadComponent(b);

                }
                pt.setText("                                             "+"Prix Total : "+Double.toString(prix_total)+" DT");
                pt.getStyle().setFgColor(0xFF5C5C);
                prixtot=prix_total;
                return elments;
                
                
            }
           
           
             
            
        };
        ic.setScrollableY(false);
        
        cnt.add(ic);
        Container CMD = new Container(BoxLayout.x());
      Button cmd = new Button("Vider Panier");
      
     
      
          Button cmd1 = new Button("Passer Commande");
        CMD.add(cmd);
        CMD.add(cmd1);
        cnt.add(CMD);
        
        cmd1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("k : "+k2);
                if(k2==1)
                {
                 Dialog.show("Erreur","Commande Déja Existante", "OK", "Cancel"); 
                }
                else
                {
                  //   Dialog.show("succes","Commande Pasee", "OK", "Cancel"); 
                    boolean ti;
                    
                 Commande c=new Commande("En cours",Utilisateur.current_user.getId(),prixtot);
               panierService.ajouterCommande(c);
              panierService.viderPanier();
                
                          
                                   Random r = new Random();
                           String val = "" + r.nextInt(10000);

                      System.out.println("val  : " +val);
                    
                         // Utilisateur.current_user.toString();
                             TwilioSMS s=new TwilioSMS("AC144a9fe7a1f7ae1f571704be8742a80c","2cffde90d972a3b28ecf61d6bfc972ce","+19073122199");
                              s.sendSmsAsync("+216"+Utilisateur.current_user.getNumtel(),"Confirmation de votre Commande Monsieur/Madame :"+Utilisateur.current_user.getNom()+" "+Utilisateur.current_user.getPrenom()+"Votre code  de commande est le suivant :"+val);
                            
                                         Button saveButton = new Button("Commands");
      
Image img = QRMaker.QRCode(String.valueOf(Utilisateur.current_user.getNom()+" "+prixtot));
ImageViewer image= new ImageViewer(img);


Dialog d = new Dialog("Voici votre qr Code");
 Image placeholder = Image.createImage(45, 45);
		

        int height = Display.getInstance().convertToPixels(25f);
        int width = Display.getInstance().convertToPixels(25f);
        Label La = new Label(image.getImage().fill(width, height));
        
          saveButton.addActionListener(e->{
          CommandeForm a=new CommandeForm(res);
                            a.show();
        });
          
        
    // d.add(CreateContainer_Y(imgg2));      
          
d.add( La);
d.add(saveButton);
   // d.add(GridLayout.encloseIn(1, saveButton));

d.show();


                }
               
            }

        });
        
        
        
        
         cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                panierService.viderPanier();
                 
                cmd.setHidden(true);
                ic.setHidden(true);
                row.setHidden(true);
                CMD.setHidden(true);
                Container PanierVide = new Container(BoxLayout.x());
                Label txt= new Label("Votre Panier est Vide");
                pt.getStyle().setFgColor(0x220000);
                PanierVide.add(txt);
                cnt.add(PanierVide);
                AllProduitsForm a=new AllProduitsForm(res);
                            a.show();
            }
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
    
  
}
