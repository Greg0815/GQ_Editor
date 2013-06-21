/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Mission.Rules.Action;
import Mission.Rules.Rule;
import Mission.Rules.Trigger;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Admin
 */
public class FromToTriggerAction
{
    private int from;
    private int to;
    private Trigger trigger;
    private Action action;
    
    public FromToTriggerAction(int from, int to, String triggerType, String actionType) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        this.from = from;
        this.to = to;
//        this.trigger = new Trigger();
        this.trigger = new Trigger(triggerType);
        String actionClassName = "Missions.Rules.Action" + actionType;
        Class actionClass = Class.forName(actionClassName);
        Constructor actionConstructor = actionClass.getConstructor();
        Object actionObject = actionConstructor.newInstance();
        this.action = (Action)actionObject;
        this.action.setParameters(actionType);
    }
    
    public int getFrom()
    {
        return this.from;
    }
    
    public int getTo()
    {
        return this.to;
    }
    
    public Trigger getTrigger()
    {
        return this.trigger;
    }
    
    public Action getAction()
    {
        return this.action;
    }
    
    public Trigger apply(String missionID)
    {
        this.action.setParameters(missionID);
        Rule rule = new Rule();
        rule.addAction(this.action);
        this.trigger.addRule(rule);
        return this.trigger;
    }
}
