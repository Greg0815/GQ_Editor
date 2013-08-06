/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mission;

import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mission.components.Answer;

/**
 *
 * @author Gregor
 */
public class MultipleChoiceQuestion extends Mission
{
    private BooleanProperty loopUntilSuccess;
    private BooleanProperty shuffle;
    private StringProperty questiontext;
    private ArrayList<Answer> answer;

    public MultipleChoiceQuestion()
    {
        super("MultipleChoiceQuestion");
        loopUntilSuccess = new SimpleBooleanProperty(true);
        shuffle = new SimpleBooleanProperty(true);
        questiontext = new SimpleStringProperty("");
        answer = new ArrayList<>();
        addNecessaryAndOptionalFields();
    }

    public Boolean getLoopUntilSuccess()
    {
        return loopUntilSuccess.get();
    }

    public Boolean getShuffle()
    {
        return shuffle.get();
    }

    public String getQuestiontext()
    {
        return questiontext.get();
    }

    public void setLoopUntilSuccess(Boolean loopUntilSuccess)
    {
        this.loopUntilSuccess.set(loopUntilSuccess);
    }

    public void setShuffle(Boolean shuffle)
    {
        this.shuffle.set(shuffle);
    }

    public void setQuestiontext(String questiontext)
    {
        this.questiontext.set(questiontext);
    }

    public BooleanProperty loopUntilSuccessProperty()
    {
        return loopUntilSuccess;
    }

    public BooleanProperty shuffleProperty()
    {
        return shuffle;
    }

    public StringProperty questiontextProperty()
    {
        return questiontext;
    }

    public Answer addAnswer()
    {
        Answer newAnswer = new Answer();
        answer.add(newAnswer);
        return newAnswer;
    }

    public void addAnswer(Answer answer)
    {
        this.answer.add(answer);
    }

    public String assembleQuestionAndAnswer()
    {
        return "<question><questiontext>" + questiontext.get() + "</questiontext>" + utilitys.createStringFromArrayList(answer) + "</question>";
    }

    @Override
    public String assemble()
    {
        return buildMissionHead() + assembleQuestionAndAnswer() + buildMissionTail();
    }

    @Override
    protected final void addNecessaryAndOptionalFields()
    {
        addNecessaryNonHeaderFields(getFieldByString("questiontext"), getFieldByString("answer"));
        addOptionalHeaderFields(getFieldByString("loopUntilSuccess"), getFieldByString("shuffle"));
    }
}
