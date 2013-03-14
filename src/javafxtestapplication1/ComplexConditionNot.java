/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

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
    
}
