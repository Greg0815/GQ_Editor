/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import junit.framework.TestCase;

/**
 *
 * @author Gregor
 */
public class AnswerTest extends TestCase
{
    
    Answer testObject;
    public AnswerTest(String testName)
    {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        testObject = new Answer("Sehr gut", "Gute Wahl", "42");
    }

    /**
     * Test of assemble method, of class Answer.
     */
    public void testAssemble()
    {
        System.out.println("assemble");
        assertEquals("<answer correct=\"Sehr gut\" onChoose=\"Gute Wahl\">42</answer>", testObject.assemble());
        
    }
}
