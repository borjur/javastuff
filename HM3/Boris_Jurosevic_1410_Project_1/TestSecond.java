//Boris Jurosevic
//Project_1
//Date 10/01/2012
//tutorial youtube videos, and how to program fifth edition

public class TestSecond {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			throw new ExceptionC();
			
		}
		catch ( ExceptionA exception1){
			System.err.println("First Exception happened! \n");
		}
		
		try {
			throw new ExceptionB();
		}
		catch ( ExceptionA exception2) {
			System.err.println("Second Exception happened! \n");
		}

	}

}

class ExceptionA extends Exception {
	
}

class ExceptionB extends ExceptionA {
	
}

class ExceptionC extends ExceptionB {
	
}
