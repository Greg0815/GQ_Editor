/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

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
}
