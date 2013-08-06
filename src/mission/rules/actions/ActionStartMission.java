/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mission.rules.actions;

import blocks.Block;

/**
 *
 * @author Gregor
 */
public class ActionStartMission extends Action
{
    private String id;
    private Block blockReference;
    
//    public ActionStartMission()
//    {
//        super("StartMission");
//        this.id = "";
//    }
    
    public ActionStartMission(String id)
    {
        super("StartMission");
        this.id = id;
    }
    
    public ActionStartMission(Block blockReference)
    {
        super("StartMission");
        this.blockReference = blockReference;
    }
    
//    public void setType(String id)
//    {
//        this.id = id;
//    }
    
    public String getBlockReferenceId()
    {
        return blockReference.getId();
    }
    
    @Override
    public String assemble()
    {
        return "<action" + utilitys.type(getType()) + utilitys.id(id) + "/>";
    }

    @Override
    public void setParameters(String... parametersList)
    {
        this.id = parametersList[0];
    }

    @Override
    public Boolean isComplete()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void addNecessaryAndOptionalFields()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
