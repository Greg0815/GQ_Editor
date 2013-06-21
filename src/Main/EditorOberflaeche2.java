/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Containers.Description2;
import Containers.Game2;
import Containers.ModelCreator2;
import Containers.UIBuilder;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author Gregor
 */
public class EditorOberflaeche2
{
    private Stage stage;
    private Scene gameSelect;
    private Scene gameEdit;
    private ArrayList<Pair<String, Description2>> descriptions;
    private ArrayList<Game2> games;
    private ListView<String> gamesList;
    private ObservableList<String> obsi;
    private ChoiceBox descriptionSelector;
    private TextArea assembleOutput;
    private ArrayList<String> choiceBoxSelectionDinger;
    private ArrayList<ModelCreator2> modelCreators;

//    public Game2 findGameForDescription(Description2 description)
//    {
//        for()
//    }
    public ModelCreator2 findModelCreatorForGame(Game2 game)
    {
        for (ModelCreator2 creator : modelCreators)
        {
            if (creator.getGame().equals(game))
            {
                return creator;
            }
        }
        return null;
    }

    public EditorOberflaeche2(Stage stage, Pair<String, Description2>... descriptions)
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
        createGameButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                stage.setScene(makeGameEditingOberflaeche());
            }
        });

        Button assembleGame = new Button("assemble selected game");
        assembleGame.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                String gameName = gamesList.getSelectionModel().getSelectedItem();
                Game2 game = findGameByGameName(gameName);
                System.out.println("GameName = " + gameName + ";;; Game ID = " + game.getId());
                findModelCreatorForGame(game).applyDescriptionRules();
                assembleOutput.setText(game.assemble());
            }
        });
        rootNode.getChildren().addAll(descriptionSelector, createGameButton, gamesList, assembleGame, assembleOutput);
        return gameSelect;
    }

    private Game2 findGameByGameName(String gameName)
    {
        for (Game2 game : games)
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

        Description2 selectedDescription = null;
        for (Pair pair : descriptions)
        {
            if (pair.getKey().equals(descriptionSelector.getSelectionModel().getSelectedItem()))
            {
                selectedDescription = (Description2) pair.getValue();
            }
        }

        ModelCreator2 newModelCreator = new ModelCreator2(selectedDescription);
        modelCreators.add(newModelCreator);
        final Game2 newGame = newModelCreator.createModel();
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
