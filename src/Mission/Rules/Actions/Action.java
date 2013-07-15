/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission.Rules.Actions;

import Main.BaseComponent;

/**
 *
 * @author Gregor
 */
public abstract class Action  extends BaseComponent
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
