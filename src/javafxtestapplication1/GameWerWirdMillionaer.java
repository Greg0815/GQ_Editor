/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Gregor
 */
public class GameWerWirdMillionaer extends Game
{
    private MissionStartAndExitScreen beginningMission;
    private ArrayList<MissionMultipleChoiceQuestion> mcqMissions;
    private MissionStartAndExitScreen endingMission;

    public GameWerWirdMillionaer()
    {
        this.name = new SimpleStringProperty();
        beginningMission = new MissionStartAndExitScreen();
        mcqMissions = new ArrayList<>();
        mcqMissions.add(new MissionMultipleChoiceQuestion());
        endingMission = new MissionStartAndExitScreen();
    }
    
    public GameWerWirdMillionaer(String name)
    {
        this.name = new SimpleStringProperty();
        this.name.set(name);
        beginningMission = new MissionStartAndExitScreen();
        mcqMissions = new ArrayList<>();
        mcqMissions.add(new MissionMultipleChoiceQuestion());
        endingMission = new MissionStartAndExitScreen();
    }

    public StringProperty nameProperty()
    {
        if(this.name == null)
        {
            this.name = new SimpleStringProperty();
            this.name.addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                {
                }
            });
        }
        return this.name;
    }
    
    public String getName()
    {
        return this.name.get();
    }
    
    public void setName(String name)
    {
        this.name.set(name);
    }
    
    public void addMissionMultipleChoiceQuestion()
    {
        this.mcqMissions.add(new MissionMultipleChoiceQuestion());
    }
    
    public void addMissionMultipleChoiceQuestion(MissionMultipleChoiceQuestion mission)
    {
        this.mcqMissions.add(mission);
    }
    
    public void addMissionMissionMultipleChoiceQuestion(MissionMultipleChoiceQuestion mission, int position)
    {
        this.mcqMissions.add(position, mission);
    }
    
    public MissionStartAndExitScreen getBeginningMission()
    {
        return this.beginningMission;
    }
    
    public MissionStartAndExitScreen getEndingMission()
    {
        return this.endingMission;
    }
    
    public ArrayList<MissionMultipleChoiceQuestion> getMultipleChoiceQuestionMissions()
    {
        return this.mcqMissions;
    }
    
    public MissionMultipleChoiceQuestion getMultipleChoiceQuestionMission(int position)
    {
        return this.mcqMissions.get(position);
    }
    
    @Override
    public Trigger definedRuleset(String nextMissionID)
    {
        Trigger onEnd = new Trigger("onEnd");
        Rule rule = new Rule();
        ActionStartMission action = new ActionStartMission(nextMissionID);
        rule.addAction(action);
        onEnd.addRule(rule);
        return onEnd;
    }

    private ArrayList<Mission> combinedMissionsArrayList()
    {
        ArrayList<Mission> combinedMissions = new ArrayList<>();
        combinedMissions.add(beginningMission);
        combinedMissions.addAll(this.mcqMissions);
        combinedMissions.add(endingMission);
        return combinedMissions;
    }
    
    @Override
    public void applyRuleset(ArrayList<Mission> missions)
    {
        for(int i=0; i<missions.size()-1; i++)
        {
            missions.get(i).addTrigger(definedRuleset(missions.get(i+1).getId()));
        }
    }

    @Override
    public String assemble()
    {
        this.applyRuleset(combinedMissionsArrayList());
        return "<game>" + utilitys.createStringFromArrayList(combinedMissionsArrayList()) + "</game>";
    } 
}
