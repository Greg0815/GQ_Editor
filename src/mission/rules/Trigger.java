/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mission.rules;

import main.BaseComponent;
import java.util.ArrayList;

/**
 *
 * @author Gregor
 */
public class Trigger extends BaseComponent
{
    private String trigger;
    private ArrayList<Rule> rules;

    public Trigger(String trigger)
    {
        this.trigger = trigger;
        this.rules = new ArrayList<>();
    }
    
    public void addRule(Rule rule)
    {
        this.rules.add(rule);
    }
    
    public void addRule(Rule rule, int position)
    {
        this.rules.add(rule);
    }

    @Override
    public String assemble()
    {
        return "<" + trigger + ">" + utilitys.createStringFromArrayList(rules) + "</" + trigger + ">";
    }

    @Override
    public Boolean isComplete()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void addNecessaryAndOptionalFields()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
