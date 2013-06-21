/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Containers;

import Main.AssembleInterface;
import Main.GameElement;
import Mission.Rules.Action;
import Mission.Rules.Trigger;
import java.util.ArrayList;

/**
 *
 * @author Gregor
 */
public abstract class Container implements AssembleInterface
{
    private String id;
    private int minMissionCount;
    private int maxMissionCount;
    private ArrayList<GameElement> gameElements;
    private ArrayList<Class> allowedClasses;
    private String internalConnector;
    private ArrayList<Action> onSuccessActions;
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
        gameElements = new ArrayList<>();
        allowedClasses = new ArrayList<>();
        onSuccessActions = new ArrayList<>();
        onFailActions = new ArrayList<>();
        onBeginActions = new ArrayList<>();
        onEndActions = new ArrayList<>();
        onEnterActions = new ArrayList<>();
        onLeaveActions = new ArrayList<>();
    }
    
    public ArrayList<GameElement> getGameElements()
    {
        return gameElements;
    }
    
    public Trigger getInternalConnector()
    {
        return new Trigger(internalConnector);
    }
    
    public GameElement makeNewGameElementByClass(Class classToInstantiate)
    {
        if (gameElements.size() < maxMissionCount) {
            try {
                GameElement gameElement = (GameElement) classToInstantiate.newInstance();
                gameElements.add(gameElement);
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

    public void addGameElement(GameElement gameElement)
    {
        if (gameElements.size() < maxMissionCount) {
            gameElements.add(gameElement);
        }
    }
    
    public void addAllowedClasses(ArrayList<Class> allowedClasses)
    {
        this.allowedClasses.addAll(allowedClasses);
    }


    public void applyTriggerToAllGameElements(Trigger trigger)
    {
        for(GameElement gameElement : gameElements)
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
        return gameElements.get(0).getId();
    }
    
    public GameElement getLastGameElement()
    {
        return gameElements.get(gameElements.size()-1);
    }
}

