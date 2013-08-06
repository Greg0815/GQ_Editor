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
public class ImageCapture extends Mission
{
    StringProperty file;
    StringProperty task;
    StringProperty image;
    StringProperty uploadURL;
    StringProperty buttontext;
    StringProperty replyOnDone;

    public ImageCapture()
    {
        super("ImageCapture");
        file = new SimpleStringProperty("");
        task = new SimpleStringProperty("");
        image = new SimpleStringProperty("");
        uploadURL = new SimpleStringProperty("");
        buttontext = new SimpleStringProperty("");
        replyOnDone = new SimpleStringProperty("");
        addNecessaryAndOptionalFields();
    }

    public String getFile()
    {
        return file.get();
    }

    public String getTask()
    {
        return task.get();
    }

    public String getImage()
    {
        return image.get();
    }

    public String getUploadURL()
    {
        return uploadURL.get();
    }

    public String getButtontext()
    {
        return buttontext.get();
    }

    public String getReplyOnDone()
    {
        return replyOnDone.get();
    }

    public void setFile(String file)
    {
        this.file.set(file);
    }

    public void setTask(String task)
    {
        this.task.set(task);
    }

    public void setImage(String image)
    {
        this.image.set(image);
    }

    public void setUploadURL(String uploadURL)
    {
        this.uploadURL.set(uploadURL);
    }

    public void setButtontext(String buttontext)
    {
        this.buttontext.set(buttontext);
    }

    public void setReplyOnDone(String replyOnDone)
    {
        this.replyOnDone.set(replyOnDone);
    }

    public StringProperty fileProperty()
    {
        return file;
    }

    public StringProperty taskProperty()
    {
        return task;
    }

    public StringProperty imageProperty()
    {
        return image;
    }

    public StringProperty uploadURLProperty()
    {
        return uploadURL;
    }

    public StringProperty buttontextProperty()
    {
        return buttontext;
    }

    public StringProperty replyOnDoneProperty()
    {
        return replyOnDone;
    }

    @Override
    protected void addNecessaryAndOptionalFields()
    {
        addNecessaryHeaderFields(getFieldByString("file"));
        addOptionalHeaderFields(getFieldByString("task"), getFieldByString("image"), getFieldByString("uploadURL"), getFieldByString("buttontext"), getFieldByString("replyOnDone"));
    }

    @Override
    public String assemble()
    {
        return buildMissionHead() + buildMissionTail();
    }
}
