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
public class StaticBlock extends Block
{
    private ArrayList<MissionDescriptor> missionDescriptions;
    
    public StaticBlock(String id)
    {
        super(id);
        missionDescriptions = new ArrayList<>();
    }
    
    public StaticBlock(String id, String internalConnector)
    {
        super(id, internalConnector);
        missionDescriptions = new ArrayList<>();
    }

    public void addMissionDescription(MissionDescriptor... missionDescription)
    {
        missionDescriptions.addAll(Arrays.asList(missionDescription));
    }
    
    public ArrayList<MissionDescriptor> getMissionDescriptions()
    {
        return missionDescriptions;
    }
}
