/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Blocks.Block;
import Blocks.BlockConnector;
import Blocks.Description;
import Blocks.LinearBlock;
import Blocks.LinearQuantityBlock;
import Blocks.VariableBlock;
import Containers.Container;
import Containers.Game;
import Containers.LinearContainer;
import Containers.VariableContainer;
import Mission.Rules.Actions.Action;
import Mission.Rules.Actions.ActionStartMission;
import Mission.Rules.Rule;
import Mission.Rules.Trigger;
import java.util.ArrayList;

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

//    public Description getDescription()
//    {
//        return description;
//    }
    public Game getGame()
    {
        return game;
    }

    public Game createModel()
    {
        game = new Game();

        for (Block block : description.getBlocks())
        {
            Container container = null;
            if (block instanceof LinearBlock)
            {
                container = new LinearContainer((LinearBlock) block);
            }
            else if (block instanceof LinearQuantityBlock)
            {
                // TODO Linear QUantity Block implementation
            }
            else if (block instanceof VariableBlock)
            {
                container = new VariableContainer((VariableBlock) block);
            }
            game.addContainer(container);
        }
        /*  Gesamter Inhalt des Blocks wird jetzt in den Containerklassen durch den Konstruktor erledigt
         * 
         * 
         for(Block block : description.getBlocks())
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
         containerForBlock.addAllowedClasses(block.getgetMissionClasses());
         game.addContainer(containerForBlock);
         }
         */
        return game;
    }

    public void applyDescriptionRules()
    {
        applyBlockConnectors();
        applyInternalRules();
        applyInternalConnector();
    }

    public void applyDescriptionRules(Game game)
    {
        this.game = game;
        applyBlockConnectors();
        applyInternalRules();
        applyInternalConnector();
    }

    private void applyToArrayList(Block block, String triggerType, ArrayList<Rule> rules)
    {
        if (!rules.isEmpty())
        {
            Trigger trigger = new Trigger(triggerType);
            for (Rule rule : rules)
            {
                for (Action action : rule.getActions())
                {
                    if (action instanceof ActionStartMission)
                    {
                        Container container = getContainerByIdString(((ActionStartMission) action).getBlockReferenceId());
                        ((ActionStartMission) action).setParameters(container.getFirstGameElementsGameId());
                    }
                }
                trigger.addRule(rule);
            }
            Container targetContainer = getContainerByIdString(block.getId());
            targetContainer.applyTriggerToAllGameElements(trigger);
        }
    }

    private Container getContainerByIdString(String id)
    {
        Container cont = null;
        for (Container container : game.getContainers())
        {
            if (container.getId().equalsIgnoreCase(id))
            {
                cont = container;
            }
        }
        return cont;
    }

    private void applyBlockConnectors()
    {
        for (BlockConnector blockConnector : description.getBlockConnectors())
        {
            Container from = getContainerByIdString(blockConnector.getFromBlockId());
            Container to = getContainerByIdString(blockConnector.getToBlockId());
            Trigger trigger = blockConnector.getTrigger();
            Rule rule = new Rule();
            ActionStartMission startMissionAction = new ActionStartMission(to.getFirstGameElementsGameId());
            rule.addAction(startMissionAction);
            trigger.addRule(rule);
            from.getLastGameElement().addTrigger(trigger);
        }
    }

    private void applyInternalRules()
    {
        for (Block block : description.getBlocks())
        {
            applyToArrayList(block, "onBegin", block.getOnBeginRules());
            applyToArrayList(block, "onEnd", block.getOnEndRules());
            applyToArrayList(block, "onSuccess", block.getOnSuccessRules());
            applyToArrayList(block, "onFail", block.getOnFailRules());
            applyToArrayList(block, "onEnter", block.getOnEnterRules());
            applyToArrayList(block, "onLeave", block.getOnLeaveRules());
        }
    }

    private void applyInternalConnector()
    {
        for (Container container : game.getContainers())
        {
            if (container.getGameElements().size() > 1)
            {
                for (int i = 0; i < container.getGameElements().size()-1; i++)
                {
                    Trigger trigger = container.getInternalConnector();
                    Rule rule = new Rule();
                    trigger.addRule(rule);
                    rule.addAction(new ActionStartMission(container.getGameElements().get(i + 1).getId()));
                    container.getGameElements().get(i).addTrigger(trigger);
                }
            }
        }

//        for (Block block : description.getBlocks())
//        {
//            if (block.getMaxMissionCount() > 1)
//            {
//                Container container = getContainerByIdString(block.getId());
//                ArrayList<GameElement> gameElementz = container.getGameElements();
//                for (int i = 0; i < gameElementz.size() - 1; i++)
//                {
//                    Trigger trigger = container.getInternalConnector();
//                    Rule rule = new Rule();
//                    trigger.addRule(rule);
//                    rule.addAction(new ActionStartMission(gameElementz.get(i + 1).getId()));
//                    gameElementz.get(i).addTrigger(trigger);
//                }
//            }
//        }
    }
}
