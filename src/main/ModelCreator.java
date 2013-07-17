/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import blocks.Block;
import blocks.BlockConnector;
import blocks.Description;
import blocks.LinearBlock;
import blocks.LinearQuantityBlock;
import blocks.VariableBlock;
import containers.Container;
import containers.Game;
import containers.LinearContainer;
import containers.VariableContainer;
import mission.rules.actions.Action;
import mission.rules.actions.ActionStartMission;
import mission.rules.Rule;
import mission.rules.Trigger;
import java.util.ArrayList;

/**
 *
 * @author Gregor
 */
public class ModelCreator
{
    private Description description;
    private Game game;

    public ModelCreator(Description description)
    {
        this.description = description;
        createModel();
    }

//    public Description getDescription()
//    {
//        return description;
//    }
    public Game getGame()
    {
        return game;
    }

    private void createModel()
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
            System.out.println(from.getId() + "\t" + from.getGameElements().size());
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
    }
}
