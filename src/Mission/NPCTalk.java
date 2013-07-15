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
        image = new SimpleStringProperty("");
        nextdialogbuttontext = new SimpleStringProperty("");
        endbuttontext = new SimpleStringProperty("");
        textsize = new SimpleStringProperty("");
        cancel = new SimpleStringProperty("");
        dialogitem = new ArrayList<>();
        addNecessaryAndOptionalFields();
    }

    public String getImage()
    {
        return image.get();
    }

    public String getNextdialogbuttontext()
    {
        return nextdialogbuttontext.get();
    }

    public String getEndbuttontext()
    {
        return endbuttontext.get();
    }

    public String getTextsize()
    {
        return textsize.get();
    }

    public String getCancel()
    {
        return cancel.get();
    }

    public void setImage(String image)
    {
        this.image.set(image);
    }

    public void setNextdialogbuttontext(String nextdialogbuttontext)
    {
        this.nextdialogbuttontext.set(nextdialogbuttontext);
    }

    public void setEndbuttontext(String endbuttontext)
    {
        this.endbuttontext.set(endbuttontext);
    }

    public void setTextsize(String textsize)
    {
        this.textsize.set(textsize);
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

    @Override
    public String assemble()
    {
        return buildMissionHead() + utilitys.createStringFromArrayList(dialogitem) + buildMissionTail();
//        return createMissionHeader() + utilitys.createStringFromArrayList(dialogitem) + buildMissionTail();
    }


    @Override
    protected void addNecessaryAndOptionalFields()
    {
        addNecessaryNonHeaderFields(getFieldByString("dialogitem"));
        addOptionalHeaderFields(getFieldByString("image"), getFieldByString("nextdialogbuttontext"), getFieldByString("endbuttontext"), getFieldByString("textsize"), getFieldByString("cancel"));
    }
}
