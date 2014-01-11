// GCS Exercise 8.1 Solution: DrawPanel.java
// Program that uses classes MyLine, MyOval and MyRectangle to draw
// random shapes.
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class DrawPanel extends JPanel
{
   private Random randomNumbers = new Random();
   
   private MyLine[] lines; // array of lines
   private MyOval[] ovals; // array of ovals
   private MyRectangle[] rectangles; // array of rectangles

   // constructor, creates a panel with random shapes
   public DrawPanel()
   {
      setBackground( Color.WHITE );
      
      lines = new MyLine[ 1 + randomNumbers.nextInt( 5 ) ];
      ovals = new MyOval[ 1 + randomNumbers.nextInt( 5 ) ];
      rectangles = new MyRectangle[ 1 + randomNumbers.nextInt( 5 ) ];

      // create lines
      for ( int count = 0; count < lines.length; count++ )
      {
         // generate random coordinates
         int x1 = randomNumbers.nextInt( 450 );
         int y1 = randomNumbers.nextInt( 450 );
         int x2 = randomNumbers.nextInt( 450 );
         int y2 = randomNumbers.nextInt( 450 );
         
         // generate a random color
         Color color = new Color( randomNumbers.nextInt( 256 ), 
            randomNumbers.nextInt( 256 ), randomNumbers.nextInt( 256 ) );
         
         // add the line to the array of lines to be displayed
         lines[ count ] = new MyLine( x1, y1, x2, y2, color );
      } // end for

      // create ovals
      for ( int count = 0; count < ovals.length; count++ )
      {
         // generate random coordinates
         int x1 = randomNumbers.nextInt( 450 );
         int y1 = randomNumbers.nextInt( 450 );
         int x2 = randomNumbers.nextInt( 450 );
         int y2 = randomNumbers.nextInt( 450 );
         
         // generate a random color
         Color color = new Color( randomNumbers.nextInt( 256 ), 
            randomNumbers.nextInt( 256 ), randomNumbers.nextInt( 256 ) );
         
         // get filled property
         boolean filled = randomNumbers.nextBoolean();
         
         // add the oval to the array of ovals to be displayed
         ovals[ count ] = new MyOval( x1, y1, x2, y2, color, filled );
      } // end for

      // create rectangles
      for ( int count = 0; count < rectangles.length; count++ )
      {
         // generate random coordinates
         int x1 = randomNumbers.nextInt( 450 );
         int y1 = randomNumbers.nextInt( 450 );
         int x2 = randomNumbers.nextInt( 450 );
         int y2 = randomNumbers.nextInt( 450 );
         
         // generate a random color
         Color color = new Color( randomNumbers.nextInt( 256 ), 
            randomNumbers.nextInt( 256 ), randomNumbers.nextInt( 256 ) );

         // get filled property
         boolean filled = randomNumbers.nextBoolean();

         // add the rectangle to the array of rectangles to be displayed
         rectangles[ count ] =
            new MyRectangle( x1, y1, x2, y2, color, filled );
      } // end for
   } // end DrawPanel constructor

   // for each shape array, draw the individual shapes
   public void paintComponent( Graphics g )
   {
      super.paintComponent( g );
      
      // draw the lines
      for ( MyLine line : lines )
         line.draw( g );

      // draw the ovals
      for ( MyOval oval: ovals )
         oval.draw( g );

      // draw the rectangles
      for ( MyRectangle rectangle : rectangles )
         rectangle.draw( g );
   } // end method paintComponent
} // end class DrawPanel


/**************************************************************************
 * (C) Copyright 1992-2012 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/