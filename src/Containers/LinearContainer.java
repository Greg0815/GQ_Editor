/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Containers;

import Blocks.LinearBlock;

/**
 *
 * @author Gregor
 */
public class LinearContainer extends Container
{
    public LinearContainer(String id, int minMissionCount, int maxMissionCount, String internalConnector)
    {
        super(id, minMissionCount, maxMissionCount, internalConnector);
    }

    public LinearContainer(LinearBlock linearBlock)
    {
        super(linearBlock.getId(), linearBlock.getMinMissionCount(), linearBlock.getMaxMissionCount(), linearBlock.getInternalConnector());
        this.addAllowedClasses(linearBlock.getMissionTypesSequence());
    }

//    public LinearContainer(VariableBlock variableBlock)
//    {
//        super(variableBlock.getId(), variableBlock.getMinMissionCount(), variableBlock.getMaxMissionCount(), variableBlock.getInternalConnector());
//    }
    @Override
    public String assemble()
    {
        // apply internal Connections
        // apply other Triggers
        return utilitys.createStringFromArrayList(getGameElements());
    }

    @Override
    public Boolean isComplete()
    {
        // TODO implementation
        return true;
    }
}
