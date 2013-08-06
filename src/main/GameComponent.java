/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import mission.rules.Trigger;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
abstract public class GameComponent extends BaseComponent
{
    private StringProperty id;
    private ArrayList<Trigger> trigger;
    private ArrayList<File> drawables;
    private ArrayList<File> videos;
    private ArrayList<File> sounds;

    public GameComponent()
    {
        super();
        id = new SimpleStringProperty("");
        trigger = new ArrayList<>();
        drawables = new ArrayList<>();
        videos = new ArrayList<>();
        sounds = new ArrayList<>();
        addNecessaryHeaderFields(getFieldByString("id"));
    }

    public void addDrawable(File drawable)
    {
        drawables.add(drawable);
    }

    public void addVideo(File video)
    {
        videos.add(video);
    }

    public void addSound(File sound)
    {
        sounds.add(sound);
    }

    public ArrayList<File> getDrawables()
    {
        return drawables;
    }

    public ArrayList<File> getVideos()
    {
        return videos;
    }

    public ArrayList<File> getSounds()
    {
        return sounds;
    }

    public boolean removeDrawable(String filePath)
    {
        for (File file : drawables)
        {
            if (filePath.contains(file.getName()))
            {
                System.out.println("successfully removed");
                drawables.remove(file);
                return true;
            }
        }
        return false;
    }

    public String getId()
    {
        return id.get();
    }

    public ArrayList<Trigger> getTrigger()
    {
        return trigger;
    }

    public void setId(String id)
    {
        this.id.set(id);
    }

    public void addTrigger(Trigger... trigger)
    {
        this.trigger.addAll(Arrays.asList(trigger));
    }

    public StringProperty idProperty()
    {
        return id;
    }
}
