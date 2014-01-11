import java.io.*;


public class FixedLengthStringIO {
	
	//read fixed number of characters from datainput stream
	public static String readFixedLengthString(int size,
			DataInput in) throws IOException {
		//declare an array of characters
		char[] chars = new char[size];
		
		//read fixed number of characters to the array
		for(int i = 0; i < size; i++)
			chars[i] = in.readChar();
		return new String (chars);
	}
	
	//write fixed number of characters to a dataoutput stream
	
public static void writeFixedLengthString(String s, int size,
		DataOutput out) throws IOException {
	char[] chars = new char[size];
	
	//fill an array of characters from the string
	s.getChars(0, s.length(), chars, 0);
	
	//fill in blank characters in the rest of the array
	for (int i = s.length(); i < chars.length; i++)
		chars[i] = ' ';
	//Arrays.fill(chars, s.length(), chars.length, ' ');
	
	//create and write a new string padded with blank characters
	out.writeChars(new String(chars));
}
	}






