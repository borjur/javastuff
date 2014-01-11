//Boris Jurosevic
//Project_1
//Date 10/01/2012
//tutorial youtube videos, and how to program fifth edition


public class TestThird {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			someMethod();
		}catch( Exception exception){
			System.err.printf("%s\n\n", exception.getMessage());
			exception.printStackTrace();
		}

	}
	
	
	
	
	
	public static void someMethod() throws Exception {
		try{
			someMethod2();
		}catch (Exception exception2){
			throw exception2;
		}
	}
	
	public static void someMethod2() throws Exception {
		throw new Exception( "Exception in Second Method!");
	}

}
