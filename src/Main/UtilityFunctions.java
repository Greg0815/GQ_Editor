/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;

/**
 *
 * @author Gregor
 */
public class UtilityFunctions<T extends BaseComponent>
{
    public UtilityFunctions()
    {
        
    }
    
    public String createStringFromArrayList(ArrayList<T> arrayList)
    {
        String returnString = "";
        for(int i=0; i<arrayList.size(); i++)
        {
            returnString += arrayList.get(i).assemble();
        }
        return returnString;
    }
    
    public String typeAndValue(String type, String value)
    {
        System.out.println("".equalsIgnoreCase(""));
        if(value.isEmpty() || value == null || value.equalsIgnoreCase(""))
        {
            return "";
        }
        else
        {
            return " " + type + "=\"" + value + "\"";
        }
    }
    
    public String typeAndValue(String type, Boolean value)
    {
        return " " + type + "=\"" + value.toString() + "\"";
    }
    
    public String typeAndValue(String type, Float value)
    {
        return " " + type + "=\"" + value.toString() + "\"";
    }
    
    public String typeAndValue(String type, Double value)
    {
        return " " + type + "=\"" + value.toString() + "\"";
    }
    
    public String typeAndValue(String type, Integer value)
    {
        return " " + type + "=\"" + value.toString() + "\"";
    }
    
    public String type(String type)
    {
        return typeAndValue("type", type);
    }
    
    public String id(String id)
    {
        return typeAndValue("id", id);
    }
    
    public String image(String image)
    {
        return typeAndValue("image", image);
    }
    
    public String duration(String duration)
    {
        return typeAndValue("duration", duration);
    }
    
    public String duration(int duration)
    {
        return typeAndValue("duration", String.valueOf(duration));
    }
    
    public String cancel(String cancel)
    {
        return typeAndValue("cancel", cancel);
    }
    
    public String cancel(Boolean cancel)
    {
        return typeAndValue("cancel", cancel.toString());
    }
    
    public String state(String state)
    {
        return typeAndValue("state", state);
    }
    
    public String correct(Boolean correct)
    {
        return typeAndValue("correct", correct);
    }
    
    public String onChoose(String onChoose)
    {
        return typeAndValue("onChoose", onChoose);
    }
    
    public String var(String var)
    {
        return typeAndValue("var", var);
    }
    
    public String loopUntilSuccess(String loopUntilSuccess)
    {
        return typeAndValue("loopUntilSuccess", loopUntilSuccess);
    }
    
    public String loopUntilSuccess(Boolean loopUntilSuccess)
    {
        return typeAndValue("loopUntilSuccess", loopUntilSuccess.toString());
    }
    
    public String shuffle(String shuffle)
    {
        return typeAndValue("shuffle", shuffle);
    }
    
    public String shuffle(Boolean shuffle)
    {
        return typeAndValue("shuffle", shuffle);
    }
    
    public String name(String name)
    {
        return typeAndValue("name", name);
    }
    
    public String speaker(String speaker)
    {
        return typeAndValue("speaker", speaker);
    }
    
    public String sound(String sound)
    {
        return typeAndValue("sound", sound);
    }
    
    public String blocking(Boolean blocking)
    {
        return typeAndValue("blocking", blocking);
    }
    
    public String nextdialogbuttontext(String nextdialogbuttontext)
    {
        return typeAndValue("nextdialogbuttontext", nextdialogbuttontext);
    }
    
    public String textsize(String textsize)
    {
        return typeAndValue("textsize", textsize);
    }
    
    public String endbuttontext(String endbuttontext)
    {
        return typeAndValue("endbuttontext", endbuttontext);
    }
    
    public String longitude(Double longitude)
    {
        return typeAndValue("longitude", longitude);
    }
    
    public String latitude(Double latitude)
    {
        return typeAndValue("latitude", latitude);
    }
    
    public String img(String img)
    {
        return typeAndValue("img", img);
    }
    
    public String initialVisibility(Boolean initialVisibility)
    {
        return typeAndValue("initialVisibility", initialVisibility);
    }
    
    public String radius(Integer radius)
    {
        return typeAndValue("radius", radius);
    }
    
    public String mapkind(String mapkind)
    {
        return typeAndValue("mapkind", mapkind);
    }
    
    public String zoomlevel(Integer zoomlevel)
    {
        return typeAndValue("zoomlevel", zoomlevel);
    }
    
    public String task(String task)
    {
        return typeAndValue("task", task);
    }
    
    public String file(String file)
    {
        return typeAndValue("file", file);
    }
    
    public String initial_image(String initial_image)
    {
        return typeAndValue("initial_image", initial_image);
    }
    
    public String if_right_image(String if_right_image)
    {
        return typeAndValue("if_right_image", if_right_image);
    }
    
    public String if_wrong_image(String if_wrong_image)
    {
        return typeAndValue("if_wrong_image", if_wrong_image);
    }
    
    public String mode(String mode)
    {
        return typeAndValue("mode", mode);
    }
    
    public String taskdescription(String taskdescription)
    {
        return typeAndValue("taskdescription", taskdescription);
    }
    
    public String buttontext(String buttontext)
    {
        return typeAndValue("buttontext", buttontext);
    }
    
    public String question(String question)
    {
        return typeAndValue("question", question);
    }
    
    public String prompt(String prompt)
    {
        return typeAndValue("prompt", prompt);
    }
    
    public String replyOnCorrect(String replyOnCorrect)
    {
        return typeAndValue("replyOnCorrect", replyOnCorrect);
    }
    
    public String replyOnWrong(String replyOnWrong)
    {
        return typeAndValue("replyOnWrong", replyOnWrong);
    }
    
    public String url(String url)
    {
        return typeAndValue("url", url);
    }
    
    public String controllable(Boolean controllable)
    {
        return typeAndValue("controllable", controllable);
    }
    
    public String exitDialogTitle(String exitDialogTitle)
    {
        return typeAndValue("exitDialogTitle", exitDialogTitle);
    }
    
    public String exitDialogMessage(String exitDialogMessage)
    {
        return typeAndValue("exitDialogMessage", exitDialogMessage);
    }
    
    public String exitDialogKeepWatchingText(String exitDialogKeepWatchingText)
    {
        return typeAndValue("exitDialogKeepWatchingText", exitDialogKeepWatchingText);
    }
    
    public String exitDialogWatchAgainText(String exitDialogWatchAgainText)
    {
        return typeAndValue("exitDialogWatchAgainText", exitDialogWatchAgainText);
    }
    
    public String exitDialogLeaveText(String exitDialogLeaveText)
    {
        return typeAndValue("exitDialogLeaveText", exitDialogLeaveText);
    }
}
