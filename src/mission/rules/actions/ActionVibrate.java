/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mission.rules.actions;

/**
 *
 * @author Gregor
 */
public class ActionVibrate extends Action
{
    private int duration = 0;

    public ActionVibrate()
    {
        super("Vibrate");
    }

    public ActionVibrate(int duration)
    {
        super("Vibrate");
        this.duration = duration;
    }

    @Override
    public void setParameters(String... parametersList)
    {
        duration = Integer.parseInt(parametersList[0]);
    }

    @Override
    protected void addNecessaryAndOptionalFields()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String assemble()
    {
        return "<action" + utilitys.type(getType()) + ((duration > 0) ? utilitys.duration(duration) : "") + "/>";
    }
}
