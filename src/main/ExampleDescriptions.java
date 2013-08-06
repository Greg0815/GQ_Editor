/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import blocks.BlockConnector;
import blocks.Description;
import blocks.LinearBlock;
import blocks.VariableBlock;
import mission.AudioRecord;
import mission.ImageCapture;
import mission.MapOverview;
import mission.MultipleChoiceQuestion;
import mission.NPCTalk;
import mission.QRTagReading;
import mission.StartAndExitScreen;
import mission.TextQuestion;
import mission.VideoPlay;
import mission.WebPage;
import mission.rules.Rule;
import mission.rules.actions.ActionDecrementVariable;
import mission.rules.actions.ActionPlayAudio;
import mission.rules.actions.ActionSetVariable;
import mission.rules.actions.ActionVibrate;
import mission.rules.conditions.ComplexConditionAnd;
import mission.rules.conditions.ComplexConditionIf;
import mission.rules.conditions.SimpleConditionMissionState;

/**
 *
 * @author Gregor
 */
public class ExampleDescriptions
{
    public ExampleDescriptions()
    {
    }

    public Description minimalExample()
    {
        Description description = new Description("Minimum");

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
        varBlo.addOnSuccessRule(successRule);
//        varBlo.addOnStartRule(new Rule(description, actions))

        linBlo.addMissionTypesAsLinearSequence(MultipleChoiceQuestion.class);
        linBlo.addOnSuccessRule(successRule);

        BlockConnector varToLin = new BlockConnector("onFail", varBlo, linBlo);

        description.addBlock(varBlo, linBlo);
        description.addBlockConnector(varToLin);

        return description;
    }

    public Description newContainerModels()
    {
        Description description = new Description("TestZweck");

        VariableBlock variableBlock1 = new VariableBlock("VariabelEins", -1, 3, "onSuccess");
        VariableBlock variableBlock2 = new VariableBlock("VariableZwei", 2, "onFail");
        VariableBlock variableBlock3 = new VariableBlock("VariableDrei");

        LinearBlock linearBlock1 = new LinearBlock("LinearEins", 2, 5, "onStart");
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
        BlockConnector L2ToL3 = new BlockConnector("onStart", linearBlock2, linearBlock3);

        description.addBlock(variableBlock1, variableBlock2, variableBlock3, linearBlock1, linearBlock2, linearBlock3);
        description.addBlockConnector(V1ToV2, V2ToV3, V3ToL1, L1ToL2, L2ToL3);

        return description;
    }

    public Description opaEnkel()
    {

        Description opaEnkel = new Description("Opa Enkel App");

        LinearBlock block0 = new LinearBlock("Willkommen");
        LinearBlock block1 = new LinearBlock("Einfuehrung", 7, "onEnd");
        LinearBlock block2 = new LinearBlock("MultipleChoice");
        LinearBlock block3 = new LinearBlock("ImageCapture", 2, "onEnd");
        LinearBlock block4 = new LinearBlock("Abschluss", 4, "onEnd");

        block0.addMissionTypesAsLinearSequence(StartAndExitScreen.class);
        block1.addMissionTypesAsLinearSequence(NPCTalk.class, NPCTalk.class, VideoPlay.class, NPCTalk.class, NPCTalk.class, NPCTalk.class, NPCTalk.class);
        block2.addMissionTypesAsLinearSequence(MultipleChoiceQuestion.class);
        block3.addMissionTypesAsLinearSequence(ImageCapture.class, TextQuestion.class);
        block4.addMissionTypesAsLinearSequence(NPCTalk.class, AudioRecord.class, NPCTalk.class, NPCTalk.class);

        BlockConnector willkommenZuEinfuehrung = new BlockConnector("onEnd", block0, block1);
        BlockConnector einfuehrungZuMultipleChoice = new BlockConnector("onEnd", block1, block2);
        BlockConnector multipleChoiceZuImageCapture = new BlockConnector("onFail", block2, block3);
//        block2.addOnEndRule(opaEnkelSplitRuleZuBlock2(block3));
//        block2.addOnEndRule(opaEnkelSplitRuleZuBlock3(block4));
        BlockConnector imageCaptureZuAbschluss = new BlockConnector("onEnd", block3, block4);
        BlockConnector multipleChoiceZuAbschluss = new BlockConnector("onSuccess", block2, block4);

        opaEnkel.addBlock(block0, block1, block2, block3, block4);
        opaEnkel.addBlockConnector(willkommenZuEinfuehrung, einfuehrungZuMultipleChoice, multipleChoiceZuImageCapture, imageCaptureZuAbschluss, multipleChoiceZuAbschluss);

        Rule vibrateRule = new Rule();
        vibrateRule.addAction(new ActionVibrate());
        Rule playAudioRuleDrinnenSpielenAuswahlQuestion = new Rule();
        playAudioRuleDrinnenSpielenAuswahlQuestion.addAction(new ActionPlayAudio("drinnenSpielenAuswahlQuestionAudio.mp3"));
        Rule setVariableRule = new Rule();
        setVariableRule.addAction(new ActionSetVariable("SpielzeugDrinnen", "var", "$_drinnenSpielenAuswahl.result"));
        
        block1.addOnStartRule(vibrateRule);
        block2.addOnStartRule(vibrateRule);
        block2.addOnStartRule(playAudioRuleDrinnenSpielenAuswahlQuestion);
        block2.addOnSuccessRule(setVariableRule);
        block4.addOnStartRule(vibrateRule);
        
        return opaEnkel;
    }

//    private Rule opaEnkelSplitRuleZuBlock3(Block block3)
//    {
//        SimpleConditionComparison comp4 = new SimpleConditionComparison("eq", "var", "select", "num", "4");
//        ComplexConditionIf ifCondition = new ComplexConditionIf();
//        Rule onEndRule2 = new Rule();
//        ActionStartMission startMissionBlock3 = new ActionStartMission(block3);
//
//        onEndRule2.addCondition(ifCondition);
//        ifCondition.addCondition(comp4);
//        onEndRule2.addAction(startMissionBlock3);
//
//        return onEndRule2;
//    }
//
//    private Rule opaEnkelSplitRuleZuBlock2(Block block2)
//    {
//        SimpleConditionComparison comp1 = new SimpleConditionComparison("eq", "var", "select", "num", "1");
//        SimpleConditionComparison comp2 = new SimpleConditionComparison("eq", "var", "select", "num", "2");
//        SimpleConditionComparison comp3 = new SimpleConditionComparison("eq", "var", "select", "num", "3");
//        ComplexConditionOr orCondition = new ComplexConditionOr();
//        ComplexConditionIf ifCondition = new ComplexConditionIf();
//        Rule onEndRule1 = new Rule();
//        ActionStartMission startMissionBlock2 = new ActionStartMission(block2);
//        onEndRule1.addCondition(ifCondition);
//        ifCondition.addCondition(orCondition);
//        orCondition.addCondition(comp1);
//        orCondition.addCondition(comp2);
//        orCondition.addCondition(comp3);
//        onEndRule1.addAction(startMissionBlock2);
//
//        return onEndRule1;
//    }
}
