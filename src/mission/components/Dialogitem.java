/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mission.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
public class Dialogitem extends MissionComponent
{
    private StringProperty speaker;
    private StringProperty sound;
    private BooleanProperty blocking;
    private StringProperty nextdialogbuttontext;
    private StringProperty dialog;

    public Dialogitem()
    {
        speaker = new SimpleStringProperty("");
        sound = new SimpleStringProperty("");
        blocking = new SimpleBooleanProperty(true);
        nextdialogbuttontext = new SimpleStringProperty("");
        dialog = new SimpleStringProperty("");
        addNecessaryAndOptionalFields();
    }

    @Override
    protected final void addNecessaryAndOptionalFields()
    {
        addNecessaryHeaderFields(getFieldByString("speaker"));
        addNecessaryNonHeaderFields(getFieldByString("dialog"));
        addOptionalHeaderFields(getFieldByString("sound"), getFieldByString("blocking"), getFieldByString("nextdialogbuttontext"));
    }

    public String getSpeaker()
    {
        return speaker.get();
    }

    public String getSound()
    {
        return sound.get();
    }

    public Boolean getBlocking()
    {
        return blocking.get();
    }

    public String getNextdialogbuttontext()
    {
        return nextdialogbuttontext.get();
    }

    public String getDialog()
    {
        return dialog.get();
    }

    public void setSpeaker(String speaker)
    {
        this.speaker.set(speaker);
    }

    public void setSound(String sound)
    {
        this.sound.set(sound);
    }

    public void setBlocking(Boolean blocking)
    {
        this.blocking.set(blocking);
    }

    public void setNextdialogbuttontext(String nextdialogbuttontext)
    {
        this.nextdialogbuttontext.set(nextdialogbuttontext);
    }

    public void setDialog(String dialog)
    {
        this.dialog.set(dialog);
    }

    public StringProperty speakerProperty()
    {
        return speaker;
    }

    public StringProperty soundProperty()
    {
        return sound;
    }

    public BooleanProperty blockingProperty()
    {
        return blocking;
    }

    public StringProperty nextdialogbuttontextProperty()
    {
        return nextdialogbuttontext;
    }

    public StringProperty dialogProperty()
    {
        return dialog;
    }

    @Override
    public String assemble()
    {
        return "<dialogitem " + utilitys.speaker(speaker.get()) + utilitys.sound(sound.get()) + utilitys.blocking(blocking.get()) + utilitys.nextdialogbuttontext(nextdialogbuttontext.get()) + ">" + dialog.get() + "</dialogitem>";
    }
//    @Override
//    public Boolean isComplete()
//    {
//        Boolean isComplete = true;
//        if(speaker.isNull().get() || dialog.isNull().get())
//        {
//            System.out.println("Dialogitem incomplete");
//            isComplete = false;
//        }
//        return isComplete;
//    }
}
