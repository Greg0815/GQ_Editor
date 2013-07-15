/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission.Components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
public class Answer extends MissionComponent
{
    private StringProperty answer;
    private BooleanProperty correct;
    private StringProperty onChoose;

    public Answer()
    {
        super();
        answer = new SimpleStringProperty("");
        correct = new SimpleBooleanProperty(true);
        onChoose = new SimpleStringProperty("");
        addNecessaryAndOptionalFields();
    }

//    public Answer(String answer, String correct)
//    {
//        super(answer);
//        this.correct = new SimpleStringProperty(correct);
//        onChoose = new SimpleStringProperty(Boolean.parseBoolean(correct) ? "Gute Wahl" : "Schlechte Wahl");
//        addNecessaryFields("correct");
//    }
//    
//    public Answer(String answer, String correct, String onChoose)
//    {
//        super(answer);
//        this.correct = new SimpleStringProperty(correct);
//        this.onChoose = new SimpleStringProperty(onChoose);
//        addNecessaryFields("correct");
//    }
    @Override
    protected final void addNecessaryAndOptionalFields()
    {
        addNecessaryHeaderFields(getFieldByString("correct"), getFieldByString("answer"));
        addOptionalHeaderFields(getFieldByString("onChoose"));
    }

    public String getAnswer()
    {
        return answer.get();
    }
    public Boolean getCorrect()
    {
        return correct.get();
    }

    public String getOnChoose()
    {
        return onChoose.get();
    }
    public void setAnswer(String answer)
    {
        this.answer.set(answer);
    }

    public void setCorrect(Boolean correct)
    {
        this.correct.set(correct);
    }

    public void setOnChoose(String onChoose)
    {
        this.onChoose.set(onChoose);
    }
    public StringProperty answerProperty()
    {
        return answer;
    }

    public BooleanProperty correctProperty()
    {
        return correct;
    }

    public StringProperty onChooseProperty()
    {
        return onChoose;
    }

    @Override
    public String assemble()
    {
        return "<answer" + utilitys.correct(correct.get()) + utilitys.onChoose(onChoose.get()) + ">" + answer.get() + "</answer>";
    }

//    @Override
//    public Boolean isComplete()
//    {
//        Boolean isComplete = true;
//        if (this.getAnswer().isEmpty())
//        {
//            isComplete = false;
//        }
//        return isComplete;
//    }
}
