/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 *
 * @author Gregor
 */
public class FileBrowser
{

    private HBox fileBrowserRootNode;
    private Button btn;
    private Label label;
    private FileChooser fileChooser;
    private FileChooser.ExtensionFilter extensionFilter;
    private File file;
    private StringProperty filePath;
    private ImageView imageView;

    public FileBrowser(String description, String... extensions)
    {
        initialization(description, extensions);
        fileBrowserRootNode.getChildren().addAll(btn, label, imageView);
        fileBrowserRootNode.setSpacing(10);
        fileChooser.getExtensionFilters().add(extensionFilter);
    }

    public StringProperty filePathProperty()
    {
        if (this.filePath == null) {
            this.filePath = new SimpleStringProperty();
        }
        return this.filePath;
    }

    private void initialization(String description, String... extensions)
    {
        fileBrowserRootNode = new HBox();
        fileChooser = new FileChooser();
        filePath = new SimpleStringProperty();
        extensionFilter = new FileChooser.ExtensionFilter(description, extensions);
        label = new Label("");
        imageView = new ImageView();
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
        if (file == null) {
            System.out.println("erstes if");
            file = fileChooser.showOpenDialog(null);
        }
        else if (file.isFile()) {
            System.out.println("zweites if");
            File newFile = fileChooser.showOpenDialog(null);
            if (newFile.isFile()) {
                System.out.println("drittes if");
                file = newFile;
            }
        }
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imageView.setImage(image);
                label.setText(file.getPath());
                filePath.set(file.getPath());
            }
            catch (IOException e) {
                System.out.println("FileBrowser browse() Exception: " + e);
            }
        }
    }

    public String filePath()
    {
        if (file.getPath().isEmpty()) {
            return "";
        }
        return file.getPath();
    }
}
