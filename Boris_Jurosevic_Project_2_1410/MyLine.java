
//Boris Jurosevic
//Project 2 1410
//Date : November 26, 2012

import java.awt.Color;
import java.awt.Graphics;

public class MyLine extends MyShape {
	// call default superclass constructor
	public MyLine() {
		super();
	} // end MyLine no-argument constructor

	// call superclass constructor passing parameters
	public MyLine(int x1, int y1, int x2, int y2, Color color) {
		super(x1, y1, x2, y2, color);
	} // end MyLine constructor

	// draw line in specified color
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(getX1(), getY1(), getX2(), getY2());
	} // end method draw
} // end class MyLine