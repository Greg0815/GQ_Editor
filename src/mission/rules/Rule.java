/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mission.rules;

import mission.rules.actions.Action;
import mission.rules.conditions.Condition;
import main.AssembleInterface;
import main.BaseComponent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Gregor
 */
public class Rule extends BaseComponent
{
    private ArrayList<Condition> conditions;
    private ArrayList<Action> actions;

    public Rule()
    {
        this.conditions = new ArrayList<>();
        this.actions = new ArrayList<>();
    }
    
    public Rule(Condition condition, Action... actions)
    {
        conditions.add(condition);
        this.actions.addAll(Arrays.asList(actions));
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
    
    public ArrayList<Action> getActions()
    {
        return actions;
    }

    @Override
    public String assemble()
    {
        return "<rule>" + utilitys.createStringFromArrayList(conditions) + utilitys.createStringFromArrayList(actions) + "</rule>";
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
