/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission.Rules.Conditions;

/**
 *
 * @author Gregor
 */
public class ComplexConditionOr extends ComplexCondition
{
    public ComplexConditionOr()
    {
        super();
    }
    
    @Override
    public String assemble()
    {
        return "<or>" + utilitys.createStringFromArrayList(this.getConditionsArrayList()) + "</or>";
    }

    @Override
    public Boolean isComplete()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
