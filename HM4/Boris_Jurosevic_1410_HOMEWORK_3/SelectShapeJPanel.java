

import java.awt.Graphics;
 import java.util.Random;
 import javax.swing.JPanel;

 public class SelectShapeJPanel extends JPanel
 {
 private final int SIZE = 250;
 private ShapeOption shape = ShapeOption.CIRCLE;

 // 
 public void paintComponent( Graphics g )
 {
 super.paintComponent( g );
 Random random = new Random(); // get random number generator

 for ( int count = 1; count <= 10; count++ )
 {
 // add 10 and 25 to prevent drawing over edge
 int x = ( int ) ( random.nextFloat() * SIZE ) + 5;
 int y = ( int ) ( random.nextFloat() * SIZE ) + 15;
int width = ( int ) ( random.nextFloat() * ( SIZE - x ) );
 int height = ( int ) ( random.nextFloat() * ( SIZE - y ) );

// used for circle and square, to prevent drawing off the window
int diameter = width;

 if ( width > height )
 diameter = height;

 // draw the appropriate shape
 switch ( shape )
 {
 case CIRCLE:
 g.drawOval( x, y, diameter, diameter );
 break;
 case SQUARE:
 g.drawRect( x, y, diameter, diameter );
 break;

case OVAL:
 g.drawOval( x, y, width, height );
break;
case RECTANGLE:
 g.drawRect( x, y, width, height );
 break;
 } 
 } 
 } 

// set new shape
 public void setShape( ShapeOption preference )
 {
 shape = preference;
 } 
 } 
