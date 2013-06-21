/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import java.io.File;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

/**
 *
 * @author Gregor
 */
public class FileBrowser
{
    private HBox fileBrowserRootNode;
    private Button btn;
    private Label lbl;
    private FileChooser fileChooser;
    private FileChooser.ExtensionFilter extensionFilter;
    private File file;
    private StringProperty filePath;
    
    public FileBrowser(String description, String... extensions)
    {
        initialization(description, extensions);
        fileBrowserRootNode.getChildren().addAll(btn, lbl);
        fileBrowserRootNode.setSpacing(10);
        fileChooser.getExtensionFilters().add(extensionFilter);
    }

    public StringProperty filePathProperty()
    {
        if(this.filePath == null)
        {
            this.filePath = new SimpleStringProperty();
        }
        return this.filePath;
    }
    
    private void initialization(String description, String... extensions)
    {
        fileBrowserRootNode = new HBox();
        fileChooser = new FileChooser();
        extensionFilter = new FileChooser.ExtensionFilter(description, extensions);
        lbl = new Label("");
        btn = new Button("Browse " + description);
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
           @Override
           public void handle(ActionEvent t)
           {
               browse();
           }
        });
    }
    
    public void bindToProperty(StringProperty anotherProperty)
    {
        this.filePath.bindBidirectional(anotherProperty);
    }
    
    public HBox getNode()
    {
        return fileBrowserRootNode;
    }
    
    public void browse()
    {
        file = fileChooser.showOpenDialog(null);
        lbl.setText(file.getPath());
        filePath.set(file.getPath());
    }
    
    public String filePath()
    {
        if(file.getPath().isEmpty())
        {
            return "";
        }
        return file.getPath();
    }
}
