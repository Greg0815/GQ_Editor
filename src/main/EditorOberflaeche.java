/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import blocks.Description;
import containers.Game;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author Gregor
 */
public class EditorOberflaeche extends Application
{
    private Stage stage;
    private Scene gameSelectionScreen;
    private Scene gameEditingScreen;
    private Scene mapViewScreen;
    private ArrayList<Pair<String, Description>> descriptions;
    private ArrayList<Game> games;
    private Game currentEditedGame;
    private ListView<String> gamesListViewByString;
    private ObservableList<String> obsi;
    private ChoiceBox descriptionSelector;
    private TextArea assembleOutput;
    private ArrayList<String> choiceBoxSelectionDinger;
    private ArrayList<ModelCreator> modelCreators;
    private MenuBar gameSelectionMenuBar;
    private MenuBar gameEditingMenuBar;
    private String cssPath;

    private void testFunktion()
    {
        String[] ids =
        {
            "hasenzahn", "rhabarberkuchen", "traumtanz", "iiaapopo"
        };
        MapViewer browsi = new MapViewer(ids);
        mapViewScreen = new Scene(browsi, 1024, 768);
        stage.setScene(mapViewScreen);

//        ArrayList<Hotspot> hotspots = new ArrayList<>();
//        Hotspot hs1 = new Hotspot();
//        hs1.setId("Pausenbrot");
//        hs1.setInitialVisibility(Boolean.FALSE);
//        hs1.setLatitude(51.065);
//        hs1.setLongitude(7.0224);
//        hs1.setRadius(20);
//        Hotspot hs2 = new Hotspot();
//        hs2.setId("lululukas Podolski");
//        hs2.setLatitude(51.077);
//        hs2.setLongitude(7.0113);
//        hotspots.add(hs1);
//        hotspots.add(hs2);
//        MapViewer browsiHotspots = new MapViewer(hotspots);
//        mapViewScreen = new Scene(browsiHotspots);
//        stage.setScene(mapViewScreen);
    }

    @Override
    public void start(final Stage primaryStage)
    {
        initialization(new Pair<String, Description>("minimal Example", (new ExampleDescriptions()).minimalExample()), new Pair<String, Description>("Opa Enkel", (new ExampleDescriptions()).opaEnkel()), new Pair<String, Description>("Container Test", (new ExampleDescriptions()).newContainerModels()));
        setCssPath("styles/customStyle1.css");

        stage = primaryStage;
        stage.setScene(makeGameSelectionOberflaeche());
        stage.show();
//        testFunktion(); // später löschen
    }

    public void initialization(Pair<String, Description>... descriptions)
    {
        this.descriptions = new ArrayList<>();
        games = new ArrayList<>();
        gamesListViewByString = new ListView<String>();
        descriptionSelector = new ChoiceBox();
        choiceBoxSelectionDinger = new ArrayList<>();
        obsi = FXCollections.observableArrayList();
        assembleOutput = new TextArea();
        modelCreators = new ArrayList<>();

        this.descriptions.addAll(Arrays.asList(descriptions));

        for (Pair pair : this.descriptions)
        {
            choiceBoxSelectionDinger.add((String) pair.getKey());
        }
        descriptionSelector.setItems(FXCollections.observableArrayList(choiceBoxSelectionDinger));

        obsi.addListener(new ListChangeListener<String>()
        {
            @Override
            public void onChanged(Change change)
            {
                gamesListViewByString.setItems(obsi);
            }
        });
        createGameSelectionMenuBar();
    }

    private void setCssPath(String cssPath)
    {
        this.cssPath = cssPath;
    }

    private void createGameEditingMenuBar(final UIBuilder ui)
    {
        gameEditingMenuBar = new MenuBar();
        Menu menu1 = new Menu("File");
        Menu menu2 = new Menu("Options");
        Menu menu3 = new Menu("Map");
        gameEditingMenuBar.getMenus().addAll(menu1, menu2, menu3);

        MenuItem menuItem11 = new MenuItem("Save & Exit");
        MenuItem menuItem12 = new MenuItem("Discard & Exit");
        menu1.getItems().addAll(menuItem11, menuItem12);

        CheckMenuItem displayAllFieldsMenuItem = new CheckMenuItem("Display all fields");
        menu2.getItems().add(displayAllFieldsMenuItem);

        MenuItem menuItem31 = new MenuItem("Show Map");
        MenuItem menuItem32 = new MenuItem("Edit Hotspots");
        menu3.getItems().addAll(menuItem31, menuItem32);

        menuItem11.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                if (currentEditedGame.isComplete())
                {
                    games.add(currentEditedGame);
                    obsi.add(currentEditedGame.getId());
                    currentEditedGame = null;
                    stage.setScene(gameSelectionScreen);
                }
            }
        });

        menuItem12.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                currentEditedGame = null;
                stage.setScene(gameSelectionScreen);
            }
        });

        displayAllFieldsMenuItem.setSelected(false);
        displayAllFieldsMenuItem.selectedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1)
            {
                if (t1.booleanValue() == true)
                {
                    ui.turnNodesVisible();
                }
                else
                {
                    ui.turnNodesInvisible();
                }
            }
        });
        menuItem31.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                // show the map
//                if(currentEditedGame.get)
//                MapViewer browsi = new MapViewer(ids);
//        mapViewScreen = new Scene(browsi, 1024, 768);
//        stage.setScene(mapViewScreen);
            }
        });
        menuItem32.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                // make hotspot editing sheiß
            }
        });
    }

    private void createGameSelectionMenuBar()
    {
        gameSelectionMenuBar = new MenuBar();
        Menu menu1 = new Menu("File");
        Menu menu2 = new Menu("GeoQuest Homepage");
        gameSelectionMenuBar.getMenus().addAll(menu1, menu2);

        MenuItem menuItem11 = new MenuItem("Export");
        MenuItem menuItem12 = new MenuItem("Exit");
        menu1.getItems().addAll(menuItem11, menuItem12);
        menuItem11.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                exportGame();
            }
        });
        menuItem12.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                Platform.exit();
            }
        });

        menu2.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                // TODO onClick MenuItem GeoQuest Homepage 
            }
        });
    }

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

    public Scene makeGameSelectionOberflaeche()
    {
        VBox rootNode = new VBox();
        gameSelectionScreen = new Scene(rootNode, 1280, 720);
        if (!cssPath.isEmpty())
        {
            gameSelectionScreen.getStylesheets().add(cssPath);
        }

        final Button createGameButton = new Button("Make Selected Game");
        createGameButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                stage.setScene(makeGameEditingOberflaeche());
            }
        });

        Button assembleGameButton = new Button("assemble selected game");
        assembleGameButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                String gameName = gamesListViewByString.getSelectionModel().getSelectedItem();
                Game game = findGameByGameName(gameName);
                findModelCreatorForGame(game).applyDescriptionRules();
                assembleOutput.setText(game.assemble());
            }
        });
        rootNode.getChildren().addAll(gameSelectionMenuBar, descriptionSelector, createGameButton, gamesListViewByString, assembleGameButton, assembleOutput);

        // TMP
        gamesListViewByString.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1)
            {
                if (t1 == true && !obsi.isEmpty())  // focus erhalten
                {
                    System.out.println("has focus");
                    createGameButton.setText("Edit this Game");
                    createGameButton.setOnAction(new EventHandler<ActionEvent>()
                    {
                        @Override
                        public void handle(ActionEvent t)
                        {
                            stage.setScene(makeGameEditingOberflaeche(findGameByGameName(gamesListViewByString.getSelectionModel().getSelectedItem())));
                        }
                    });
                }
                else if ((t1 == false) && (createGameButton.focusedProperty().get() == false))
                {
                    System.out.println("lost focus");
                    createGameButton.setText("Make Selected Game");
                    createGameButton.setOnAction(new EventHandler<ActionEvent>()
                    {
                        @Override
                        public void handle(ActionEvent t)
                        {
                            stage.setScene(makeGameEditingOberflaeche());
                        }
                    });
                }
            }
        });
        // TMP END
        return gameSelectionScreen;
    }

    private Game findGameByGameName(String gameName)
    {
        for (Game game : games)
        {
            if (game.getId().equalsIgnoreCase(gameName))
            {
                return game;
            }
        }
        return null;
    }

    private Scene makeGameEditingOberflaeche()
    {
        VBox rootNode = new VBox();
        gameEditingScreen = new Scene(rootNode, 1280, 720);
        if (!cssPath.isEmpty())
        {
            gameEditingScreen.getStylesheets().add(cssPath);
        }

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
        UIBuilder newUI = new UIBuilder(currentEditedGame = newModelCreator.getGame());
        createGameEditingMenuBar(newUI);

        rootNode.getChildren().addAll(gameEditingMenuBar, newUI.parseGame());
        return gameEditingScreen;
    }

    private Scene makeGameEditingOberflaeche(Game game)
    {
        VBox rootNode = new VBox();
        gameEditingScreen = new Scene(rootNode, 1280, 720);
        if (!cssPath.isEmpty())
        {
            gameEditingScreen.getStylesheets().add(cssPath);
        }

        UIBuilder newUI = new UIBuilder(game);
        createGameEditingMenuBar(newUI);

        rootNode.getChildren().addAll(gameEditingMenuBar, newUI.parseGame());
        return gameEditingScreen;
    }

    private void exportGame()
    {
        Game game = findGameByGameName(gamesListViewByString.getSelectionModel().getSelectedItem());
        if (game != null)
        {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File targetDirectory = directoryChooser.showDialog(stage);
            if (targetDirectory.isDirectory())
            {
                FileOutputStream fileOutputStream = null;
                ZipOutputStream zip = null;

                try
                {
                    String exportLocation = targetDirectory.getAbsolutePath();
                    game.getDrawablesSoundsAndVideosFromGameComponents();
                    fileOutputStream = new FileOutputStream(exportLocation + game.getId() + ".zip");
                    zip = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));

                    for (File drawable : game.getDrawables())
                    {
                        ZipEntry drawableFileEntry = new ZipEntry("drawable\\" + drawable.getName());
                        zip.putNextEntry(drawableFileEntry);
                        FileInputStream fis = new FileInputStream(drawable);
                        int b;
                        while ((b = fis.read()) != -1)
                        {
                            zip.write(b);
                        }
                        fis.close();
                        zip.closeEntry();
                    }
                    for (File video : game.getVideos())
                    {
                        ZipEntry videoFileEntry = new ZipEntry("videos\\" + video.getName());
                        zip.putNextEntry(videoFileEntry);
                        FileInputStream fis = new FileInputStream(video);
                        int b;
                        while ((b = fis.read()) != -1)
                        {
                            zip.write(b);
                        }
                        fis.close();
                        zip.closeEntry();
                    }
                    for (File sound : game.getSounds())
                    {
                        ZipEntry soundFileEntry = new ZipEntry("sound\\" + sound.getName());
                        zip.putNextEntry(soundFileEntry);
                        FileInputStream fis = new FileInputStream(sound);
                        int b;
                        while ((b = fis.read()) != -1)
                        {
                            zip.write(b);
                        }
                        fis.close();
                        zip.closeEntry();
                    }

                    BufferedReader br = new BufferedReader(new StringReader(game.assemble()));
                    ZipEntry gameFile = new ZipEntry("game.xml");
                    zip.putNextEntry(gameFile);
                    int b;
                    while ((b = br.read()) != -1)
                    {
                        zip.write(b);
                    }
                    zip.closeEntry();
                    br.close();
                    zip.close();
                    fileOutputStream.close();
                }
                catch (IOException ex)
                {
                    System.out.println("exportGame() Error: " + ex);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
