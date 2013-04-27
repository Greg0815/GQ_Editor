/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unused;

import java.lang.reflect.Field;
import javafx.scene.control.TitledPane;
import javafxtestapplication1.UIBuilder;

/**
 *
 * @author Gregor
 */
public class SimpleUIBuilder extends UIBuilder
{
    
    public SimpleUIBuilder(Object objectReference)
    {
        super(objectReference);
    }


    public TitledPane makeForm()
    {
        for(Field field : uiClass.getDeclaredFields())
        {
            field.setAccessible(true);
//            titledPaneContent.getChildren().add(makeHBoxDependingOnPropertyType(field.get(uiObject)));
        }
        returnTitledPane.setContent(titledPaneContent);
        return returnTitledPane;
    }
    

    
}
