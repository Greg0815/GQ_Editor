/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Missions;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafxtestapplication1.AssembleInterface;

/**
 *
 * @author Gregor
 */
public class Dialogitem implements AssembleInterface
{
    private StringProperty speaker;
    private StringProperty sound;
    private BooleanProperty blocking;
    private StringProperty nextdialogbuttontext;
    private StringProperty dialog;
    
    public Dialogitem()
    {
        this.speaker = new SimpleStringProperty();
        this.sound = new SimpleStringProperty();
        this.blocking = new SimpleBooleanProperty();
        this.nextdialogbuttontext = new SimpleStringProperty();
        this.dialog = new SimpleStringProperty();
    }
    
    public StringProperty speakerProperty()
    {
        if(this.speaker == null)
        {
            this.speaker = new SimpleStringProperty();
            this.speaker.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                }
            });
        }
        return this.speaker;
    }
    
    public StringProperty soundProperty()
    {
        if(this.sound == null)
        {
            this.sound = new SimpleStringProperty();
            this.sound.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                }
            });
        }
        return this.sound;
    }
    
    public BooleanProperty blockingProperty()
    {
        if(this.blocking == null)
        {
            this.blocking = new SimpleBooleanProperty();
            this.blocking.addListener(new ChangeListener<Boolean>()
            {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
                {
                }
            });
        }
        return this.blocking;
    }
    
    public StringProperty nextdialogbuttontextProperty()
    {
        if(this.nextdialogbuttontext == null)
        {
            this.nextdialogbuttontext = new SimpleStringProperty();
            this.nextdialogbuttontext.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                }
            });
        }
        return this.nextdialogbuttontext;
    }
    
    public StringProperty dialogProperty()
    {
        if(this.dialog == null)
        {
            this.dialog = new SimpleStringProperty();
            this.dialog.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                }
            });
        }
        return this.dialog;
    }
    
    @Override
    public String assemble() {
        return "<dialogitem " + utilitys.speaker(this.speaker.get()) + utilitys.sound(this.sound.get()) + utilitys.blocking(this.blocking.get()) + utilitys.nextdialogbuttontext(this.nextdialogbuttontext.get()) + ">" + this.dialog + "</dialogitem>";
    }
    
}
