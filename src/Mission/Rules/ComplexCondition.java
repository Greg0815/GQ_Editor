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
public abstract class ComplexCondition extends Condition
{
    private ArrayList<Condition> conditions;
    
    public ComplexCondition()
    {
        conditions = new ArrayList<>();
    }
    
    protected ArrayList<Condition> getConditionsArrayList()
    {
        return this.conditions;
    }
    
    public Condition getConditionAtPosition(int position)
    {
        return this.conditions.get(position);
    }
    
    public void addCondition(Condition condition)
    {
        this.conditions.add(condition);
    }
    
    public void addConditionAtPosition(Condition condition, int position)
    {
        this.conditions.add(position, condition);
    }
}
