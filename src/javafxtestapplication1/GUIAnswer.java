/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Gregor
 */
public class GUIAnswer {        // combines View and Controller
    HBox answerHBox;
    HBox correctHBox;
    HBox onChooseHBox;
    Label answerLabel;
    Label correctLabel;
    Label onChooseLabel;
    TextField answerTextField;
    TextField correctTextField;
    TextField onChooseTextField;
    VBox layoutVBox;
    TitledPane answerTitledPane;
    Accordion answerAccordion;
    Answer answerReference;
    
    public GUIAnswer(Answer answerReference)
    {
        this.answerReference = answerReference;
        instantiate();
        layout();
        bind();
    }
    
    public GUIAnswer(Answer answerReference, String textForAnswerTitledPane)
    {
        this.answerReference = answerReference;
        instantiate();
        layout(textForAnswerTitledPane);
        bind();
    }
    
    private void instantiate()
    {
        answerHBox = new HBox();
        correctHBox = new HBox();
        onChooseHBox = new HBox();
        answerLabel = new Label("answer: ");
        correctLabel = new Label("correct: ");
        onChooseLabel = new Label("onChoose: ");
        answerTextField = new TextField();
        correctTextField = new TextField();
        onChooseTextField = new TextField();
        layoutVBox = new VBox();
        answerTitledPane = new TitledPane();
        answerAccordion = new Accordion();
    }
    
    private void layout()
    {
        answerHBox.getChildren().addAll(answerLabel, answerTextField);
        correctHBox.getChildren().addAll(correctLabel, correctTextField);
        onChooseHBox.getChildren().addAll(onChooseLabel, onChooseTextField);
        layoutVBox.getChildren().addAll(answerHBox, correctHBox, onChooseHBox);
        answerTitledPane.setText("Bitte noch einfügen");
        answerTitledPane.setContent(layoutVBox);
        answerAccordion.getPanes().addAll(answerTitledPane);
    }
    
    private void layout(String textForAnswerTitledPane)
    {
        answerHBox.getChildren().addAll(answerLabel, answerTextField);
        correctHBox.getChildren().addAll(correctLabel, correctTextField);
        onChooseHBox.getChildren().addAll(onChooseLabel, onChooseTextField);
        layoutVBox.getChildren().addAll(answerHBox, correctHBox, onChooseHBox);
        answerTitledPane.setText(textForAnswerTitledPane);
        answerTitledPane.setContent(layoutVBox);
        answerAccordion.getPanes().addAll(answerTitledPane);
    }

    private void bind() {
        answerReference.answerProperty().bindBidirectional(answerTextField.textProperty());
        answerReference.correctProperty().bindBidirectional(correctTextField.textProperty());
        answerReference.onChooseProperty().bindBidirectional(onChooseTextField.textProperty());
    }
    
    public void setTextForAnswerTitledPane(String textForAnswerTitledPane)
    {
        answerTitledPane.setText(textForAnswerTitledPane);
    }
    
    public Accordion getLayout()
    {
        return this.answerAccordion;
    }
}
