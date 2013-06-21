/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Mission.Mission;
import javafx.event.*;
import javafx.scene.control.TitledPane;

/**
 *
 * @author Gregor
 */
public class DeleteMissionEvent extends Event
{
    public static final EventType<DeleteMissionEvent> TITLED_PANE_DELETION = new EventType(ANY, "TITLED_PANE_DELETION");
    
    private TitledPane toBeDeletedTitledPane;
    private Mission toBeDeletedMission;
    
    public DeleteMissionEvent(TitledPane toBeDeleted, Mission toBeDeletedMission)
    {
        super(TITLED_PANE_DELETION);
        this.toBeDeletedTitledPane = toBeDeleted;
        this.toBeDeletedMission = toBeDeletedMission;
    }
    public DeleteMissionEvent(EventType<? extends Event> eventType, TitledPane toBeDeleted, Mission toBeDeletedMission)
    {
        super(eventType);
        this.toBeDeletedTitledPane = toBeDeleted;
        this.toBeDeletedMission = toBeDeletedMission;
    }
    
    public DeleteMissionEvent(Object eventSource, EventTarget eventTarget, EventType<? extends Event> eventType, TitledPane toBeDeleted, Mission toBeDeletedMission)
    {
        super(eventSource, eventTarget, eventType);
        this.toBeDeletedTitledPane = toBeDeleted;
        this.toBeDeletedMission = toBeDeletedMission;
    }

    public TitledPane getToBeDeletedTitledPane()
    {
        return this.toBeDeletedTitledPane;
    }
    
    public Mission getToBeDeletedMission()
    {
        return this.toBeDeletedMission;
    }
}
