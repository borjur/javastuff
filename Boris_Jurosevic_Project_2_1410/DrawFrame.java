//Boris Jurosevic
//Project 2 1410
//Date : November 26, 2012

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawFrame extends JFrame implements ItemListener, ActionListener {
	// Array of possible colors
	private Color colors[] = { Color.BLACK, Color.BLUE, Color.CYAN,
			Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY,
			Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE,
			Color.YELLOW };

	// Array of names corresponding to the possible colors
	private String colorNames[] = { "Black", "Blue", "Cyan", "Dark Gray",
			"Gray", "Green", "Light Gray", "Magenta", "Orange", "Pink", "Red",
			"White", "Yellow" };

	// Array of possible shapes
	private String shapes[] = { "Line", "Oval", "Rectangle" };

	private DrawPanel drawPanel; // panel that handles the drawing

	private JButton loadButton; // button to load saved drawing
	private JButton saveButton; // button to save drawing to file
	private JButton undoButton; // button to undo the last shape drawn
	private JButton clearButton; // button to clear all shapes
	private JComboBox colorChoices; // combo box for selecting the color
	private JComboBox shapeChoices; // combo box for selecting shapes
	private JCheckBox filledCheckBox; // check box to toggle filled shapes

	// constructor
	public DrawFrame() {
		super("Boris Jurosevic Project 2");

		// create a panel to store the components at the top of the frame
		JPanel topPanel = new JPanel(new FlowLayout());

		// create a button for loading a saved drawing
		loadButton = new JButton("Load");
		loadButton.addActionListener(this);
		topPanel.add(loadButton);

		// create a button for saving drawing
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		topPanel.add(saveButton);

		// create a button for clearing the last drawing
		undoButton = new JButton("Undo");
		undoButton.addActionListener(this);
		topPanel.add(undoButton);

		// create a button for clearing all drawings
		clearButton = new JButton("Clear");
		clearButton.addActionListener(this);
		topPanel.add(clearButton);

		// create a combo-box for choosing colors
		colorChoices = new JComboBox(colorNames);
		colorChoices.addItemListener(this);
		topPanel.add(colorChoices);

		// create a combo-box for choosing shapes
		shapeChoices = new JComboBox(shapes);
		shapeChoices.addItemListener(this);
		topPanel.add(shapeChoices);

		// create a check-box to determine whether the shape is filled
		filledCheckBox = new JCheckBox("Filled");
		filledCheckBox.addItemListener(this);
		topPanel.add(filledCheckBox);

		// add the top panel to the frame
		add(topPanel, BorderLayout.NORTH);

		// create a label for the status bar
		JLabel statusLabel = new JLabel("(0,0)");

		// add the status bar at the bottom
		add(statusLabel, BorderLayout.SOUTH);

		// create the DrawPanel with its status bar label
		drawPanel = new DrawPanel(statusLabel);

		add(drawPanel); // add the drawing area to the center
	} // end DrawFrame constructor

	// handle selections made to a combo box of check box
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == shapeChoices) // choosing a shape

			drawPanel.setShapeType(shapeChoices.getSelectedIndex());
		else if (e.getSource() == colorChoices) // choosing a color
			drawPanel.setDrawingColor(colors[colorChoices.getSelectedIndex()]);
		else if (e.getSource() == filledCheckBox) // filled/unfilled
			drawPanel.setFilledShape(filledCheckBox.isSelected());
	} // end method itemStateChanged

	// handle button clicks
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loadButton)
			drawPanel.loadDrawing();
		else if (e.getSource() == saveButton)
			drawPanel.saveDrawing();
		else if (e.getSource() == undoButton)
			drawPanel.clearLastShape();
		else if (e.getSource() == clearButton)
			drawPanel.clearDrawing();
	} // end method actionPerformed
}