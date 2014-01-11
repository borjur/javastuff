

public enum ShapeOption
 {
// declare contents of enum type
CIRCLE( 1 ),
 SQUARE( 2 ),
 OVAL( 4 ),
 RECTANGLE( 6 );

 private final int value; 

 ShapeOption( int valueOption )

{
 value = valueOption;
 } 

 public int getValue()
 {
 return value;
 } 
 } 


