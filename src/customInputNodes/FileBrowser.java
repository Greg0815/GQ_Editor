/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package customInputNodes;

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
    private Label label;
    private FileChooser fileChooser;
    private FileChooser.ExtensionFilter extensionFilter;
    private File file;
    private StringProperty filePath;
//    private ImageView imageView;

    public FileBrowser(String description, String... extensions)
    {
        initialization(description, extensions);
        fileBrowserRootNode.getChildren().addAll(btn, label);//, imageView);
        fileBrowserRootNode.setSpacing(10);
        fileChooser.getExtensionFilters().add(extensionFilter);
    }

    private void initialization(String description, String... extensions)
    {
        fileBrowserRootNode = new HBox();
        fileChooser = new FileChooser();
        filePath = new SimpleStringProperty();
        extensionFilter = new FileChooser.ExtensionFilter(description, extensions);
        label = new Label("");
//        imageView = new ImageView();
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

    public String getFilePath()
    {
        return filePath.get();
    }

    public void setFilePath(String filePath)
    {
        this.filePath.set(filePath);
    }

    public StringProperty filePathProperty()
    {
        return filePath;
    }

    public void bindToProperty(StringProperty anotherProperty)
    {
        this.filePath.bindBidirectional(anotherProperty);
    }

    public HBox getNode()
    {
        return fileBrowserRootNode;
    }
    
    public File getFile()
    {
        return file;
    }

    public void browse()
    {
        if (file == null)
        {
            file = fileChooser.showOpenDialog(null);
        }
        else if (file.isFile())
        {
            File newFile = fileChooser.showOpenDialog(null);
            if (newFile.isFile())
            {
                file = newFile;
            }
        }
        if (file != null)
        {
//            try
//            {
//                Image image = new Image(file.toURI().toString());
//                imageView = new ImageView(image);
                //
//                BufferedImage bufferedImage = ImageIO.read(file);
//                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
//                imageView.setImage(image);
                label.setText(file.getPath());
                filePath.set(file.getPath());
//            }
//            catch (IOException e)
//            {
//                System.out.println("FileBrowser browse() Exception: " + e);
//            }
        }
    }

    public String filePath()
    {
        if (file.getPath().isEmpty())
        {
            return "";
        }
        return file.getPath();
    }
}
