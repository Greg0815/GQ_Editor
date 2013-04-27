/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Missions;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
public class MissionNPCTalk extends Mission
{
    private StringProperty image;
    private StringProperty nextdialogbuttontext;
    private StringProperty endbuttontext;
    private StringProperty textsize;
    private StringProperty cancel;
    private ArrayList<Dialogitem> dialogitem;
    
    public MissionNPCTalk()
    {
        image = new SimpleStringProperty();
        nextdialogbuttontext = new SimpleStringProperty();
        endbuttontext = new SimpleStringProperty();
        textsize = new SimpleStringProperty();
        cancel = new SimpleStringProperty();
        dialogitem = new ArrayList<>();
    }
    
    public StringProperty imageProperty()
    {
        if(this.image == null)
        {
            this.image = new SimpleStringProperty();
//            this.duration.addListener(new ChangeListener<String>()
//            {
//                @Override
//                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
//                {
//                }
//            });
        }
        return this.image;
    }
    
    public StringProperty nextdialogbuttontextProperty()
    {
        if(this.nextdialogbuttontext == null)
        {
            this.nextdialogbuttontext = new SimpleStringProperty();
//            this.duration.addListener(new ChangeListener<String>()
//            {
//                @Override
//                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
//                {
//                }
//            });
        }
        return this.nextdialogbuttontext;
    }
    
    public StringProperty endbuttontextProperty()
    {
        if(this.endbuttontext == null)
        {
            this.endbuttontext = new SimpleStringProperty();
//            this.duration.addListener(new ChangeListener<String>()
//            {
//                @Override
//                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
//                {
//                }
//            });
        }
        return this.endbuttontext;
    }
    
    public StringProperty textsizeProperty()
    {
        if(this.textsize == null)
        {
            this.textsize = new SimpleStringProperty();
//            this.duration.addListener(new ChangeListener<String>()
//            {
//                @Override
//                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
//                {
//                }
//            });
        }
        return this.textsize;
    }
    
    public StringProperty cancelProperty()
    {
        if(this.cancel == null)
        {
            this.cancel = new SimpleStringProperty();
//            this.duration.addListener(new ChangeListener<String>()
//            {
//                @Override
//                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
//                {
//                }
//            });
        }
        return this.cancel;
    }
    
    public void addDialogitem(Dialogitem dialogitem)
    {
        this.dialogitem.add(dialogitem);
    }
    
    @Override
    public String createSpecificMissionHeader()
    {
        return utilitys.image(this.image.get()) + utilitys.nextdialogbuttontext(this.nextdialogbuttontext.get()) + utilitys.endbuttontext(this.endbuttontext.get()) + utilitys.textsize(this.textsize.get()) + utilitys.cancel(this.cancel.get());
    }

    @Override
    public String assemble()
    {
        return createMissionHeader() + utilitys.createStringFromArrayList(dialogitem) + createMissionTrailer();
    }
    
}
