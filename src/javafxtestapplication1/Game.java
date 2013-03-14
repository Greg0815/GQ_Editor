/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import java.util.ArrayList;
import javafx.beans.property.StringProperty;


/**
 *
 * @author Gregor
 */
public abstract class Game implements AssembleInterface
{
    public StringProperty name;
    
    public abstract Trigger definedRuleset(String nextMissionID);
    
    public abstract void applyRuleset(ArrayList<Mission> missions);
    
    public String createGameHeader()
    {
        return "<game " + utilitys.name(this.name.get()) + ">";
    }
    
    public String createGameTrailer()
    {
        return "</game>";
    }
}
