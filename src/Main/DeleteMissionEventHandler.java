/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import javafx.event.EventHandler;

/**
 *
 * @author Gregor
 */
public class DeleteMissionEventHandler implements EventHandler<DeleteMissionEvent>
{
    @Override
    public void handle(DeleteMissionEvent t)
    {
        if(t.getEventType().equals(DeleteMissionEvent.TITLED_PANE_DELETION))
        {
            System.out.println(t.getToBeDeletedTitledPane().getText());
        }
    }
    
}
