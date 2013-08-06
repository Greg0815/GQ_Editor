package main;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package Main;
//
//import Blocks.Block;
//import Blocks.BlockConnector;
//import Blocks.Description;
//import Blocks.LinearBlock;
//import Blocks.VariableBlock;
//import Mission.AudioRecord;
//import Mission.MapOverview;
//import Mission.MultipleChoiceQuestion;
//import Mission.NPCTalk;
//import Mission.QRTagReading;
//import Mission.Rules.Actions.ActionDecrementVariable;
//import Mission.Rules.Actions.ActionStartMission;
//import Mission.Rules.Conditions.ComplexConditionAnd;
//import Mission.Rules.Conditions.ComplexConditionIf;
//import Mission.Rules.Conditions.ComplexConditionOr;
//import Mission.Rules.Conditions.SimpleConditionComparison;
//import Mission.Rules.Conditions.SimpleConditionMissionState;
//import Mission.Rules.Rule;
//import Mission.StartAndExitScreen;
//import Mission.TextQuestion;
//import Mission.VideoPlay;
//import Mission.WebPage;
//import javafx.application.Application;
//import javafx.stage.Stage;
//import javafx.util.Pair;
//
///**
// *
// * @author Gregor
// */
//public class Main extends Application
//{
//
////        styleSwitcher.setOnAction(new EventHandler<ActionEvent>()
////        {
////            @Override
////            public void handle(ActionEvent t)
////            {
////                if (firstScene.getStylesheets().get(0).contains("controlStyle1.css")) {
////                    firstScene.getStylesheets().clear();
////                    firstScene.getStylesheets().add("Styles/controlStyle2.css");
////                }
////                else {
////                    firstScene.getStylesheets().clear();
////                    firstScene.getStylesheets().add("Styles/controlStyle1.css");
////                }
////            }
////        });
////
//////        showHideFields.setOnAction(new EventHandler<ActionEvent>()
//////        {
//////            @Override
//////            public void handle(ActionEvent t)
//////            {
//////                uiBuilder.switchVisibilityOfHiddenNodes();
//////            }
//////        });
////
////
//////        deleteIconView.setOnMouseClicked(new EventHandler<MouseEvent>()
//////        {
//////            @Override
//////            public void handle(MouseEvent mouseEvent)
//////            {
//////                mouseEvent.consume();
//////                deleteIconView.fireEvent(new DeleteMissionEvent(titledPaneForDeletion));
//////            }
//////        });
////
////        rootNodeVBox.addEventHandler(DeleteMissionEvent.TITLED_PANE_DELETION, new EventHandler<DeleteMissionEvent>()
////        {
////            @Override
////            public void handle(DeleteMissionEvent t)
////            {
////                if (rootNodeVBox.getChildren().contains(t.getToBeDeletedTitledPane())) {
////                    System.out.println("ES KLAPPT!");
////                }
////                rootNodeVBox.getChildren().remove(t.getToBeDeletedTitledPane());
////                t.consume();
////            }
////        });
//////        rootNodeVBox.addEventHandler(DeleteMissionEvent.TITLED_PANE_DELETION, new DeleteMissionEventHandler());
////
//////        deleteIconView.setOnMouseClicked(new EventHandler<MouseEvent>()
//////        {
//////            @Override
//////            public void handle(MouseEvent event)
//////            {
//////                rootNodeVBox.getChildren().remove(titledPaneForDeletion);
//////            }
//////        });
////
//////        deleteIconView.setOnMouseClicked(new EventHandler<MouseEvent>()
//////        {
//////            @Override
//////            public void handle(MouseEvent t)
//////            {
//////                rootNodeVBox.getChildren().remove(titledPaneForDeletion);
//////            }
//////
//////        });
////
////    }
//
//    
//    private Description minimalExample()
//    {
//        Description description = new Description("Minimum");
//        
//        VariableBlock varBlo = new VariableBlock("VarBlo");
//        
//        LinearBlock linBlo = new LinearBlock("LinBlo");
//        
//        varBlo.addMissionTypes(NPCTalk.class);
//        Rule successRule = new Rule();
//        ComplexConditionIf ifCond = new ComplexConditionIf();
//        ComplexConditionAnd andCond = new ComplexConditionAnd();
//        SimpleConditionMissionState ms1 = new SimpleConditionMissionState("Fahrrad", "new");
//        SimpleConditionMissionState ms2 = new SimpleConditionMissionState("Bus", "running");
//        andCond.addCondition(ms1);
//        andCond.addCondition(ms2);
//        ifCond.addCondition(andCond);
//        successRule.addCondition(ifCond);
//        ActionDecrementVariable decVarAction = new ActionDecrementVariable("count");
//        successRule.addAction(decVarAction);
//        varBlo.addSuccessRule(successRule);
////        varBlo.addBeginRule(new Rule(description, actions))
//        
//        linBlo.addMissionTypesAsLinearSequence(MultipleChoiceQuestion.class);
//        linBlo.addSuccessRule(successRule);
//        
//        BlockConnector varToLin = new BlockConnector("onFail", varBlo, linBlo);
//        
//        description.addBlock(varBlo, linBlo);
//        description.addBlockConnector(varToLin);
//        
//        return description;
//    }
//    
//    @Override
//    public void start(final Stage primaryStage)
//    {
//        NPCTalk tmp = new NPCTalk();
//        tmp.setId("och noe");
//        tmp.setCancel("böböböböööö");
//        tmp.setEndbuttontext("ende");
//        tmp.setImage("c:\\lustigesbild.bmp");
//        tmp.setNextdialogbuttontext("weitah");
//        tmp.setTextsize("groß");
//        System.out.println(tmp.assemble());
////        AudioRecord tmp = new AudioRecord();
////        tmp.setId("katze");
////        tmp.setFile("c:\\nix.jpg");
////        tmp.setTask("Geh Zähne putzen!");
////        Trigger trig = new Trigger("onFail");
////        Rule rule = new Rule();
////        ActionDecrementVariable act = new ActionDecrementVariable("pups");
////        rule.addAction(act);
////        trig.addRule(rule);
////        tmp.addTrigger(trig);
////        System.out.println(tmp.assemble());
//        EditorOberflaeche editor = new EditorOberflaeche(primaryStage, new Pair("OpaEnkel", opaEnkel()), new Pair("neue Modelle", newContainerModels()), new Pair("minEx", minimalExample()));
//        primaryStage.setScene(editor.makeGameSelectionOberflaeche());
//
//        primaryStage.show();
//    }
//
//    public static void main(String[] args)
//    {
//        launch(args);
//    }
//}
