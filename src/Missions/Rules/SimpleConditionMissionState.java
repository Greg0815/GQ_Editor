/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

/**
 *
 * @author Gregor
 */
public class SimpleConditionMissionState extends SimpleCondition
{
    private String id;
    private String state;
    
    public SimpleConditionMissionState()
    {
        super();
        this.id = "";
        this.state = "";
    }
    
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
    
}
