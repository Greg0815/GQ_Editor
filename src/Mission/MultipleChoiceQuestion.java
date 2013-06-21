/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission;

import static Main.AssembleInterface.utilitys;
import Mission.Components.Answer;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
        loopUntilSuccess = new SimpleBooleanProperty();
        shuffle = new SimpleBooleanProperty();
        questiontext = new SimpleStringProperty();
        answer = new ArrayList<>();
        addNecessaryField("questiontext", "answer");
        addCompletenessVariable(questiontext);
        addCompletenessVariable(answer);
    }

    public BooleanProperty loopUntilSuccessProperty()
    {
        return loopUntilSuccess;
    }

    public Boolean getLoopUntilSuccess()
    {
        return loopUntilSuccess.get();
    }

    public void setLoopUntilSuccess(Boolean loopUntilSuccess)
    {
        this.loopUntilSuccess.set(loopUntilSuccess);
    }

    public BooleanProperty shuffleProperty()
    {
        return shuffle;
    }

    public Boolean getShuffle()
    {
        return shuffle.get();
    }

    public void setShuffle(Boolean shuffle)
    {
        this.shuffle.set(shuffle);
    }

    public StringProperty questiontextProperty()
    {
        return questiontext;
    }

    public String getQuestiontext()
    {
        return questiontext.get();
    }

    public void setQuestiontext(String questiontext)
    {
        this.questiontext.set(questiontext);
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
        return "<question><questiontext>" + this.questiontext.get() + "</questiontext>" + utilitys.createStringFromArrayList(this.answer) + "</question>";
    }

    @Override
    public String assemble()
    {
        return createMissionHeader() + utilitys.createStringFromArrayList(this.getTriggers()) + assembleQuestionAndAnswer() + createMissionTrailer();
    }

    @Override
    public String createSpecificMissionHeader()
    {
        return utilitys.loopUntilSuccess(loopUntilSuccess.get()) + utilitys.shuffle(shuffle.get());
    }
}
