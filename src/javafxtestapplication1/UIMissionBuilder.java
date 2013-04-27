/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Gregor
 */
public class GUIBuilder {
    
    private Class buildGuiFromMe;
    private Method[] privateMethods;
    private Method[] publicMethods;
    private Field[] privateFields;
    private Field[] publicFields;
    private Constructor<?>[] privateConstructors;
    private Constructor<?>[] publicConstructors;
    
    public GUIBuilder()
    {
        buildGuiFromMe = null;
    }
    
    public GUIBuilder(Class someClass)
    {
        this.buildGuiFromMe = someClass;
        extractMethodsFieldsAndConstructors();
    }
    
    public Class getThisClass()
    {
        return this.buildGuiFromMe;
    }
    
    public void changeClassTo(Class newClass)
    {
        this.buildGuiFromMe = newClass;
        this.extractMethodsFieldsAndConstructors();
    }
    
    public void printPrivateConstructors()
    {
        System.out.println("Private Konstruktoren:");
        for(Constructor constructor : privateConstructors)
        {
            System.out.println(constructor.toString());
            System.out.println(constructor.getGenericParameterTypes().length);
            System.out.println(constructor.toGenericString());
        }
    }
    
    public Constructor getPrivateConstructor(int index)
    {
        return this.getPrivateConstructor(index);
    }
    
    public void experiment()
    {
        System.out.println("generic parameter types - " + privateConstructors[3].getGenericParameterTypes());
        System.out.println("modifiers - " + privateConstructors[3].getModifiers());
        System.out.println("parameter types - " + privateConstructors[3].getParameterTypes());
        System.out.println("type parameters - " + privateConstructors[3].getTypeParameters());
    }
    
    public void printMethodsFieldsAndConstructors()
    {
//         privateMethods[0].getModifiers(); liefert public static final etc
        System.out.println("Private Methoden:");
        for(int i=0; i<privateMethods.length; i++)
        {
            System.out.println(privateMethods[i]);
        }
        System.out.println("Public Methoden:");
        for(int i=0; i<publicMethods.length; i++)
        {
            System.out.println(publicMethods[i]);
        }
        System.out.println("Private Felder:");
        for(int i=0; i<privateFields.length; i++)
        {
            System.out.println(privateFields[i]);
        }
        System.out.println("Public Felder:");
        for(int i=0; i<publicFields.length; i++)
        {
            System.out.println(publicFields[i]);
        }
        System.out.println("Private Konstruktoren:");
        for(int i=0; i<privateConstructors.length; i++)
        {
            System.out.println(privateConstructors[i]);
        }
        System.out.println("Public Konstruktoren:");
        for(int i=0; i<publicConstructors.length; i++)
        {
            System.out.println(publicConstructors[i]);
        }
    }

    private void extractMethodsFieldsAndConstructors() throws SecurityException {
        this.privateMethods = this.buildGuiFromMe.getDeclaredMethods();       // wahrscheinlich nur zum aktuellen objekt gehörend ohne superklassen
        this.publicMethods = this.buildGuiFromMe.getMethods();                // alle methode (auch geerbte)
        this.privateFields = this.buildGuiFromMe.getDeclaredFields();         // alle variablen der klasse (member)
        this.publicFields = this.buildGuiFromMe.getFields();                  // weiß nicht so genau, nur das geerbte utilitys ding ?!? 
        this.privateConstructors = this.buildGuiFromMe.getDeclaredConstructors();     // für die klasse Answer gleiche ausgabe wie getConstructors(), von daher ist der unterschied noch nicht ersichtlich
        this.publicConstructors = this.buildGuiFromMe.getConstructors();
    }
    
    public GridPane makeForm()
    {
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        
        ArrayList<Label> labels = new ArrayList<>();
        for(int i=0; i<privateFields.length; i++)
        {
            labels.add(new Label(privateFields[i].getName()));
        }
        final ArrayList<TextField> textFields = new ArrayList<>();
        for(int i=0; i<labels.size(); i++)
        {
            textFields.add(new TextField());
        }
        Button saveButton = new Button("Save");
        StringProperty answerText = new SimpleStringProperty();
//        saveButton.setOnAction(new EventHandler<ActionEvent>());
        
        for(int i=0; i<labels.size(); i++)
        {
            GridPane.setHalignment(labels.get(i), HPos.RIGHT);
            gridpane.add(labels.get(i), 0, i);
            GridPane.setHalignment(textFields.get(i), HPos.LEFT);
            gridpane.add(textFields.get(i), 1, i);
        }
        GridPane.setHalignment(saveButton, HPos.RIGHT);
        gridpane.add(saveButton, 1, labels.size());
        return gridpane;
    }
    
}
