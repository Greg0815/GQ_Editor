/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Containers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Gregor
 */
public class BlockToBeReplaced
{
    private String id;
    private int maxMissionCount;
    private ArrayList<Class> missionTypes;
    private ArrayList<TriggerAction> triggerActions;
    private String internalConnector;
    
    public BlockToBeReplaced(String id)
    {
        this.id = id;
        this.maxMissionCount = 1;
        missionTypes = new ArrayList<>();
        triggerActions = new ArrayList<>();
    }
    
    public BlockToBeReplaced(String id, int maxMissionCount)
    {
        this.id = id;
        this.maxMissionCount = maxMissionCount;
        missionTypes = new ArrayList<>();
        triggerActions = new ArrayList<>();
    }
    
    public ArrayList<TriggerAction> getTriggerActions()
    {
        return triggerActions;
    }
    
    public String getInternalConnector()
    {
        return internalConnector;
    }
    
    public int getMaxMissionCount()
    {
        return maxMissionCount;
    }
    
    public String getId()
    {
        return id;
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
    
    public void setInternalConnectorToOnStart()
    {
        internalConnector = "onStart";
    }
    
    public void addMissionTypes(Class... missionTypes)
    {
        this.missionTypes.addAll(Arrays.asList(missionTypes));
    }
    
    public void addMissionTypes(String... missionTypes)
    {
        
        for(String mission : missionTypes)
        {
            try {
                Class newClass = Class.forName("Mission." + mission);
                if(!this.missionTypes.contains(newClass))
                {
                    this.missionTypes.add(newClass);
                }
            }
            catch (ClassNotFoundException ex) {
                System.out.println("Class Block - Method addMissions - Class.forName Error: " + ex);
            }
        }
    }
    
    public ArrayList<Class> getMissionClasses()
    {
        return missionTypes;
    }
    
    public void addTriggerActionParameter(TriggerAction... dinger)
    {
        this.triggerActions.addAll(Arrays.asList(dinger));
    }
    
    public void addTriggerActionParameter(String trigger, String action, String... parameters)
    {
        TriggerAction ding = new TriggerAction(trigger, action, parameters);
        this.triggerActions.add(ding);
    }
}
