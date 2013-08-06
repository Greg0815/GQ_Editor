/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mission;

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
        url = new SimpleStringProperty("");
        file = new SimpleStringProperty("");
        cancel = new SimpleStringProperty("");
        addNecessaryAndOptionalFields();
    }

    public String getUrl()
    {
        return url.get();
    }

    public String getFile()
    {
        return file.get();
    }

    public String getCancel()
    {
        return cancel.get();
    }

    public void setUrl(String url)
    {
        this.url.set(url);
    }

    public void setFile(String file)
    {
        this.file.set(file);
    }

    public void setCancel(String cancel)
    {
        this.cancel.set(cancel);
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

    @Override
    public String assemble()
    {
        return buildMissionHead() + buildMissionTail();
    }

    @Override
    protected final void addNecessaryAndOptionalFields()
    {
        this.addNecessaryHeaderFields(getFieldByString("url"));
        this.addOptionalHeaderFields(getFieldByString("cancel"), getFieldByString("file"));
    }
}
