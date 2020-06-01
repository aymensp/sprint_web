/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Utils.PDFHandler;
import com.codename1.ui.util.Resources;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import entities.Panier;
import entities.Commande;

import entities.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;

import services.CommandeService;
import services.PanierService;
import com.codename1.payment.Product;
import com.codename1.payment.Purchase;
import com.codename1.payment.Receipt;
import com.codename1.payment.PurchaseCallback;
import com.codename1.payment.ReceiptStore;
import com.codename1.payment.RestoreCallback;
import java.util.*;
import services.ServiceUser;



/**
 *
 * @author Aziz
 */
public class CommandeForm extends BaseForm  {
     Resources res;
 Container cnt;
 ArrayList<Commande> listcmd = new ArrayList<>();
 ServiceUser u=new ServiceUser();
    public CommandeForm(Resources res) {
        super("Commandes", BoxLayout.y());
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
        RadioButton all = RadioButton.createToggle("Commandes", barGroup);
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
      
      Button Back=new Button();
   
     
      
      
      Label bien=new Label("Vos Commandes : ");
      bien.getStyle().setFgColor(0x220000);
      row.add(Back);
   
      
      Back.setIcon(FontImage.createMaterial(FontImage.MATERIAL_REPLAY, j.getUnselectedStyle()));
      row.add(bien);
      
      Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              AllProduitsForm a=new AllProduitsForm(res);
                            a.show();
            }
        });
      
      
       
        cnt.add(row);
         //listcmd=commandeService.getCmdClient(Utilisateur.current_user.getId());
         // for (Commande a : listcmd) {

              
              Container ic = new InfiniteContainer() {
            double prix_total=0;
            
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    listcmd=commandeService.getCmdClient(Utilisateur.current_user.getId());
                    amount = listcmd.size();

                }
                if (index + amount > listcmd.size()) {

                    amount = listcmd.size() - index;

                }
                if (amount <= 0) {
                    return null;
                }
                
                Component[] elments = new Component[amount];
                int i = 0;
                int i2=1;
                for (Commande a : listcmd) {
                    Container element = new Container(BoxLayout.y());
                    Container ls = new Container(BoxLayout.x());
                    try {
                        Image img = Image.createImage("file:/C:/wamp64/www/ecosystemweb/web/uploads/Annonce/photo/cmd.jpg").fill(300, 300);
Button j = new Button("");
                        ImageViewer v = new ImageViewer(img);
                        Container ls1 = new Container(BoxLayout.y());
                        Container buttons = new Container(BoxLayout.x());
                        String foufa =a.getDate_emission().toString();
                        System.out.println(foufa);
                        String f1=foufa.substring(0,10);
                        String f3=foufa.substring(19,24);
                        String f4=foufa.substring(11,18);
                                
                      
                        Label datee=new Label("Date d'émission :");
                   Label tmp=new Label("Temps :");
                   Label etatcmd=new Label("Etat Commande :");
                     Label prixx=new Label("Prix Total :");
                     
                        
                        
                        
                        Label date = new Label(f1+" "+f3);
                        Label temps=new Label(f4);
                        
                        Label etat = new Label(a.getEtat_commande());
                        Label lbl_prix = new Label(Double.toString(a.getPrixTotal())+" DT");
                        Button delete=new Button("Annuler");
                        Button download = new Button("Imprimer");
    download.setIcon(FontImage.createMaterial(FontImage.MATERIAL_LOCAL_PRINTSHOP, download.getUnselectedStyle()));
                      //delete.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, j.getUnselectedStyle()));
                       
                        date.getStyle().setFgColor(0x220000);
                        etat.getStyle().setFgColor(0x220000);
                        temps.getStyle().setFgColor(0x220000);
                        lbl_prix.getStyle().setFgColor(0x220000);
                         datee.getStyle().setFgColor(0xE54040);
                          tmp.getStyle().setFgColor(0xE54040);
                           etatcmd.getStyle().setFgColor(0xE54040);
                            prixx.getStyle().setFgColor(0xE54040);
                       
                        Label Article=new Label("Commande "+i2);
                         Article.getStyle().setFgColor(0x220000);
                         //Article.getStyle().setFont(Font.STYLE_ITALIC);
                       // ls.add(Article);
                        ls.add(v);
                       ls1.add(datee);
                        ls1.add(date);
                        ls1.add(tmp);
                        ls1.add(temps);
                        ls1.add(etatcmd);
                        ls1.add(etat);
                        ls1.add(prixx);
                        ls1.add(lbl_prix);
                       // ls.add(livraison);
                       
                       
                        ls1.add(buttons);
                    
                        buttons.add(delete);
                        buttons.add(download);
                        element.add(ls);
                        ls.add(ls1);

                        elments[i] = element;
                        i++;
                        i2++;
                        
                        
                        
                        delete.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                               commandeService.deleteCMD(a.getId());
                                Dialog.show("Information", "Commande Annulé", "OK", "Cancel");
                                CommandeForm r=new CommandeForm(res);
                            r.show();
                                
                            }
                        });
                        download.addActionListener(e -> {
        PDFHandler pdfh = new PDFHandler();
       // commentairesP = FrmService.getAllCommentsByPublication(p.getId());
       
       Utilisateur ut = u.getUserData(a.getId_user());
        pdfh.getFile("<br><br><br>"
                + "<h2><center style=\"color: red;\">Commande de Mr/Mme" +ut.getNom()+ " " + ut.getPrenom()+"</center></h2><br>"
                +"<h3> Passe le : "+a.getDate_emission().toString()+ "</h3><br>"
                
                +"<h3> Etat :</h3> "+a.getEtat_commande() + "<br>"
                +"<h3> Prix Totale est :</h3> "+ String.valueOf(a.getPrixTotal()) + "  <br>"
              
                , "commande" + a.getId() + ".pdf");
    });
    
                      
                        
                      
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
     
}
