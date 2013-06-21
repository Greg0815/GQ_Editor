/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import java.util.ArrayList;

/**
 *
 * @author Gregor
 */
public class Rule implements AssembleInterface
{
    private ArrayList<Condition> conditions;
    private ArrayList<Action> actions;

    public Rule()
    {
        this.conditions = new ArrayList<>();
        this.actions = new ArrayList<>();
    }
    
    public void addCondition(Condition condition)
    {
        this.conditions.add(condition);
    }
    
    public void addCondition(Condition conditon, int position)
    {
        this.conditions.add(position, conditon);
    }
    
    public void addAction(Action action)
    {
        this.actions.add(action);
    }
    
    public void addAction(Action action, int position)
    {
        this.actions.add(position, action);
    }

    @Override
    public String assemble()
    {
        return "<rule>" + utilitys.createStringFromArrayList(conditions) + utilitys.createStringFromArrayList(actions) + "</rule>";
    }
}
