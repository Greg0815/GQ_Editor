/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
public class StartAndExitScreen extends Mission
{
    private StringProperty image;
    private IntegerProperty duration;
    private StringProperty cancel;
    
    public StartAndExitScreen()
    {
        super("StartAndExitScreen");
        image = new SimpleStringProperty("");
        duration = new SimpleIntegerProperty(5000);
        cancel = new SimpleStringProperty("");
        addNecessaryField("image");
        this.addCompletenessVariable(image);
    }
    
    public StringProperty imageProperty()
    {
//        if(this.image == null)
//        {
//            this.image = new SimpleStringProperty();
//        }
        return image;
    }
    
    public String getImage()
    {
        return image.get();
    }
    
    public void setImage(String image)
    {
        this.image.set(image);
    }
    
    public IntegerProperty durationProperty()
    {
//        if(this.duration == null)
//        {
//            this.duration = new SimpleIntegerProperty();
//        }
        return duration;
    }
    
    public Integer getDuration()
    {
        return duration.get();
    }
    
    public void setDuration(Integer duration)
    {
        this.duration.set(duration);
    }
    
    public StringProperty cancelProperty()
    {
//        if(this.cancel == null)
//        {
//            this.cancel = new SimpleStringProperty();
//        }
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

    @Override
    public String createSpecificMissionHeader()
    {
        return utilitys.image(this.image.get()) + utilitys.duration(this.duration.get()) + utilitys.cancel(this.cancel.get());
    }

    @Override
    public String assemble()
    {
        return createMissionHeader() + utilitys.createStringFromArrayList(this.getTriggers()) + createMissionTrailer();
    }

    @Override
    public Boolean isComplete()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
