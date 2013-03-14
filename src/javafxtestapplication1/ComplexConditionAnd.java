/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

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
}
