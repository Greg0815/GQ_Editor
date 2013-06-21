/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unused;

import Mission.Mission;
import Mission.Rules.Trigger;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import Main.AssembleInterface;


/**
 *
 * @author Gregor
 */
public abstract class AbstractGame implements AssembleInterface
{
    public StringProperty name;
    
    public StringProperty nameProperty()
    {
        if(this.name == null)
        {
            this.name = new SimpleStringProperty();
        }
        return this.name;
    }
    
    public String getName()
    {
        return this.name.get();
    }
    
    public void setName(String name)
    {
        this.name.set(name);
    }
    
    public abstract Trigger definedRuleset(String nextMissionID);
    
    abstract void applyRuleset(ArrayList<Mission> missions);
    
    public String createGameHeader()
    {
        return "<game " + utilitys.name(this.name.get()) + ">";
    }
    
    public String createGameTrailer()
    {
        return "</game>";
    }
}
