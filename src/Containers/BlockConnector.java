/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Containers;

/**
 *
 * @author Gregor
 */
public class BlockConnector
{

    private String trigger;
//    private String fromBlockID;
//    private String toBlockID;
    private BlockToBeReplaced fromBlock;
    private BlockToBeReplaced toBlock;

//    public BlockConnector(String trigger, String fromBlockID, String toBlockID)
//    {
//        this.trigger = trigger;
//        this.fromBlockID = fromBlockID;
//        this.toBlockID = toBlockID;
//    }
    
    public BlockConnector(String trigger, BlockToBeReplaced fromBlock, BlockToBeReplaced toBlock)
    {
        this.trigger = trigger;
        this.fromBlock = fromBlock;
        this.toBlock = toBlock;
    }
    
    public BlockToBeReplaced getFromBlock()
    {
        return fromBlock;
    }
    
    public String getFromBlockId()
    {
        return fromBlock.getId();
    }
    
    public BlockToBeReplaced getToBlock()
    {
        return toBlock;
    }
    
    public String getToBlockId()
    {
        return toBlock.getId();
    }
    
    public String getTrigger()
    {
        return trigger;
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
