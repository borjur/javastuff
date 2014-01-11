
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame("safdf");
	 frame.setLayout(new BorderLayout());
    frame.setSize(300, 300);

    JPanel statusPanel = new JPanel();
    statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
    frame.add(statusPanel, BorderLayout.SOUTH);
    statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
    statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
    JLabel statusLabel = new JLabel("status");
    statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
    statusPanel.add(statusLabel);

    frame.setVisible(true);
  }

}