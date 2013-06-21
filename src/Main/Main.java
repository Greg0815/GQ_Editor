/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Containers.Block;
import Containers.BlockConnector;
import Containers.BlockConnector2;
import Containers.BlockToBeReplaced;
import Containers.Description;
import Containers.Description2;
import Containers.Game2;
import Containers.LinearBlock;
import Containers.ModelCreator2;
import Containers.TriggerAction;
import Containers.UIBuilder;
import Containers.VariableBlock;
import Mission.AudioRecord;
import Mission.MapOverview;
import Mission.MultipleChoiceQuestion;
import Mission.NPCTalk;
import Mission.QRTagReading;
import Mission.Rules.ActionDecrementVariable;
import Mission.Rules.ActionStartMission;
import Mission.Rules.ComplexConditionAnd;
import Mission.Rules.ComplexConditionIf;
import Mission.Rules.ComplexConditionOr;
import Mission.Rules.Rule;
import Mission.Rules.SimpleConditionComparison;
import Mission.Rules.SimpleConditionMissionState;
import Mission.StartAndExitScreen;
import Mission.TextQuestion;
import Mission.VideoPlay;
import Mission.WebPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
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

//    Game testGame;
////    OldUIBuilder uiBuilder;
//    VBox rootNodeVBox;
//    Label output;
//    Scene firstScene;
//    Button assembleWWMButton;
//    Button styleSwitcher;
//    Button showHideFields;
//    TextArea outputTextArea;
//
//    private void initialize()
//    {
//        rootNodeVBox = new VBox();
//        firstScene = new Scene(rootNodeVBox, 600, 800);
//        firstScene.getStylesheets().add("Styles/controlStyle1.css");
//        outputTextArea = new TextArea();
//        assembleWWMButton = new Button("assemble WWM");
//        styleSwitcher = new Button("Switch Style");
//        showHideFields = new Button("Show/Hide Fields");
//    }
//
//    private void layout()
//    {
//        rootNodeVBox.getChildren().addAll(styleSwitcher, assembleWWMButton, showHideFields);
//    }
//
//    private void bind()
//    {
//        BooleanStringConverter converter = new BooleanStringConverter();
//
//        assembleWWMButton.setOnAction(new EventHandler<ActionEvent>()
//        {
//            @Override
//            public void handle(ActionEvent event)
//            {
//                if (!rootNodeVBox.getChildren().contains(outputTextArea)) {
//                    rootNodeVBox.getChildren().add(outputTextArea);
//                }
//                outputTextArea.setText(testGame.assemble());
//            }
//        });
//
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

//    private void simpleTest1()
//    {
//        try {
//            // Missions
//            MultipleChoiceQuestion mmcq = new MultipleChoiceQuestion();
//            StartAndExitScreen msaes = new StartAndExitScreen();
//            AudioRecord mar = new AudioRecord();
//            MapOverview mmo = new MapOverview();
//            NPCTalk mnpct = new NPCTalk();
//            QRTagReading mqrtr = new QRTagReading();
//            TextQuestion mtq = new TextQuestion();
//            VideoPlay mvp = new VideoPlay();
//            WebPage mwp = new WebPage();
//
//            // Lists
////            LinearList firstList = new LinearList();
////            LinearList secondList = new LinearList();
//
//            testGame = new Game();
//
//            // Filling Lists
////            firstList.addGameObject(msaes, mar, mmo);
////            secondList.addGameObject(mnpct, mqrtr, mtq);
////            testGame.add(mmcq, firstList, secondList, mvp, mwp);
//
//            // Connecting Missions
//            FromToTriggerAction ruling1 = new FromToTriggerAction(0, 1, "onEnd", "StartMission");
//            FromToTriggerAction ruling2 = new FromToTriggerAction(1, 2, "onEnd", "StartMission");
//            FromToTriggerAction ruling3 = new FromToTriggerAction(2, 3, "onEnd", "StartMission");
//            FromToTriggerAction ruling4 = new FromToTriggerAction(3, 4, "onEnd", "StartMission");
////            testGame.addRule(ruling1, ruling2, ruling3, ruling4);
//
////            uiBuilder = new OldUIBuilder(testGame);
//
//            // Default Values
//            Pair<String, Integer> durationDefault = new Pair("duration", 42);
//            Pair<String, Boolean> loopUntilSuccessDefault = new Pair("loopUntilSuccess", true);
//            Pair<String, String> cancelDefault = new Pair("cancel", "false");
//            Pair<String, Boolean> shuffleDefault = new Pair("shuffle", true);
//
////            uiBuilder.addDefault(durationDefault, loopUntilSuccessDefault, cancelDefault, shuffleDefault);
////
////            rootNodeVBox.getChildren().add(uiBuilder.makeForm());
//        }
//        catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    private void simpleTest2() throws NoSuchFieldException, TransformerConfigurationException, TransformerException
//    {
//        testGame = new Game();
////        LinearList innerList = new LinearList();
////        innerList.setMinAndMax(2, 15);
//        MultipleChoiceQuestion mcq1 = new MultipleChoiceQuestion();
//        mcq1.setId("MCQ1");
//        MultipleChoiceQuestion mcq2 = new MultipleChoiceQuestion();
//        mcq2.setId("MCQ2");
//        MultipleChoiceQuestion mcq3 = new MultipleChoiceQuestion();
//        mcq3.setId("MCQ3");
////        innerList.addGameObject(mcq1, mcq2, mcq3);
//        StartAndExitScreen startSAES = new StartAndExitScreen();
//        startSAES.setId("Start SAES");
//        startSAES.setDuration(5000);
//        startSAES.setCancel("false");
//        StartAndExitScreen failSAES = new StartAndExitScreen();
//        failSAES.setId("Fail SAES");
//        StartAndExitScreen successSAES = new StartAndExitScreen();
//        successSAES.setId("Success SAES");
////        testGame.add(startSAES, innerList, failSAES, successSAES);
//
//
//        try {
//            FromToTriggerAction startToMCQ = new FromToTriggerAction(0, 1, "onEnd", "StartMission");
//            FromToTriggerAction listToFail = new FromToTriggerAction(1, 2, "onFail", "StartMission");
//            FromToTriggerAction listToSuccess = new FromToTriggerAction(1, 3, "onSuccess", "StartMission");
////            testGame.addRule(startToMCQ, listToFail, listToSuccess);
//        }
//        catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
////        uiBuilder = new OldUIBuilder(testGame);
//
//        Source xmlInput = new StreamSource(new StringReader(testGame.assemble()));
//        StreamResult xmlOutput = new StreamResult(new StringWriter());
//
//        Transformer transformer = TransformerFactory.newInstance().newTransformer();
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//        transformer.transform(xmlInput, xmlOutput);
////        System.out.println(xmlOutput.getWriter().toString());
//
//
//        Pair<String, Integer> durationDefault = new Pair("duration", 42);
//        Pair<String, Boolean> loopUntilSuccessDefault = new Pair("loopUntilSuccess", true);
//        Pair<String, String> cancelDefault = new Pair("cancel", "false");
//        Pair<String, Boolean> shuffleDefault = new Pair("shuffle", true);
//
////        uiBuilder.addDefault(durationDefault, loopUntilSuccessDefault, cancelDefault, shuffleDefault);
//
////        try {
//////            rootNodeVBox.getChildren().addGameObject(saes.makeForm());
//////            rootNodeVBox.getChildren().addGameObject(mcq.makeForm());
//////            rootNodeVBox.getChildren().addGameObject(npctalk.makeForm());
//////            rootNodeVBox.getChildren().add(wwm.makeForm());
//////            rootNodeVBox.getChildren().add(newGame.makeForm());
//////            System.out.println(wwmGame.getMissionMultipleChoiceQuestionCount());
////            //        rootNodeVBox.getChildren().addGameObject(uiBuilder.makeMissionForm());
//////        } catch ( IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex)
////        }
////        catch (IllegalArgumentException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
////            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
////        }
//    }
    
    private Description2 minimalExample()
    {
        Description2 description = new Description2("Minimum");
        
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
        
        BlockConnector2 varToLin = new BlockConnector2("onFail", varBlo, linBlo);
        
        description.addBlock(varBlo, linBlo);
        description.addBlockConnector(varToLin);
        
        return description;
    }
    
    private Description2 newContainerModels()
    {
        Description2 description = new Description2("TestZweck");
        
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
        
        BlockConnector2 V1ToV2 = new BlockConnector2("onEnd", variableBlock1, variableBlock2);
        BlockConnector2 V2ToV3 = new BlockConnector2("onSuccess", variableBlock2, variableBlock3);
        BlockConnector2 V3ToL1 = new BlockConnector2("onFail", variableBlock3, linearBlock1);
        BlockConnector2 L1ToL2 = new BlockConnector2("onEnter", linearBlock1, linearBlock2);
        BlockConnector2 L2ToL3 = new BlockConnector2("onBegin", linearBlock2, linearBlock3);
        
        description.addBlock(variableBlock1, variableBlock2, variableBlock3, linearBlock1, linearBlock2, linearBlock3);
        description.addBlockConnector(V1ToV2, V2ToV3, V3ToL1, L1ToL2, L2ToL3);
        
        return description;
    }
    
    private Description2 opaEnkel(){
        
        Description2 opaEnkel = new Description2("Opa Enkel App");
        
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
        
        BlockConnector2 willkommenZuSpiel = new BlockConnector2("onEnd", block0, block1);
        BlockConnector2 spielZuMultipleChoice = new BlockConnector2("onEnd", block1, block2);
        block2.addEndRule(opaEnkelSplitRuleZuBlock2(block3));
        block2.addEndRule(opaEnkelSplitRuleZuBlock3(block4));
        BlockConnector2 imageCaptureZuAbschluss = new BlockConnector2("onEnd", block3, block4);
        
        opaEnkel.addBlock(block0, block1, block2, block3, block4);
        opaEnkel.addBlockConnector(willkommenZuSpiel, spielZuMultipleChoice, imageCaptureZuAbschluss);
        
        return opaEnkel;
    }
    
    private Rule opaEnkelSplitRuleZuBlock3(Block block3)
    {
        /*
                rule
                        if
                                eq var select num 4
                        /if
                /rule
                action start mission block 3
         */
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
        /*
                rule
                        if
                                or
                                        eq var select num 1
                                        eq var select num 2
                                        eq var select num 3
                                /or
                        /if
                /rule
                action start mission block 2
         */
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

//    private Description wwmDescription()
//    {
//        Description wwmGameDescription = new Description("Wer wird Millionär - Beantworte 15 Fragen richtig und gewinne eine virtuelle Million!");
//        BlockToBeReplaced failBlock = new BlockToBeReplaced("fail");
//        failBlock.addMissionTypes("StartAndExitScreen");
//        BlockToBeReplaced openingBlock = new BlockToBeReplaced("opening");
//        openingBlock.addMissionTypes("StartAndExitScreen");
//        openingBlock.addTriggerActionParameter(new TriggerAction("onStart", "vibrate", "5000"), new TriggerAction("onStart", "incrementVariable", "temp"));
//        openingBlock.addTriggerActionParameter(new TriggerAction("onLeave", "startMission", failBlock));        // testzweck: TriggerAction verhalten mit block parameter
//        BlockToBeReplaced questionBlock = new BlockToBeReplaced("questions", 15);
//        questionBlock.addMissionTypes("MultipleChoiceQuestion", "TextQuestion");
//        questionBlock.setInternalConnectorToOnSuccess();
//
//        BlockToBeReplaced successBlock = new BlockToBeReplaced("success");
//        successBlock.addMissionTypes("NPCTalk");
//
//
//        wwmGameDescription.addBlock(openingBlock, questionBlock, successBlock, failBlock);
//        BlockConnector openingToQuestion = new BlockConnector("onEnd", openingBlock, questionBlock);
//        BlockConnector questionToSuccess = new BlockConnector("onSuccess", questionBlock, successBlock);
//        BlockConnector questionToFail = new BlockConnector("onFail", questionBlock, failBlock);
//        wwmGameDescription.addBlockConnector(openingToQuestion, questionToFail, questionToSuccess);
//        return wwmGameDescription;
//    }
    
//    private Game2 buildUI(Description2 description, Stage primaryStage)
//    {
//        VBox rootNode = new VBox();
//        Scene secondaryScene = new Scene(rootNode, 800, 600);
//        ModelCreator2 model = new ModelCreator2(description);
//        Game2 game = model.createModel();
//        UIBuilder newUI = new UIBuilder(game);
//        rootNode.getChildren().add(newUI.parseGame());
//        primaryStage.setScene(secondaryScene);
//        primaryStage.show();
//        return game;
//    }
    
//    private Scene editorOberfläche(final Stage primaryStage)
//    {
//        final Game currentGame;
//        VBox sceneRootVBox = new VBox();
//        Scene editorScene = new Scene(sceneRootVBox, 800, 600);
//        final ChoiceBox choiceBox = new ChoiceBox();
//        Button makeNewGame = new Button("Make selected Game");
////        makeNewGame.setOnAction(new EventHandler<ActionEvent>(){
////            public void handle(ActionEvent t)
////            {
////                currentGame = buildUI((Description)choiceBox.getSelectionModel().getSelectedItem(), primaryStage);
////            }
////                
////        });
////        ArrayList<Description> descriptions = new ArrayList<>();
////        descriptions.add(FXCollections.observableArrayList(wwmDescription()));
//        choiceBox.setItems(FXCollections.observableArrayList(wwmDescription()));
//        return editorScene;
//    }

    @Override
    public void start(final Stage primaryStage) throws NoSuchFieldException, TransformerConfigurationException, TransformerException
    {
        EditorOberflaeche2 editor = new EditorOberflaeche2(primaryStage, new Pair("OpaEnkel", opaEnkel()), new Pair("neue Modelle", newContainerModels()), new Pair("minEx", minimalExample()));
        primaryStage.setScene(editor.makeGameSelectionOberflaeche());
//        Game tmp = buildUI(wwmDescription(), primaryStage);
//        initialize();
//        layout();
//        bind();
//
//        simpleTest1();
//        wwmDescription();
//        primaryStage.setScene(firstScene);
//        primaryStage.setTitle("GeoQuest Spiele-Editor");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
