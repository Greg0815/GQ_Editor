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
public class MissionMultipleChoiceQuestion extends Mission
{
    private StringProperty loopUntilSuccess;
    private StringProperty shuffle;
    private MultipleChoiceQuestion question;

    public MissionMultipleChoiceQuestion()
    {
        super("MultipleChoiceQuestion");
        this.loopUntilSuccess = new SimpleStringProperty();
        this.shuffle = new SimpleStringProperty();
        this.question = new MultipleChoiceQuestion();
    }
    
    public MissionMultipleChoiceQuestion(String id, String loopUntilSuccess, String shuffle)
    {
        super("MultipleChoiceQuestion", id);
        this.loopUntilSuccess = new SimpleStringProperty();
        this.loopUntilSuccess.set(loopUntilSuccess);
        this.shuffle = new SimpleStringProperty();
        this.shuffle.set(shuffle);
        this.question = new MultipleChoiceQuestion();
    }
    
    public MissionMultipleChoiceQuestion(String id, String loopUntilSuccess, String shuffle, MultipleChoiceQuestion question)
    {
        super("MultipleChoiceQuestion", id);
        this.loopUntilSuccess = new SimpleStringProperty();
        this.loopUntilSuccess.set(loopUntilSuccess);
        this.shuffle = new SimpleStringProperty();
        this.shuffle.set(shuffle);
        this.question = question;
    }
    
    public StringProperty loopUntilSuccessProperty()
    {
        if(this.loopUntilSuccess == null)
        {
            this.loopUntilSuccess = new SimpleStringProperty();
            this.loopUntilSuccess.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                }
            });
        }
        return this.loopUntilSuccess;
    }
    
    public String getLoopUntilSuccess()
    {
        return this.loopUntilSuccess.get();
    }
    
    public void setLoopUntilSuccess(String answer)
    {
        this.loopUntilSuccess.set(answer);
    }
    
    public StringProperty shuffleProperty()
    {
        if(this.shuffle == null)
        {
            this.shuffle = new SimpleStringProperty();
            this.shuffle.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                }
            });
        }
        return this.shuffle;
    }
    
    public String getShuffle()
    {
        return this.shuffle.get();
    }
    
    public void setShuffle(String answer)
    {
        this.shuffle.set(answer);
    }
    
    
    public void addMultipleChoiceQuestion(MultipleChoiceQuestion question)
    {
        this.question = question;
    }
    
    public MultipleChoiceQuestion getMultipleChoiceQuestion()
    {
        return this.question;
    }

    @Override
    public String assemble()
    {
        return createMissionHeader() + utilitys.createStringFromArrayList(triggers) + question.assemble() + createMissionTrailer();
    }

    @Override
    public String createSpecificMissionHeader()
    {
        return utilitys.loopUntilSuccess(loopUntilSuccess.get()) + utilitys.shuffle(shuffle.get());
    }
    
}
