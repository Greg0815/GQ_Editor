/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package containers;

import java.io.File;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import main.BaseComponent;
import main.GameComponent;

/**
 *
 * @author Gregor
 */
public class Game extends BaseComponent
{
    private StringProperty id;
    private ArrayList<Container> containers;
    private ArrayList<File> drawables;  //TODO implementation for sound video and image files
    private ArrayList<File> videos;
    private ArrayList<File> sounds;

    public Game()
    {
        id = new SimpleStringProperty("");
        containers = new ArrayList<>();
        drawables = new ArrayList<>();
        videos = new ArrayList<>();
        sounds = new ArrayList<>();
        addNecessaryAndOptionalFields();
    }

    public StringProperty idProperty()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id.set(id);
    }

    public String getId()
    {
        return id.get();
    }

    public void addContainer(Container container)
    {
        containers.add(container);
    }

    public ArrayList<Container> getContainers()
    {
        return containers;
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

    public void getDrawablesSoundsAndVideosFromGameComponents()
    {
        for (Container container : containers)
        {
            for (GameComponent gameComponent : container.getGameComponents())
            {
                drawables.addAll(gameComponent.getDrawables());
                sounds.addAll(gameComponent.getSounds());
                videos.addAll(gameComponent.getVideos());
            }
        }
    }

    @Override
    public String assemble()
    {
        String encoding = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        getDrawablesSoundsAndVideosFromGameComponents();
        return encoding + "<game id=\"" + idProperty().get() + "\">" + utilitys.createStringFromArrayList(containers) + "</game>";
    }

    @Override
    protected void addNecessaryAndOptionalFields()
    {
        addNecessaryHeaderFields(getFieldByString("id"));
    }
}
