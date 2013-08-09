/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package containers;

import blocks.MissionDescriptor;
import blocks.StaticBlock;
import mission.Mission;

/**
 *
 * @author Gregor
 */
public class StaticContainer extends Container
{
    public StaticContainer(StaticBlock staticBlock)
    {
        super(staticBlock);
        parseStaticBlock(staticBlock);
    }

    private void parseStaticBlock(StaticBlock staticBlock)
    {
        for (MissionDescriptor missionDescription : staticBlock.getMissionDescriptions())
        {
            try
            {
                Mission mission = (Mission) missionDescription.getMissionClass().newInstance();
                Object fieldObject = missionDescription.getMissionAttributeValues().get(0).getKey().get(mission);
                fieldObject = missionDescription.getMissionAttributeValues().get(0).getValue();
                if (missionDescription.getMissionComponents().size() > 0)
                {
                    missionDescription.getMissionComponents();
                }
            }
            catch (InstantiationException | IllegalAccessException ex)
            {
                System.out.println("parseStaticBlock Error in StaticContainer: " + ex);
            }
        }
    }

    @Override
    protected void addNecessaryAndOptionalFields()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
