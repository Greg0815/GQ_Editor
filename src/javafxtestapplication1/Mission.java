/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Gregor
 */
abstract public class Mission implements AssembleInterface
{
    private String type;
    private StringProperty id;
    protected ArrayList<Trigger> triggers;
    
    public Mission()
    {
        this.type = "";
        this.id = new SimpleStringProperty();
        triggers = new ArrayList<>();
    }
    
    public Mission(String type)
    {
        this.type = type;
        this.id = new SimpleStringProperty();
        triggers = new ArrayList<>();
    }
    
    public Mission(String type, String id)      // überflüssig?
    {
        this.type = type;
        this.id = new SimpleStringProperty();
        this.id.set(id);
        triggers = new ArrayList<>();
    }
    
    public Mission(String type, String id, ArrayList<Trigger> triggers)     // überflüssig?
    {
        this.type = type;
        this.id = new SimpleStringProperty();
        this.id.set(id);
        this.triggers = triggers;
    }

    public StringProperty idProperty()
    {
        if(this.id == null)
        {
            this.id = new SimpleStringProperty();
            this.id.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                }
            });
        }
        return this.id;
    }
    
    public String getId()
    {
        return this.id.get();
    }
    
    public void setId(String id)
    {
        this.id.set(id);
    }
    
    public void addTrigger(Trigger newTrigger)
    {
        this.triggers.add(newTrigger);
    }
    
    public void addTriggerAtPosition(Trigger newTrigger, int position)
    {
        this.triggers.add(position, newTrigger);
    }
    
    public String createMissionHeader()
    {
        return "<mission" + utilitys.type(this.type) + utilitys.id(this.id.get()) + createSpecificMissionHeader() + ">";
    }
    
    public String createMissionTrailer()
    {
        return "</mission>";
    }
    
    public abstract String createSpecificMissionHeader();
}
