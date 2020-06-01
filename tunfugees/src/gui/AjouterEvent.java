/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Event;

import java.util.ArrayList;
import services.EventService;
import com.codename1.capture.Capture;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import rest.file.uploader.tn.FileUploader;
import services.PanierService;
import services.ProduitService;
    




/**
 *
 * @author syrine
 */
public class AjouterEvent extends BaseForm{
     private Resources res;
     Container cnt;
       private String reg,imgPath,fileNameInServer;
    private FileUploader file;
    private int id_user_actif = 9;
     public AjouterEvent(Resources res) {
    //this(com.codename1.ui.util.Resources.getGlobalResources());
         super("Show Event", BoxLayout.y());
        this.res = res;
  
        cnt = new Container(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            new AfficherBack(res).show();
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
        RadioButton all = RadioButton.createToggle("Add Event", barGroup);
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
        //Tabs : toolbar
      
        //cnt1.add(new SpanLabel(new ServiceGroupeMembre().Afficher().toString()));
      
        ArrayList<Event> list_e = new EventService().getList();
         Picker p=new Picker();
         Picker pt=new Picker();
         
         p.setType(Display.PICKER_TYPE_DATE);
         p.setFormatter(new SimpleDateFormat("yyyy-MM-dd"));
      
        // pt.setFormatter(new SimpleDateFormat("HH:mm"));
         
         TextField nom = new TextField(null, "Nom");//textfield
        // AutoCompleteTextField lieu = new AutoCompleteTextField(null, "adresse");
      final DefaultListModel<String> options = new DefaultListModel<>();
      AutoCompleteTextField lieu = new AutoCompleteTextField(options) {
      @Override
      protected boolean filter(String text) {
          if(text.length() == 0) {
              return false;
          }
          String[] l = searchLocations(text);
          if(l == null || l.length == 0) {
              return false;
          }
  
          options.removeAll();
          for(String s : l) {
              options.addItem(s);
          }
          return true;
      }
  
  };
  lieu.setMinimumElementsShownInPopup(5);
 
       
         TextField description = new TextField(null, "Description");//textfield
         TextField nbplace = new TextField(null, "nbplace");
         TextField image = new TextField(null, "image");
         Button Cap = new Button ("Importer");
         Label Photo = new Label();
            TextField path = new TextField("");
          Cap.addActionListener(x -> {
           try {
               
               
               imgPath =Capture.capturePhoto();
                 Image im = Image.createImage(imgPath).scaledSmallerRatio(1000, 500);
              // im = Image.createImage(imgPath);
                 Photo.setIcon(im);
               System.out.println(imgPath);
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
          Button save = new Button("Enregistrer");
           System.out.println(pt.getText());
          save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //ajouter groupe
                EventService sg = new EventService();  
              
                System.out.println(p.getText());
                 if (nom.getText()==""|| lieu.getText()==""||p.getText()==""||description.getText()==""||nbplace.getText()=="")
                   Dialog.show("ERREUR", "Champ(s) vide(nt)",null, "ok");
                    else { 
                        if(Integer.parseInt(nbplace.getText())>0)
                    {
                sg.ajouter(new Event(nom.getText(), lieu.getText(),p.getText(), description.getText(),Integer.parseInt(nbplace.getText()), fileNameInServer));
                 Dialog.show("Ajout", "Ajouté avec succés",null,"ok");
                 
                System.out.println(pt.getText());
  
                new AfficherBack(res).show();
                
            } else 
                         Dialog.show("ERREUR", "le nombre maximum est negatif ou null",null, "ok");
                    }}  
        });
        
        
        cnt.setLayout(BoxLayout.y());
        cnt.addAll(nom,lieu,p,description,nbplace,Cap,save,Photo);
        super.add(cnt); 
        
        
         
          
           
 /* this.add(new SpanLabel("This demo requires a valid google API key to be set below "
           + "you can get this key for the webservice (not the native key) by following the instructions here: "
           + "https://developers.google.com/places/web-service/get-api-key"));*/
  apiKey.setText("AIzaSyD8gVLOFa4zw9_WM4uVxOSvBSUzOj8KcrE");
  //this.add(apiKey);
 // this.getToolbar().addCommandToRightBar("Get Key", null, e -> Display.getInstance().execute("https://developers.google.com/places/web-service/get-api-key"));
  this.show();
          
          
          
          
          
          
          
          
          
          
          
     }
     
     
     TextField apiKey = new TextField();

     String[] searchLocations(String text) {        
    try {
        if(text.length() > 0) {
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json"); 
            r.addArgument("key", apiKey.getText());
            r.addArgument("input", text);
            NetworkManager.getInstance().addToQueueAndWait(r);
            Map<String,Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            String[] res = Result.fromContent(result).getAsStringArray("//description");
            return res;
        }
    } catch(Exception err) {
      err.printStackTrace();
    }
    return null;
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