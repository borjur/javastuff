//CS1410
//Junit

import junit.framework.TestCase; 

public class SubscriptionTest extends TestCase
{
	//test fixtures
	private Subscription s1, s2;

	//called before every test case method
	protected void setUp()
	{
		s1 = new Subscription(200,2);
		s2 = new Subscription(200,3);
	}
	
	//called after every test case method
	protected void tearDown() 
	{ 
		// release objects under test here, if necessary 
		s1 = null;
		s2 = null;
	} 
	
	//test returns Euro
   public void test_if_pricePerMonth_returns_Euro() {
      assertEquals(1.0, s1.pricePerMonth());
   }

	//test rounds up
   public void test_if_pricePerMonth_rounds_up() {
      assertEquals(0.67, s2.pricePerMonth());
   }
}