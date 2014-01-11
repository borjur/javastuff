// GCS Exercise 8.1 Solution: MyLine.java
// Declaration of class MyLine.
import java.awt.Color;
import java.awt.Graphics;

public class MyLine
{
   private int x1; // x coordinate of first endpoint
   private int y1; // y coordinate of first endpoint
   private int x2; // x coordinate of second endpoint
   private int y2; // y coordinate of second endpoint
   private Color myColor; // color of this shape

   // constructor initializes private variables with default values
   public MyLine()
   {
      this( 0, 0, 0, 0, Color.BLACK ); // call constructor to set values
   } // end MyLine no-argument constructor

   // constructor with input values
   public MyLine( int x1, int y1, int x2, int y2, Color color )
   {
      setX1( x1 ); // set x coordinate of first endpoint
      setY1( y1 ); // set y coordinate of first endpoint
      setX2( x2 ); // set x coordinate of second endpoint
      setY2( y2 ); // set y coordinate of second endpoint
      setColor( color ); // set the color
   } // end MyLine constructor

   // set the x-coordinate of the first point
   public void setX1( int x1 )
   {
     this.x1 = ( x1 >= 0 ? x1 : 0 );
   } // end method setX1

   // get the x-coordinate of the first point
   public int getX1()
   {
     return x1;
   } // end method getX1

   // set the x-coordinate of the second point
   public void setX2( int x2 )
   {
     this.x2 = ( x2 >= 0 ? x2 : 0 );
   } // end method setX2

   // get the x-coordinate of the second point
   public int getX2()
   {
     return x2;
   } // end method getX2

   // set the y-coordinate of the first point
   public void setY1( int y1 )
   {
     this.y1 = ( y1 >= 0 ? y1 : 0 );
   } // end method setY1

   // get the y-coordinate of the first point
   public int getY1()
   {
     return y1;
   } // end method getY1

   // set the y-coordinate of the second point
   public void setY2( int y2 )
   {
     this.y2 = ( y2 >= 0 ? y2 : 0 );
   } // end method setY2

   // get the y-coordinate of the second point
   public int getY2()
   {
     return y2;
   } // end method getY2

   // set the color
   public void setColor( Color color )
   {
      myColor = color;
   } // end method setColor

   // get the color
   public Color getColor()
   {
      return myColor;
   } // end method getColor
   
   // draw the line in the specified color
   public void draw( Graphics g )
   {
      g.setColor( getColor() );
      g.drawLine( getX1(), getY1(), getX2(), getY2() );
   } // end method draw
} // end class MyLine


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