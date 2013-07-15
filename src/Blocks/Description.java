/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Blocks;

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
    private ArrayList<Block> blocks;
    private ArrayList<BlockConnector> connectors;
    private ArrayList<Pair> defaults;       // TODO default-werte

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

    public void addDefaults(Pair... defaults)         // evtl check for duplicates
    {
        this.defaults.addAll(Arrays.asList(defaults));
    }

    public ArrayList<Block> getBlocks()
    {
        return blocks;
    }

    public void addBlock(Block... block)
    {
        blocks.addAll(Arrays.asList(block));
    }

    public void addBlock(Block block)
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

    public void addBlockConnector(String trigger, Block toBlock, Block fromBlock)
    {
        BlockConnector connector = new BlockConnector(trigger, toBlock, fromBlock);
        this.addBlockConnector(connector);
    }

    public ArrayList<BlockConnector> getBlockConnectors()
    {
        return connectors;
    }
}
