/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package containers;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import main.BaseComponent;

/**
 *
 * @author Gregor
 */
public class Game extends BaseComponent
{
    private StringProperty id;
    private ArrayList<Container> containers;

    public Game()
    {
        id = new SimpleStringProperty("");
        containers = new ArrayList<>();
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

    @Override
    public String assemble()
    {
        return "<game id=\"" + idProperty().get() + "\">" + utilitys.createStringFromArrayList(containers) + "</game>";
    }

    @Override
    protected void addNecessaryAndOptionalFields()
    {
        addNecessaryHeaderFields(getFieldByString("id"));
    }
}
