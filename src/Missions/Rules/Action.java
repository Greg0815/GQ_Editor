/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

/**
 *
 * @author Gregor
 */
abstract public class Action implements AssembleInterface
{
    private String type;
    
    public Action()
    {
        this.type = "";
    }
    
    public Action(String type)
    {
        this.type = type;
    }

    protected String getType()
    {
        return this.type;
    }
}
