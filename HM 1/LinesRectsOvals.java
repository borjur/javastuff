




import java.awt.*;
import javax.swing.*;


public class LinesRectsOvals {

public static void main( String args[] ) {

 
  
    JFrame frame =
          new JFrame( "Lines, rectangles and ovals frame ");
				 
			 LinesRectsOvalsJPanel linesRectsOvalsJPanel =
          new LinesRectsOvalsJPanel();
			 

          
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




			 
			 linesRectsOvalsJPanel.setBackground( Color.WHITE );
          frame.add( linesRectsOvalsJPanel ); // add panel to frame
          frame.setSize( 300, 300 ); // set frame size
			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setVisible( true );	
			 
			 JPanel panel = new JPanel(); // the panel is not visible in output
          JLabel label = new JLabel("Lines:3 Ovals:5 Rect:5" );
			 
			 
          
			 
			 panel.add(label);
			



          frame.getContentPane().add(BorderLayout.SOUTH,panel);
			 
			 frame.setVisible(true);
			
	}


}
