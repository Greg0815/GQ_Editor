/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Gregor
 */
public class GUIGameWerWirdMillionaer
{
    Stage wwmStage;
    Scene wwmScene;
    VBox layoutVBox;
    HBox nameHBox;
    Label nameLabel;
    TextField nameTextField;
    Button addMissionMultipleChoiceQuestionButton;
    Accordion wwmMissionsAccordion;
    GameWerWirdMillionaer wwmReference;
    
    public GUIGameWerWirdMillionaer(GameWerWirdMillionaer wwmReference)
    {
        this.wwmReference = wwmReference;
        instantiate();
        layout();
        bind();
    }
    
    private void instantiate()
    {
        nameHBox = new HBox();
        nameLabel = new Label("name");
        nameTextField = new TextField();
        addMissionMultipleChoiceQuestionButton = new Button("add Multiple Choice Question Mission");
        layoutVBox = new VBox();
        wwmMissionsAccordion = new Accordion();
        wwmScene = new Scene(layoutVBox, 500, 500);
        wwmStage = new Stage();
        wwmStage.initModality(Modality.APPLICATION_MODAL);
        wwmStage.setTitle("Wer wird Millionaer?");
        wwmStage.setScene(wwmScene);
        wwmStage.show();
    }
    
    private void layout()
    {
        nameHBox.getChildren().addAll(nameLabel, nameTextField);
        wwmMissionsAccordion.getPanes().addAll(new GUIMissionStartAndExitScreen(wwmReference.getBeginningMission()).getLayout(),
                                                new GUIMissionMultipleChoiceQuestion(wwmReference.getMultipleChoiceQuestionMission(0)).getLayout(),
                                                new GUIMissionStartAndExitScreen(wwmReference.getEndingMission()).getLayout());
        layoutVBox.getChildren().addAll(nameHBox, addMissionMultipleChoiceQuestionButton, wwmMissionsAccordion);
    }
    
    private void bind()
    {
        wwmReference.nameProperty().bindBidirectional(nameTextField.textProperty());
        addMissionMultipleChoiceQuestionButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                MissionMultipleChoiceQuestion tmp = new MissionMultipleChoiceQuestion();
                GUIMissionMultipleChoiceQuestion tmp2 = new GUIMissionMultipleChoiceQuestion(tmp);
                wwmReference.addMissionMultipleChoiceQuestion(tmp);
                wwmMissionsAccordion.getPanes().add(wwmMissionsAccordion.getPanes().size()-1, tmp2.getLayout());
            }
        });
    }
}
