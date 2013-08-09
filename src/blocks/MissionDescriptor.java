/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blocks;

import java.lang.reflect.Field;
import java.util.ArrayList;
import javafx.util.Pair;
import mission.components.MissionComponent;

/**
 *
 * @author Gregor
 */
public class MissionDescriptor
{
    private Class missionClass;
    private ArrayList<Pair<Field, String>> missionAttributeValues;
    private ArrayList<MissionComponent> missionComponents;
    
    public MissionDescriptor(Class missionClass)
    {
        this.missionClass = missionClass;
        missionAttributeValues = new ArrayList<>();
        missionComponents = new ArrayList<>();
    }
    
    public Class getMissionClass()
    {
        return missionClass;
    }
    
    public ArrayList<Pair<Field, String>> getMissionAttributeValues()
    {
        return missionAttributeValues;
    }
    
    public ArrayList<MissionComponent> getMissionComponents()
    {
        return missionComponents;
    }
    
    public void addMissionAttribute(String fieldName, String fieldValue)
    {
        Field field = getFieldByString(fieldName);
        if(field != null)
        {
            missionAttributeValues.add(new Pair<>(field, fieldValue));
        }
        else
        {
            System.out.println("addMissionAttribute failed; no \"" + fieldName + "\" field in class \"" + missionClass.getSimpleName() + "\"");
        }
    }
    
    public void addMissionComponent(MissionComponent missionComponent)
    {
        if(!missionComponents.contains(missionComponent))
        {
            missionComponents.add(missionComponent);
        }
    }
    
    private Field getFieldByString(String fieldName)
    {
        for (Field field : missionClass.getDeclaredFields())
        {
            if (field.getName().equals(fieldName))
            {
                return field;
            }
        }
        return null;
    }
}
