/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mission.rules.actions;

/**
 *
 * @author Gregor
 */
public class ActionSetVariable extends Action
{
    String var;
    String key;
    String value;
    
    public ActionSetVariable(String var, String key, String value)
    {
        super("SetVariable");
        this.var = var;
        this.key = key;
        this.value = value;
    }
    @Override
    public void setParameters(String... parametersList)
    {
        var = parametersList[0];
        key = parametersList[1];
        value = parametersList[2];
    }

    @Override
    protected void addNecessaryAndOptionalFields()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String assemble()
    {
        return "<action" + utilitys.var(var) + "><value><" + key + ">" + value + "</" + key + "></value></action>";
    }
    
}
