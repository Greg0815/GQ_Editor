/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
public class AudioRecord extends Mission
{
    private StringProperty task;
    private StringProperty file;
    
    public AudioRecord()
    {
        super("AudioRecord");
        this.task = new SimpleStringProperty();
        this.file = new SimpleStringProperty();
    }
    
    public StringProperty taskProperty()
    {
        return task;
    }
    
    public StringProperty fileProperty()
    {
        return file;
    }
    
    @Override
    public String createSpecificMissionHeader()
    {
        return utilitys.task(this.task.get()) + utilitys.file(this.file.get());
    }

    @Override
    public String assemble()
    {
        return createMissionHeader() + createMissionTrailer();
    }
}
