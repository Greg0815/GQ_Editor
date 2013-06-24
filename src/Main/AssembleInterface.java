/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Gregor
 */



public interface AssembleInterface
{
    public String assemble();
    
    public Boolean isComplete();
    
    public UtilityFunctions utilitys = new UtilityFunctions();
    
    public enum DISPLAYABLE {
    TASK, FILE, LOOP_UNTIL_SUCCESS, SHUFFLE, ON_CHOOSE, NEXT_DIALOG_BUTTON_TEXT, END_BUTTON_TEXT, 
    TEXT_SIZE, CANCEL, SOUND, BLOCKING, IMAGE, DURATION, PROMPT, REPLY_ON_CORRECT, REPLY_ON_WRONG, 
    URL, INITIAL_IMAGE, IF_RIGHT_IMAGE, IF_WRONG_IMAGE, MODE, TASK_DESCRIPTION, BUTTON_TEXT,
    CONTROLLABLE, EXIT_DIALOG_TITLE, EXIT_DIALOG_MESSAGE, EXIT_DIALOG_KEEP_WATCHING_TEXT,
    EXIT_DIALOG_WATCH_AGAIN_TEXT, EXIT_DIALOG_LEAVE_TEXT
}
}
