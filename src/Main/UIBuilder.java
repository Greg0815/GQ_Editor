/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Containers.Container;
import Containers.Game;
import Containers.LinearContainer;
import Containers.VariableContainer;
import CustomInputNodes.NumericTextField;
import Mission.Components.MissionComponent;
import Mission.Mission;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
//    private VBox layoutContainer;

    public UIBuilder(Game gameObjectForUIBuildingProcess)
    {
        game = gameObjectForUIBuildingProcess;
//        layoutContainer = new VBox();
    }
//    public Node parseObject()
//    {
//        Node additionalNode = null;
//        if (objectUIHasToBeBuild instanceof Game) {
//            // Game-Part is being parsed
//            additionalNode = parseGame();
//            layoutContainer.getChildren().add(additionalNode);
////            for (ContainerToBeReplaced container : ((Game) objectUIHasToBeBuild).getContainers()) {
////                UIBuilder innerUI = new UIBuilder(container);
////                additionalNode = innerUI.parseObject();
////                layoutContainer.getChildren().add(additionalNode);
////            }
//        }
//        else if (objectUIHasToBeBuild instanceof VariableContainer) {
//            additionalNode = parseVariableContainer();
//            layoutContainer.getChildren().add(additionalNode);
//
//        }
//        else if(objectUIHasToBeBuild instanceof LinearContainer)
//        {
//            additionalNode = parseLinearContainer();
//            layoutContainer.getChildren().add(additionalNode);
//
//        }
//        else if(objectUIHasToBeBuild instanceof LinearQuantityContainer)
//        {
//
//        }
//        else if (objectUIHasToBeBuild instanceof Mission) {
//            additionalNode = parseMission();
//            layoutContainer.getChildren().add(additionalNode);
//        }
//        else if (objectUIHasToBeBuild instanceof MissionComponent) {
//            additionalNode = parseMissionComponent();
//            layoutContainer.getChildren().add(additionalNode);
//        }
//
//        return layoutContainer;
//    }

    private TitledPane parseGameElement(GameElement gameElement)
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
        //        missionComponentLayout.setStyle("-fx-border:style solid;" + "-fx-border-width:1;" + "-fx-border-color: black;");
//            MissionComponent missionComponent = ((MissionComponent) field.get(this));
        HBox missionComponentLayoutContent = new HBox();
        missionComponentLayoutContent.setStyle("-fx-border:style solid; -fx-border-width:1; -fx-border-color: black;");
        for (String string : misComp.getNecessaryFields())
        {
            try
            {
                Field someField = getFieldByString(string, misComp);
                missionComponentLayout.getChildren().add(makeHBoxDependingOnFieldAndClass(someField, misComp));
            }
            catch (IllegalAccessException | SecurityException | IllegalArgumentException | NoSuchMethodException ex)
            {
                System.out.println("NewUIBuilder Class - parseMissionComponent Function: " + ex);
            }
        }
        missionComponentLayout.getChildren().add(missionComponentLayoutContent);

        return missionComponentLayout;
    }

    private TitledPane parseMission(GameElement gameElement)
    {
        final Mission mission = (Mission) gameElement;
        TitledPane missionLayout = new TitledPane();
        VBox titledPaneContent = new VBox();
        missionLayout.setText(mission.getClass().getSimpleName());
        missionLayout.setContent(titledPaneContent);
        missionLayout.setExpanded(true);

        for (String necessaryField : mission.getNecessaryFields())
        {
            try
            {
                Field field = getFieldByString(necessaryField, mission);
                if (field.getType().toString().toLowerCase().contains("property")) // nicht-arraylist feld
                {
                    titledPaneContent.getChildren().add(makeHBoxDependingOnFieldAndClass(field, mission));
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
            catch (IllegalAccessException | NoSuchMethodException | SecurityException ex)
            {
                System.out.println("NewUIBuilder Class - parseMission Function: " + ex);
            }
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

        for (Class allowedClass : linCont.getAllowedClasses())
        {
//            TitledPane missionTitledPane = new TitledPane();
//            missionTitledPane.setText(allowedClass.getSimpleName());
//            missionTitledPane.setExpanded(false);

            GameElement newGameElement = linCont.makeNewGameElementByClass(allowedClass);
            newGameElement.setId(linCont.getId() + "-" + ((Mission) newGameElement).getType() + "-" + linCont.getGameElements().indexOf(newGameElement));

//            UIBuilder newGameElementUI = new UIBuilder(newGameElement);
//            missionTitledPane.setContent();
            innerContainerLayoutContent.getPanes().add(parseGameElement(newGameElement));
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

        if (varCont.getMaxMissionCount() == 1 && varCont.getAllowedClasses().size() == 1)
        {
            GameElement newGameElementInstance = varCont.makeNewGameElementByClass(varCont.getAllowedClasses().get(0));
            newGameElementInstance.setId(varCont.getId() + "-" + ((Mission) newGameElementInstance).getType() + "-" + varCont.getGameElements().indexOf(newGameElementInstance));
//            UIBuilder newGameElementUI = new UIBuilder(newGameElementInstance);
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
                            final GameElement newGameElementInstance = varCont.makeNewGameElementByClass(allowedClass);
                            newGameElementInstance.setId(varCont.getId() + "-" + ((Mission) newGameElementInstance).getType() + "-" + varCont.getGameElements().indexOf(newGameElementInstance));
//                        UIBuilder newGameElementUI = new UIBuilder(newGameElementInstance);
                            final TitledPane tp = parseGameElement(newGameElementInstance);
                            tp.setOnMouseClicked(new EventHandler<MouseEvent>(){
                               @Override
                               public void handle(MouseEvent t)
                               {
                                   if(t.getButton() == MouseButton.SECONDARY)
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
//        Game gameReference = (Game) objectUIHasToBeBuild;
        VBox gameLayout = new VBox();
        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("File");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");
        menu1.getItems().addAll(saveMenuItem, exitMenuItem);
        menuBar.getMenus().add(menu1);
        gameLayout.getChildren().add(menuBar);

        for(String fieldName : game.getNecessaryFields())
        {
            try
            {
                Field field = getFieldByString(fieldName, game);
                HBox hBox = makeHBoxDependingOnFieldAndClass(field, game);
                gameLayout.getChildren().add(hBox);
            }
            catch (IllegalAccessException | SecurityException | IllegalArgumentException | NoSuchMethodException ex)
            {
                System.out.println("UIBuilder Class - parseGame Function: " + ex);
            }
        }
//        HBox hBox = makeHBoxDependingOnFieldAndClass(new Field(game.idProperty(), game);
//        Label newLabel = new Label("Game ID ");
//        TextField textField = new TextField();
//        hBox.getChildren().addAll(newLabel, textField);
//        gameLayout.getChildren().add(hBox);
//
//        if (game.idProperty().isNotNull().get())
//        {
//            textField.setText(game.idProperty().get());
//        }
//        game.idProperty().bindBidirectional(textField.textProperty());
        for (Container container : game.getContainers())
        {
            gameLayout.getChildren().add(parseContainer(container));
        }

        return gameLayout;
    }

    
    // Enthält zwei mögliche Fehlerquellen, hat keiner dazu geschrieben welche
    protected HBox makeHBoxDependingOnFieldAndClass(Field field, Object gameElement) throws IllegalAccessException, SecurityException, IllegalArgumentException, NoSuchMethodException
    {
        HBox returnHBox = new HBox();
        try
        {
            if (field.getType().toString().contains("StringProperty"))
            {
                final TextField bindToStringPropertyTextField = new TextField();
                
                field.setAccessible(true);
                if (!((StringProperty) field.get(gameElement)).get().isEmpty())
                {
                    bindToStringPropertyTextField.setText(((StringProperty) field.get(gameElement)).get());
                }
                else
                {
                    bindToStringPropertyTextField.setStyle("-fx-background-color: red;");
                }
                bindToStringPropertyTextField.textProperty().bindBidirectional(getStringPropertyByFieldAndClass(field, gameElement));
                
                //
                //      Color changeing part of TextFields if TextFields are still empty
                bindToStringPropertyTextField.textProperty().addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue<? extends String> ov, String t, String t1)
                    {
                        if(t1.isEmpty())//bindToStringPropertyTextField.textProperty().get().isEmpty() || bindToStringPropertyTextField.getText().isEmpty())
                        {
                           bindToStringPropertyTextField.setStyle("-fx-background-color: red;");
                        }
                        else
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
                if (((IntegerProperty) field.get(gameElement)).asString().isNotNull().get())
                {
                    bindToNumiercPropertyTextField.setText(((Integer) field.get(gameElement)).toString());          // Testen !!
                }
                bindToNumiercPropertyTextField.textProperty().bindBidirectional(getIntegerPropertyByFieldAndClass(field, gameElement), new NumberStringConverter());
                returnHBox.getChildren().addAll(new Label(field.getName()), bindToNumiercPropertyTextField);
            }
            else if (field.getType().toString().contains("BooleanProperty"))
            {
                ChoiceBox bindToBooleanPropertyChoiceBox = new ChoiceBox();
                bindToBooleanPropertyChoiceBox.getItems().addAll(true, false);
                field.setAccessible(true);
                if (((BooleanProperty) field.get(gameElement)).asString().isNotNull().get())
                {
//                            bindToBooleanPropertyChoiceBox.setText(((BooleanProperty)field.get(objectUIHasToBeBuild)).toString());
                    bindToBooleanPropertyChoiceBox.getSelectionModel().select(((BooleanProperty) field.get(gameElement)).toString());       // Testen !!
                }
                bindToBooleanPropertyChoiceBox.valueProperty().bindBidirectional(getBooleanPropertyByFieldAndClass(field, gameElement));
                returnHBox.getChildren().addAll(new Label(field.getName()), bindToBooleanPropertyChoiceBox);
            }
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException ex)
        {
            System.out.println("NewUIBuilder Class - makeHBoxDependingOnFieldAndClass Function: " + ex);
        }
        return returnHBox;
    }

    protected Method getMethodPropertyByFieldAndClass(Field field, Class currentClass) throws NoSuchMethodException, SecurityException
    {
        Method methodProperty = currentClass.getMethod(field.getName() + "Property");
        return methodProperty;
    }

    protected StringProperty getStringPropertyByFieldAndClass(Field field, Object gameElement) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
    {
        Class currentClass = gameElement.getClass();
        Method propertyMethod = getMethodPropertyByFieldAndClass(field, currentClass);
        StringProperty invokedProperty = (StringProperty) propertyMethod.invoke(gameElement);
        return invokedProperty;
    }

    protected BooleanProperty getBooleanPropertyByFieldAndClass(Field field, Object gameElement) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
    {
        Class currentClass = gameElement.getClass();
        Method propertyMethod = getMethodPropertyByFieldAndClass(field, currentClass);
        BooleanProperty invokedProperty = (BooleanProperty) propertyMethod.invoke(gameElement);
        return invokedProperty;
    }

    protected IntegerProperty getIntegerPropertyByFieldAndClass(Field field, Object gameElement) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
    {
        Class currentClass = gameElement.getClass();
        Method propertyMethod = getMethodPropertyByFieldAndClass(field, currentClass);
        IntegerProperty invokedProperty = (IntegerProperty) propertyMethod.invoke(gameElement);
        return invokedProperty;
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
//                    UIBuilder newUI = new UIBuilder(object);
                    returnValue.getChildren().add(parseMissionComponent(object));
                }
                catch (InvocationTargetException | IllegalAccessException ex)
                {
                    System.out.println("NewUIBuilder Class - makeVBoxForArrayList Function: " + ex);
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
            System.out.println("NewUIBuilder Class - getAddMethodForArrayList Function: " + ex);
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
