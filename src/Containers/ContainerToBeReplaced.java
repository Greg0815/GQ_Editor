/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Containers;

import Main.AssembleInterface;
import Main.GameElement;
import Mission.Rules.Trigger;
import java.util.ArrayList;

/**
 *
 * @author Gregor
 */
public class ContainerToBeReplaced implements AssembleInterface
{

    private String id;
    private int maxMissionCount;
    private ArrayList<GameElement> gameElements;
    private ArrayList<Class> allowedClasses;
    private Trigger internalConnector;
    
    public ContainerToBeReplaced(String id)
    {
        this.id = id;
        this.maxMissionCount = 1;
        gameElements = new ArrayList<>();
        allowedClasses = new ArrayList<>();
    }

    public ContainerToBeReplaced(String id, int maxMissionCount, String internalConnector)
    {
        this.id = id;
        this.maxMissionCount = maxMissionCount;
        gameElements = new ArrayList<>();
        allowedClasses = new ArrayList<>();
        this.internalConnector = new Trigger(internalConnector);
    }
    
    public void applyTriggerToAllGameElements(Trigger trigger)
    {
        for(GameElement gameElement : gameElements)
        {
            gameElement.addTrigger(trigger);
        }
    }
    
    public GameElement getLastGameElement()
    {
        return gameElements.get(gameElements.size()-1);
    }
    
    public void setInternalConnector(String triggerString)
    {
        internalConnector = new Trigger(triggerString);
    }

    public int getMaxMissionCount()
    {
        return maxMissionCount;
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
        else {
            System.out.println("No more Missions allowed");
        }
    }

    public void addAllowedClasses(ArrayList<Class> allowedClasses)
    {
        // TODO: prevent duplicates
        this.allowedClasses.addAll(allowedClasses);
    }

    public String getFirstElementsID()
    {
        return gameElements.get(0).getId();
    }

    @Override
    public String assemble()
    {
        return utilitys.createStringFromArrayList(gameElements);
    }
    
    @Override
    public Boolean isComplete()         // TODO: rework
    {
        Boolean isComplete = true;
//        for(GameElement gameElement : gameElements)
//        {
//            if(!gameElement.isComplete())
//            {
//                System.out.println("GameElement incomplete");
//                isComplete = false;
//            }
//        }
        return isComplete;
    }
}
