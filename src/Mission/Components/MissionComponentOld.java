/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission.Components;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Gregor
 */
public class MissionComponentOld
{
    private ArrayList<Field> necessaryFields;
    private ArrayList<Field> optionalFields;
    
    public MissionComponentOld()
    {
        necessaryFields = new ArrayList<>();
        optionalFields = new ArrayList<>();
    }
    
    public ArrayList<Field> getOptinalFields()
    {
        return optionalFields;
    }

    public void addOptionalFields(Field... field)
    {
        optionalFields.addAll(Arrays.asList(field));
    }

    public ArrayList<Field> getNecessaryFields()
    {
        return necessaryFields;
    }

    public void addNecessaryFields(Field... fields)
    {
        this.necessaryFields.addAll(Arrays.asList(fields));
    }
    
    private ArrayList<Class> getSuperClassesUpToObjectClassAsArrayList(Object obj)
    {
        ArrayList<Class> classes = new ArrayList<>();
        Class baseClass = obj.getClass();

        do
        {
            classes.add(baseClass);
            baseClass = baseClass.getSuperclass();
        }
        while (!baseClass.equals(Object.class));

        Collections.reverse(classes);

        return classes;
    }

    protected Field getFieldByString(String fieldName)
    {
        for (Class clazz : getSuperClassesUpToObjectClassAsArrayList(this))
        {
            for (Field field : clazz.getDeclaredFields())
            {
                if (field.getName().equals(fieldName))
                {
                    return field;
                }
            }
        }
        return null;
    }
}
