/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    TextArea outputTextArea;
    GameWerWirdMillionaer wwmGame;
    
//    TitledPane titledPaneForDeletion;
//    Button insideOfTheTitledPane;
//    Image deleteIcon;
//    ImageView deleteIconView;
//    Button deleteButton;
    
    private void initialize()
    {
        rootNodeVBox = new VBox();
        firstScene = new Scene(rootNodeVBox, 500, 500);
        outputTextArea = new TextArea();
        assembleWWMButton = new Button("assemble WWM");
        newWWMGameButton = new Button("new WWM");
        
//        titledPaneForDeletion = new TitledPane();
//        insideOfTheTitledPane = new Button("blub");
//        deleteIcon = new Image("/images/delete-icon.png");
//        deleteIconView = new ImageView(deleteIcon);
//        deleteButton = new Button();
    }
    
    private void layout()
    {
//        deleteButton.setGraphic(deleteIconView);
        
//        deleteIconView.setFitHeight(20.0);
//        deleteIconView.setFitWidth(20.0);
//        
//        titledPaneForDeletion.setText("hallöchen");
//        titledPaneForDeletion.setGraphic(deleteIconView);
//        titledPaneForDeletion.setGraphicTextGap(50.0);
//        titledPaneForDeletion.setContent(insideOfTheTitledPane);
//        titledPaneForDeletion.setPrefWidth(300.0);
        
        rootNodeVBox.getChildren().addAll(newWWMGameButton, assembleWWMButton);
    }
    
    private void bind()
    {
        assembleWWMButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if(!rootNodeVBox.getChildren().contains(outputTextArea))
                {
                    rootNodeVBox.getChildren().add(outputTextArea);
                }
                outputTextArea.setText(wwmGame.assemble());
            }
        });
        
        newWWMGameButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                wwmGame = new GameWerWirdMillionaer();
                new GUIGameWerWirdMillionaer(wwmGame);
            }
        });
        
//        deleteIconView.setOnMouseClicked(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent mouseEvent)
//            {
//                mouseEvent.consume();
//                deleteIconView.fireEvent(new DeleteMissionEvent(titledPaneForDeletion));
//            }
//        });
        
//        rootNodeVBox.addEventHandler(DeleteMissionEvent.TITLED_PANE_DELETION, new EventHandler<DeleteMissionEvent>()
//        {
//            @Override
//            public void handle(DeleteMissionEvent t) {
//                if(rootNodeVBox.getChildren().contains(t.getToBeDeletedTitledPane()))
//                {
//                   System.out.println("ES KLAPPTETETETETETETE!");
//                }
//                rootNodeVBox.getChildren().remove(t.getToBeDeletedTitledPane());
//                t.consume();
//            }
//            
//        });
//        rootNodeVBox.addEventHandler(DeleteMissionEvent.TITLED_PANE_DELETION, new DeleteMissionEventHandler());
        
//        deleteIconView.setOnMouseClicked(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                rootNodeVBox.getChildren().remove(titledPaneForDeletion);
//            }
//        });
        
//        deleteIconView.setOnMouseClicked(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent t)
//            {
//                rootNodeVBox.getChildren().remove(titledPaneForDeletion);
//            }
//
//        });

    }
    
    @Override
    public void start(final Stage primaryStage) {
        initialize();
        layout();
        bind();
        
        
        //  END INITALIZATION
        
        primaryStage.setScene(firstScene);
        primaryStage.setTitle("GeoQuest Spiele-Editor");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
