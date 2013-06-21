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
public class ActionDecrementVariableTest extends TestCase
{
    
    ActionDecrementVariable testObject;

    
    public ActionDecrementVariableTest(String testName)
    {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        testObject = new ActionDecrementVariable("testVariable");
    }

    public void testGetType()
    {
        System.out.println("getType()");
        assertEquals("DecrementVariable", testObject.getType());
    }
    /**
     * Test of assemble method, of class ActionDecrementVariable.
     */
    public void testAssemble()
    {
        System.out.println("assemble()");
        assertEquals("<action type=\"DecrementVariable\" var=\"testVariable\"/>", testObject.assemble());
    }
}
