/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mission.rules.conditions;

/**
 *
 * @author Gregor
 */
public class ComplexConditionAnd extends ComplexCondition
{
    public ComplexConditionAnd()
    {
        super();
    }
    
    @Override
    public String assemble()
    {
        return "<and>" + utilitys.createStringFromArrayList(this.getConditionsArrayList()) + "</and>";
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
