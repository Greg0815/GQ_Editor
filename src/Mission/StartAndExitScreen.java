/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Gregor
 */
public class MissionStartAndExitScreen extends Mission
{
    private StringProperty image;
    private StringProperty duration;
    private StringProperty cancel;
    
    public MissionStartAndExitScreen()
    {
        super("StartAndExitScreen");
        this.image = new SimpleStringProperty();
        this.duration = new SimpleStringProperty();
        this.duration.set("5000");
        this.cancel = new SimpleStringProperty();
        this.cancel.set("true");
    }
    
    public StringProperty imageProperty()
    {
        if(this.image == null)
        {
            this.image = new SimpleStringProperty();
            this.image.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                }
            });
        }
        return this.image;
    }
    
    public String getImage()
    {
        return this.image.get();
    }
    
    public void setImage(String answer)
    {
        this.image.set(answer);
    }
    
    public StringProperty durationProperty()
    {
        if(this.duration == null)
        {
            this.duration = new SimpleStringProperty();
            this.duration.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                }
            });
        }
        return this.duration;
    }
    
    public String getDuration()
    {
        return this.duration.get();
    }
    
    public void setDuration(String answer)
    {
        this.duration.set(answer);
    }
    
    public StringProperty cancelProperty()
    {
        if(this.cancel == null)
        {
            this.cancel = new SimpleStringProperty();
            this.cancel.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                }
            });
        }
        return this.cancel;
    }
    
    public String getCancel()
    {
        return this.cancel.get();
    }
    
    public void setCancel(String answer)
    {
        this.cancel.set(answer);
    }

    @Override
    public String createSpecificMissionHeader()
    {
        return utilitys.image(this.image.get()) + utilitys.duration(this.duration.get()) + utilitys.cancel(this.cancel.get());
    }

    @Override
    public String assemble()
    {
        return createMissionHeader() + utilitys.createStringFromArrayList(triggers) + createMissionTrailer();
    }
    
}
