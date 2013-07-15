/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission.Rules.Conditions;

/**
 *
 * @author Gregor
 */
public class ComplexConditionIf extends ComplexCondition
{
    public ComplexConditionIf()
    {
        super();
    }
    
    @Override
    public String assemble()
    {
        return "<if>" + utilitys.createStringFromArrayList(this.getConditionsArrayList()) + "</if>";
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
