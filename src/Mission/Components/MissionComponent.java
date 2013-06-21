/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission.Components;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Gregor
 */
public class MissionComponent
{
    private ArrayList<String> necessaryFields;
    
    public MissionComponent()
    {
        necessaryFields = new ArrayList<>();
    }
    
    public void addNecessaryFields(String... necessaryFields)
    {
        this.necessaryFields.addAll(Arrays.asList(necessaryFields));
    }
    
    public ArrayList<String> getNecessaryFields()
    {
        return necessaryFields;
    }
}
