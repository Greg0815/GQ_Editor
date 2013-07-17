/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import containers.Container;
import containers.Game;
import containers.LinearContainer;
import containers.VariableContainer;
import customInputNodes.FileBrowser;
import customInputNodes.NumericTextField;
import mission.components.MissionComponent;
import mission.Mission;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Gregor
 */
public class UIBuilder
{
    private Game game;
    private ArrayList<Node> invisibleNodes;
    private Boolean allNodesVisible = false;

    public UIBuilder(Game gameObjectForUIBuildingProcess)
    {
        game = gameObjectForUIBuildingProcess;
        invisibleNodes = new ArrayList<>();
    }

    public void turnNodesVisible()
    {
        for (Node node : invisibleNodes)
        {
            allNodesVisible = true;
            node.managedProperty().bind(node.visibleProperty());
            node.setVisible(true);
        }
    }

    public void turnNodesInvisible()
    {
        for (Node node : invisibleNodes)
        {
            allNodesVisible = false;
            node.managedProperty().bind(node.visibleProperty());
            node.setVisible(false);
        }
    }

    private TitledPane parseGameElement(GameComponent gameElement)
    {
        if (gameElement instanceof Mission)
        {
            return parseMission(gameElement);
        }
        return null;
    }

    private TitledPane parseContainer(Container container)
    {
        if (container instanceof LinearContainer)
        {
            return parseLinearContainer(container);
        }
        else if (container instanceof VariableContainer)
        {
            return parseVariableContainer(container);
        }
        return null;
    }

    private Node parseMissionComponent(MissionComponent misComp)
    {
        VBox missionComponentLayout = new VBox();
        HBox missionComponentLayoutContent = new HBox();
        missionComponentLayoutContent.setStyle("-fx-border:style solid; -fx-border-width:1; -fx-border-color: black;");
        for (Field someField : misComp.getNecessaryHeaderFields())
        {
            missionComponentLayout.getChildren().add(makeHBoxDependingOnFieldAndMissionObject(someField, misComp));
        }
        for (Field someField : misComp.getOptinalHeaderFields())
        {
            if (allNodesVisible)
            {
                missionComponentLayout.getChildren().add(makeInvisibleHBoxForOptionalFieldsDependingOnFieldAndClass(someField, misComp));
                this.turnNodesVisible();
            }
            else
            {
                missionComponentLayout.getChildren().add(makeInvisibleHBoxForOptionalFieldsDependingOnFieldAndClass(someField, misComp));
            }
        }
        for (Field someField : misComp.getNecessaryNonHeaderFields())
        {
            missionComponentLayout.getChildren().add(makeHBoxDependingOnFieldAndMissionObject(someField, misComp));
        }
        missionComponentLayout.getChildren().add(missionComponentLayoutContent);

        return missionComponentLayout;
    }

    private TitledPane parseMission(GameComponent gameElement)
    {
        Mission mission = (Mission) gameElement;
        TitledPane missionLayout = new TitledPane();
        VBox titledPaneContent = new VBox();
        titledPaneContent.setStyle("-fx-background-color: green;");
        missionLayout.setText(mission.getClass().getSimpleName());
        missionLayout.setContent(titledPaneContent);
        missionLayout.setExpanded(true);

        ArrayList<Field> allNecessaryMissionFields = new ArrayList<>();
        allNecessaryMissionFields.addAll(mission.getNecessaryHeaderFields());
        allNecessaryMissionFields.addAll(mission.getNecessaryNonHeaderFields());

        for (Field field : allNecessaryMissionFields)
        {
            if (field.getType().toString().toLowerCase().contains("property")) // nicht-arraylist feld
            {
                titledPaneContent.getChildren().add(makeHBoxDependingOnFieldAndMissionObject(field, mission));
            }
            else if (field.getType().toString().contains(ArrayList.class.getName())) // arraylist feld
            {
                titledPaneContent.getChildren().add(makeVBoxForArrayList(field, mission));
            }
//                else
//                {
//                    titledPaneContent.getChildren().add(parseMissionComponent(field));
//                }
        }
        for (Field field : mission.getOptinalHeaderFields())    // Bearbeitet aktuell nur Property-type Felder
        {
            System.out.println(field.getName());
            if (field.getType().toString().toLowerCase().contains("property")) // nicht-arraylist feld
            {
//                    HBox invisibleBox = makeInvisibleHBoxForOptionalFieldsDependingOnFieldAndClass(field, mission);
//                    invisibleBox.setVisible(false);
//                    invisibleNodes.add(invisibleBox);
                titledPaneContent.getChildren().add(makeInvisibleHBoxForOptionalFieldsDependingOnFieldAndClass(field, mission));
            }
//                else if (field.getType().toString().contains(ArrayList.class.getName())) // arraylist feld
//                {
//                    System.out.println("wird nie ausgeführt!");
//                    VBox invisibleVBox = makeVBoxForArrayList(field, mission);
//                    invisibleVBox.setVisible(false);
//                    invisibleNodes.add(invisibleVBox);
//                    titledPaneContent.getChildren().add(invisibleVBox);
//                }
        }
        return missionLayout;
    }

    private TitledPane parseLinearContainer(Container container)
    {
        LinearContainer linCont = (LinearContainer) container;

        TitledPane outerContainerLayout = new TitledPane();
        outerContainerLayout.setText(linCont.getId());
        outerContainerLayout.setExpanded(false);

        VBox innerContainerLayout = new VBox();
        outerContainerLayout.setContent(innerContainerLayout);

        Accordion innerContainerLayoutContent = new Accordion();
        innerContainerLayout.getChildren().add(innerContainerLayoutContent);

        if (linCont.getGameElements().size() > 0)
        {
            for (int i = 0; i < linCont.getGameElements().size(); i++)
            {
                innerContainerLayoutContent.getPanes().add(parseGameElement(linCont.getGameElements().get(i)));
            }
        }
        else
        {
            for (Class allowedClass : linCont.getAllowedClasses())
            {
//            TitledPane missionTitledPane = new TitledPane();
//            missionTitledPane.setText(allowedClass.getSimpleName());
//            missionTitledPane.setExpanded(false);

                GameComponent newGameElement = linCont.makeNewGameElementByClass(allowedClass);
                newGameElement.setId(linCont.getId() + "-" + ((Mission) newGameElement).getType() + "-" + linCont.getGameElements().indexOf(newGameElement));

//            UIBuilder newGameElementUI = new UIBuilder(newGameElement);
//            missionTitledPane.setContent();
                innerContainerLayoutContent.getPanes().add(parseGameElement(newGameElement));
            }
        }
        return outerContainerLayout;
    }

    private TitledPane parseVariableContainer(Container container)
    {
        final VariableContainer varCont = (VariableContainer) container;
        TitledPane outerContainerLayout = new TitledPane();
        outerContainerLayout.setText(varCont.getId());
        outerContainerLayout.setExpanded(false);

        VBox innerContainerLayout = new VBox();
        outerContainerLayout.setContent(innerContainerLayout);

        final Accordion innerContainerLayoutContent = new Accordion();

        if (varCont.getGameElements().size() > 0)    // es sind objekte vorhanden, also kein jungfräuliches game mehr
        {
            for (int i = 0; i < varCont.getGameElements().size(); i++)
            {
                innerContainerLayoutContent.getPanes().add(parseGameElement(varCont.getGameElements().get(i)));
            }
        }
        else if (varCont.getMaxMissionCount() == 1 && varCont.getAllowedClasses().size() == 1)
        {
            GameComponent newGameElementInstance = varCont.makeNewGameElementByClass(varCont.getAllowedClasses().get(0));
            newGameElementInstance.setId(varCont.getId() + "-" + ((Mission) newGameElementInstance).getType() + "-" + varCont.getGameElements().indexOf(newGameElementInstance));
            innerContainerLayoutContent.getPanes().add(parseGameElement(newGameElementInstance));
        }
        else
        {
            HBox addToTitledPaneContentButtons = new HBox();
            for (final Class allowedClass : varCont.getAllowedClasses())
            {
                Button addMissionByClassButton = new Button("Add " + allowedClass.getSimpleName());
                addMissionByClassButton.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent t)
                    {
                        if (varCont.hasSpaceLeft())
                        {
                            final GameComponent newGameElementInstance = varCont.makeNewGameElementByClass(allowedClass);
                            newGameElementInstance.setId(varCont.getId() + "-" + ((Mission) newGameElementInstance).getType() + "-" + varCont.getGameElements().indexOf(newGameElementInstance));
                            final TitledPane tp = parseGameElement(newGameElementInstance);
                            tp.setOnMouseClicked(new EventHandler<MouseEvent>()
                            {
                                @Override
                                public void handle(MouseEvent t)
                                {
                                    if (t.getButton() == MouseButton.SECONDARY)
                                    {
                                        varCont.deleteGameElement(newGameElementInstance);
                                        innerContainerLayoutContent.getPanes().remove(tp);
                                    }
                                }
                            });
                            innerContainerLayoutContent.getPanes().add(tp);
                        }
                    }
                });
                addToTitledPaneContentButtons.getChildren().add(addMissionByClassButton);
            }
            innerContainerLayout.getChildren().add(addToTitledPaneContentButtons);
        }
        // später noch drag and drop

        innerContainerLayout.getChildren().add(innerContainerLayoutContent);

        return outerContainerLayout;
    }

    public Node parseGame()
    {
        VBox gameLayout = new VBox();

        for (String fieldName : game.getNecessaryFields())
        {
            Field field = getFieldByString(fieldName, game);
            HBox hBox = makeHBoxDependingOnFieldAndMissionObject(field, game);
            gameLayout.getChildren().add(hBox);
        }
        for (Container container : game.getContainers())
        {
            gameLayout.getChildren().add(parseContainer(container));
        }

        return gameLayout;
    }

    private HBox makeInvisibleHBoxForOptionalFieldsDependingOnFieldAndClass(Field field, Object missionObject)
    {
        final HBox returnHBox = new HBox();
        returnHBox.managedProperty().bind(returnHBox.visibleProperty());
        returnHBox.setVisible(false);
        invisibleNodes.add(returnHBox);
        try
        {
            if (field.getName().equalsIgnoreCase("image"))
            {
                field.setAccessible(true);
                FileBrowser fb = new FileBrowser("Images", "*.jpg", "*.png", "*.bmp");
                fb.filePathProperty().bindBidirectional(getStringPropertyByFieldAndClass(field, missionObject));
                returnHBox.getChildren().add(fb.getNode());
            }
            else if (field.getType().toString().contains("StringProperty") && !field.getName().equalsIgnoreCase("image"))
            {
                final TextField bindToStringPropertyTextField = new TextField();

                field.setAccessible(true);
                if (!((StringProperty) field.get(missionObject)).get().isEmpty())
                {
                    bindToStringPropertyTextField.setText(((StringProperty) field.get(missionObject)).get());
                }
                bindToStringPropertyTextField.textProperty().bindBidirectional(getStringPropertyByFieldAndClass(field, missionObject));

                //
                //      Color changeing part of TextFields if TextFields are still empty
                bindToStringPropertyTextField.textProperty().addListener(new ChangeListener<String>()
                {
                    @Override
                    public void changed(ObservableValue<? extends String> ov, String oldString, String newString)
                    {
                        if (oldString.isEmpty() && !newString.isEmpty())//bindToStringPropertyTextField.textProperty().get().isEmpty() || bindToStringPropertyTextField.getText().isEmpty())
                        {
                            invisibleNodes.remove(returnHBox);
                        }
                        else if (!oldString.isEmpty() && newString.isEmpty())
                        {
                            invisibleNodes.add(returnHBox);
                        }
                    }
                });
                //
                //
                returnHBox.getChildren().addAll(new Label(field.getName()), bindToStringPropertyTextField);
            }
            else if (field.getType().toString().contains("IntegerProperty"))
            {
                NumericTextField bindToNumiercPropertyTextField = new NumericTextField();
                field.setAccessible(true);
                if (((IntegerProperty) field.get(missionObject)).asString().isNotNull().get())
                {
                    bindToNumiercPropertyTextField.setText(((Integer) field.get(missionObject)).toString());          // Testen !!
                }
                bindToNumiercPropertyTextField.textProperty().bindBidirectional(getIntegerPropertyByFieldAndClass(field, missionObject), new NumberStringConverter());
                returnHBox.getChildren().addAll(new Label(field.getName()), bindToNumiercPropertyTextField);
            }
            else if (field.getType().toString().contains("BooleanProperty"))
            {
                ChoiceBox bindToBooleanPropertyChoiceBox = new ChoiceBox();
                bindToBooleanPropertyChoiceBox.getItems().addAll(true, false);
                field.setAccessible(true);
                if (((BooleanProperty) field.get(missionObject)).asString().isNotNull().get())
                {
                    bindToBooleanPropertyChoiceBox.getSelectionModel().select(((BooleanProperty) field.get(missionObject)).toString());       // Testen !!
                }
                bindToBooleanPropertyChoiceBox.valueProperty().bindBidirectional(getBooleanPropertyByFieldAndClass(field, missionObject));
                returnHBox.getChildren().addAll(new Label(field.getName()), bindToBooleanPropertyChoiceBox);
            }
        }
        catch (IllegalAccessException | IllegalArgumentException | SecurityException ex)
        {
            System.out.println(this.getClass().getSimpleName() + " - makeHBoxDependingOnFieldAndClass: " + ex);
        }
        return returnHBox;
    }

    // Enthält zwei mögliche Fehlerquellen, hat keiner dazu geschrieben welche
    protected HBox makeHBoxDependingOnFieldAndMissionObject(Field field, Object missionObject)
    {
        HBox returnHBox = new HBox();
        try
        {
            if (field.getType().toString().contains("StringProperty"))
            {
                final TextField bindToStringPropertyTextField = new TextField();

                field.setAccessible(true);
                if (!((StringProperty) field.get(missionObject)).get().isEmpty())
                {
                    bindToStringPropertyTextField.setText(((StringProperty) field.get(missionObject)).get());
                }
                else
                {
                    bindToStringPropertyTextField.setStyle("-fx-background-color: red;");
                }
                bindToStringPropertyTextField.textProperty().bindBidirectional(getStringPropertyByFieldAndClass(field, missionObject));

                //
                //      Color changeing part of TextFields if TextFields are still empty
                bindToStringPropertyTextField.textProperty().addListener(new ChangeListener<String>()
                {
                    @Override
                    public void changed(ObservableValue<? extends String> ov, String t, String t1)
                    {
                        if (!t.isEmpty() && t1.isEmpty())
                        {
                            bindToStringPropertyTextField.setStyle("-fx-background-color: red;");
                        }
                        else if (t.isEmpty() && !t1.isEmpty())
                        {
                            bindToStringPropertyTextField.setStyle("-fx-background-color: white;");
                        }
                    }
                });
                //
                //
                returnHBox.getChildren().addAll(new Label(field.getName()), bindToStringPropertyTextField);
            }
            else if (field.getType().toString().contains("IntegerProperty"))
            {
                NumericTextField bindToNumiercPropertyTextField = new NumericTextField();
                field.setAccessible(true);
                if (((IntegerProperty) field.get(missionObject)).asString().isNotNull().get())
                {
                    bindToNumiercPropertyTextField.setText(((Integer) field.get(missionObject)).toString());          // Testen !!
                }
                bindToNumiercPropertyTextField.textProperty().bindBidirectional(getIntegerPropertyByFieldAndClass(field, missionObject), new NumberStringConverter());
                returnHBox.getChildren().addAll(new Label(field.getName()), bindToNumiercPropertyTextField);
            }
            else if (field.getType().toString().contains("BooleanProperty"))
            {
                ChoiceBox bindToBooleanPropertyChoiceBox = new ChoiceBox();
                bindToBooleanPropertyChoiceBox.getItems().addAll(true, false);
                field.setAccessible(true);
                if (((BooleanProperty) field.get(missionObject)).asString().isNotNull().get())
                {
//                            bindToBooleanPropertyChoiceBox.setText(((BooleanProperty)field.get(objectUIHasToBeBuild)).toString());
                    bindToBooleanPropertyChoiceBox.getSelectionModel().select(((BooleanProperty) field.get(missionObject)).toString());       // Testen !!
                }
                bindToBooleanPropertyChoiceBox.valueProperty().bindBidirectional(getBooleanPropertyByFieldAndClass(field, missionObject));
                returnHBox.getChildren().addAll(new Label(field.getName()), bindToBooleanPropertyChoiceBox);
            }
        }
        catch (IllegalAccessException | IllegalArgumentException | SecurityException ex)
        {
            System.out.println(this.getClass().getSimpleName() + " - makeHBoxDependingOnFieldAndMissionObject: " + ex);
        }
        return returnHBox;
    }

    protected Method getMethodPropertyByFieldAndClass(Field field, Class currentClass)
    {
        try
        {
            Method methodProperty = currentClass.getMethod(field.getName() + "Property");
            return methodProperty;
        }
        catch (NoSuchMethodException | SecurityException e)
        {
            System.out.println(this.getClass().getSimpleName() + " - getMethodPropertyByFieldAndClass: " + e);
        }
        return null;
    }

    protected StringProperty getStringPropertyByFieldAndClass(Field field, Object gameElement)
    {
        try
        {
            Class currentClass = gameElement.getClass();
            Method propertyMethod = getMethodPropertyByFieldAndClass(field, currentClass);
            StringProperty invokedProperty = (StringProperty) propertyMethod.invoke(gameElement);
            return invokedProperty;
        }
        catch (IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException ex)
        {
            System.out.println(this.getClass().getSimpleName() + " - getStringPropertyByFieldAndClass: " + ex);
        }
        return null;
    }

    protected BooleanProperty getBooleanPropertyByFieldAndClass(Field field, Object gameElement)
    {
        try
        {
            Class currentClass = gameElement.getClass();
            Method propertyMethod = getMethodPropertyByFieldAndClass(field, currentClass);
            BooleanProperty invokedProperty = (BooleanProperty) propertyMethod.invoke(gameElement);
            return invokedProperty;
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
        {
            System.out.println(this.getClass().getSimpleName() + " - getBooleanPropertyByFieldAndClass: " + ex);
        }
        return null;
    }

    protected IntegerProperty getIntegerPropertyByFieldAndClass(Field field, Object gameElement)
    {
        try
        {
            Class currentClass = gameElement.getClass();
            Method propertyMethod = getMethodPropertyByFieldAndClass(field, currentClass);
            IntegerProperty invokedProperty = (IntegerProperty) propertyMethod.invoke(gameElement);
            return invokedProperty;
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
        {
            System.out.println(this.getClass().getSimpleName() + " - getIntegerPropertyByFieldAndClass: " + ex);
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

    private VBox makeVBoxForArrayList(final Field field, final Mission gameElement)
    {
        final Class currentClass = gameElement.getClass();
        final VBox returnValue = new VBox();
        Button addItemToArrayListButton = new Button("Add " + field.getName());

        returnValue.getChildren().add(addItemToArrayListButton);

        addItemToArrayListButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                try
                {
                    MissionComponent object = (MissionComponent) getAddMethodForArrayList(field, currentClass).invoke(gameElement);
                    returnValue.getChildren().add(parseMissionComponent(object));
                }
                catch (InvocationTargetException | IllegalAccessException ex)
                {
                    System.out.println(this.getClass().getSimpleName() + " - makeVBoxForArrayList: " + ex);
                }
            }
        });
        return returnValue;
    }

    private Method getAddMethodForArrayList(Field field, Class currentClass)
    {
        try
        {
            Method addMethod = currentClass.getMethod("add" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
            return addMethod;
        }
        catch (NoSuchMethodException | SecurityException ex)
        {
            System.out.println(this.getClass().getSimpleName() + " - getAddMethodForArrayList: " + ex);
        }
        return null;
    }

    private Field getFieldByString(String fieldName, Object obj)
    {
        for (Class clazz : getSuperClassesUpToObjectClassAsArrayList(obj))
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
