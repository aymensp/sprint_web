/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;

/**
 *
 * @author Hiro
 */
public class UtilityGui {
    
    
    
    // THIS CLASS IS OUTDATED BUT USED
    
    
    //Global
    public static void Add2ColumnsList(Form FormToAddTo, Component LeftComponent, Component... RightComponents){

        //Containers
        Container XContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container YContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        //Adding to the Containers
        XContainer.add(LeftComponent);
        for(Component TempComp : RightComponents){
            YContainer.add(TempComp);
        }
        XContainer.add(YContainer);
        
        //Add Containers to Form
        FormToAddTo.add(XContainer);
       // FormToAddTo.add(YContainer);
    }
    public static void Add1ColumnList(Form FormToAddTo, Component... Components){

        //Containers
        Container YContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        
        //Adding to the Containers
        for(Component TempComp : Components){
            YContainer.add(TempComp);
        }
        
        //Add Containers to Form
        FormToAddTo.add(YContainer);
       // FormToAddTo.add(YContainer);
    }
    public static void AddToContainerAsBlocks(Container ContainerSource, Container... ContainersToAdd){
        ContainerSource.setLayout(new GridLayout(2, 2));
        for (Container ContainersTemp : ContainersToAdd)
            ContainerSource.add(ContainersTemp);
    }
    public static Button AddButtonGoToForm(Form FormToAddTo, String ButtonValueString, Form FormToGoTo){
        Button CurrentButton = new Button(ButtonValueString);
        CurrentButton.addActionListener((evt) -> {
            FormToGoTo.show();
        });
        
        return CurrentButton;
    }
    
    
    //Toolbars
    public static Toolbar CreateToolBar(Form... Forms){
        Toolbar CurrentToolBar = new Toolbar();
        for(Form TempForm : Forms ){
            TempForm.setToolbar(CurrentToolBar);
        }
        return CurrentToolBar;
    }
    public static void AddToToolBarSideMenu(Toolbar CurrentToolbar, Form FormToShow, String ButtonDisplayValue){
        CurrentToolbar.addMaterialCommandToSideMenu(ButtonDisplayValue, FontImage.MATERIAL_WEB, (evt) -> {
            FormToShow.show();
        });
        
    }
    public static void AddToToolBarOverflowMenu(Toolbar CurrentToolbar, Form FormToShow, String ButtonDisplayValue){
        Command CurrentCommand = new Command(ButtonDisplayValue);
        CurrentToolbar.addCommandToOverflowMenu(ButtonDisplayValue, null, (ActionListener) (ActionEvent e) -> {
            System.err.println(ButtonDisplayValue + " Pressed");
            FormToShow.show();
        });
    }
    
    
    
}
