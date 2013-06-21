/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Containers;

import Mission.Rules.ActionStartMission;
import Mission.Rules.Rule;
import Mission.Rules.Trigger;

/**
 *
 * @author Gregor
 */
public class ModelCreator
{
    Description description;
    Game game;
    
    public ModelCreator(Description description)
    {
        this.description = description;
    }
    
    public Game createModel()
    {
        game = new Game();
        for(BlockToBeReplaced block : description.getBlocks())
        {
            ContainerToBeReplaced containerForBlock;
            if(block.getMaxMissionCount() == 1)
            {
                containerForBlock = new ContainerToBeReplaced(block.getId());
            }
            else
            {
                containerForBlock = new ContainerToBeReplaced(block.getId(), block.getMaxMissionCount(), block.getInternalConnector());
//                containerForBlock.setInternalConnector(block.getInternalConnector());
            }
            containerForBlock.addAllowedClasses(block.getMissionClasses());
            game.addContainer(containerForBlock);
        }
        return game;
    }
    
    public void applyDescriptionRules(Game game)
    {
        this.game = game;
        
        // verbindung der einzelnen blöcke/container
        for(BlockConnector blockConnector : description.getBlockConnectors())
        {
            ContainerToBeReplaced from = getContainerByIdString(blockConnector.getFromBlockId());
            ContainerToBeReplaced to = getContainerByIdString(blockConnector.getToBlockId());
            // trigger erzeugen
            Trigger trigger = new Trigger(blockConnector.getTrigger());
            Rule rule = new Rule();
            ActionStartMission startMissionAction = new ActionStartMission(to.getFirstElementsID());
            rule.addAction(startMissionAction);
            trigger.addRule(rule);
            //
            from.getLastGameElement().addTrigger(trigger);
        }
        
        // regeln für die einzelnen blöcke/container
        for(BlockToBeReplaced block : description.getBlocks())
        {
            if(!block.getTriggerActions().isEmpty())
            {
                ContainerToBeReplaced targetContainer = getContainerByIdString(block.getId());
                for(TriggerAction triggerAction : block.getTriggerActions())
                {
                    if(triggerAction.isStartMissionAction())
                    {
                        String missionIdFromBlockThatHasToBeStarted = getContainerByIdString(triggerAction.getBlockReferenceId()).getFirstElementsID();
                        targetContainer.applyTriggerToAllGameElements(triggerAction.getTrigger(missionIdFromBlockThatHasToBeStarted));
                    }
                    else
                    {
                        targetContainer.applyTriggerToAllGameElements(triggerAction.getTrigger());
                    }
                }
            }
        }
    }
    
    private ContainerToBeReplaced getContainerByIdString(String id)
    {
        ContainerToBeReplaced cont = null;
        for(ContainerToBeReplaced container : game.getContainers())
        {
            if(container.getId().equalsIgnoreCase(id))
            {
                cont = container;
            }
        }
        return cont;
    }
    
}
