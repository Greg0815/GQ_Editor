/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Blocks;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Gregor
 */
public class LinearQuantityBlock extends Block
{

    private ArrayList<Pair<Class, Integer>> missionTypesWithQuantity;

    public LinearQuantityBlock(String id, int minMissionCount, int maxMissionCount, String internalConnector)
    {
        super(id, minMissionCount, maxMissionCount, internalConnector);
        missionTypesWithQuantity = new ArrayList<>();
    }

    public LinearQuantityBlock(String id, int maxMissionCount, String internalConnector)
    {
        super(id, maxMissionCount, internalConnector);
        missionTypesWithQuantity = new ArrayList<>();
    }

    public LinearQuantityBlock(String id)
    {
        super(id);
        missionTypesWithQuantity = new ArrayList<>();
    }
    
    public ArrayList<Pair<Class, Integer>> getMissionTypesWithQuantity()
    {
        return missionTypesWithQuantity;
    }
}
