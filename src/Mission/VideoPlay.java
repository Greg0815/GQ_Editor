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
        file = new SimpleStringProperty("");
        url = new SimpleStringProperty("");
        controllable = new SimpleBooleanProperty(true);
        exitDialogTitle = new SimpleStringProperty("");
        exitDialogMessage = new SimpleStringProperty("");
        exitDialogKeepWatchingText = new SimpleStringProperty("");
        exitDialogWatchAgainText = new SimpleStringProperty("");
        exitDialogLeaveText = new SimpleStringProperty("");
        addNecessaryAndOptionalFields();
    }

    public String getFile()
    {
        return file.get();
    }

    public String getUrl()
    {
        return url.get();
    }

    public Boolean getControllable()
    {
        return controllable.get();
    }

    public String getExitDialogTitle()
    {
        return exitDialogTitle.get();
    }

    public String getExitDialogMessage()
    {
        return exitDialogMessage.get();
    }

    public String getExitDialogKeepWatchingText()
    {
        return exitDialogKeepWatchingText.get();
    }

    public String getExitDialogWatchAgainText()
    {
        return exitDialogWatchAgainText.get();
    }

    public String getExitDialogLeaveText()
    {
        return exitDialogLeaveText.get();
    }

    public void setFile(String file)
    {
        this.file.set(file);
    }

    public void setUrl(String url)
    {
        this.url.set(url);
    }

    public void setControllable(Boolean controllalbe)
    {
        this.controllable.set(controllalbe);
    }

    public void setExitDialogTitle(String exitDialogTitle)
    {
        this.exitDialogTitle.set(exitDialogTitle);
    }

    public void setExitDialogMessage(String exitDialogMessage)
    {
        this.exitDialogMessage.set(exitDialogMessage);
    }

    public void setExitDialogKeepWatchingText(String exitDialogKeepWatchingText)
    {
        this.exitDialogKeepWatchingText.set(exitDialogKeepWatchingText);
    }

    public void setExitDialogWatchAgainText(String exitDialogWatchAgainText)
    {
        this.exitDialogWatchAgainText.set(exitDialogWatchAgainText);
    }

    public void setExitDialogLeaveText(String exitDialogLeaveText)
    {
        this.exitDialogLeaveText.set(exitDialogLeaveText);
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

//    @Override
//    public String createSpecificMissionHeader()
//    {
//        if (file.isNull().get())
//        {
//            return utilitys.url(url.get()) + utilitys.controllable(controllable.get()) + utilitys.exitDialogTitle(exitDialogTitle.get()) + utilitys.exitDialogMessage(exitDialogMessage.get()) + utilitys.exitDialogKeepWatchingText(exitDialogKeepWatchingText.get()) + utilitys.exitDialogWatchAgainText(exitDialogWatchAgainText.get()) + utilitys.exitDialogLeaveText(exitDialogLeaveText.get());
//        }
//        else
//        {
//            return utilitys.file(file.get()) + utilitys.controllable(controllable.get()) + utilitys.exitDialogTitle(exitDialogTitle.get()) + utilitys.exitDialogMessage(exitDialogMessage.get()) + utilitys.exitDialogKeepWatchingText(exitDialogKeepWatchingText.get()) + utilitys.exitDialogWatchAgainText(exitDialogWatchAgainText.get()) + utilitys.exitDialogLeaveText(exitDialogLeaveText.get());
//        }
//    }

    @Override
    public String assemble()
    {
//        return createMissionHeader() + utilitys.createStringFromArrayList(this.getTrigger()) + buildMissionTail();
        return buildMissionHead() + buildMissionTail();
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
        addNecessaryHeaderFields(getFieldByString("file"));
        addOptionalHeaderFields(getFieldByString("url"), getFieldByString("controllable"), getFieldByString("exitDialogTitle"), getFieldByString("exitDialogMessage"), getFieldByString("exitDialogKeepWatchingText"), getFieldByString("exitDialogWatchAgainText"), getFieldByString("exitDialogLeaveText"));
    }
}
