/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

/**
 *
 * @author Gregor
 */
public class ActionDecrementVariable extends Action
{
    private String var;
    
    public ActionDecrementVariable()
    {
        super();
        this.var = "";
    }
    
    public ActionDecrementVariable(String var)
    {
        super("DecrementVariable");
        this.var = var;
    }
    
    @Override
    public String assemble()
    {
        return "<action" + utilitys.type(this.getType()) + utilitys.var(this.var) + "/>";
    }
    
}
