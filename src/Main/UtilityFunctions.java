/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import java.util.ArrayList;

/**
 *
 * @author Gregor
 */
public class UtilityFunctions<T extends AssembleInterface>
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
        return " " + type + "=\"" + value + "\"";
    }
    
    public String typeAndValue(String type, Boolean value)
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
    
    public String correct(String correct)
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
}
