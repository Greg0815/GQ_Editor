/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package containers;

import main.BaseComponent;
import main.GameComponent;
import mission.rules.actions.Action;
import mission.rules.Trigger;
import java.util.ArrayList;

/**
 *
 * @author Gregor
 */
public abstract class Container extends BaseComponent
{
    private String id;
    private int minMissionCount;
    private int maxMissionCount;
    private ArrayList<GameComponent> gameComponents;
    private ArrayList<Class> allowedClasses;
    private String internalConnector;
    private ArrayList<Action> onSuccessActions;     // TODO container onTrigger ArrayList verarbeitung
    private ArrayList<Action> onFailActions;
    private ArrayList<Action> onBeginActions;
    private ArrayList<Action> onEndActions;
    private ArrayList<Action> onEnterActions;
    private ArrayList<Action> onLeaveActions;
    
    public Container(String id, int minMissionCount, int maxMissionCount, String internalConnector)
    {
        this.id = id;
        this.minMissionCount = minMissionCount;
        this.maxMissionCount = maxMissionCount;
        this.internalConnector = internalConnector;
        gameComponents = new ArrayList<>();
        allowedClasses = new ArrayList<>();
        onSuccessActions = new ArrayList<>();
        onFailActions = new ArrayList<>();
        onBeginActions = new ArrayList<>();
        onEndActions = new ArrayList<>();
        onEnterActions = new ArrayList<>();
        onLeaveActions = new ArrayList<>();
    }
    
    public ArrayList<GameComponent> getGameComponents()
    {
        return gameComponents;
    }
    
    public Trigger getInternalConnector()
    {
        return new Trigger(internalConnector);
    }
    
    public GameComponent makeNewGameElementByClass(Class classToInstantiate)
    {
        if (gameComponents.size() < maxMissionCount) {
            try {
                GameComponent gameElement = (GameComponent) classToInstantiate.newInstance();
                gameComponents.add(gameElement);
                return gameElement;
            }
            catch (InstantiationException | IllegalAccessException ex) {
                System.out.println("Container Class - makeNewGameElementByClass Function: " + ex);
            }
        }
        return null;
    }

    public ArrayList<Class> getAllowedClasses()
    {
        return allowedClasses;
    }

    public String getId()
    {
        return id;
    }

    public void addGameElement(GameComponent gameElement)
    {
        if (gameComponents.size() < maxMissionCount) {
            gameComponents.add(gameElement);
        }
    }
    
    public void addAllowedClasses(ArrayList<Class> allowedClasses)
    {
        this.allowedClasses.addAll(allowedClasses);
    }


    public void applyTriggerToAllGameElements(Trigger trigger)
    {
        for(GameComponent gameElement : gameComponents)
        {
            gameElement.addTrigger(trigger);
        }
    }
    
    public int getMinMissionCount()
    {
        return minMissionCount;
    }
    
    public int getMaxMissionCount()
    {
        return maxMissionCount;
    }
    
    public String getFirstGameElementsGameId()
    {
        return gameComponents.get(0).getId();
    }
    
    public GameComponent getLastGameElement()
    {
        return gameComponents.get(gameComponents.size()-1);
    }
}

