/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Mission.Rules.Trigger;
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

    public GameComponent()
    {
        super();
        id = new SimpleStringProperty("");
        trigger = new ArrayList<>();
        addNecessaryHeaderFields(getFieldByString("id"));
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
