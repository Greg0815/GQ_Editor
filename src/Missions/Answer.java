/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Gregor
 */
public class Answer implements AssembleInterface
{
    private StringProperty answer;
    private StringProperty correct;
    private StringProperty onChoose;
    
    public Answer()
    {
        this.answer = new SimpleStringProperty();
        this.correct = new SimpleStringProperty();
        this.onChoose = new SimpleStringProperty();
    }
    
    public Answer(String answer, String correct)
    {
        this.answer.set(answer);
        this.correct.set(correct);
        this.onChoose.set(Boolean.parseBoolean(correct) ? "Gute Wahl" : "Schlechte Wahl");
    }
    
    public Answer(String answer, String correct, String onChoose)
    {
        this.answer.set(answer);
        this.correct.set(correct);
        this.onChoose.set(onChoose);
    }

    public StringProperty answerProperty()
    {
        if(this.answer == null)
        {
            this.answer = new SimpleStringProperty();
            this.answer.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                }
            });
        }
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
    
    public StringProperty correctProperty()
    {
        if(this.correct == null)
        {
            this.correct = new SimpleStringProperty();
            this.correct.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                }
            });
        }
        return this.correct;
    }
    
    public String getCorrect()
    {
        return this.correct.get();
    }
    
    public void setCorrect(String correct)
    {
        this.correct.set(correct);
    }
    
    public StringProperty onChooseProperty()
    {
        if(this.onChoose == null)
        {
            this.onChoose = new SimpleStringProperty();
            this.onChoose.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                }
            });
        }
        return this.onChoose;
    }
    
    public String getOnChoose()
    {
        return this.onChoose.get();
    }
    public void setOnChoose(String onChoose)
    {
        this.onChoose.set(onChoose);
    }
    
    @Override
    public String assemble()
    {
        return "<answer" + utilitys.correct(this.correct.get()) + utilitys.onChoose(this.onChoose.get()) + ">" + this.answer.get() + "</answer>";
    }
}
