


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScrollBarProject_2 extends JFrame {
//creates horizontal and vertical scroll bars
    private JScrollBar jscbHort =
	 new JScrollBar(JScrollBar.HORIZONTAL);   //horizontal  scroll bar
	 private JScrollBar jscbVert = 
	 new JScrollBar(JScrollBar.VERTICAL);     //vertical scroll bar
	 
	 //Create a message Panel
	 private MessagePanel messagePanel = new MessagePanel("Project 2 by Boris Jurosevic");
	 
	 public static void main(String[] args){
	 ScrollBarProject_2 frame = new ScrollBarProject_2();
	 frame.setTitle("Scroll Bar");
	 frame.setLocationRelativeTo(null);// Center the frame
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 frame.pack();
	 frame.setVisible(true);
	 }
	 
	 public ScrollBarProject_2() {
	 //add scroll bars and message panel to the frame
	 setLayout(new BorderLayout());
	 add(messagePanel, BorderLayout.CENTER);
	 add(jscbVert, BorderLayout.EAST);
	 add(jscbHort, BorderLayout.SOUTH);
	 
	 //register listener for the scroll bars
	 jscbHort.addAdjustmentListener(new AdjustmentListener() {
	 public void adjustmentValueChanged(AdjustmentEvent e) {
	 //getValue() and getMaximumValue() return int, but for better
	 //precision, use double
	 double value = jscbHort.getValue();
	 double maximumValue = jscbHort.getMaximum();
	 double newX = (value * messagePanel.getWidth() / maximumValue);
		 messagePanel.setXCoordinate((int)newX);
		 }
		 });
		 		 jscbVert.addAdjustmentListener(new AdjustmentListener() {
		  public void adjustmentValueChanged(AdjustmentEvent e) {
		  	 //getValue() and getMaximumValue() return int, but for better
	       //precision, use double
			 double value = jscbVert.getValue();
			 double maximumValue = jscbVert.getMaximum();
			 double newY = (value * messagePanel.getHeight() /
			  			  maximumValue);
			  messagePanel.setYCoordinate((int)newY);
			  }
			  });
			  }
	 		  
	 
	}