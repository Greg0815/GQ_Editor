/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission.Components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
public class Dialogitem extends MissionComponent implements Main.AssembleInterface
{

    private StringProperty speaker;
    private StringProperty sound;
    private BooleanProperty blocking;
    private StringProperty nextdialogbuttontext;
    private StringProperty dialog;
//    private ArrayList<String> necessaryFields;

    public Dialogitem()
    {
        this.speaker = new SimpleStringProperty("");
        this.sound = new SimpleStringProperty("");
        this.blocking = new SimpleBooleanProperty(true);
        this.nextdialogbuttontext = new SimpleStringProperty("");
        this.dialog = new SimpleStringProperty("");
        this.addNecessaryFields("speaker", "dialog");
//        this.necessaryFields.add("dialog");
    }

//    public ArrayList<String> getNecessaryFields()
//    {
//        return necessaryFields;
//    }

    public StringProperty speakerProperty()
    {
        return this.speaker;
    }

    public StringProperty soundProperty()
    {
        return this.sound;
    }

    public BooleanProperty blockingProperty()
    {
        return this.blocking;
    }

    public StringProperty nextdialogbuttontextProperty()
    {
        return this.nextdialogbuttontext;
    }

    public StringProperty dialogProperty()
    {
        return this.dialog;
    }

    @Override
    public String assemble()
    {
        return "<dialogitem " + utilitys.speaker(speaker.get()) + utilitys.sound(sound.get()) + utilitys.blocking(blocking.get()) + utilitys.nextdialogbuttontext(nextdialogbuttontext.get()) + ">" + dialog.get() + "</dialogitem>";
    }

    @Override
    public Boolean isComplete()
    {
        Boolean isComplete = true;
        if(speaker.isNull().get() || dialog.isNull().get())
        {
            System.out.println("Dialogitem incomplete");
            isComplete = false;
        }
        return isComplete;
    }
}
