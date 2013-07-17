/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mission.rules.conditions;

/**
 *
 * @author Gregor
 */
public class ComplexConditionNot extends ComplexCondition
{
    public ComplexConditionNot()
    {
        super();
    }
    
    @Override
    public String assemble()
    {
        return "<not>" + utilitys.createStringFromArrayList(this.getConditionsArrayList()) + "</not>";
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
