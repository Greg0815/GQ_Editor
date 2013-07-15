/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission.Rules.Conditions;

/**
 *
 * @author Gregor
 */
public class SimpleConditionComparison extends SimpleCondition
{
    private String relation;
    private String typeOfFirstVariable;
    private String valueOfFirstVariable;
    private String typeOfSecondVariable;
    private String valueOfSecondVariable;
    
//    public SimpleConditionComparison()
//    {
//        this.relation = "";
//        this.typeOfFirstVariable = "";
//        this.valueOfFirstVariable = "";
//        this.typeOfSecondVariable = "";
//        this.valueOfSecondVariable = "";
//    }
    
    public SimpleConditionComparison(String relation, String typeOfFirstVariable, String valueOfFirstVariable, String typeOfSecondVariable, String valueOfSecondVariable)
    {
        this.relation = relation;
        this.typeOfFirstVariable = typeOfFirstVariable;
        this.valueOfFirstVariable = valueOfFirstVariable;
        this.typeOfSecondVariable = typeOfSecondVariable;
        this.valueOfSecondVariable = valueOfSecondVariable;
    }
    
    @Override
    public String assemble()
    {
        return    "<" + this.relation + ">"
                + "<" + this.typeOfFirstVariable + ">" 
                + this.valueOfFirstVariable
                + "</" + this.typeOfFirstVariable + ">"
                + "<" + this.typeOfSecondVariable + ">"
                + this.valueOfSecondVariable
                + "</" + this.typeOfSecondVariable + ">"
                + "</" + this.relation + ">";
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
