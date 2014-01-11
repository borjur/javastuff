//CS1410
//Junit

import junit.framework.Test; 
import junit.framework.TestSuite; 

public class SubscriptionTestSuite 
{
	//Builds the test suite.
	public static Test suite() 
	{ 
		TestSuite suite = new TestSuite(); 
		
		suite.addTestSuite( SubscriptionTest.class );
		
		return suite; 
	}
	 
	//Runs the test suite. 
	public static void main(String[] args) 
	{ 
		junit.textui.TestRunner.run(suite());
	} 
	
} 
