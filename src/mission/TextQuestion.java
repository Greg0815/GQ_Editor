/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mission;

import mission.components.Simpleanswer;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
public class TextQuestion extends Mission
{
    private StringProperty question;
    private BooleanProperty loopUntilSuccess;
    private StringProperty prompt;
    private StringProperty replyOnCorrect;
    private StringProperty replyOnWrong;
    private ArrayList<Simpleanswer> simpleanswer;

    public TextQuestion()
    {
        super("TextQuestion");
        this.question = new SimpleStringProperty("");
        this.loopUntilSuccess = new SimpleBooleanProperty(true);
        this.prompt = new SimpleStringProperty("");
        this.replyOnCorrect = new SimpleStringProperty("");
        this.replyOnWrong = new SimpleStringProperty("");
        this.simpleanswer = new ArrayList<>();
        addNecessaryAndOptionalFields();
    }

    public Simpleanswer addSimpleanswer()
    {
        Simpleanswer newSimpleAnswer = new Simpleanswer();
        simpleanswer.add(newSimpleAnswer);
        return newSimpleAnswer;
    }

    public void addSimpleanswer(Simpleanswer answer)
    {
        simpleanswer.add(answer);
    }

    public StringProperty questionProperty()
    {
        return question;
    }

    public BooleanProperty loopUntilSuccessProperty()
    {
        return loopUntilSuccess;
    }

    public Boolean getLoopUntilSuccess()
    {
        return this.loopUntilSuccess.get();
    }

    public void setLoopUntilSuccess(Boolean loopUntilSuccess)
    {
        this.loopUntilSuccess.set(loopUntilSuccess);
    }

    public StringProperty promptProperty()
    {
        return prompt;
    }

    public StringProperty replyOnCorrectProperty()
    {
        return replyOnCorrect;
    }

    public StringProperty replyOnWrongProperty()
    {
        return replyOnWrong;
    }

//    @Override
//    public String createSpecificMissionHeader()
//    {
//        return utilitys.question(question.get()) + utilitys.loopUntilSuccess(loopUntilSuccess.get()) + utilitys.prompt(prompt.get()) + utilitys.replyOnCorrect(replyOnCorrect.get()) + utilitys.replyOnWrong(replyOnWrong.get());
//    }
    public String assembleAnswers()
    {
        return "<answers>" + utilitys.createStringFromArrayList(simpleanswer) + "</answers>";
    }

    @Override
    public String assemble()
    {
//        return createMissionHeader() + "<answers>" + utilitys.createStringFromArrayList(simpleanswer) + "</answers>" + buildMissionTail();
        return buildMissionHead() + assembleAnswers() + buildMissionTail();
    }

//    @Override
//    public Boolean isComplete()
//    {
//        Boolean isComplete = true;
//        return isComplete;
//    }
    @Override
    protected final void addNecessaryAndOptionalFields()
    {
        this.addNecessaryNonHeaderFields(getFieldByString("question"), getFieldByString("simpleanswer"));
        this.addOptionalHeaderFields(getFieldByString("loopUntilSuccess"), getFieldByString("prompt"), getFieldByString("replyOnCorrect"), getFieldByString("replyOnWrong"));
    }
}
