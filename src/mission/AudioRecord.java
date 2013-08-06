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
public class AudioRecord extends Mission
{
    private StringProperty task;
    private StringProperty file;

    public AudioRecord()
    {
        super("AudioRecord");
        task = new SimpleStringProperty("");
        file = new SimpleStringProperty("");
        addNecessaryAndOptionalFields();
    }

    public String getTask()
    {
        return task.get();
    }

    public String getFile()
    {
        return file.get();
    }

    public void setTask(String task)
    {
        this.task.set(task);
    }

    public void setFile(String file)
    {
        this.file.set(file);
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
    public String assemble()
    {
        return buildMissionHead() + buildMissionTail();
    }

    @Override
    protected final void addNecessaryAndOptionalFields()
    {
        addNecessaryHeaderFields(getFieldByString("file"));
        addOptionalHeaderFields(getFieldByString("task"));
    }
}
