/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
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
    MissionMultipleChoiceQuestion mmcqReference;

    public GUIMissionMultipleChoiceQuestion(MissionMultipleChoiceQuestion mmcqReference)
    {
        this.mmcqReference = mmcqReference;
        instantiate();
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
        mmcqTitledPane = new TitledPane("MissionMultipleChoiceQuestion", layoutVBox);
    }
    
    private void layout()
    {
        idHBox.getChildren().addAll(idLabel, idTextField);
        shuffleHBox.getChildren().addAll(shuffleLabel, shuffleTextField);
        loopUntilSuccessHBox.getChildren().addAll(loopUntilSuccessLabel, loopUntilSuccessTextField);
        layoutVBox.getChildren().addAll(idHBox, shuffleHBox, loopUntilSuccessHBox, new GUIMultipleChoiceQuestion(mmcqReference.getMultipleChoiceQuestion()).getLayout());
    }
    
    private void bind()
    {
        mmcqReference.idProperty().bindBidirectional(idTextField.textProperty());
        mmcqReference.loopUntilSuccessProperty().bindBidirectional(loopUntilSuccessTextField.textProperty());
        mmcqReference.shuffleProperty().bindBidirectional(shuffleTextField.textProperty());
    }

    public TitledPane getLayout()
    {
        return this.mmcqTitledPane;
    }
}
