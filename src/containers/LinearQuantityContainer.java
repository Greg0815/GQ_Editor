/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package containers;

import blocks.LinearQuantityBlock;

/**
 *
 * @author Gregor
 */
public class LinearQuantityContainer extends Container
{
    public LinearQuantityContainer(LinearQuantityBlock block)
    {
        super(block.getId(), block.getMinMissionCount(), block.getMaxMissionCount(), block.getInternalConnector());
//        this.addAllowedClasses(block.getMissionTypesWithQuantity());  // TODO übersetzen in linearContainer oder eigenes system erzeugen
    }
    @Override
    public String assemble()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean isComplete()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void addNecessaryAndOptionalFields()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
