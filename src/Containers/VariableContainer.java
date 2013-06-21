/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Containers;

/**
 *
 * @author Gregor
 */
public class VariableContainer extends Container
{ 

    public VariableContainer(String id, int minMissionCount, int maxMissionCount, String internalConnector)
    {
        super(id, minMissionCount, maxMissionCount, internalConnector);
    }
    
    // TODO übrigen Constuctoren hinzufügen
    
    public VariableContainer(VariableBlock variableBlock)
    {
        super(variableBlock.getId(), variableBlock.getMinMissionCount(), variableBlock.getMaxMissionCount(), variableBlock.getInternalConnector());
        this.addAllowedClasses(variableBlock.getMissionTypes());
    }
    
    public Boolean hasSpaceLeft()
    {
        if(this.getGameElements().size() < this.getMaxMissionCount())
        {
            return true;
        }
        return false;
    }
    
    @Override
    public String assemble()
    {
        return utilitys.createStringFromArrayList(this.getGameElements());
    }

    @Override
    public Boolean isComplete()
    {
        // TODO implement
        return true;
    }
    
}
