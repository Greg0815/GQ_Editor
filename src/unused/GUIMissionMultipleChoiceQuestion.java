/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Gregor
 */
public class GUIMissionMultipleChoiceQuestion {
    VBox layoutVBox;
    HBox idHBox;
    HBox loopUntilSuccessHBox;
    HBox shuffleHBox;
    Label idLabel;
    Label loopUntilSuccessLabel;
    Label shuffleLabel;
    TextField idTextField;
    TextField loopUntilSuccessTextField;
    TextField shuffleTextField;
    TitledPane mmcqTitledPane;
    ImageView deleteIcon;
    MissionMultipleChoiceQuestion mmcqReference;

    public GUIMissionMultipleChoiceQuestion(MissionMultipleChoiceQuestion mmcqReference)
    {
        this.mmcqReference = mmcqReference;
        instantiate();
        layout();
        bind();
    }

    public GUIMissionMultipleChoiceQuestion(MissionMultipleChoiceQuestion mmcqReference, String titledPaneLabel)
    {
        this.mmcqReference = mmcqReference;
        instantiate();
        mmcqTitledPane.setText(titledPaneLabel);
        layout();
        bind();
    }
 
    public GUIMissionMultipleChoiceQuestion(MissionMultipleChoiceQuestion mmcqReference, int missionCount)
    {
        this.mmcqReference = mmcqReference;
        instantiate();
        mmcqTitledPane.setText(mmcqTitledPane.getText() + " " + missionCount);
        layout();
        bind();
    }
            
    private void instantiate()
    {
        shuffleTextField = new TextField();
        loopUntilSuccessTextField = new TextField();
        idTextField = new TextField();
        shuffleLabel = new Label("shuffle");
        loopUntilSuccessLabel = new Label("loopUntilSuccess");
        idLabel = new Label("id");
        shuffleHBox = new HBox();
        loopUntilSuccessHBox = new HBox();
        idHBox = new HBox();
        layoutVBox = new VBox();
//        deleteButton = new Button();
//        deleteButton.setGraphic(ImageViewBuilder.create().fitHeight(20.0).fitWidth(20.0).image(new Image("images/delete-icon.png")).build());
//        ImageView tmp = ImageViewBuilder.create().fitHeight(20.0).fitWidth(20.0).image(new Image("images/delete-icon.png")).build();
        deleteIcon = ImageViewBuilder.create().fitHeight(20.0).fitWidth(20.0).image(new Image("images/delete-icon.png")).build();
        deleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                event.consume();
                deleteIcon.fireEvent(new DeleteMissionEvent(mmcqTitledPane, mmcqReference));
            }
        });
        mmcqTitledPane = new TitledPane();
        mmcqTitledPane.setText("MissionMultipleChoiceQuestion");
        mmcqTitledPane.setGraphic(deleteIcon);
        mmcqTitledPane.setGraphicTextGap(50.0);    // new
        mmcqTitledPane.setContent(layoutVBox);
    }
    
    private void layout()
    {
//        mmcqTitledPane.setAlignment(Pos.CENTER_RIGHT);
        idHBox.getChildren().addAll(idLabel, idTextField);
        shuffleHBox.getChildren().addAll(shuffleLabel, shuffleTextField);
        loopUntilSuccessHBox.getChildren().addAll(loopUntilSuccessLabel, loopUntilSuccessTextField);
        layoutVBox.getChildren().addAll(idHBox, shuffleHBox, loopUntilSuccessHBox, new GUIMultipleChoiceQuestion(mmcqReference.getMultipleChoiceQuestion()).getLayout());
    }
    
    private void bind()
    {
        mmcqReference.idProperty().bindBidirectional(idTextField.textProperty());
        
        idTextField.textProperty().bindBidirectional(mmcqTitledPane.textProperty());    // new
        
        mmcqReference.loopUntilSuccessProperty().bindBidirectional(loopUntilSuccessTextField.textProperty());
        mmcqReference.shuffleProperty().bindBidirectional(shuffleTextField.textProperty());
    }

    public TitledPane getLayout()
    {
        return this.mmcqTitledPane;
    }
    
    public MissionMultipleChoiceQuestion getReference()
    {
        return this.mmcqReference;
    }
//    
//    public void setID(String id)
//    {
//        mmcqTitledPane.setId(id);
//    }
//    
//    public String getID()
//    {
//        return mmcqTitledPane.getId();
//    }
}
