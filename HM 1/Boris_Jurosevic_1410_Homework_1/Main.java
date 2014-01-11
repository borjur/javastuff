//Boris Jurosevic
//Homework 1




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Boris Jurosevic");
	 frame.setLayout(new BorderLayout());
    frame.setSize(300, 300);

    JPanel statusPanel = new JPanel();
    frame.add(new LinesRectsOvalsJPanel());
    statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
    frame.add(statusPanel, BorderLayout.SOUTH);
    statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
    statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
    JLabel statusLabel = new JLabel("Lines:3, Rectangles:6, Ovals:6");
    statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
    statusPanel.add(statusLabel);

    frame.setVisible(true);
  }

}

