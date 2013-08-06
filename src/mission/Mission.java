/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mission;

import java.io.File;
import main.GameComponent;
import main.UtilityFunctions;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gregor
 */
abstract public class Mission extends GameComponent
{
    private String type;

    public Mission(String type)
    {
        super();
        this.type = type;
    }

    public String getType()
    {
        return this.type;
    }

    public String buildMissionTail()
    {
        return "</mission>";
    }

    public String buildMissionHead()
    {
        return buildHeaderString() + utilitys.createStringFromArrayList(getTrigger());
    }

    private String buildStringFromFieldArrayList(ArrayList<Field> fields)
    {
        String returnString = "";
        for (Field field : fields)
        {
            try
            {
                field.setAccessible(true);
                if (field.getType().toString().contains("StringProperty"))
                {
                    StringProperty fld = (StringProperty) field.get(this);
                    if (!fld.get().isEmpty())
                    {
                        Method mtd = UtilityFunctions.class.getMethod(field.getName(), String.class);
                        returnString += mtd.invoke(utilitys, fld.get());
                    }
                }
                else if (field.getType().toString().contains("IntegerProperty"))
                {
                    IntegerProperty fld = (IntegerProperty) field.get(this);    // TODO sinnvolle werte früher rausfiltern
                    if (-180 <= fld.get() && fld.get() <= 10000)      // Longitude/Latitude Range up to -180, StartScreen duration 10000ms = 10sec useful value?
                    {
                        Method mtd = UtilityFunctions.class.getMethod(field.getName(), int.class);
                        returnString += mtd.invoke(utilitys, fld.get());
                    }
                }
                else if (field.getType().toString().contains("BooleanProperty"))
                {
                    BooleanProperty fld = (BooleanProperty) field.get(this);
                    Method mtd = UtilityFunctions.class.getMethod(field.getName(), Boolean.class);
                    returnString += mtd.invoke(utilitys, fld.get());
                }
            }
            catch (InvocationTargetException | NoSuchMethodException | IllegalArgumentException | IllegalAccessException e)
            {
                System.out.println("Mission Class - buildHeaderString function - getNecessaryHeaderFields Part: " + e);
            }
        }
        return returnString;
    }

    private String buildHeaderString()
    {
        String tmp = "<mission" + utilitys.type(type);
        tmp += buildStringFromFieldArrayList(getNecessaryHeaderFields());
        tmp += buildStringFromFieldArrayList(getOptinalHeaderFields());
        tmp += ">";
        return tmp;
    }
}
