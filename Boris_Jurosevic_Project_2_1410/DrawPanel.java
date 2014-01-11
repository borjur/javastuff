//Boris Jurosevic
//Project 2 1410
//Date : November 26, 2012

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	private MyShape shapes[]; // array containing all the shapes
	private int shapeCount; // statistic on the number of each shape

	private int shapeType; // the type of shape to draw
	private MyShape currentShape; // the current shape being drawn
	private Color currentColor; // the color of the shape
	private boolean filledShape; // whether this shape is filled

	private JLabel statusLabel; // label displaying mouse coordinates

	// constructor
	public DrawPanel(JLabel status) {
		shapes = new MyShape[100]; // create the array
		shapeCount = 0; // initially we have no shapes

		setShapeType(0); // initially draw lines
		setDrawingColor(Color.BLACK); // start drawing with black40
										// setFilledShape( false );// not filled
										// by default
		currentShape = null; // not drawing anything initially

		setBackground(Color.WHITE); // set a white background

		// add the mouse listeners
		MouseHandler mouseHandler = new MouseHandler();
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);

		// set the status label for displaying mouse coordinates
		statusLabel = status;
	} // end DrawPanel constructor

	// draw shapes using polymorphism
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < shapeCount; i++)
			shapes[i].draw(g);

		if (currentShape != null)
			currentShape.draw(g);
	} // end method paintComponent

	// sets the type of shape to draw
	public void setShapeType(int shapeType) {
		if (shapeType < 0 || shapeType > 2)
			shapeType = 0;

		this.shapeType = shapeType;
	} // end method setShapeType

	// sets the drawing color
	public void setDrawingColor(Color c) {
		currentColor = c;
	} // end method setDrawingColor

	// clears the last shape drawn
	public void clearLastShape() {
		if (shapeCount > 0) {
			shapeCount--;
			repaint();
		} // end if
	} // end method clearLastShape

	// clears all drawings on this panel
	public void clearDrawing() {
		shapeCount = 0;

		repaint();
	} // end method clearDrawing

	// sets whether to draw a filled shape
	public void setFilledShape(boolean isFilled) {
		filledShape = isFilled;
	} // end method setFilledShape

	// load saved drawing
	public void loadDrawing() {
		ObjectInputStream input = null;

		try // user selects file, shapes are input
		{
			// use JFileChooser to select file
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

			int result = fileChooser.showOpenDialog(DrawPanel.this);

			// if user clicked Cancel button on dialog, return
			if (result == JFileChooser.CANCEL_OPTION)
				return;

			// get selected file
			File fileName = fileChooser.getSelectedFile();

			// display error if invalid
			if ((fileName == null) || (fileName.getName().equals(""))) {
				JOptionPane.showMessageDialog(DrawPanel.this,
						"Invalid File Name", "Invalid File Name",
						JOptionPane.ERROR_MESSAGE);
				return;
			} // end if

			// open file for input
			input = new ObjectInputStream(new FileInputStream(fileName));

			// read in number of shapes using deserialization
			shapeCount = (Integer) input.readObject();

			// read in shapes using deserialization
			// set shapes to be displayed on drawPanel
			shapes = (MyShape[]) input.readObject();

			repaint(); // redraw shapes
		} // end try
		catch (EOFException eofException) {
			JOptionPane.showMessageDialog(DrawPanel.this,
					"No more records in file.", "End of File",
					JOptionPane.ERROR_MESSAGE);
		} // end catch
		catch (ClassNotFoundException classNotFoundException) {
			JOptionPane.showMessageDialog(DrawPanel.this,
					"Unable to create object.", "Class Not Found",
					JOptionPane.ERROR_MESSAGE);
		} // end catch
		catch (IOException ioException) {
			JOptionPane.showMessageDialog(DrawPanel.this,
					"Error opening file.", "Error", JOptionPane.ERROR_MESSAGE);
		} // end catch
		finally {
			try {
				if (input != null)
					input.close(); // close file and stream
			} // end try
			catch (IOException ioException) {
				JOptionPane.showMessageDialog(DrawPanel.this,
						"Error closing file.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} // end catch
		} // end finally
	} // end method loadDrawing

	// save drawing as serialized objects
	public void saveDrawing() {
		ObjectOutputStream output = null;

		try {
			// use JFileChooser to select file
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

			int result = fileChooser.showSaveDialog(DrawPanel.this);

			// if user clicked Cancel button on dialog, return
			if (result == JFileChooser.CANCEL_OPTION)
				return;

			// get selected file
			File fileName = fileChooser.getSelectedFile();

			// display error if invalid
			if ((fileName == null) || (fileName.getName().equals(""))) {
				JOptionPane.showMessageDialog(DrawPanel.this,
						"Invalid File Name", "Invalid File Name",
						JOptionPane.ERROR_MESSAGE);
				return;
			} // end if

			// open file for output
			output = new ObjectOutputStream(new FileOutputStream(fileName));

			// write number of shapes to file
			output.writeObject(shapeCount);

			// write shapes to file using serialization
			output.writeObject(shapes);
		} // end try
		catch (IOException ioException) {
			JOptionPane.showMessageDialog(DrawPanel.this, "Error Opening File",
					"Error.", JOptionPane.ERROR_MESSAGE);
		} // end catch
		finally {
			try {
				if (output != null)
					output.close(); // close file and stream
			} // end try
			catch (IOException ioException) {
				JOptionPane.showMessageDialog(DrawPanel.this,
						"Error closing file.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} // end catch
		} // end finally
	} // end method saveDrawing

	// Handles mouse events for this JPanel
	private class MouseHandler extends MouseAdapter implements
			MouseMotionListener {
		// creates and sets the initial position for the new shape
		public void mousePressed(MouseEvent e) {
			if (currentShape != null)
				return;

			// create the appropriate shape based on shapeType
			switch (shapeType) {

			case 0:
				currentShape = new MyLine(e.getX(), e.getY(), e.getX(),
						e.getY(), currentColor);
				break;
			case 1:
				currentShape = new MyOval(e.getX(), e.getY(), e.getX(),
						e.getY(), currentColor, filledShape);
				break;
			case 2:
				currentShape = new MyRect(e.getX(), e.getY(), e.getX(),
						e.getY(), currentColor, filledShape);
				break;
			} // end switch
		} // end method mousePressed

		// fixes the current shape onto the panel
		public void mouseReleased(MouseEvent e) {
			if (currentShape == null)
				return;

			// set the second point on the shape
			currentShape.setX2(e.getX());
			currentShape.setY2(e.getY());

			// only set the shape if there is room in the array
			if (shapeCount < shapes.length) {
				shapes[shapeCount] = currentShape;
				shapeCount++;
			} // end if

			currentShape = null; // clear the temporary drawing shape
			repaint();
		} // end method mouseReleased

		// update the shape to the current mouse position while dragging
		public void mouseDragged(MouseEvent e) {
			if (currentShape != null) {
				currentShape.setX2(e.getX());
				currentShape.setY2(e.getY());
				repaint();
			} // end if

			mouseMoved(e); // update status bar
		} // end method mouseDragged

		// updates the status bar to show the current mouse coordinates
		public void mouseMoved(MouseEvent e) {
			statusLabel.setText(String.format("(%d,%d)", e.getX(), e.getY()));
		} // end method mouseMoved

	}
}