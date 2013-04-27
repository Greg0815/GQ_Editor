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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    int missionsCount;
    
    public GUIGameWerWirdMillionaer(GameWerWirdMillionaer wwmReference)
    {
        this.wwmReference = wwmReference;
        instantiate();
        layout();
        bind();
    }
    
    private void instantiate()
    {
        missionsCount = 0;
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
        
        GUIMissionStartAndExitScreen openingMission = new GUIMissionStartAndExitScreen(wwmReference.getBeginningMission(), "Opening Mission");
        GUIMissionMultipleChoiceQuestion firstMCQ = new GUIMissionMultipleChoiceQuestion(wwmReference.getMultipleChoiceQuestionMission(missionsCount++), wwmReference.getMultipleChoiceQuestionMissions().size());
//        firstMCQ.addHandlerToDeleteButton(makeDeleteButton());
//        firstMCQ.setID("mcq" + missionsCount);
        GUIMissionStartAndExitScreen endingMission = new GUIMissionStartAndExitScreen(wwmReference.getEndingMission(), "Ending Mission");
        
        wwmMissionsAccordion.getPanes().addAll(openingMission.getLayout(), firstMCQ.getLayout(), endingMission.getLayout());
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
                wwmReference.addMissionMultipleChoiceQuestion(tmp);
                GUIMissionMultipleChoiceQuestion tmp2 = new GUIMissionMultipleChoiceQuestion(tmp, ++missionsCount);
//                tmp2.addHandlerToDeleteButton();
//                tmp2.setID("mcqTitledPane" + missionsCount);
                wwmMissionsAccordion.getPanes().add(wwmMissionsAccordion.getPanes().size()-1, tmp2.getLayout());
            }
        });
        
        wwmMissionsAccordion.addEventHandler(DeleteMissionEvent.TITLED_PANE_DELETION, new EventHandler<DeleteMissionEvent>()
        {
            @Override
            public void handle(DeleteMissionEvent event)
            {
//                System.out.println("size: " + wwmMissionsAccordion.getPanes().size());
//                System.out.println("indexof: " + wwmMissionsAccordion.getPanes().indexOf(event.getToBeDeletedTitledPane()));
                if(wwmMissionsAccordion.getPanes().size() > 3)
                {
                    wwmReference.deleteMissionMultipleChoiceQuestion(event.getToBeDeletedMission());
                    wwmMissionsAccordion.getPanes().remove(event.getToBeDeletedTitledPane());
                    event.consume();
                }
            }
        });
    }
    
    private EventHandler deleteMission()
    {
        EventHandler deletor = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
//                ((Button)t.getSource()).
            }
        };
        return deletor;
    }
    
//    private Button makeDeleteButton()
//    {
//        final Button delete = new Button();
//        delete.setId("mcqButton" + missionsCount);
//        ImageView deleteIcon = new ImageView(new Image("images/delete-icon.png"));
//        deleteIcon.setFitHeight(20.0);
//        deleteIcon.setFitWidth(20.0);
//        delete.setGraphic(deleteIcon);
//        delete.setOnAction(new EventHandler<ActionEvent>()
//        {
//            @Override
//            public void handle(ActionEvent event)
//            {
//                String deletionID = delete.getId().substring(0, 3) + "TitledPane" + delete.getId().substring(delete.getId().length()-1, delete.getId().length());
//                System.out.println(deletionID + "\n");
////                wwmReference.deleteMissionMultipleChoiceQuestion();
//            }
//        });
//        return delete;
//    }
}
