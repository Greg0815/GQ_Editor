/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Containers;

import Mission.Rules.Action;
import Mission.Rules.ActionStartMission;
import Mission.Rules.Rule;
import Mission.Rules.Trigger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gregor
 */
public class TriggerAction
{
    private String triggerString;
    private String actionString;
    private ArrayList<String> parametersString;
    private BlockToBeReplaced blockReference;
    private Boolean isStartMissionAction;
//    private Trigger trigger;
//    private Action action;
//    private ArrayList<Object> parameters;
    
    public TriggerAction(String trigger, String action, String... parameters)
    {
        triggerString = trigger;
        actionString = action;
        isStartMissionAction = false;
        parametersString = new ArrayList<>();
        parametersString.addAll(Arrays.asList(parameters));
        blockReference = null;
        
    }
    
    public TriggerAction(String trigger, String action, BlockToBeReplaced blockReference)
    {
        triggerString = trigger;
        actionString = action;
        isStartMissionAction = true;
        this.blockReference = blockReference;
        parametersString = null;
    }
    
    public String getBlockReferenceId()
    {
        return blockReference.getId();
    }
    public Boolean isStartMissionAction()
    {
        if(isStartMissionAction)
        {
            return true;
        }
        return false;
    }
    
    public Trigger getTrigger(String missionId)
    {
        Trigger trigger = new Trigger(triggerString);
        Rule rule = new Rule();
        Action action = new ActionStartMission(missionId);
        rule.addAction(action);
        trigger.addRule(rule);
        return trigger;
    }
    
    public Trigger getTrigger()
    {
        try {
            Trigger trigger = new Trigger(triggerString);
            Rule rule = new Rule();
            Class actionClass = Class.forName("Action" + actionString);
            Action action = (Action)actionClass.newInstance();
            action.setParameters((String[])parametersString.toArray());
            rule.addAction(action);
            trigger.addRule(rule);
            return trigger;
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            Logger.getLogger(TriggerAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
