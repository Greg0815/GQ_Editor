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
public class SimpleConditionMissionStateTest extends TestCase
{
    
    SimpleConditionMissionState testObject;

    public SimpleConditionMissionStateTest(String testName)
    {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        testObject = new SimpleConditionMissionState("NoId", "new");
    }
    
    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        testObject = null;
    }

    public void testAssemble()
    {
        System.out.println("SimpleConditionMissionState.assemble()");
        assertNotNull(testObject);
        String expectedResult = "<missionState id=\"NoId\" state=\"new\" />";
        assertEquals(expectedResult, testObject.assemble());
    }
}
