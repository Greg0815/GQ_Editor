/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mission.rules.conditions;

/**
 *
 * @author Gregor
 */
public class SimpleConditionMissionState extends SimpleCondition
{
    private String id;
    private String state;
    
//    public SimpleConditionMissionState()
//    {
//        super();
//        this.id = "";
//        this.state = "";
//    }
    
    public SimpleConditionMissionState(String id, String state)
    {
        super();
        this.id = id;
        this.state = state;
    }
    
    @Override
    public String assemble()
    {
        return "<missionState" + utilitys.id(this.id) + utilitys.state(this.state) + " />";
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
