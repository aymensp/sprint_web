/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.File;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import entities.Produit;
import entities.Categorie_Produit;
import entities.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import rest.file.uploader.tn.FileUploader;
import services.ProduitService;

/**
 *
 * @author anasc
 */
public class AddProduitForm extends BaseForm {

    Resources res;
   // File file;
    Container cnt;
    TextField tnom, tprix;
    TextField tdesc,nomr;
    ComboBox Cregion, Ccateg;
    Button btnajout, btncapture, back;
    Image img, img1;
    ImageViewer vu1, vu2;
    private int idc;
    private String reg,imgPath,fileNameInServer;
    private FileUploader file;
    //private String path, absolutePathPhotoAnnonce;
    ArrayList<Categorie_Produit> listCat = new ArrayList<>();
    ArrayList<String> Region = new ArrayList<>();
    
    private static String i;

    public AddProduitForm(Resources res) {

        super("Annonces", BoxLayout.y());
        this.res = res;
        ProduitService serviceAnnonce = new ProduitService();
        cnt = new Container();
        this.res = res;
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
        RadioButton all = RadioButton.createToggle("Ajouter Produits", barGroup);
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

        ProduitService annonceS = new ProduitService();
        listCat = annonceS.getAllCategories();
       // Region = annonceS.getRegion();
        Ccateg = new ComboBox<Categorie_Produit>();
        Cregion = new ComboBox<String>();
        for (Categorie_Produit l : listCat) {
            Ccateg.addItem(l.getLibelle());
        }
        Validator val = new Validator();
        
            Cregion.addItem("disponible");
        Cregion.addItem("non disponbile");
        Container element = new Container(BoxLayout.y());
        tnom = new TextField("", "Titre");
        nomr= new TextField("","Nom refugies");
        tdesc = new TextField("", "Description", 500, TextArea.ANY);
        tprix = new TextField("", "Prix",4,TextArea.NUMERIC);
        val.addConstraint(tnom, new LengthConstraint(1));
           val.addConstraint(nomr, new LengthConstraint(1));
              val.addConstraint(tdesc, new LengthConstraint(1));
        
val.addConstraint(tprix, new NumericConstraint(true, 0, 10000, "Price should be greater than 0") );
   ScaleImageLabel Photo = new ScaleImageLabel();
        btnajout = new Button("ajouter");
        btncapture = new Button("Select Image");
        try {
            img = Image.createImage("file:/C:/Users/aymen/Desktop/ecosystemMobile-master/res/annonce.jpg").fill(300, 300);
            vu1 = new ImageViewer(img);
            element.add(vu1);
            element.add(tnom);
            element.add(nomr);
            element.add(tdesc);
            element.add(tprix);
            element.add(btncapture);
            element.add(Photo);
            element.add(Cregion);
            element.add(Ccateg);
            element.add(btnajout);
            cnt.add(element);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        TextField path = new TextField("");
        btncapture.addActionListener(x -> {
           try {
               
                Image im;
               imgPath =Capture.capturePhoto();
                im = Image.createImage(imgPath).scaledSmallerRatio(1000, 500);
               Photo.setIcon(im);
               
               System.out.println(imgPath);
               String link=imgPath.toString();
               int pod =link.indexOf("/", 2);
               String news= link.substring(pod + 2, link.length());
               FileUploader fu = new FileUploader ("http://127.0.0.1:8000");
               fileNameInServer = fu.upload(news);
               path.setText(fileNameInServer);
               
               
           } catch(IOException ex){
               ex.printStackTrace();
           }catch(Exception ex){
               
           }
          
        });
        Ccateg.addActionListener((evt) -> {

            idc = listCat.get(Ccateg.getSelectedIndex()).getId();
            //  System.out.println(Ccateg.getSelectedItem().toString());
        });
        Cregion.addActionListener((evt) -> {
            reg = Cregion.getSelectedItem().toString();
            // System.out.println(Cregion.getSelectedItem());
        });
        btnajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ( val.isValid()==false ) {
                    
                 
                    
                     Dialog.show("Erreur", val.getErrorMessage(tprix) + " and "+ val.getErrorMessage(tnom)  + "  and "+ val.getErrorMessage(tdesc) + " and "+ val.getErrorMessage(nomr)    , "OK", "Cancel");
                    
                } else {
                    Produit a = new Produit(tnom.getText(),nomr.getText(), tdesc.getText(), Double.parseDouble(tprix.getText()), reg, fileNameInServer, idc);
                    annonceS.ajouterAnnonce(a);
                    new GestionProduit(res).show();
                }
                //copyImages.deplacerVers(, absolutePathPhotoAnnonce, "C:\\ecosystemjava\\src\\res\\Annonce\\photo\\");
                // copyImages.deplacerVers(path, absolutePathPhotoAnnonce, "C:\\wamp\\www\\ecosystemweb\\web\\uploads\\Annonce\\photo\\");

            }
        });
        add(cnt);
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
