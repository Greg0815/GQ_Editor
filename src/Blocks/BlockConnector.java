/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Blocks;

import Mission.Rules.Trigger;

/**
 *
 * @author Gregor
 */
public class BlockConnector
{

    private String trigger;
//    private String fromBlockID;
//    private String toBlockID;
    private Block fromBlock;
    private Block toBlock;

//    public BlockConnector(String trigger, String fromBlockID, String toBlockID)
//    {
//        this.trigger = trigger;
//        this.fromBlockID = fromBlockID;
//        this.toBlockID = toBlockID;
//    }
    
    public BlockConnector(String trigger, Block fromBlock, Block toBlock)
    {
        this.trigger = trigger;
        this.fromBlock = fromBlock;
        this.toBlock = toBlock;
    }
    
    public Block getFromBlock()
    {
        return fromBlock;
    }
    
    public String getFromBlockId()
    {
        return fromBlock.getId();
    }
    
    public Block getToBlock()
    {
        return toBlock;
    }
    
    public String getToBlockId()
    {
        return toBlock.getId();
    }
    
    public Trigger getTrigger()
    {
        return new Trigger(trigger);
    }

//    public String getFromBlockId()
//    {
//        return fromBlockID;
//    }
//
//    public String getToBlockId()
//    {
//        return toBlockID;
//    }
}
