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
public class VideoPlay extends Mission
{
    private StringProperty file;
    private StringProperty url;
    private BooleanProperty controllable;
    private StringProperty exitDialogTitle;
    private StringProperty exitDialogMessage;
    private StringProperty exitDialogKeepWatchingText;
    private StringProperty exitDialogWatchAgainText;
    private StringProperty exitDialogLeaveText;
    
    public VideoPlay()
    {
        super("VideoPlay");
        file = new SimpleStringProperty();
        url = new SimpleStringProperty();
        controllable = new SimpleBooleanProperty();
        exitDialogTitle = new SimpleStringProperty();
        exitDialogMessage = new SimpleStringProperty();
        exitDialogKeepWatchingText = new SimpleStringProperty();
        exitDialogWatchAgainText = new SimpleStringProperty();
        exitDialogLeaveText = new SimpleStringProperty();
        this.addNecessaryField("file", "url");
        this.addCompletenessVariable(file);
        this.addCompletenessVariable(url);
    }
    
    public StringProperty fileProperty()
    {
        return file;
    }
    
    public StringProperty urlProperty()
    {
        return url;
    }
    
    public BooleanProperty controllableProperty()
    {
        return controllable;
    }
    
    public StringProperty exitDialogTitleProperty()
    {
        return exitDialogTitle;
    }
    
    public StringProperty exitDialogMessageProperty()
    {
        return exitDialogMessage;
    }
    
    public StringProperty exitDialogKeepWatchingTextProperty()
    {
        return exitDialogKeepWatchingText;
    }
    
    public StringProperty exitDialogWatchAgainTextProperty()
    {
        return exitDialogWatchAgainText;
    }
    
    public StringProperty exitDialogLeaveTextProperty()
    {
        return exitDialogLeaveText;
    }
    
    @Override
    public String createSpecificMissionHeader()
    {
        if(file.isNull().get())
        {
            return utilitys.url(url.get()) + utilitys.controllable(controllable.get()) + utilitys.exitDialogTitle(exitDialogTitle.get()) + utilitys.exitDialogMessage(exitDialogMessage.get()) + utilitys.exitDialogKeepWatchingText(exitDialogKeepWatchingText.get()) + utilitys.exitDialogWatchAgainText(exitDialogWatchAgainText.get()) + utilitys.exitDialogLeaveText(exitDialogLeaveText.get());
        }
        else
        {
            return utilitys.file(file.get()) + utilitys.controllable(controllable.get()) + utilitys.exitDialogTitle(exitDialogTitle.get()) + utilitys.exitDialogMessage(exitDialogMessage.get()) + utilitys.exitDialogKeepWatchingText(exitDialogKeepWatchingText.get()) + utilitys.exitDialogWatchAgainText(exitDialogWatchAgainText.get()) + utilitys.exitDialogLeaveText(exitDialogLeaveText.get());
        }
    }

    @Override
    public String assemble()
    {
        return createMissionHeader() + utilitys.createStringFromArrayList(this.getTriggers()) + createMissionTrailer();
    }
    
}
