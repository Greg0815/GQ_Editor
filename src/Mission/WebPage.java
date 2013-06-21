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
public class WebPage extends Mission
{
    private StringProperty url;
    private StringProperty file;
    private StringProperty cancel;
    
    public WebPage()
    {
        super("WebPage");
        url = new SimpleStringProperty();
        file = new SimpleStringProperty();
        cancel = new SimpleStringProperty();
    }

    public StringProperty urlProperty()
    {
        return url;
    }
    
    public StringProperty fileProperty()
    {
        return file;
    }
    
    public StringProperty cancelProperty()
    {
        return cancel;
    }
    
    public String getCancel()
    {
        return this.cancel.get();
    }
    
    public void setCancel(String cancel)
    {
        this.cancel.set(cancel);
    }
    
    @Override
    public String createSpecificMissionHeader()
    {
        return utilitys.url(url.get()) + utilitys.file(file.get()) + utilitys.cancel(cancel.get());
    }

    @Override
    public String assemble()
    {
        return createMissionHeader() + createMissionTrailer();
    }
}
