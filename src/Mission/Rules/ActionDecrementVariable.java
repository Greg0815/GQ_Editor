/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission.Rules;

/**
 *
 * @author Gregor
 */
public class ActionDecrementVariable extends Action
{
    private String var;
    
    public ActionDecrementVariable()
    {
        super("DecrementVariable");
        this.var = "";
    }
    
    public ActionDecrementVariable(String var)
    {
        super("DecrementVariable");
        this.var = var;
    }
    
    public void setType(String var)
    {
        this.var = var;
    }
    
    @Override
    public String assemble()
    {
        return "<action" + utilitys.type(this.getType()) + utilitys.var(this.var) + "/>";
    }

    @Override
    public void setParameters(String... parametersList)
    {
        this.var = parametersList[0];
    }

    @Override
    public Boolean isComplete()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
