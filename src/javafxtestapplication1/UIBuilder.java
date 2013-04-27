/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import unused.SimpleUIBuilder;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Gregor
 */
public class UIBuilder
{
    protected Class uiClass;
    protected Object uiObject;
    protected TitledPane returnTitledPane;
    protected VBox titledPaneContent;
    
    public UIBuilder(Object objectReference)
    {
        initialization(objectReference);
        returnTitledPane = new TitledPane();
        titledPaneContent = new VBox();
    }
    
        private void initialization(Object objectReference)
    {
        try
        {
            uiClass = objectReference.getClass();
            uiObject = uiClass.newInstance();    // force simpleObject to be cast to type of objectReference; wirklich nötig???
            uiObject = objectReference;
        }
        catch(InstantiationException | IllegalAccessException ex)
        {
            System.out.println(ex);
        }
    }
    
    protected StringProperty getStringPropertyByFieldAndClass(Field field, Class currentClass) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        Method propertyMethod = getMethodPropertyByFieldAndClass(field, currentClass);
//        System.out.println("propertyMethod = " + propertyMethod.getName());
        StringProperty invokedProperty = (StringProperty)propertyMethod.invoke(uiObject);
//        if(invokedProperty.equals(null))
//            System.out.println(true);
        return invokedProperty;
    }
    
    protected BooleanProperty getBooleanPropertyByFieldAndClass(Field field, Class currentClass) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        Method propertyMethod = getMethodPropertyByFieldAndClass(field, currentClass);
        BooleanProperty invokedProperty = (BooleanProperty)propertyMethod.invoke(uiObject);
        return invokedProperty;
    }
    
    protected IntegerProperty getIntegerPropertyByFieldAndClass(Field field, Class currentClass) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        Method propertyMethod = getMethodPropertyByFieldAndClass(field, currentClass);
        IntegerProperty invokedProperty = (IntegerProperty)propertyMethod.invoke(uiObject);
        return invokedProperty;
    }

    protected Method getMethodPropertyByFieldAndClass(Field field, Class currentClass) throws NoSuchMethodException, SecurityException {
        Method methodProperty = currentClass.getMethod(field.getName() + "Property");
        return methodProperty;
    }
    
    protected HBox makeHBoxDependingOnFieldAndClass(Field field, Class currentClass) throws IllegalAccessException, SecurityException, IllegalArgumentException, NoSuchMethodException
    {
        HBox returnHBox = new HBox();
        try
        {
            if(field.getType().toString().contains("StringProperty"))
            {
                TextField bindToStringPropertyTextField = new TextField();
                bindToStringPropertyTextField.textProperty().bindBidirectional(getStringPropertyByFieldAndClass(field, currentClass));
                returnHBox.getChildren().addAll(new Label(field.getName()), bindToStringPropertyTextField);
            }
            else if(field.getType().toString().contains("IntegerProperty"))
            {
                NumericTextField bindToNumiercPropertyTextField = new NumericTextField();
                bindToNumiercPropertyTextField.textProperty().bindBidirectional(getIntegerPropertyByFieldAndClass(field, currentClass), new NumberStringConverter());
                returnHBox.getChildren().addAll(new Label(field.getName()), bindToNumiercPropertyTextField);
            }
            else if(field.getType().toString().contains("BooleanProperty"))
            {
                ChoiceBox bindToBooleanPropertyChoiceBox = new ChoiceBox();
                bindToBooleanPropertyChoiceBox.getItems().addAll(true, false);
                bindToBooleanPropertyChoiceBox.getSelectionModel().selectFirst();
                bindToBooleanPropertyChoiceBox.valueProperty().bindBidirectional(getBooleanPropertyByFieldAndClass(field, currentClass));
                returnHBox.getChildren().addAll(new Label(field.getName()), bindToBooleanPropertyChoiceBox);
            }
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException ex)
        {
            Logger.getLogger(SimpleUIBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnHBox;
    }
    
    protected HBox makeHBoxDependingOnPropertyType(Method propertyMethod)
    {
        HBox returnHBox = new HBox();
        
        try {
            if(propertyMethod.getReturnType().getName().contains("StringProperty"))
            {
                TextField bindToStringPropertyTextField = new TextField();
//                Method propertyMethod = uiClass.getMethod(object.getName() + "Property");
                StringProperty invokedProperty = (StringProperty)propertyMethod.invoke(uiObject);
                bindToStringPropertyTextField.textProperty().bindBidirectional(invokedProperty);
                returnHBox.getChildren().addAll(new Label(propertyMethod.getName()), bindToStringPropertyTextField);
            }
            else if(propertyMethod.getReturnType().getName().contains("BooleanProperty"))
            {
                ChoiceBox bindToBooleanPropertyChoiceBox = new ChoiceBox();
                bindToBooleanPropertyChoiceBox.getItems().addAll(true, false);
                bindToBooleanPropertyChoiceBox.getSelectionModel().selectFirst();
//                Method propertyMethod = uiClass.getMethod(object.getName() + "Property");
                BooleanProperty invokedProperty = (BooleanProperty)propertyMethod.invoke(uiObject);
                bindToBooleanPropertyChoiceBox.valueProperty().bindBidirectional(invokedProperty);
//                Bindings.bindBidirectional(bindToBooleanPropertyChoiceBox.valueProperty(), invokedProperty);
                returnHBox.getChildren().addAll(new Label(propertyMethod.getName()), bindToBooleanPropertyChoiceBox);
            }
            else if(propertyMethod.getReturnType().getName().contains("IntegerProperty"))
            {
                NumericTextField bindToNumiercPropertyTextField = new NumericTextField();
//                Method propertyMethod = uiClass.getMethod(object.getName() + "Property");
                IntegerProperty invokedProperty = (IntegerProperty)propertyMethod.invoke(uiObject);
                bindToNumiercPropertyTextField.textProperty().bindBidirectional(invokedProperty, new NumberStringConverter());
                returnHBox.getChildren().addAll(new Label(propertyMethod.getName()), bindToNumiercPropertyTextField);
            }
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException ex)
        {
            Logger.getLogger(SimpleUIBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnHBox;
    }
}
