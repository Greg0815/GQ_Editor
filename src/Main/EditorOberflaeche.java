/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Blocks.Description;
import Containers.Game;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author Gregor
 */
public class EditorOberflaeche
{
    private Stage stage;
    private Scene gameSelect;
    private Scene gameEdit;
    private ArrayList<Pair<String, Description>> descriptions;
    private ArrayList<Game> games;
    private ListView<String> gamesList;
    private ObservableList<String> obsi;
    private ChoiceBox descriptionSelector;
    private TextArea assembleOutput;
    private ArrayList<String> choiceBoxSelectionDinger;
    private ArrayList<ModelCreator> modelCreators;

//    public Game findGameForDescription(Description description)
//    {
//        for()
//    }
    public ModelCreator findModelCreatorForGame(Game game)
    {
        for (ModelCreator creator : modelCreators)
        {
            if (creator.getGame().equals(game))
            {
                return creator;
            }
        }
        return null;
    }

    public EditorOberflaeche(Stage stage, Pair<String, Description>... descriptions)
    {
        this.stage = stage;
        this.descriptions = new ArrayList<>();
        games = new ArrayList<>();
        gamesList = new ListView();
        descriptionSelector = new ChoiceBox();
        choiceBoxSelectionDinger = new ArrayList<>();
        obsi = FXCollections.observableArrayList();
        assembleOutput = new TextArea();
        modelCreators = new ArrayList<>();

        this.descriptions.addAll(Arrays.asList(descriptions));

        for (Pair pair : descriptions)
        {
            choiceBoxSelectionDinger.add((String) pair.getKey());
        }
        descriptionSelector.setItems(FXCollections.observableArrayList(choiceBoxSelectionDinger));

        obsi.addListener(new ListChangeListener()
        {
            @Override
            public void onChanged(Change change)
            {
                gamesList.setItems(obsi);
            }
        });
    }

    public Scene makeGameSelectionOberflaeche()
    {
        VBox rootNode = new VBox();
        gameSelect = new Scene(rootNode, 1280, 720);

        Button createGameButton = new Button("Make Selected Game");
        createGameButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent t)
            {
                if(t.getButton() == MouseButton.PRIMARY)
                {
                    stage.setScene(makeGameEditingOberflaeche());
                }
//                if(t.isSecondaryButtonDown())
//                {
//                    System.out.println("Right Button");
//                }
//                else if(t.isPrimaryButtonDown())
//                {
//                    System.out.println("Left Button");
//                    stage.setScene(makeGameEditingOberflaeche());
//                }
            }
            
        });
//        createGameButton.setOnAction(new EventHandler<ActionEvent>()
//        {
//            @Override
//            public void handle(ActionEvent t)
//            {
//                stage.setScene(makeGameEditingOberflaeche());
//            }
//        });

        Button assembleGame = new Button("assemble selected game");
        assembleGame.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                String gameName = gamesList.getSelectionModel().getSelectedItem();
                Game game = findGameByGameName(gameName);
                System.out.println("GameName = " + gameName + ";;; Game ID = " + game.getId());
                findModelCreatorForGame(game).applyDescriptionRules();
                assembleOutput.setText(game.assemble());
            }
        });
        rootNode.getChildren().addAll(descriptionSelector, createGameButton, gamesList, assembleGame, assembleOutput);
        return gameSelect;
    }

    private Game findGameByGameName(String gameName)
    {
        for (Game game : games)
        {
            if(game.getId().equalsIgnoreCase(gameName))
            {
                return game;
            }
        }
        return null;
    }

    private Scene makeGameEditingOberflaeche()
    {
        VBox rootNode = new VBox();
        gameEdit = new Scene(rootNode, 1280, 720);
        Button endButton = new Button("speichern und raus");

        Description selectedDescription = null;
        for (Pair pair : descriptions)
        {
            if (pair.getKey().equals(descriptionSelector.getSelectionModel().getSelectedItem()))
            {
                selectedDescription = (Description) pair.getValue();
            }
        }

        ModelCreator newModelCreator = new ModelCreator(selectedDescription);
        modelCreators.add(newModelCreator);
        final Game newGame = newModelCreator.createModel();
        endButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                // check ui for empty fields
                // if all fields not empty go out
                if (newGame.isComplete())
                {
                    stage.setScene(gameSelect);
                }
                games.add(newGame);
                obsi.add(newGame.getId());
            }
        });

        UIBuilder newUI = new UIBuilder(newGame);
        rootNode.getChildren().addAll(newUI.parseGame(), endButton);
        return gameEdit;
    }
}
