/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unused;

import Mission.Components.Answer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Gregor
 */
public class GUIMultipleChoiceQuestion
{
    VBox layoutVBox;
    HBox questiontextHBox;
    Label questiontextLabel;
    TextField questiontextTextField;
    Button addAnswer;
    Accordion multipleChoiceQuestionAccordion;      // Nutzen von dem Überdenken, rückgabe von VBOX reicht vermutlich
//    MultiChoiceQuestion multipleChoiceQuestionReference;
    
//    public GUIMultipleChoiceQuestion(MultiChoiceQuestion multipleChoiceQuestionReference)
//    {
//        this.multipleChoiceQuestionReference = multipleChoiceQuestionReference;
//        instantiate();
//        layout();
//        bind();
//    }

    private void instantiate()
    {
        this.layoutVBox = new VBox();
        this.questiontextHBox = new HBox();
        this.questiontextLabel = new Label("questiontext: ");
        this.questiontextTextField = new TextField();
        this.addAnswer = new Button("add Answer");
        this.multipleChoiceQuestionAccordion = new Accordion();
    }

    private void layout()
    {
        questiontextHBox.getChildren().addAll(questiontextLabel, questiontextTextField);
        layoutVBox.getChildren().addAll(questiontextHBox, addAnswer);
        multipleChoiceQuestionAccordion.getPanes().add(new TitledPane("MultipleChoiceQuestion", layoutVBox));
    }

    private void bind() {
        addAnswer.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Answer newAnswer = new Answer();
//                multipleChoiceQuestionReference.addAnswer(newAnswer);
//                GUIAnswer newGUIAnswerForMultipleChoiceQuestion = new GUIAnswer(newAnswer, "Antwort " + Integer.toString(multipleChoiceQuestionReference.getAnswerCount()));
//                layoutVBox.getChildren().addAll(newGUIAnswerForMultipleChoiceQuestion.getLayout());
            }
        });
//        multipleChoiceQuestionReference.questiontextProperty().bindBidirectional(questiontextTextField.textProperty());
    }

    public Accordion getLayout()
    {
        return multipleChoiceQuestionAccordion;
    }
}
