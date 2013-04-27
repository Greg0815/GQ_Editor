/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unused;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.NumberStringConverter;
import javafxtestapplication1.NumericTextField;
import javafxtestapplication1.NumericTextField;

/**
 *
 * @author Gregor
 */
public class UIInnerClassBuilder
{
    private Class innerClass;
    private Object objectReference;
    private ArrayList<Method> innerClassProperties;
    private TitledPane returnTitledPane;
    private VBox returnTitledPaneContent;
    
    public UIInnerClassBuilder(Object objectReference)
    {
        try {
            System.out.println("übergebenes objekt klasse == " + objectReference.getClass().toString());
            this.objectReference = new Object();
            this.objectReference = objectReference.getClass().newInstance();
            this.objectReference = objectReference;
            this.innerClass = objectReference.getClass();
            innerClassProperties = new ArrayList<>();
            extractProperties();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UIInnerClassBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void extractProperties()
    {
        for(Method method : innerClass.getDeclaredMethods())
        {
            if(method.getName().toLowerCase().contains("property"))
            {
                innerClassProperties.add(method);
            }
        }
    }
    
    public TitledPane makeForm()
    {
        returnTitledPane = new TitledPane();
        returnTitledPaneContent = new VBox();
        processPropertys();
        processNonPropertys();
        return returnTitledPane;
    }

    private void processNonPropertys()
    {
//        for(Field field : innerClass.getDeclaredFields())
//        {
//            System.out.println(field);
//        }
        
        for(Field field : innerClass.getDeclaredFields())
        {
            if(!field.getType().getName().toLowerCase().contains("property"))       // Propertys aussortieren
            {
//                System.out.println("InnerClassBuilder : processRemainingFields : field.getName.notcontains.property : " + field.getName());
                try
                {
                    if(field.getType().getName().toLowerCase().contains("arraylist"))     // ArrayList abarbeiten
                    {
                        // arraylist objekt hinzufügen button   : button erzeugen : event feuern : objekt erzeugen : objekt hinzufügen grafisch und objektisch
                        // arraylist objekt löschen button      : prüfen ob letztes element : falls nicht event feuern : objekt löschen : Ansicht zerstören
                        // ein initiales objekt erstellen und anfügen
                        field.setAccessible(true);
                    }
                    else
                    {
                        field.setAccessible(true);
                        UIInnerClassBuilder innerClassBuilder;
                        if(field.getGenericType().toString().toLowerCase().contains("arraylist"))
                        {
                            ParameterizedType paramType = (ParameterizedType)field.getGenericType();
                            Class<?> genericClass = (Class<?>)paramType.getActualTypeArguments()[0];
                            Method arrayListAdd = field.getDeclaringClass().getDeclaredMethod("add" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), genericClass);
                            Object hopefullyAnAnswerObject = genericClass.newInstance();
                            arrayListAdd.invoke(objectReference, hopefullyAnAnswerObject);
                            innerClassBuilder = new UIInnerClassBuilder(hopefullyAnAnswerObject);
                        }
                        else
                        {
                            innerClassBuilder = new UIInnerClassBuilder(field.get(this.objectReference));
                        }
                        returnTitledPaneContent.getChildren().add(innerClassBuilder.makeForm());
                    }
                } catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InstantiationException ex)
                {
                    Logger.getLogger(UIInnerClassBuilder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void makeHBoxDependingOnPropertyType(String propertyType, Method property, VBox returnValueContent)
    {
        try
        {
            if (propertyType.equalsIgnoreCase("StringProperty"))
            {
                StringProperty invokedProperty = (StringProperty) property.invoke(objectReference);
                returnValueContent.getChildren().add(makeTextualHBox(property.getName().replace("Property", ""), invokedProperty));

            } else if (propertyType.equalsIgnoreCase("IntegerProperty"))
            {
                IntegerProperty invokedProperty = (IntegerProperty) property.invoke(objectReference);
                returnValueContent.getChildren().add(makeNumbericHBox(property.getName().replace("Property", ""), invokedProperty));

            } else if (propertyType.equalsIgnoreCase("BooleanProperty"))
            {
                BooleanProperty invokedProperty = (BooleanProperty) property.invoke(objectReference);
                returnValueContent.getChildren().add(makeBooleanChoiceBox(property.getName().replace("Property", ""), invokedProperty));
            }
        } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
                System.out.println(e);
        }
    }

    public HBox makeTextualHBox(String label, StringProperty stringProperty) // später private machen
    {
        TextField bindToPropertyTextField = new TextField();
        bindToPropertyTextField.textProperty().bindBidirectional(stringProperty);
        HBox returnHBox = new HBox();
        returnHBox.getChildren().addAll(new Label(label), bindToPropertyTextField);
        return returnHBox;
    }

    public HBox makeBooleanChoiceBox(String label, BooleanProperty booleanProperty) // später private machen
    {
        ChoiceBox bindToPropertyChoiceBox = new ChoiceBox();
        bindToPropertyChoiceBox.getItems().addAll("true", "false");
        bindToPropertyChoiceBox.getSelectionModel().selectFirst();
        BooleanStringConverter booleanStringConverter = new BooleanStringConverter();
        Bindings.bindBidirectional(bindToPropertyChoiceBox.valueProperty(), booleanProperty, booleanStringConverter);
        HBox returnHBox = new HBox();
        returnHBox.getChildren().addAll(new Label(label), bindToPropertyChoiceBox);
        return returnHBox;
    }

    public HBox makeNumbericHBox(String label, IntegerProperty integerProperty) // später private machen
    {
        NumericTextField bindToPropertyNumericTextField = new NumericTextField();
        NumberStringConverter numberStringConverter = new NumberStringConverter();
        bindToPropertyNumericTextField.textProperty().bindBidirectional(integerProperty, numberStringConverter);
        HBox returnHBox = new HBox();
        returnHBox.getChildren().addAll(new Label(label), bindToPropertyNumericTextField);
        return returnHBox;
    }

    private void processPropertys()
    {
        for(Method property : innerClassProperties)
        {
            String propertyType = property.getReturnType().toString().substring(28);
            makeHBoxDependingOnPropertyType(propertyType, property, returnTitledPaneContent);
        }
        returnTitledPane.setContent(returnTitledPaneContent);
    }
}
