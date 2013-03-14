/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Gregor
 */
public class JavaFXTestApplication1 extends Application {
    VBox rootNodeVBox;
    Label output;
    Scene firstScene;
    Button assembleWWMButton;
    Button newWWMGameButton;
    TextArea outputLabel;
    GameWerWirdMillionaer blablabla;
    @Override
    public void start(final Stage primaryStage) {
        rootNodeVBox = new VBox();
        firstScene = new Scene(rootNodeVBox, 500, 500);
        //  START ACCORDION TEST MIT BINDING
        
//        Accordion questionPane = new Accordion();
//        questionPane.getPanes().addAll(new GUIMissionStartAndExitScreen(temp1).getLayout());

        outputLabel = new TextArea();
        
        assembleWWMButton = new Button("assemble WWM");
        newWWMGameButton = new Button("new WWM");
        
        assembleWWMButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if(!rootNodeVBox.getChildren().contains(outputLabel))
                {
                    rootNodeVBox.getChildren().add(outputLabel);
                }
                outputLabel.setText(blablabla.assemble());
            }
        });
        
        newWWMGameButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                blablabla = new GameWerWirdMillionaer();
                new GUIGameWerWirdMillionaer(blablabla);
            }
        });
        //  END
        rootNodeVBox.getChildren().addAll(newWWMGameButton, assembleWWMButton);
        
        primaryStage.setScene(firstScene);
        primaryStage.setTitle("GeoQuest Spiele-Editor");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    /*
     // the main class for a JavaFX application extends javafx.application.Application
     // Stage = Main Container (top level)
     // Scene = Background for UI Elements (container for all content)
     // Textbox, Imageview, Mediaplayer etc. = Example UI Elements
     @Override
     public void start(Stage primaryStage) throws Exception
     {   
     // First Screen with Button and later Input Fields of Answer Class
     final GridPane gridpaneRootElement = new GridPane();
     Scene scene = new Scene(gridpaneRootElement, 800, 600);        
     Button reflectionButton = new Button();
     Button assembleButton = new Button();
     gridpaneRootElement.add(reflectionButton, 1, 2);
     gridpaneRootElement.add(assembleButton, 1, 3);
     primaryStage.setTitle("Hello World!");
     primaryStage.setScene(scene);
     primaryStage.show();
        
     // GeoQuest Output Creation
     MissionStartAndExitScreen saesMission = new MissionStartAndExitScreen("fling", "", 333, true);
     MultipleChoiceQuestion question1 = new MultipleChoiceQuestion("Wie geht's dir heute?");
     question1.addAnswer("Gut", Boolean.TRUE);
     question1.addAnswer("Schlecht", false);
     question1.addAnswer("Mittel", false);
     MissionMultipleChoiceQuestion mcqMission1 = new MissionMultipleChoiceQuestion("wwm1", Boolean.TRUE, Boolean.TRUE, question1);
     MultipleChoiceQuestion question2 = new MultipleChoiceQuestion("Wer wird Millionär?");
     question2.addAnswer("Ich", true);
     question2.addAnswer("Du", false);
     question2.addAnswer("Keiner von uns beiden", true);
     MissionMultipleChoiceQuestion mcqMission2 = new MissionMultipleChoiceQuestion("wwm2", Boolean.TRUE, Boolean.TRUE);
     mcqMission2.addMultipleChoiceQuestion(question2);
     MultipleChoiceQuestion question3 = new MultipleChoiceQuestion("Ich und Du, Müller's Kuh, Müller's Esel das bist?");
     question3.addAnswer("Hackfresse Hoeneß", true);
     question3.addAnswer("Angela Ferkel", true);
     question3.addAnswer("Bunga Bunga Berlusconi", true);
     question3.addAnswer("Alle anderen", false);
     MissionMultipleChoiceQuestion mcqMission3 = new MissionMultipleChoiceQuestion("wwm3", Boolean.TRUE, Boolean.TRUE, question3);
        
     GameWerWirdMillionaer wwmGame = new GameWerWirdMillionaer();
     wwmGame.addMissionStartAndExitScreen(saesMission);
     wwmGame.addMissionStartAndExitScreen(saesMission);
     wwmGame.addMissionMultipleChoiceQuestion(mcqMission1);
     wwmGame.addMissionMultipleChoiceQuestion(mcqMission2);
     wwmGame.addMissionMultipleChoiceQuestion(mcqMission3);

     String toXML = wwmGame.assemble();
        
     // Second Screen with GeoQuest XML Output in TextArea
     final Stage secondaryStage = new Stage();
     GridPane secondaryStageRootElementStackPane = new GridPane();
     TextArea outputArea = new TextArea(toXML);
     Button closeThisStage = new Button();
     closeThisStage.setText("Close This Window");
     closeThisStage.setOnAction(new EventHandler<ActionEvent>()
     {
     @Override
     public void handle(ActionEvent event)
     {
     try
     {
     secondaryStage.close();
     }
     catch(Exception e)
     {
     System.out.println(e);
     }
     }
     });
     secondaryStageRootElementStackPane.add(closeThisStage, 0, 2);
     secondaryStageRootElementStackPane.add(outputArea, 0, 0);
     secondaryStage.setScene(new Scene(secondaryStageRootElementStackPane, 640, 480));
     secondaryStage.setTitle("the Output");
        
     reflectionButton.setText("Reflect Answer Class");
     reflectionButton.setOnAction(new EventHandler<ActionEvent>() {
     @Override
     public void handle(ActionEvent event) {
     try {
     //                    Answer newAnswer = new Answer("hallo", true);
     //                    Class answerClass = newAnswer.getClass();
     //                    Class answerClass2 = Answer.class;
     GUIBuilder answerGui = new GUIBuilder(Answer.class);
     gridpaneRootElement.getChildren().add(answerGui.makeForm());
     }
     catch (Exception e)
     {
     System.out.println(e);
     }
     }
     });
        
     assembleButton.setText("Assemble Wer Wird Millionär?");
     assembleButton.setOnAction(new EventHandler<ActionEvent>()
     {
     @Override
     public void handle(ActionEvent event)
     {
     try
     {
     secondaryStage.show();
     }
     catch (Exception e)
     {
     System.out.println(e);
     }
     }
     });
        
     //                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
     //                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
     //                    transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT,"1");
     //                    String formattedToXML = new String();
     //                    transformer.transform(new StreamSource(new StringReader(toXML)),new StreamResult(formattedToXML));
     }


     public static void main(String[] args)
     {
     System.out.println("Something went wrong\n main() executed");
     launch(args);        
     }
     */
}
