/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unused;

import Mission.StartAndExitScreen;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import CustomInputNodes.NumericTextField;

/**
 *
 * @author Gregor
 */
public class GUIMissionStartAndExitScreen
{
    VBox layoutVBox;
    HBox idHBox;
    HBox imageHBox;
    HBox durationHBox;
    HBox cancelHBox;
    Label idLabel;
    Label imageLabel;
    Label durationLabel;
    Label cancelLabel;
    TextField idTextField;
    TextField imageTextField;
//    TextField durationTextField;
    NumericTextField durationTextField;
    TextField cancelTextField;
    TitledPane saesTitledPane;
    StartAndExitScreen saesReference;
    
    public GUIMissionStartAndExitScreen(StartAndExitScreen saesReference)
    {
        this.saesReference = saesReference;
        instantiate();
        layout();
        bind();
    }
        
    public GUIMissionStartAndExitScreen(StartAndExitScreen saesReference, String titledPaneLabel)
    {
        this.saesReference = saesReference;
        instantiate();
        saesTitledPane.setText(titledPaneLabel);
        layout();
        bind();
    }
    
    private void instantiate()
    {
        cancelTextField = new TextField();
        durationTextField = new NumericTextField();
        imageTextField = new TextField();
        idTextField = new TextField();
        cancelLabel = new Label("cancel");
        durationLabel = new Label("duration");
        imageLabel = new Label("image");
        idLabel = new Label("id");
        cancelHBox = new HBox();
        durationHBox = new HBox();
        imageHBox = new HBox();
        idHBox = new HBox();
        layoutVBox = new VBox();
        saesTitledPane = new TitledPane("MissionStartAndExitScreen", layoutVBox);
    }
    
    private void layout()
    {
        idHBox.getChildren().addAll(idLabel, idTextField);
        imageHBox.getChildren().addAll(imageLabel, imageTextField);
        durationHBox.getChildren().addAll(durationLabel, durationTextField);
        cancelHBox.getChildren().addAll(cancelLabel, cancelTextField);
        layoutVBox.getChildren().addAll(idHBox, imageHBox, durationHBox, cancelHBox);
    }

    private void bind()
    {
        saesReference.idProperty().bindBidirectional(idTextField.textProperty());
        saesReference.imageProperty().bindBidirectional(imageTextField.textProperty());
//        saesReference.durationProperty().bindBidirectional(durationTextField.textProperty());
        saesReference.cancelProperty().bindBidirectional(cancelTextField.textProperty());
    }
    
    public TitledPane getLayout()
    {
        return this.saesTitledPane;
    }
}
