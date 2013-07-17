/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blocks;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Gregor
 */
public class VariableBlock extends Block
{
    private ArrayList<Class> missionTypes;

    public VariableBlock(String id, int minMissionCount, int maxMissionCount, String internalConnector)
    {
        super(id, minMissionCount, maxMissionCount, internalConnector);
        missionTypes = new ArrayList<>();
    }

    public VariableBlock(String id, int maxMissionCount, String internalConnector)
    {
        super(id, maxMissionCount, internalConnector);
        missionTypes = new ArrayList<>();
    }

    public VariableBlock(String id)
    {
        super(id);
        missionTypes = new ArrayList<>();
    }
    
    public ArrayList<Class> getMissionTypes()
    {
        return missionTypes;
    }
    
    public void addMissionTypes(Class... missionTypes)
    {
        if (this.getMaxMissionCount()-this.missionTypes.size() > missionTypes.length) {
            this.missionTypes.addAll(Arrays.asList(missionTypes));
        }
        else {
            int alreadyFilled = this.missionTypes.size();
            for (int i = 0; i < this.getMaxMissionCount() - alreadyFilled; i++) {
                this.missionTypes.add(missionTypes[i]);
            }
        }
    }
}
