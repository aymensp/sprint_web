/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Panier;
import entities.Produit;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import services.PanierService;
import services.ProduitService;


/**
 *
 * @author anasc
 */

public class ShowProduitForm extends BaseForm {
    Container cnt;
    Resources res;
    Label lbl_text, lbl_desc, lbl_prix, lbl_region, lbl_date, lbl_likes, lbl_viwes;
        private ArrayList<Panier> annoncesp = new ArrayList<>();
    public ShowProduitForm(Resources res, Produit a) throws IOException {
        super("Show Product", BoxLayout.y());
        this.res = res;
        ProduitService as = new ProduitService();        
         PanierService panierService = new PanierService();
        as.UpdateViwesAnnonce(a.getId());
        cnt = new Container(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            new AllProduitsForm(res).show();
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
        RadioButton all = RadioButton.createToggle("Show Product", barGroup);
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
        Image img = Image.createImage("file:/C:/wamp64/www/final/tunfugees/web/uploads/Produit/photo/" + a.getPhoto()).fill(800, 1200);
        ImageViewer v = new ImageViewer(img);
        v.setWidth(1500);
        v.setHeight(1000);
        Container cloum = new Container(BoxLayout.y());
        Container row1 = new Container(BoxLayout.x());
        Label titre = new Label("Nom Du Produit :");
        lbl_text = new Label(a.getNomProd());
        lbl_text.setIcon(FontImage.createMaterial(FontImage.MATERIAL_TEXT_FIELDS, lbl_text.getUnselectedStyle()));
        lbl_text.setUIID("CenterLabel");
        row1.add(titre);
        row1.add(lbl_text);
        Container row2 = new Container(BoxLayout.x());
        Label desc = new Label("Description du Produit :");
        desc.setUIID("CenterLabel");
        lbl_desc = new Label(a.getDescription());
        lbl_desc.setUIID("Label");
        row2.add(desc);
        row2.add(lbl_desc);
        Container row3 = new Container(BoxLayout.x());
        Label pr = new Label("Prix :");
        pr.setUIID("CenterLabel");
        lbl_prix = new Label(Double.toString(a.getPrix())+" Dt");
        lbl_prix.setUIID("Label");
        row3.add(pr);
        row3.add(lbl_prix);
        Container row4 = new Container(BoxLayout.x());
        Label reg = new Label("Nom du Refugies :");
        reg.setUIID("Label");
        lbl_region = new Label(a.getNomRef());
        lbl_region.setUIID("Label");
        row4.add(reg);
        row4.add(lbl_region);
        Container row5 = new Container(BoxLayout.x());
        SimpleDateFormat sm = new SimpleDateFormat("EEEE,dd,MMMM,yyyy");
        Label date = new Label("Cree le :");
        date.setUIID("CenterLabel");     
        Container row6 = new Container(BoxLayout.x());
        lbl_likes = new Label(Integer.toString(a.getLikes()));
        lbl_likes.setIcon(FontImage.createMaterial(FontImage.MATERIAL_THUMB_UP, lbl_likes.getUnselectedStyle()));
        lbl_likes.setUIID("Label");
        lbl_viwes = new Label(Integer.toString(a.getViews()));
        lbl_viwes.setIcon(FontImage.createMaterial(FontImage.MATERIAL_VISIBILITY, lbl_viwes.getUnselectedStyle()));
        lbl_viwes.setUIID("Label");
        row6.add(lbl_likes);
        row6.add(lbl_viwes);
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        starRank.setProgress((int) a.getNote());
        Container row7 = new Container(BoxLayout.x());
        row7.add(starRank);
        cloum.add(row1);
        cloum.add(row2);
        cloum.add(row3);
        cloum.add(row4);   
        cloum.add(row6);
        cloum.add(row7);
         Container buttons = new Container(BoxLayout.x());
        Button j = new Button("J'adore");
                        //Button show = new Button("");
                        Button panier = new Button("Ajouter au Panier");
                         j.setIcon(FontImage.createMaterial(FontImage.MATERIAL_THUMB_UP, j.getUnselectedStyle()));
                        panier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SHOPPING_CART, panier.getUnselectedStyle()));
                        buttons.add(j);
                         buttons.add(panier);   
                         
                         Container pa = FlowLayout.encloseRight();
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
        
          c.setText("("+sizePanier+")");
                              c.getStyle().setFgColor(0x220000);
        
        pa.add(Tailler);
      //  pa.add(c);
        cnt.add(pa);
        cnt.add(v);
        cnt.add(buttons);
        cnt.add(cloum);
        add(cnt);
  panier.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                int x=1;
                                annoncesp=panierService.getAllAnnoncesP();
                                 for (Panier ty : annoncesp) {
                                    
                                   if(ty.getNomProd().equals(a.getNomProd())) 
                                   {
                                   x=2;
                                   }
                                   else
                                   {
                                   x=1;
                                   }
                                 }
                                 System.out.println(x);
                                 
                                   
                                 if(x==1)
                                 {
                                     
                                 Panier ap = new Panier(a.getId()+"",
                                                        a.getNomProd(),
                                                        a.getPrix(),
                                                        a.getNomRef(),
                                                        a.getPhoto());
                                     System.out.println(ap);
                              panierService.ajouterAnnonce(ap);
                              System.out.println("C bn ajout panier ");
                              annoncesp=panierService.getAllAnnoncesP();
                              int sizePanier=annoncesp.size();
                          Tailler.setText("("+sizePanier+")");
                              //c.getStyle().setFgColor(0x220000);
                                 }
                                 else                                    
                                 {                                                                     
                                   push("article deja ajouter au panier ")    ;                       
                                 }      
                               
                            }                         
                        });
                        j.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                ProduitService as = new ProduitService();
                                as.UpdateLikesAnnonce(a.getId());
                                buttons.removeComponent(j);
                                push("vous avez aimé cette Annonce" + a.getNomProd());
                                j.setVisible(false);
                            }
                        });
        starRank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                float x = starRank.getProgress();
                ProduitService as = new ProduitService();
                as.UpdateNoteAnnonce(a.getId(), x);
                starRank.setEditable(false);
            }
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
    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
    public void push(String value) {
        Dialog.show("", value, "ok", "cancel");
    }
}
