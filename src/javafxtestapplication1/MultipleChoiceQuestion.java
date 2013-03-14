/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Gregor
 */
public class MultipleChoiceQuestion implements AssembleInterface
{
    private StringProperty questiontext;
    private ArrayList<Answer> answers;
    
    public MultipleChoiceQuestion()
    {
        this.questiontext = new SimpleStringProperty();
        this.answers = new ArrayList<>();
    }
    
    public MultipleChoiceQuestion(String questiontext)
    {
        this.questiontext = new SimpleStringProperty();
        this.questiontext.set(questiontext);
        this.answers = new ArrayList<>();
    }
    
//    public MultipleChoiceQuestion(String questiontext, ArrayList<Answer> answers)
//    {
//        this.questiontext = questiontext;
//        this.answers = answers;
//    }
    
    public StringProperty questiontextProperty()
    {
        if(this.questiontext == null)
        {
            this.questiontext = new SimpleStringProperty();
            this.questiontext.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                }
            });
        }
        return this.questiontext;
    }
    
    public String getQuestiontext()
    {
        return this.questiontext.get();
    }
    
    public void setQuestiontext(String questiontext)
    {
        this.questiontext.set(questiontext);
    }
    
    public void addAnswer(Answer answer)
    {
        this.answers.add(answer);
    }
    
    public void addAnswer(String answer, String correct)
    {
        this.answers.add(new Answer(answer, correct));
    }
        
    public void addAnswer(String answer, String correct, String onChoose)
    {
        this.answers.add(new Answer(answer, correct, onChoose));
    }
    
    public int getAnswerCount()
    {
        return this.answers.size();
    }
    
    @Override
    public String assemble()
    {
        return "<question><questiontext>" + this.questiontext.get() + "</questiontext>" + utilitys.createStringFromArrayList(this.answers) + "</question>";
    }
}
