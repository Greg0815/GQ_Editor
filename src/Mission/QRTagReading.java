/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
public class QRTagReading extends Mission
{
    private StringProperty initial_image;
    private StringProperty if_right_image;
    private StringProperty if_wrong_image;
    private BooleanProperty mode;
    private StringProperty taskdescription;
    private StringProperty buttontext;
    private StringProperty endbuttontext;
    
    public QRTagReading()
    {
        super("QRTagReading");
        this.initial_image = new SimpleStringProperty();
        this.if_right_image = new SimpleStringProperty();
        this.if_wrong_image = new SimpleStringProperty();
        this.mode = new SimpleBooleanProperty();
        this.taskdescription = new SimpleStringProperty();
        this.buttontext = new SimpleStringProperty();
        this.endbuttontext = new SimpleStringProperty();
    }
    
    public StringProperty initial_imageProperty()
    {
        return initial_image;
    }
    
    public StringProperty if_right_imageProperty()
    {
        return if_right_image;
    }
    
    public StringProperty if_wrong_imageProperty()
    {
        return if_wrong_image;
    }
    
    public BooleanProperty modeProperty()
    {
        return mode;
    }
    
    public StringProperty taskdescriptionProperty()
    {
        return taskdescription;
    }
    
    public StringProperty buttontextProperty()
    {
        return buttontext;
    }
    
    public StringProperty endbuttontextProperty()
    {
        return endbuttontext;
    }
    
    @Override
    public String createSpecificMissionHeader()
    {
        return utilitys.initial_image(initial_image.get()) + utilitys.if_right_image(if_right_image.get()) + utilitys.if_wrong_image(if_wrong_image.get()) 
                + utilitys.mode(mode.get()) + utilitys.taskdescription(taskdescription.get())
                + utilitys.buttontext(buttontext.get()) + utilitys.endbuttontext(endbuttontext.get());
    }

    @Override
    public String assemble()
    {
        return createMissionHeader() + createMissionTrailer();
    }
    
}
