/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unused;

import Mission.Mission;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import Main.NumericTextField;
import Main.NumericTextField;
import unused.UIInnerClassBuilder;

/**
 *
 * @author Gregor
 */
public class UIMissionBuilder {

    private Class mainClass;
    private Class superClass;
    private Mission missionReference;
    private ArrayList<Method> mainClassProperties;
    private ArrayList<Method> superClassProperties;
    TitledPane returnTitledPane;
    VBox returnTitledPaneContent;

    public UIMissionBuilder(Mission missionReference) {
        this.mainClass = missionReference.getClass();
        this.missionReference = missionReference;
        this.superClass = missionReference.getClass().getSuperclass();
//        System.out.println("superclass ist == " + superClass.getName());
        mainClassProperties = new ArrayList<>();
        superClassProperties = new ArrayList<>();
        extractProperties();
    }

    private void extractProperties() throws SecurityException {
        for (Method method : mainClass.getDeclaredMethods())
        {
            if (method.getName().toLowerCase().contains("property")) {
                mainClassProperties.add(method);
            }
        }
        for (Method method : superClass.getDeclaredMethods())
        {
            if (method.getName().toLowerCase().contains("property")) {
                superClassProperties.add(method);
            }
        }
    }

    public TitledPane makeMissionForm() // muss irgendwas zurückgeben
    {
        returnTitledPane = new TitledPane();
        returnTitledPaneContent = new VBox();

        for (Method property : superClassProperties) {
            if (property.getName().toLowerCase().contains("id"))
            {
                Label boundToId = new Label();
                try {
                    boundToId.textProperty().bindBidirectional((StringProperty) property.invoke(missionReference));
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(UIMissionBuilder.class.getName()).log(Level.SEVERE, null, ex);
                }
                boundToId.setText(missionReference.getType());
                returnTitledPane.setGraphic(boundToId);
            }
            String propertyType = property.getReturnType().toString().substring(28);
            makeHBoxDependingOnPropertyType(propertyType, property, returnTitledPaneContent);
        }

        for (Method property : mainClassProperties) {
            String propertyType = property.getReturnType().toString().substring(28);
            makeHBoxDependingOnPropertyType(propertyType, property, returnTitledPaneContent);
        }

        returnTitledPane.setContent(returnTitledPaneContent);
        processRemainingFields();
        return returnTitledPane;
    }

    private void processRemainingFields()
    {
        for(Field field : missionReference.getClass().getDeclaredFields())
        {
            if(!field.getType().getName().toLowerCase().contains("property"))
            {
//                System.out.println("field name == " + field.getName());
                try
                {
                    field.setAccessible(true);
                    UIInnerClassBuilder innerClass = new UIInnerClassBuilder(field.get(missionReference));    // statt field irgendwas wie missionReference.getObject(field.getName())
                    returnTitledPaneContent.getChildren().add(innerClass.makeForm());
                } catch (IllegalArgumentException | IllegalAccessException ex)
                {
                    Logger.getLogger(UIMissionBuilder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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

    public void printMethods()
    {
        System.out.println("main class Methoden:");
        for (int i = 0; i < mainClass.getDeclaredMethods().length; i++) {
            System.out.println(mainClass.getDeclaredMethods()[i].toString());
        }
        System.out.println("super class Methoden:");
        for (int i = 0; i < superClass.getDeclaredMethods().length; i++) {
            System.out.println(superClass.getDeclaredMethods()[i].toString());
        }
    }

    public void printProperty() {
        for (Method method : mainClassProperties) {
            System.out.println("\n" + method.getName());
        }
        for (Method method : superClassProperties) {
            System.out.println("\n" + method.getName());
        }
    }

    private void makeHBoxDependingOnPropertyType(String propertyType, Method property, VBox returnValueContent)
    {
        if (propertyType.equalsIgnoreCase("StringProperty"))
        {
            try
            {
                StringProperty invokedProperty = (StringProperty) property.invoke(missionReference);
                returnValueContent.getChildren().add(makeTextualHBox(property.getName().replace("Property", ""), invokedProperty));
            } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
                System.out.println(e);
            }

        } else if (propertyType.equalsIgnoreCase("IntegerProperty"))
        {
            try
            {
                IntegerProperty invokedProperty = (IntegerProperty) property.invoke(missionReference);
                returnValueContent.getChildren().add(makeNumbericHBox(property.getName().replace("Property", ""), invokedProperty));
            } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
                System.out.println(e);
            }
        } else if (propertyType.equalsIgnoreCase("BooleanProperty"))
        {
            try
            {
                BooleanProperty invokedProperty = (BooleanProperty) property.invoke(missionReference);
                returnValueContent.getChildren().add(makeBooleanChoiceBox(property.getName().replace("Property", ""), invokedProperty));
            } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
                System.out.println(e);
            }
        }
    }
}
