//Boris Jurosevic
//Project_1
//Date 10/01/2012
//tutorial youtube videos, and how to program fifth edition


import java.io.IOException;

public class Error {
	
	public static void main(String[] args) {
		
		try {
			throw new IOException();
			
		}catch( Exception exception){
			exception.printStackTrace();
		}
		catch ( IOException ioException ){
			System.err.println(" Exception error");
		}
		
	}

}
