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
public class Answer extends Simpleanswer
{
    private StringProperty correct;
    private StringProperty onChoose;
    
    public Answer()
    {
        super();
        correct = new SimpleStringProperty();
        onChoose = new SimpleStringProperty();
        addNecessaryFields("correct");
    }
    
    public Answer(String answer, String correct)
    {
        super(answer);
        this.correct = new SimpleStringProperty(correct);
        onChoose = new SimpleStringProperty(Boolean.parseBoolean(correct) ? "Gute Wahl" : "Schlechte Wahl");
        addNecessaryFields("correct");
    }
    
    public Answer(String answer, String correct, String onChoose)
    {
        super(answer);
        this.correct = new SimpleStringProperty(correct);
        this.onChoose = new SimpleStringProperty(onChoose);
        addNecessaryFields("correct");
    }

    public StringProperty correctProperty()
    {
        return correct;
    }
    
    public String getCorrect()
    {
        return correct.get();
    }
    
    public void setCorrect(String correct)
    {
        this.correct.set(correct);
    }
    
    public StringProperty onChooseProperty()
    {
        return onChoose;
    }
    
    public String getOnChoose()
    {
        return onChoose.get();
    }
    public void setOnChoose(String onChoose)
    {
        this.onChoose.set(onChoose);
    }
    
    @Override
    public String assemble()
    {
        return "<answer" + utilitys.correct(this.correct.get()) + utilitys.onChoose(this.onChoose.get()) + ">" + this.getAnswer() + "</answer>";
    }
    
    @Override
    public Boolean isComplete()
    {
        Boolean isComplete = true;
        if(this.getAnswer().isEmpty() || correct.isNull().get())
        {
            System.out.println("Answer incomplete");
            isComplete = false;
        }
        return isComplete;
    }
}
