package gui;


import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.FlowLayout;
import services.ServiceTask;


public class StockEdit extends Form{
    
    public StockEdit(Form previous) {
        setTitle("Editer Stock");
        setLayout(new FlowLayout(Component.CENTER, Component.CENTER));
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
