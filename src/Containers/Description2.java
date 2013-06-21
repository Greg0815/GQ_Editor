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
public class Description2
{
    private String gameDescription;
    private ArrayList<Block> blocks;
    private ArrayList<BlockConnector2> connectors;
    private ArrayList<Pair> defaults;       // TODO default-werte
    private ArrayList<String> fieldsToShow;     // TODO anzuzeigende felder

    public Description2(String gameDescription)
    {
        this.gameDescription = gameDescription;
        blocks = new ArrayList<>();
        connectors = new ArrayList<>();
        defaults = new ArrayList<>();
        fieldsToShow = new ArrayList<>();
    }

    public String getGameDescription()
    {
        return gameDescription;
    }

    public ArrayList<Pair> getDefaults()
    {
        return defaults;
    }

    public ArrayList<String> getFieldsToShow()
    {
        return fieldsToShow;
    }

    public void addFieldsToShow(String... fieldsToShow)         // evtl check for duplicates
    {
        this.fieldsToShow.addAll(Arrays.asList(fieldsToShow));
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

    public void addBlockConnector(BlockConnector2 connector)
    {
        connectors.add(connector);
    }

    public void addBlockConnector(BlockConnector2... connector)
    {
        connectors.addAll(Arrays.asList(connector));
    }

    public void addBlockConnector(String trigger, Block toBlock, Block fromBlock)
    {
        BlockConnector2 connector = new BlockConnector2(trigger, toBlock, fromBlock);
        this.addBlockConnector(connector);
    }

    public ArrayList<BlockConnector2> getBlockConnectors()
    {
        return connectors;
    }
}
