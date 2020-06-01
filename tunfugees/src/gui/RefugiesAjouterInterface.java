/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.capture.Capture;
import com.codename1.components.ScaleImageLabel;
import com.codename1.l10n.SimpleDateFormat;
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
import java.io.IOException;
import java.util.ArrayList;
import rest.file.uploader.tn.FileUploader;
import services.PanierService;
import services.ProduitService;
import services.RefugiesService;

/**
 *
 * @author hp
 */
public class RefugiesAjouterInterface extends BaseForm  {
    private Resources res;
        private static String i;
Container cnt1;
         private String reg,imgPath,fileNameInServer;
     public RefugiesAjouterInterface(Resources res) {
    //this(com.codename1.ui.util.Resources.getGlobalResources());
              
    super("Modiefier Refugies", BoxLayout.y());
        ProduitService serviceAnnonce = new ProduitService();
         PanierService panierService = new PanierService();
        cnt1 = new Container(BoxLayout.y());
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
        super.add(cnt1);
        //cnt1.add(new SpanLabel(new ServiceGroupeMembre().Afficher().toString()));
      
        ArrayList<Refugies> list_e = new RefugiesService().getList2();
        // Picker p=new Picker();
         //Picker pt=new Picker();
         
        // p.setType(Display.PICKER_TYPE_DATE);
         //p.setFormatter(new SimpleDateFormat("yyyy-MM-dd"));
         //pt.setType(Display.PICKER_TYPE_TIME);
        // pt.setFormatter(new SimpleDateFormat("HH:mm"));
         
         TextField nom = new TextField(null, "Nom");//textfield
         TextField prenom = new TextField(null, "Prenom");
         
         //TextField date = new TextField(null, "date");
         TextField age = new TextField(null, "age");
         //TextField heure = new TextField(null, "heure");
         TextField pays = new TextField(null, "pays");//textfield
      //   TextField image = new TextField(null, "image");
         
         
        
        ScaleImageLabel Photo = new ScaleImageLabel();
        
      TextField path = new TextField("");
      Button btncapture = new Button("Capture");
       
         btncapture.addActionListener(x -> {
           try {
              Image im;
               imgPath =Capture.capturePhoto();
                im = Image.createImage(imgPath).scaledSmallerRatio(1000, 500);
               Photo.setIcon(im);
               
           
               String link=imgPath.toString();
               int pod =link.indexOf("/", 2);
               String news= link.substring(pod + 2, link.length());
               FileUploader fu = new FileUploader ("http://127.0.0.1:8000");
               fileNameInServer = fu.upload(news);
               path.setText(fileNameInServer);
                   System.out.println(fileNameInServer);
           } catch(IOException ex){
               ex.printStackTrace();
           }catch(Exception ex){
               
           }
          
        });
         Button save = new Button("ajouter");
          // System.out.println(pt.getText());
          save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //ajouter groupe
                RefugiesService sg = new RefugiesService();  
               Refugies a =new Refugies(nom.getText(), prenom.getText(),Integer.parseInt(age.getText()),pays.getText(), fileNameInServer);
                System.out.println(a);
                sg.ajouter(a);
             //  System.out.println(pt.getText());
                //ajouter membre
               
               
                new afficherrefugies(res).show();
                
            }
        });
         
       
        cnt1.addAll(nom,prenom,age,pays,btncapture,Photo,save);
      
        
       
        setToolbar(tb);
        
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new afficherrefugies(res).show());

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

 
    public void push(String value) {
        Dialog.show(value, "ok", "", "");
    }


    public void registeredForPush(String deviceId) {
    }

    
    public void pushRegistrationError(String error, int errorCode)
    {
        
        
    }
  
}
