/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission.Rules.Actions;

import Main.AssembleInterface;

/**
 *
 * @author Gregor
 */
public abstract class Action implements AssembleInterface
{
    private String type;
    
//    public Action()
//    {
//        this.type = "";
//    }
    
    public Action(String type)
    {
        this.type = type;
    }

    protected String getType()
    {
        return this.type;
    }
    
//    abstract public void setType(String var);
    
    abstract public void setParameters(String... parametersList);
}
