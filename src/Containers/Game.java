/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Containers;

import Main.AssembleInterface;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
public class Game2 implements AssembleInterface
{
    private StringProperty id;
    private ArrayList<Container> containers;

    public Game2()
    {
        id = new SimpleStringProperty();
        containers = new ArrayList<>();
    }

    @Override
    public Boolean isComplete()
    {
        Boolean isComplete = true;
        for (Container container : containers)
        {
            if (!container.isComplete())
            {
                System.out.println("Container incomplete");
                isComplete = false;
            }
        }
        if (id.isNull().get())
        {
            System.out.println("Game ID Null");
            isComplete = false;
        }
        return isComplete;
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

    @Override
    public String assemble()
    {
        return "<game id=\"" + idProperty().get() + "\">" + utilitys.createStringFromArrayList(containers) + "</game>";
    }
}
