/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Containers;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.util.Pair;

/**
 *
 * @author Gregor
 */
public class Description
{
    private String gameDescription;
    private ArrayList<BlockToBeReplaced> blocks;
    private ArrayList<BlockConnector> connectors;
    private ArrayList<Pair> defaults;
    private ArrayList<String> fieldsToShow; // TODO

    public Description(String gameDescription)
    {
        this.gameDescription = gameDescription;
        blocks = new ArrayList<>();
        connectors = new ArrayList<>();
        defaults = new ArrayList<>();
    }
    
    public String getGameDescription()
    {
        return gameDescription;
    }
    
    public ArrayList<Pair> getDefaults()
    {
        return defaults;
    }
    
    public void addDefaults(Pair... defaults)
    {
        this.defaults.addAll(Arrays.asList(defaults));
    }
    
    public ArrayList<BlockToBeReplaced> getBlocks()
    {
        return blocks;
    }
    
    public void addBlock(BlockToBeReplaced... block)
    {
        blocks.addAll(Arrays.asList(block));
    }
    
    public void addBlock(BlockToBeReplaced block)
    {
        blocks.add(block);
    }
    
    public void addBlockConnector(BlockConnector connector)
    {
        connectors.add(connector);
    }
    
    public void addBlockConnector(BlockConnector... connector)
    {
        connectors.addAll(Arrays.asList(connector));
    }
    
    public void addBlockConnector(String trigger, BlockToBeReplaced toBlock, BlockToBeReplaced fromBlock)
    {
        BlockConnector connector = new BlockConnector(trigger, toBlock, fromBlock);
        this.addBlockConnector(connector);
    }
    
    public ArrayList<BlockConnector> getBlockConnectors()
    {
        return connectors;
    }
}
