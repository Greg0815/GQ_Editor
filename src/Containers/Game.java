/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Containers;

import Main.AssembleInterface;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
public class Game implements AssembleInterface
{
    private StringProperty id;
    private ArrayList<Container> containers;
    private ArrayList<String> necessaryFields;

    public Game()
    {
        id = new SimpleStringProperty("");
        containers = new ArrayList<>();
        necessaryFields = new ArrayList<>();
        necessaryFields.add("id");
    }
    
    public ArrayList<String> getNecessaryFields()
    {
        return necessaryFields;
    }

    public void addNecessaryFields(String... necessaryFields)
    {
        this.necessaryFields.addAll(Arrays.asList(necessaryFields));
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
