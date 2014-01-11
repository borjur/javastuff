
import java.awt.*;
import javax.swing.*; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LinesRectsOvalsJPanel extends JPanel {


public LinesRectsOvalsJPanel() {

    
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(10, 23));

    JPanel rightPanel = new JPanel(new BorderLayout());
    rightPanel.setOpaque(true);

    add(rightPanel, BorderLayout.EAST);
	 

  }




   public void paintComponent( Graphics g ) {

       super.paintComponent( g ); 
		 
		 this.setBackground( Color.WHITE );
		 //   x y width height
		 g.setColor(Color.BLACK);
		 g.drawLine(5,10,5,30);
		 g.setColor(Color.BLUE);
		 g.drawLine(18,70,127,24);
		 g.setColor(Color.RED);
		 g.drawLine(25,45,100,38);
		 
		 g.setColor(Color.YELLOW);
		 g.drawOval(23,25,23,55);
		 g.setColor(Color.BLACK);
		 g.drawOval(15,14,40,78);
		 g.setColor(Color.CYAN);
		 g.drawOval(180,102,5,90);
		 g.setColor(Color.RED);
		 g.drawOval(21,20,89,11);
		 g.setColor(Color.BLUE);
		 g.drawOval(35,87,39,27);
		 g.setColor(Color.YELLOW);
		 g.fillRect(87,5,5,60);
		 g.setColor(Color.GREEN);
		 g.fillRect(105,15,15,85);
		 g.setColor(Color.CYAN);
		 g.fillRect(14,45,76,86);
		 g.setColor(Color.RED);
		 g.fillRect(70,79,65,86);
		 g.setColor(Color.BLUE);
		 g.fillRect(90,108,5,8); 
		 
  }
 
}