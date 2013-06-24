/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Blocks.Block;
import Blocks.BlockConnector;
import Blocks.Description;
import Blocks.LinearBlock;
import Blocks.VariableBlock;
import Main.AssembleInterface.DISPLAYABLE;
import Mission.AudioRecord;
import Mission.MapOverview;
import Mission.MultipleChoiceQuestion;
import Mission.NPCTalk;
import Mission.QRTagReading;
import Mission.Rules.Actions.ActionDecrementVariable;
import Mission.Rules.Actions.ActionStartMission;
import Mission.Rules.Conditions.ComplexConditionAnd;
import Mission.Rules.Conditions.ComplexConditionIf;
import Mission.Rules.Conditions.ComplexConditionOr;
import Mission.Rules.Conditions.SimpleConditionComparison;
import Mission.Rules.Conditions.SimpleConditionMissionState;
import Mission.Rules.Rule;
import Mission.StartAndExitScreen;
import Mission.TextQuestion;
import Mission.VideoPlay;
import Mission.WebPage;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Gregor
 */
public class Main extends Application
{

//        styleSwitcher.setOnAction(new EventHandler<ActionEvent>()
//        {
//            @Override
//            public void handle(ActionEvent t)
//            {
//                if (firstScene.getStylesheets().get(0).contains("controlStyle1.css")) {
//                    firstScene.getStylesheets().clear();
//                    firstScene.getStylesheets().add("Styles/controlStyle2.css");
//                }
//                else {
//                    firstScene.getStylesheets().clear();
//                    firstScene.getStylesheets().add("Styles/controlStyle1.css");
//                }
//            }
//        });
//
////        showHideFields.setOnAction(new EventHandler<ActionEvent>()
////        {
////            @Override
////            public void handle(ActionEvent t)
////            {
////                uiBuilder.switchVisibilityOfHiddenNodes();
////            }
////        });
//
//
////        deleteIconView.setOnMouseClicked(new EventHandler<MouseEvent>()
////        {
////            @Override
////            public void handle(MouseEvent mouseEvent)
////            {
////                mouseEvent.consume();
////                deleteIconView.fireEvent(new DeleteMissionEvent(titledPaneForDeletion));
////            }
////        });
//
//        rootNodeVBox.addEventHandler(DeleteMissionEvent.TITLED_PANE_DELETION, new EventHandler<DeleteMissionEvent>()
//        {
//            @Override
//            public void handle(DeleteMissionEvent t)
//            {
//                if (rootNodeVBox.getChildren().contains(t.getToBeDeletedTitledPane())) {
//                    System.out.println("ES KLAPPT!");
//                }
//                rootNodeVBox.getChildren().remove(t.getToBeDeletedTitledPane());
//                t.consume();
//            }
//        });
////        rootNodeVBox.addEventHandler(DeleteMissionEvent.TITLED_PANE_DELETION, new DeleteMissionEventHandler());
//
////        deleteIconView.setOnMouseClicked(new EventHandler<MouseEvent>()
////        {
////            @Override
////            public void handle(MouseEvent event)
////            {
////                rootNodeVBox.getChildren().remove(titledPaneForDeletion);
////            }
////        });
//
////        deleteIconView.setOnMouseClicked(new EventHandler<MouseEvent>()
////        {
////            @Override
////            public void handle(MouseEvent t)
////            {
////                rootNodeVBox.getChildren().remove(titledPaneForDeletion);
////            }
////
////        });
//
//    }

    
    private Description minimalExample()
    {
        Description description = new Description("Minimum");
        description.addDisplayableFields(DISPLAYABLE.IMAGE, DISPLAYABLE.SHUFFLE);
        
        VariableBlock varBlo = new VariableBlock("VarBlo");
        
        LinearBlock linBlo = new LinearBlock("LinBlo");
        
        varBlo.addMissionTypes(NPCTalk.class);
        Rule successRule = new Rule();
        ComplexConditionIf ifCond = new ComplexConditionIf();
        ComplexConditionAnd andCond = new ComplexConditionAnd();
        SimpleConditionMissionState ms1 = new SimpleConditionMissionState("Fahrrad", "new");
        SimpleConditionMissionState ms2 = new SimpleConditionMissionState("Bus", "running");
        andCond.addCondition(ms1);
        andCond.addCondition(ms2);
        ifCond.addCondition(andCond);
        successRule.addCondition(ifCond);
        ActionDecrementVariable decVarAction = new ActionDecrementVariable("count");
        successRule.addAction(decVarAction);
        varBlo.addSuccessRule(successRule);
//        varBlo.addBeginRule(new Rule(description, actions))
        
        linBlo.addMissionTypesAsLinearSequence(MultipleChoiceQuestion.class);
        linBlo.addSuccessRule(successRule);
        
        BlockConnector varToLin = new BlockConnector("onFail", varBlo, linBlo);
        
        description.addBlock(varBlo, linBlo);
        description.addBlockConnector(varToLin);
        
        return description;
    }
    
    private Description newContainerModels()
    {
        Description description = new Description("TestZweck");
        
        VariableBlock variableBlock1 = new VariableBlock("VariabelEins", -1, 3, "onSuccess");
        VariableBlock variableBlock2 = new VariableBlock("VariableZwei", 2, "onFail");
        VariableBlock variableBlock3 = new VariableBlock("VariableDrei");
        
        LinearBlock linearBlock1 = new LinearBlock("LinearEins", 2, 5, "onBegin");
        LinearBlock linearBlock2 = new LinearBlock("LinearZwei", 4, "onLeave");
        LinearBlock linearBlock3 = new LinearBlock("LinearDrei");
        
        variableBlock1.addMissionTypes(AudioRecord.class, MultipleChoiceQuestion.class, QRTagReading.class, WebPage.class);
        variableBlock2.addMissionTypes(VideoPlay.class, NPCTalk.class);
        variableBlock3.addMissionTypes(StartAndExitScreen.class);
        
        linearBlock1.addMissionTypesAsLinearSequence(StartAndExitScreen.class, NPCTalk.class, NPCTalk.class);
        linearBlock2.addMissionTypesAsLinearSequence(StartAndExitScreen.class, TextQuestion.class, MapOverview.class);
        linearBlock3.addMissionTypesAsLinearSequence(NPCTalk.class, QRTagReading.class);
        
        BlockConnector V1ToV2 = new BlockConnector("onEnd", variableBlock1, variableBlock2);
        BlockConnector V2ToV3 = new BlockConnector("onSuccess", variableBlock2, variableBlock3);
        BlockConnector V3ToL1 = new BlockConnector("onFail", variableBlock3, linearBlock1);
        BlockConnector L1ToL2 = new BlockConnector("onEnter", linearBlock1, linearBlock2);
        BlockConnector L2ToL3 = new BlockConnector("onBegin", linearBlock2, linearBlock3);
        
        description.addBlock(variableBlock1, variableBlock2, variableBlock3, linearBlock1, linearBlock2, linearBlock3);
        description.addBlockConnector(V1ToV2, V2ToV3, V3ToL1, L1ToL2, L2ToL3);
        
        return description;
    }
    
    private Description opaEnkel(){
        
        Description opaEnkel = new Description("Opa Enkel App");
        
        LinearBlock block0 = new LinearBlock("Willkommen", 2, "onEnd");
        LinearBlock block1 = new LinearBlock("Spiel", 5, "onEnd");
        LinearBlock block2 = new LinearBlock("MultipleChoice", 1, "onEnd");
        LinearBlock block3 = new LinearBlock("ImageCapture", 3, "onEnd"); 
        LinearBlock block4 = new LinearBlock("Abschluss", 2, "onEnd");
        
        block0.addMissionTypesAsLinearSequence(StartAndExitScreen.class, NPCTalk.class);
        block1.addMissionTypesAsLinearSequence(NPCTalk.class, VideoPlay.class, NPCTalk.class, NPCTalk.class, NPCTalk.class);
        block2.addMissionTypesAsLinearSequence(MultipleChoiceQuestion.class);
        block3.addMissionTypesAsLinearSequence(StartAndExitScreen.class);  // Hier müsste die Mission ImageCapture rein
        block4.addMissionTypesAsLinearSequence(NPCTalk.class, AudioRecord.class);
        
        BlockConnector willkommenZuSpiel = new BlockConnector("onEnd", block0, block1);
        BlockConnector spielZuMultipleChoice = new BlockConnector("onEnd", block1, block2);
        block2.addEndRule(opaEnkelSplitRuleZuBlock2(block3));
        block2.addEndRule(opaEnkelSplitRuleZuBlock3(block4));
        BlockConnector imageCaptureZuAbschluss = new BlockConnector("onEnd", block3, block4);
        
        opaEnkel.addBlock(block0, block1, block2, block3, block4);
        opaEnkel.addBlockConnector(willkommenZuSpiel, spielZuMultipleChoice, imageCaptureZuAbschluss);
        
        return opaEnkel;
    }
    
    private Rule opaEnkelSplitRuleZuBlock3(Block block3)
    {
        SimpleConditionComparison comp4 = new SimpleConditionComparison("eq", "var", "select", "num", "4");
        ComplexConditionIf ifCondition = new ComplexConditionIf();
        Rule onEndRule2 = new Rule();
        ActionStartMission startMissionBlock3 = new ActionStartMission(block3);
        
        onEndRule2.addCondition(ifCondition);
        ifCondition.addCondition(comp4);
        onEndRule2.addAction(startMissionBlock3);
        
        return onEndRule2;
    }
    
    private Rule opaEnkelSplitRuleZuBlock2(Block block2)
    {
        SimpleConditionComparison comp1 = new SimpleConditionComparison("eq", "var", "select", "num", "1");
        SimpleConditionComparison comp2 = new SimpleConditionComparison("eq", "var", "select", "num", "2");
        SimpleConditionComparison comp3 = new SimpleConditionComparison("eq", "var", "select", "num", "3");
        ComplexConditionOr orCondition = new ComplexConditionOr();
        ComplexConditionIf ifCondition = new ComplexConditionIf();
        Rule onEndRule1 = new Rule();
        ActionStartMission startMissionBlock2 = new ActionStartMission(block2);
        onEndRule1.addCondition(ifCondition);
        ifCondition.addCondition(orCondition);
        orCondition.addCondition(comp1);
        orCondition.addCondition(comp2);
        orCondition.addCondition(comp3);
        onEndRule1.addAction(startMissionBlock2);
        
        return onEndRule1;
    }


    @Override
    public void start(final Stage primaryStage) throws NoSuchFieldException, TransformerConfigurationException, TransformerException
    {
        EditorOberflaeche editor = new EditorOberflaeche(primaryStage, new Pair("OpaEnkel", opaEnkel()), new Pair("neue Modelle", newContainerModels()), new Pair("minEx", minimalExample()));
        primaryStage.setScene(editor.makeGameSelectionOberflaeche());

        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
