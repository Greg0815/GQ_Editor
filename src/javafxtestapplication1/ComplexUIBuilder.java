/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;

/**
 *
 * @author Gregor
 */
public class ComplexUIBuilder extends UIBuilder {

    public ComplexUIBuilder(Object objectReference) {
        super(objectReference);
    }

    public TitledPane makeForm() throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
//        processSuperClass();
//        processFields();
        processClass();
        return returnTitledPane;
    }

    private void processClass() throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        ArrayList<Class> classes = new ArrayList<>();
        Class baseClass = uiObject.getClass();

        returnTitledPane.setText(baseClass.getName());

        do {
            classes.add(baseClass);
//           System.out.println(baseClass.toString());
            baseClass = baseClass.getSuperclass();
        } while (!baseClass.equals(Object.class));

        Collections.reverse(classes);

        for (Class currentClass : classes) {
            for (Field field : currentClass.getDeclaredFields()) {
//               System.out.println("CLASS: " + currentClass.toString() + " --- FIELD: " + field.getName() + " --- TYPE: " + field.getType());
                field.setAccessible(true);

                if (field.getType().toString().contains("Property")) {
                    if (field.getName().equalsIgnoreCase("image") || field.getName().equalsIgnoreCase("img")) {
                        FileBrowser fileBrowser = new FileBrowser(field.getName(), "*.jpg", "*.png");
                        fileBrowser.bindToProperty(getStringPropertyByFieldAndClass(field, currentClass));
                        titledPaneContent.getChildren().add(fileBrowser.getNode());
                    } else {
                        titledPaneContent.getChildren().add(makeHBoxDependingOnFieldAndClass(field, currentClass));
                    }
                } else if (field.getType().toString().contains("ArrayList")) {
                    if (!field.getGenericType().toString().contains("Trigger")) {
                        ParameterizedType paramType = (ParameterizedType) field.getGenericType();
                        Class<?> genericClass = (Class<?>) paramType.getActualTypeArguments()[0];
                        Method arrayListAdd = field.getDeclaringClass().getDeclaredMethod("add" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), genericClass);
                        Object genericObject = genericClass.newInstance();
                        arrayListAdd.invoke(uiObject, genericObject);
                        ComplexUIBuilder genericClassUIConstruct = new ComplexUIBuilder(genericObject);
                        titledPaneContent.getChildren().add(genericClassUIConstruct.makeForm());
                    }
                } else if(!isPrimitiveType(field))
                {
                    System.out.println(field.getName() + " - " + field.getType());
                    ComplexUIBuilder currenctClassUIConstruct = new ComplexUIBuilder(field.get(uiObject));
                    titledPaneContent.getChildren().add(currenctClassUIConstruct.makeForm());
                }

            }
        }
        returnTitledPane.setContent(titledPaneContent);
    }

    private boolean isPrimitiveType(Field field)
    {
        if(field.getType().equals(String.class) || field.getType().equals(Long.class) || field.getType().equals(Boolean.class) || field.getType().equals(Integer.class))
            return true;
        return false;
    }
    
    private void processSuperClass() throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        Class superClass = uiClass.getSuperclass();
        if (!superClass.equals(Object.class)) {
            for (Field field : superClass.getDeclaredFields()) {
                field.setAccessible(true);
//                if(field.getName().equalsIgnoreCase("type"))
//                {
//                    returnTitledPane.setText(field.get(uiObject).toString());
//                }
                if (field.getName().contains("image") || field.getName().contains("img")) {
                    FileBrowser fileBrowser = new FileBrowser(field.getName(), "*.jpg", "*.png");
                    StringProperty invokedProperty = getStringPropertyByFieldAndClass(field, superClass);
                    fileBrowser.bindToProperty(invokedProperty);
                    titledPaneContent.getChildren().add(fileBrowser.getNode());
                } else if (field.getType().getName().contains("Property")) {
                    Method propertyMethod = getMethodPropertyByFieldAndClass(field, superClass);
                    titledPaneContent.getChildren().add(makeHBoxDependingOnPropertyType(propertyMethod));
                    break;
                } else if (!field.getGenericType().toString().contains("Trigger") && field.getType().getName().contains("ArrayList")) {
                    ParameterizedType paramType = (ParameterizedType) field.getGenericType();
                    Class<?> genericClass = (Class<?>) paramType.getActualTypeArguments()[0];
                    Method arrayListAdd = field.getDeclaringClass().getDeclaredMethod("add" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), genericClass);
                    Object genericObject = genericClass.newInstance();
                    arrayListAdd.invoke(uiObject, genericObject);
                    ComplexUIBuilder genericClassUIConstruct = new ComplexUIBuilder(genericObject);

                    titledPaneContent.getChildren().add(genericClassUIConstruct.makeForm());
                    break;
                } else if (!field.getType().toString().contains("String") && !field.getType().toString().contains("Integer") && !field.getType().toString().contains("Long")) {
                    ComplexUIBuilder superClassUIConstruct = new ComplexUIBuilder(field.get(uiObject));
                    titledPaneContent.getChildren().add(superClassUIConstruct.makeForm());
                }
            }
        }
    }

    private void processFields() throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        returnTitledPane.setText(uiClass.getSimpleName());
        for (final Field field : uiClass.getDeclaredFields()) {
            System.out.println("field.getName(): " + field.getName());
            field.setAccessible(true);
            if (field.getName().contains("image") || field.getName().contains("img")) {
                FileBrowser fileBrowser = new FileBrowser(field.getName(), "*.jpg", "*.png");
                StringProperty invokedProperty = getStringPropertyByFieldAndClass(field, uiClass);
                invokedProperty.bindBidirectional(fileBrowser.filePathProperty());
                titledPaneContent.getChildren().add(fileBrowser.getNode());
            } else if (field.getType().getName().contains("Property")) {
                Method propertyMethod = getMethodPropertyByFieldAndClass(field, uiClass);
                titledPaneContent.getChildren().add(makeHBoxDependingOnPropertyType(propertyMethod));
            } else if (!field.getGenericType().toString().contains("Trigger") && field.getType().getName().contains("ArrayList")) {
                ParameterizedType paramType = (ParameterizedType) field.getGenericType();
                final Class<?> genericClass = (Class<?>) paramType.getActualTypeArguments()[0];
                final Method arrayListAdd = field.getDeclaringClass().getDeclaredMethod("add" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), genericClass);
                Object genericObject = genericClass.newInstance();
//                    Object genericObject = field.get(uiObject);
                arrayListAdd.invoke(uiObject, genericObject);
                ComplexUIBuilder genericClassUIConstruct = new ComplexUIBuilder(genericObject);

//                    System.out.println("Field.getName(): " + field.getName());
                Button addArrayListItem = new Button("add " + field.getName());
                addArrayListItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        try {
//                                    Object newGenericObject = genericClass.newInstance();
//                                    arrayListAdd.invoke(uiObject, newGenericObject);
                            Method arrayListAdd = uiClass.getMethod("add" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
//                                    Mission newMission = (Mission)arrayListAdd.invoke(uiObject);
                            Method arrayListSize = uiClass.getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1) + "Count");
                            int size = (int) arrayListSize.invoke(uiObject);
                            titledPaneContent.getChildren().add(titledPaneContent.getChildren().size() - 1, new ComplexUIBuilder(arrayListAdd.invoke(uiObject)).makeForm());
                        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            Logger.getLogger(ComplexUIBuilder.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                titledPaneContent.getChildren().addAll(addArrayListItem, genericClassUIConstruct.makeForm());
            } else if (!field.getType().toString().contains("String") && !field.getType().toString().contains("Integer") && !field.getType().toString().contains("Long")) {
                ComplexUIBuilder superClassUIConstruct = new ComplexUIBuilder(field.get(uiObject));
                titledPaneContent.getChildren().add(superClassUIConstruct.makeForm());
            }
        }
        returnTitledPane.setContent(titledPaneContent);
    }
}
