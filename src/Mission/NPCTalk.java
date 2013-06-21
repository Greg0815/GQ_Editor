/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission;

import Mission.Components.Dialogitem;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
public class NPCTalk extends Mission
{

    private StringProperty image;
    private StringProperty nextdialogbuttontext;
    private StringProperty endbuttontext;
    private StringProperty textsize;
    private StringProperty cancel;
    private ArrayList<Dialogitem> dialogitem;

    public NPCTalk()
    {
        super("NPCTalk");
        image = new SimpleStringProperty();
        nextdialogbuttontext = new SimpleStringProperty();
        endbuttontext = new SimpleStringProperty();
        textsize = new SimpleStringProperty();
        cancel = new SimpleStringProperty();
        dialogitem = new ArrayList<>();
        this.addNecessaryField("dialogitem");
        this.addCompletenessVariable(dialogitem);
    }

    public StringProperty imageProperty()
    {
        return image;
    }

    public StringProperty nextdialogbuttontextProperty()
    {
        return nextdialogbuttontext;
    }

    public StringProperty endbuttontextProperty()
    {
        return endbuttontext;
    }

    public StringProperty textsizeProperty()
    {
        return textsize;
    }

    public StringProperty cancelProperty()
    {
        return cancel;
    }

    public String getCancel()
    {
        return cancel.get();
    }

    public void setCancel(String cancel)
    {
        this.cancel.set(cancel);
    }

    public void addDialogitem(Dialogitem dialogitem)
    {
        this.dialogitem.add(dialogitem);
    }
    
    public Dialogitem addDialogitem()
    {
        Dialogitem newDialogitem = new Dialogitem();
        dialogitem.add(newDialogitem);
        return newDialogitem;
    }

    @Override
    public String createSpecificMissionHeader()
    {
        return utilitys.image(image.get()) + utilitys.nextdialogbuttontext(nextdialogbuttontext.get()) + utilitys.endbuttontext(endbuttontext.get()) + utilitys.textsize(textsize.get()) + utilitys.cancel(cancel.get());
    }

    @Override
    public String assemble()
    {
        return createMissionHeader() + utilitys.createStringFromArrayList(this.getTriggers()) + utilitys.createStringFromArrayList(dialogitem) + createMissionTrailer();
    }
}
