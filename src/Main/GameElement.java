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
 * @author Admin
 */
public abstract class GameElement implements AssembleInterface
{
    private StringProperty id;
    private ArrayList<Trigger> triggers;
    private ArrayList<Object> completenessArrayList;
    
    public GameElement()
    {
        this.id = new SimpleStringProperty();
        this.triggers = new ArrayList<>();
        completenessArrayList = new ArrayList<>();
    }
    
    public ArrayList<Object> getCompletenessArrayList()
    {
        return completenessArrayList;
    }
    
    public void addCompletenessVariable(Object object)
    {
        completenessArrayList.add(object);
    }
    
    public String getId()
    {
        return id.get();
    }
    
    public void setId(String id)
    {
        this.id.set(id);
    }
    
    public StringProperty idProperty()
    {
        return id;
    }
    
    public ArrayList<Trigger> getTriggers()
    {
        return triggers;
    }
    
    public void addTrigger(Trigger trigger)
    {
        triggers.add(trigger);
    }
    
    public void addTrigger(Trigger... triggers)
    {
        this.triggers.addAll(Arrays.asList(triggers));
    }
    
    public void addTriggerAtPosition(Trigger trigger, int position)
    {
        triggers.add(position, trigger);
    }
}
