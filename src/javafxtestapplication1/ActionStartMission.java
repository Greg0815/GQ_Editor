/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

/**
 *
 * @author Gregor
 */
public class ActionStartMission extends Action
{
    private String id;
    
    public ActionStartMission()
    {
        super("StartMission");
        this.id = "";
    }
    
    public ActionStartMission(String id)
    {
        super("StartMission");
        this.id = id;
    }
    
    @Override
    public String assemble()
    {
        return "<action" + utilitys.type(this.getType()) + utilitys.id(this.id) + "/>";
    }
}
