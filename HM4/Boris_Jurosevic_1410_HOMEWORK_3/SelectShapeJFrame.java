
import java.awt.BorderLayout;
 import java.awt.event.ItemListener;
 import java.awt.event.ItemEvent;
 import java.awt.Graphics;
 import javax.swing.JComboBox;
 import javax.swing.JFrame;

 public class SelectShapeJFrame extends JFrame
 {
 private ShapeOption shapeOptions[] = { ShapeOption.CIRCLE,
 ShapeOption.SQUARE, ShapeOption.OVAL, ShapeOption.RECTANGLE };
 private ShapeOption shape = ShapeOption.CIRCLE;

 private JComboBox choiceJComboBox;
 private SelectShapeJPanel selectShapeJPanel;

 public SelectShapeJFrame()
 {
 super( "Different Shapes" );

 selectShapeJPanel = new SelectShapeJPanel();

 choiceJComboBox = new JComboBox( shapeOptions );
 choiceJComboBox.addItemListener(

 new ItemListener() // anonymous inner class
 {
 public void itemStateChanged( ItemEvent e )
 {
 selectShapeJPanel.setShape(
 ( ShapeOption ) choiceJComboBox.getSelectedItem() );
 repaint();
 } 
 } 
 ); 

 add( selectShapeJPanel, BorderLayout.CENTER );
 add( choiceJComboBox, BorderLayout.NORTH );
 } 
 } 