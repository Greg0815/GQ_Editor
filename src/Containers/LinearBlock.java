package Containers;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gregor
 */
public class LinearBlock extends Block
{

    private ArrayList<Class> missionTypesSequence;

    public LinearBlock(String id, int minMissionCount, int maxMissionCount, String internalConnector)
    {
        super(id, minMissionCount, maxMissionCount, internalConnector);
        missionTypesSequence = new ArrayList<>();
    }

    public LinearBlock(String id, int maxMissionCount, String internalConnector)
    {
        super(id, maxMissionCount, internalConnector);
        missionTypesSequence = new ArrayList<>();
    }

    public LinearBlock(String id)
    {
        super(id);
        missionTypesSequence = new ArrayList<>();
    }

    public ArrayList<Class> getMissionTypesSequence()
    {
        return missionTypesSequence;
    }

    public void addMissionTypesAsLinearSequence(Class... missionTypes)
    {
        if (this.getMaxMissionCount()-missionTypesSequence.size() > missionTypes.length) {
            this.missionTypesSequence.addAll(Arrays.asList(missionTypes));
        }
        else {
            int alreadyFilled = missionTypesSequence.size();
            for (int i = 0; i < this.getMaxMissionCount() - alreadyFilled; i++) {
                missionTypesSequence.add(missionTypes[i]);
            }
        }
    }
}
