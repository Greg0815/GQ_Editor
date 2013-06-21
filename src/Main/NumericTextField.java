/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javafx.scene.control.TextField;

/**
 *
 * @author Gregor
 */
public class NumericTextField extends TextField
{
    @Override
    public void replaceText(int start, int end, String text)
    {
        if(text.matches("[0-9]") || text.equalsIgnoreCase(""))
        {
            super.replaceText(start, end, text);
        }
    }
    
    @Override
    public void replaceSelection(String text)
    {
        if(text.matches("[0-9]") || text.equalsIgnoreCase(""))
        {
            super.replaceSelection(text);
        }
    }
}
