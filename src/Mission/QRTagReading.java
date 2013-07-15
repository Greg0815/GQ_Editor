/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
public class QRTagReading extends Mission
{
    private StringProperty initial_image;
    private StringProperty if_right_image;
    private StringProperty if_wrong_image;
    private StringProperty mode;
    private StringProperty taskdescription;
    private StringProperty buttontext;
    private StringProperty endbuttontext;

    public QRTagReading()
    {
        super("QRTagReading");
        initial_image = new SimpleStringProperty("");
        if_right_image = new SimpleStringProperty("");
        if_wrong_image = new SimpleStringProperty("");
        mode = new SimpleStringProperty("");
        taskdescription = new SimpleStringProperty("");
        buttontext = new SimpleStringProperty("");
        endbuttontext = new SimpleStringProperty("");
        addNecessaryAndOptionalFields();
    }

    public String getInitial_image()
    {
        return initial_image.get();
    }

    public String getIf_right_image()
    {
        return if_right_image.get();
    }

    public String getIf_wrong_image()
    {
        return if_wrong_image.get();
    }

    public String getMode()
    {
        return mode.get();
    }

    public String getTaskdescription()
    {
        return taskdescription.get();
    }

    public String getButtontext()
    {
        return buttontext.get();
    }

    public String getEndbuttontext()
    {
        return endbuttontext.get();
    }

    public void setInitial_image(String initial_image)
    {
        this.initial_image.set(initial_image);
    }

    public void setIf_right_image(String if_right_image)
    {
        this.if_right_image.set(if_right_image);
    }

    public void setIf_wrong_image(String if_wrong_image)
    {
        this.if_wrong_image.set(if_wrong_image);
    }

    public void setMode(String mode)
    {
        this.mode.set(mode);
    }

    public void setTaskdescription(String taskdescription)
    {
        this.taskdescription.set(taskdescription);
    }

    public void setButtontext(String buttontext)
    {
        this.buttontext.set(buttontext);
    }

    public void setEndbuttontext(String endbuttontext)
    {
        this.endbuttontext.set(endbuttontext);
    }

    public StringProperty initial_imageProperty()
    {
        return initial_image;
    }

    public StringProperty if_right_imageProperty()
    {
        return if_right_image;
    }

    public StringProperty if_wrong_imageProperty()
    {
        return if_wrong_image;
    }

    public StringProperty modeProperty()
    {
        return mode;
    }

    public StringProperty taskdescriptionProperty()
    {
        return taskdescription;
    }

    public StringProperty buttontextProperty()
    {
        return buttontext;
    }

    public StringProperty endbuttontextProperty()
    {
        return endbuttontext;
    }

//    @Override
//    public String createSpecificMissionHeader()
//    {
//        String returnString = utilitys.taskdescription(taskdescription.get());
//        if (!initial_image.get().isEmpty())
//        {
//            returnString += utilitys.initial_image(initial_image.get());
//        }
//        if (!if_right_image.get().isEmpty())
//        {
//            returnString += utilitys.if_right_image(if_right_image.get());
//        }
//        if (!if_wrong_image.get().isEmpty())
//        {
//            returnString += utilitys.if_wrong_image(if_wrong_image.get());
//        }
//            return utilitys.initial_image(initial_image.get()) + utilitys.if_right_image(if_right_image.get()) + utilitys.if_wrong_image(if_wrong_image.get())
//                    + utilitys.mode(mode.get()) + utilitys.taskdescription(taskdescription.get())
//                    + utilitys.buttontext(buttontext.get()) + utilitys.endbuttontext(endbuttontext.get());
//    }

    @Override
    public String assemble()
    {
        return buildMissionHead() + buildMissionTail();
//        return createMissionHeader() + buildMissionTail();
    }

    @Override
    protected final void addNecessaryAndOptionalFields()
    {
        this.addNecessaryHeaderFields(getFieldByString("taskdescription"));
        this.addOptionalHeaderFields(getFieldByString("initial_image"), getFieldByString("if_right_image"), getFieldByString("if_wrong_image"), getFieldByString("mode"), getFieldByString("buttontext"), getFieldByString("endbuttontext"));
    }
}
