/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mission.rules.actions;

import java.io.File;

/**
 *
 * @author Gregor
 */
public class ActionPlayAudio extends Action
{
    String file;
    
    public ActionPlayAudio(String file)
    {
        super("PlayAudio");
        this.file = "sound\\" + file;
    }
    
    @Override
    public void setParameters(String... parametersList)
    {
        this.file = parametersList[0];
    }

    @Override
    protected void addNecessaryAndOptionalFields()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String assemble()
    {
        return "<action" + utilitys.type(getType()) + utilitys.file(file) + "/>";
    }
    
}
