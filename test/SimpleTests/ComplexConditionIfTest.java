/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author Gregor
 */
public class ComplexConditionIfTest extends TestCase
{  
    ComplexConditionIf testObject;
    
    public ComplexConditionIfTest(String testName)
    {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        testObject = new ComplexConditionIf();
        testObject.addCondition(new ComplexConditionIf());
    }
    
    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        testObject = null;
    }

    public void testGetConditionsArrayList()
    {
        System.out.println("getConditionsArrayList()");
        assertNotNull(testObject.getConditionsArrayList());
        assertTrue(testObject.getConditionsArrayList() instanceof ArrayList<?>);
    }
    
    public void testGetConditionAtPosition()
    {
        System.out.println("getConditionAtPosition()");
        assertNotNull(testObject.getConditionsArrayList());
        assertTrue(testObject.getConditionAtPosition(0) instanceof ComplexConditionIf);
    }
    
    public void testAddCondition()
    {
        System.out.println("addCondition()");
        assertNotNull(testObject.getConditionsArrayList());
        assertEquals(testObject.getConditionsArrayList().size(), 1);
        
        testObject.addCondition(new ComplexConditionIf());
        
        assertEquals(testObject.getConditionsArrayList().size(), 2);
    }
    
    public void addConditionAtPosition()
    {
        System.out.println("addConditionAtPosition()");
        assertNotNull(testObject.getConditionsArrayList());
        assertEquals(testObject.getConditionsArrayList().size(), 1);
        
        testObject.addConditionAtPosition(new ComplexConditionIf(), 0);
        
        assertEquals(testObject.getConditionsArrayList().size(), 2);
        assertTrue(testObject.getConditionAtPosition(0) instanceof ComplexConditionIf);
    }

    public void testAssemble()
    {
        System.out.println("assemble()");
        assertNotNull(testObject.getConditionsArrayList());
        assertEquals("<if><if></if></if>", testObject.assemble());
    }
}
