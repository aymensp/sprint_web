/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.FlowLayout;
import services.ServiceTask;
/**
 *
 * @author Fares
 */

public class ReclamationEdit extends Form{
    
    public ReclamationEdit(Form previous) {
        setTitle("Editer Reclamation");
        setLayout(new FlowLayout(Component.CENTER, Component.CENTER));
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
