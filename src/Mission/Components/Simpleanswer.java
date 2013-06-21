/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission.Components;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
public class Simpleanswer extends MissionComponent implements Main.AssembleInterface
{

    private StringProperty answer;
//    private ArrayList<String> necessaryFields;

    public Simpleanswer()
    {
        super();
        this.answer = new SimpleStringProperty();
//        this.necessaryFields = new ArrayList<>();
        this.addNecessaryFields("answer");
    }

    public Simpleanswer(String answer)
    {
        super();
        this.answer = new SimpleStringProperty(answer);
//        this.necessaryFields = new ArrayList<>();
        this.addNecessaryFields("answer");
    }

//    public ArrayList<String> getNecessaryFields()
//    {
//        return necessaryFields;
//    }
//
//    public void addNecessaryFields(String... fields)
//    {
//        necessaryFields.addAll(Arrays.asList(fields));
//    }

    public StringProperty answerProperty()
    {
        return this.answer;
    }

    public String getAnswer()
    {
        return this.answer.get();
    }

    public void setAnswer(String answer)
    {
        this.answer.set(answer);
    }

    @Override
    public String assemble()
    {
        return "<answer>" + this.answerProperty().get() + "</answer>";
    }

    @Override
    public Boolean isComplete()
    {
        Boolean isComplete = true;
        if(answer.isNull().get())
        {
            System.out.println("Simpleanswer incomplete");
            isComplete = false;
        }
        return isComplete;
    }
}
