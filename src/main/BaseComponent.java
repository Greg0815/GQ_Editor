/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
abstract public class BaseComponent
{
    private ArrayList<Field> necessaryHeaderFields;
    private ArrayList<Field> necessaryNonHeaderFields;
    private ArrayList<Field> optionalHeaderFields;
    public UtilityFunctions utilitys;

    protected abstract void addNecessaryAndOptionalFields();

    public abstract String assemble();

    public Boolean isComplete()
    {
        Boolean isComplete = true;
        if(!isCompleted(necessaryHeaderFields))
        {
            isComplete = false;
        }
        if(!isCompleted(necessaryNonHeaderFields))
        {
            isComplete = false;
        }
        return isComplete;
    }

    public Boolean isCompleted(ArrayList<Field> fieldsArrayList)
    {
        Boolean isCompleted = true;
        for (Field field : fieldsArrayList)
        {
            System.out.println("Field: " + field.getName());
            try
            {
                field.setAccessible(true);
                if (field.getType().toString().contains("StringProperty"))
                {
                    StringProperty fld = (StringProperty) field.get(this);
                    if (fld.get().isEmpty())
                    {
                        System.out.println(field.getName() + " is missing");
                        isCompleted = false;
                        break;
                    }
                }
                else if (field.getType().toString().contains("IntegerProperty"))
                {
                    IntegerProperty fld = (IntegerProperty) field.get(this);    // TODO sinnvolle werte früher rausfiltern
                    if(fld.get() < -180 && 10000 < fld.get())      // Longitude/Latitude Range up to -180, StartScreen duration 10000ms = 10sec useful value?
                    {
                        System.out.println(field.getName() + " is missing");
                        isCompleted = false;
                        break;
                    }
                }
                else if(field.getType().toString().contains("ArrayList"))
                {
                    ArrayList<BaseComponent> tmp = (ArrayList<BaseComponent>)field.get(this);
                    if(tmp.size() < 1)
                    {
                        System.out.println(field.getName() + " is missing");
                        isCompleted = false;
                        break;
                    }
                    for(BaseComponent baseComponent : tmp)
                    {
                        if(!baseComponent.isComplete())
                        {
                            isCompleted = false;
                            break;
                        }
                    }
                }
            }
            catch (IllegalArgumentException | IllegalAccessException e)
            {
                System.out.println("BaseComponent Class - isComplete function: " + e);
            }
        }
        return isCompleted;
    }
    
    public BaseComponent()
    {
        necessaryHeaderFields = new ArrayList<>();
        necessaryNonHeaderFields = new ArrayList<>();
        optionalHeaderFields = new ArrayList<>();
        utilitys = new UtilityFunctions();
    }

    public ArrayList<Field> getNecessaryHeaderFields()
    {
        return necessaryHeaderFields;
    }
  
    public ArrayList<Field> getNecessaryNonHeaderFields()
    {
        return necessaryNonHeaderFields;
    }

    public ArrayList<Field> getOptinalHeaderFields()
    {
        return optionalHeaderFields;
    }
  
    protected void addNecessaryHeaderFields(Field... field)
    {
        necessaryHeaderFields.addAll(Arrays.asList(field));
    }
    
    protected void addNecessaryNonHeaderFields(Field... field)
    {
        necessaryNonHeaderFields.addAll(Arrays.asList(field));
    }

    protected void addOptionalHeaderFields(Field... field)
    {
        optionalHeaderFields.addAll(Arrays.asList(field));
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
}
