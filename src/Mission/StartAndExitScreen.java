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
        addNecessaryAndOptionalFields();
    }
    
    public String getImage()
    {
        return image.get();
    }
    
    public Integer getDuration()
    {
        return duration.get();
    }
    
    public String getCancel()
    {
        return cancel.get();
    }
    
    public void setImage(String image)
    {
        this.image.set(image);
    }
    
    public void setDuration(Integer duration)
    {
        this.duration.set(duration);
    }
    
    public void setCancel(String cancel)
    {
        this.cancel.set(cancel);
    }

    public StringProperty imageProperty()
    {
        return image;
    }
    
    public IntegerProperty durationProperty()
    {
        return duration;
    }
    
    public StringProperty cancelProperty()
    {
        return cancel;
    }
    
//    @Override
//    public String createSpecificMissionHeader()
//    {
//        return utilitys.image(this.image.get()) + utilitys.duration(this.duration.get()) + utilitys.cancel(this.cancel.get());
//    }

    @Override
    public String assemble()
    {
        return buildMissionHead() + buildMissionTail();
//        return createMissionHeader() + utilitys.createStringFromArrayList(this.getTrigger()) + buildMissionTail();
    }

//    @Override
//    public Boolean isComplete()
//    {
//        Boolean isComplete = true;
//        return isComplete;
//    }
    
    @Override
    protected final void addNecessaryAndOptionalFields()
    {
        this.addNecessaryHeaderFields(getFieldByString("image"));
        this.addOptionalHeaderFields(getFieldByString("duration"), getFieldByString("cancel"));
    }
    
}
