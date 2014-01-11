//Boris Jurosevic
//Homework 4
//CS 1410
//Date : November 27, 2012


import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.*;
import java.util.*;

public class SpamFilterUsingJFileChooser{

   public static void main(String[] args) throws Exception {
	   
	   int nnames;
	   
	   String[] words = { "hate", "late", "mate", "date","gate", "state", "fate", "al", "alabama", "algore", "alta", "alma", "alpha", "alex"};
	
	JFileChooser fileChooser = new JFileChooser();
	if(fileChooser.showOpenDialog(null)
	   ==JFileChooser.APPROVE_OPTION ) {
		//get the selected file
		java.io.File file = fileChooser.getSelectedFile();
		
		 //create a scanner for the file
		Scanner input = new Scanner(file);
		
		//read data from the file
		while(input.hasNext()){

		
		System.out.println(input.nextLine());
		}
		//close the file
		input.close();
		}
		else {
		   System.out.println("No file selected");
			}
	
	System.out.print("How many words are you going to save ? Enter a number: ");
	Scanner in = new Scanner(System.in);
	nnames = Integer.parseInt(in.nextLine().trim());

	words = new String[nnames];
	for (int i = 0; i < words.length; i++){
	        System.out.print("Type a word: ");
	        words[i] = in.nextLine();
	}
		
    for (String w: words) {
        if (w.startsWith("al")) {
            System.out.println(w + " word is a high Spam!");
            
            
        }
        else if(w.isEmpty()){
        	System.out.println(w + " word is a low Spam");
        	return;
        }

    }
	
    for (String w: words) {
        if (w.endsWith("te")) {
            System.out.println(w + " word is a high Spam!");
            
		}
        else if(w.isEmpty()){
        	System.out.println(w + " word is a low Spam");
        	return;
        }



		

		
   }
    
    in.close();
   }
   
  
   
   }


		

	




