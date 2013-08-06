/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package containers;

import blocks.VariableBlock;
import main.GameComponent;

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
    
    public VariableContainer(VariableBlock variableBlock)
    {
        super(variableBlock.getId(), variableBlock.getMinMissionCount(), variableBlock.getMaxMissionCount(), variableBlock.getInternalConnector());
        this.addAllowedClasses(variableBlock.getMissionTypes());
    }
    
    public void deleteGameElement(GameComponent gameElement)
    {
        this.getGameComponents().remove(gameElement);
    }
    
    public Boolean hasSpaceLeft()
    {
        if(this.getGameComponents().size() < this.getMaxMissionCount())
        {
            return true;
        }
        return false;
    }
    
    @Override
    public String assemble()
    {
        return utilitys.createStringFromArrayList(this.getGameComponents());
    }

    @Override
    public Boolean isComplete()
    {
        Boolean isComplete = true;
        for(GameComponent gameComponent : this.getGameComponents())
        {
            if(!gameComponent.isComplete())
            {
                System.out.println("GameComponent incomplete: " + gameComponent.getId());
                isComplete = false;
                break;
            }
        }
        return isComplete;
    }

    @Override
    protected void addNecessaryAndOptionalFields()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
