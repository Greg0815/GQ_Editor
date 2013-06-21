/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Containers;

import Mission.Rules.Rule;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Gregor
 */
public class Block
{
    public enum triggerType {       // TODO einbauen!
        ON_END, ON_START, ON_SUCCESS, ON_FAIL, ON_ENTER, ON_LEAVE
    }
    
    private String id;
    private int minMissionCount;
    private int maxMissionCount;
    private String internalConnector;
//    private ArrayList<TriggerAction> triggerActions;
    private ArrayList<Rule> onSuccessRules;
    private ArrayList<Rule> onFailRules;
    private ArrayList<Rule> onBeginRules;
    private ArrayList<Rule> onEndRules;
    private ArrayList<Rule> onEnterRules;
    private ArrayList<Rule> onLeaveRules;

    public Block(String id, int minMissionCount, int maxMissionCount, String internalConnector)
    {
        this.id = id;
        this.minMissionCount = minMissionCount > 1 ? minMissionCount : 1;
        this.maxMissionCount = maxMissionCount > 1 && maxMissionCount > minMissionCount ? maxMissionCount : minMissionCount + 1;
        this.internalConnector = checkInternalConnectorCorrectness(internalConnector) ? internalConnector : "onEnd";
//        triggerActions = new ArrayList<>();
        // pfffffffffffft
        onSuccessRules = new ArrayList<>();
        onFailRules = new ArrayList<>();
        onBeginRules = new ArrayList<>();
        onEndRules = new ArrayList<>();
        onEnterRules = new ArrayList<>();
        onLeaveRules = new ArrayList<>();
    }

    public Block(String id, int maxMissionCount, String internalConnector)
    {
        this.id = id;
        minMissionCount = 1;
        this.maxMissionCount = maxMissionCount > 1 ? maxMissionCount : minMissionCount + 1;
        this.internalConnector = checkInternalConnectorCorrectness(internalConnector) ? internalConnector : "onEnd";
//        triggerActions = new ArrayList<>();
        // pfffffffffffft
        onSuccessRules = new ArrayList<>();
        onFailRules = new ArrayList<>();
        onBeginRules = new ArrayList<>();
        onEndRules = new ArrayList<>();
        onEnterRules = new ArrayList<>();
        onLeaveRules = new ArrayList<>();
    }

    public Block(String id)
    {
        this.id = id;
        minMissionCount = 1;
        maxMissionCount = 1;
        internalConnector = "";
//        triggerActions = new ArrayList<>();
        // pfffffffffffft
        onSuccessRules = new ArrayList<>();
        onFailRules = new ArrayList<>();
        onBeginRules = new ArrayList<>();
        onEndRules = new ArrayList<>();
        onEnterRules = new ArrayList<>();
        onLeaveRules = new ArrayList<>();
    }

    public ArrayList<Rule> getOnSuccessRules()
    {
        return onSuccessRules;
    }

    public ArrayList<Rule> getOnFailRules()
    {
        return onFailRules;
    }

    public ArrayList<Rule> getOnBeginRules()
    {
        return onBeginRules;
    }

    public ArrayList<Rule> getOnEndRules()
    {
        return onEndRules;
    }

    public ArrayList<Rule> getOnEnterRules()
    {
        return onEnterRules;
    }

    public ArrayList<Rule> getOnLeaveRules()
    {
        return onLeaveRules;
    }

    public void addSuccessRule(Rule... rules)
    {
        onSuccessRules.addAll(Arrays.asList(rules));
    }

//    public void addSuccessRule(String type, String parameters)
//    {
//        throw new NotImplementedException();
//    }
//
//    public void addSuccessRule(ContainerToBeReplaced containerReference)
//    {
//        onSuccessActions.add(new ActionStartMission(containerReference));
//    }

    public void addFailRule(Rule... rules)
    {
        onFailRules.addAll(Arrays.asList(rules));
    }

//    public void addFailRule(String type, String parameters)
//    {
//        throw new NotImplementedException();
//    }
//
//    public void addFailRule(ContainerToBeReplaced containerReference)
//    {
//        onFailActions.add(new ActionStartMission(containerReference));
//    }

    public void addBeginRule(Rule... rules)
    {
        onBeginRules.addAll(Arrays.asList(rules));
    }

//    public void addBeginRule(String type, String parameters)
//    {
//        throw new NotImplementedException();
//    }
//
//    public void addBeginRule(ContainerToBeReplaced containerReference)
//    {
//        onBeginActions.add(new ActionStartMission(containerReference));
//    }

    public void addEndRule(Rule... rules)
    {
        onEndRules.addAll(Arrays.asList(rules));
    }

//    public void addEndRule(String type, String parameters)
//    {
//        throw new NotImplementedException();
//    }
//
//    public void addEndRule(ContainerToBeReplaced containerReference)
//    {
//        onEndActions.add(new ActionStartMission(containerReference));
//    }

    public void addEnterRule(Rule... rules)
    {
        onEnterRules.addAll(Arrays.asList(rules));
    }

//    public void addEnterRule(String type, String parameters)
//    {
//        throw new NotImplementedException();
//    }
//
//    public void addEnterRule(ContainerToBeReplaced containerReference)
//    {
//        onEnterActions.add(new ActionStartMission(containerReference));
//    }

    public void addLeaveRule(Rule... rules)
    {
        onLeaveRules.addAll(Arrays.asList(rules));
    }

//    public void addLeaveRule(String type, String parameters)
//    {
//        throw new NotImplementedException();
//    }
//
//    public void addLeaveRule(ContainerToBeReplaced containerReference)
//    {
//        onLeaveActions.add(new ActionStartMission(containerReference));
//    }

//    public void addTriggerActionParameter(TriggerAction... triggerActions)
//    {
//        this.triggerActions.addAll(Arrays.asList(triggerActions));
//    }
//
//    public void addTriggerActionParameter(String trigger, String action, String... parameters)
//    {
//        TriggerAction triggerAction = new TriggerAction(trigger, action, parameters);
//        this.triggerActions.add(triggerAction);
//    }

    private Boolean checkInternalConnectorCorrectness(String internalConnector)
    {
        if (internalConnector.contentEquals("onStart") || internalConnector.contentEquals("onEnd") || internalConnector.contentEquals("onSuccess") || internalConnector.contentEquals("onFail") || internalConnector.contentEquals("onEnter") || internalConnector.contentEquals("onLeave")) {
            return true;
        }
        return false;
    }

    public String getInternalConnector()
    {
        return internalConnector;
    }
    
    public void setInternalConnectorToOnStart()
    {
        internalConnector = "onStart";
    }

    public void setInternalConnectorToOnEnd()
    {
        internalConnector = "onEnd";
    }

    public void setInternalConnectorToOnSuccess()
    {
        internalConnector = "onSuccess";
    }

    public void setInternalConnectorToOnFail()
    {
        internalConnector = "onFail";
    }

    public void setInternalConnectorToOnEnter()
    {
        internalConnector = "onEnter";
    }

    public void setInternalConnectorToOnLeave()
    {
        internalConnector = "onLeave";
    }

    public String getId()
    {
        return id;
    }

    public int getMinMissionCount()
    {
        return minMissionCount;
    }

    public int getMaxMissionCount()
    {
        return maxMissionCount;
    }

//    public ArrayList<TriggerAction> getTriggerActions()
//    {
//        return triggerActions;
//    }
}
