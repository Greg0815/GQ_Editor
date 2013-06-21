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
public class SimpleConditionComparisonTest extends TestCase
{
    
    SimpleConditionComparison testObject;
    
    public SimpleConditionComparisonTest(String testName)
    {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        testObject = new SimpleConditionComparison("eq", "var", "Urlaub", "bool", "true");
    }
    
    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        testObject = null;
    }

    public void testAssemble()
    {
        System.out.println("SimpleConditionComparison.assemble()");
        assertNotNull(testObject);
        String expectedResult = "<eq><var>Urlaub</var><bool>true</bool></eq>";
        assertEquals(expectedResult, testObject.assemble());
    }
}
