

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
 
public class FacePanel extends JPanel {
 
   private Point mouseDown,  mouseHere,  mouseClicked;
   private Face faceToDrag;
   private Ellipse2D.Double circle;
   private Rectangle2D.Double rect;
   private List<Face> faces = new ArrayList<Face>();
 
   public FacePanel() {
      FaceListener listener = new FaceListener();
      addMouseListener(listener);
      addMouseMotionListener(listener);
      addMouseWheelListener(listener);
 
      setBackground(Color.WHITE);
      setFocusable(true);
   }
 
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
              RenderingHints.VALUE_ANTIALIAS_ON);
      for (Face face : faces) {
         face.paint(g2);
      }
      if (rect != null) {
         if (circle == null) {
            g2.setColor(Color.RED);
         } else {
            g2.draw(circle);
         }
         g2.setStroke(new BasicStroke(1.0f,
                 BasicStroke.CAP_BUTT,
                 BasicStroke.JOIN_BEVEL,
                 1.0f,
                 new float[] {0.8f, 6.0f},
                 0.0f));
         g2.draw(rect);
      }
   }
 
   public void makeUI() {
      JFrame frame = new JFrame("Midterm_2");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(600, 400);
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.add(this);
      frame.setVisible(true);
 
      String[] instructions = {"Drag the mouse diagonally to draw a face.",
      "Move the mouse and the eyes follow.",
      "Click and the nose points at the mouse.",
      "Double click to change the mouth.",
      "Drag the face around with the mouse.",
      "Use the scroll wheel to change the size.",
      "Right click to delete a face.",
      "",
      "Draw as many faces as you like!"
      };
      JOptionPane.showMessageDialog(frame, instructions);
   }
 
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
 
         @Override
         public void run() {
            new FacePanel().makeUI();
         }
      });
   }
 
   private class FaceListener extends MouseAdapter {
 
      @Override
      public void mousePressed(MouseEvent e) {
         if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = e.getPoint();
            faceToDrag = getFaceAt(mouseDown);
            if (faceToDrag != null) {
               faceToDrag.setOffset(mouseDown);
            }
         }
      }
 
      @Override
      public void mouseDragged(MouseEvent e) {
         if ((e.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) == 0) {
            return;
         }
 
         mouseHere = e.getPoint();
         if (faceToDrag != null) {
            faceToDrag.setLocation(mouseHere);
         } else {
            double downX = mouseDown.getX();
            double downY = mouseDown.getY();
            double hereX = mouseHere.getX();
            double hereY = mouseHere.getY();
 
            double l = Math.min(downX, hereX);
            double t = Math.min(downY, hereY);
            double w = Math.abs(downX - hereX);
            double h = Math.abs(downY - hereY);
            rect = new Rectangle2D.Double(l, t, w, h);
 
            double dia = Math.min(w, h);
            if (dia >= Face.MIN_SIDE) {
               l = downX < hereX ? downX : downX - dia;
               t = downY < hereY ? downY : downY - dia;
               circle = new Ellipse2D.Double(l, t, dia, dia);
            } else {
               circle = null;
            }
         }
         repaint();
      }
 
      @Override
      public void mouseReleased(MouseEvent e) {
         faceToDrag = null;
         if (circle != null) {
            if (mouseClicked == null) {
               mouseClicked = new Point((int) circle.getCenterX(),
                       (int) circle.getCenterY());
            }
            faces.add(new Face(circle));
         }
         circle = null;
         rect = null;
         repaint();
      }
 
      @Override
      public void mouseMoved(MouseEvent e) {
         mouseHere = e.getPoint();
         repaint();
      }
 
      @Override
      public void mouseClicked(MouseEvent e) {
         Point p = e.getPoint();
         switch (e.getButton()) {
            case MouseEvent.BUTTON1:
               switch (e.getClickCount()) {
                  case 1:
                     mouseClicked = p;
                     break;
                  case 2:
                     for (int i = faces.size() - 1; i >= 0; i--) {
                        if (faces.get(i).
                                contains(p)) {
                           faces.get(i).
                                   changeMouth();
                           break;
                        }
                     }
                     break;
               }
               break;
            case MouseEvent.BUTTON3:
               Face face = getFaceAt(p);
               if (face != null) {
                  faces.remove(face);
               }
               break;
         }
         repaint();
      }
 
      @Override
      public void mouseWheelMoved(MouseWheelEvent e) {
         Point p = e.getPoint();
         Face face = getFaceAt(p);
         if (face != null) {
            face.setOffset(p);
            face.setSize(e.getUnitsToScroll());
            repaint();
         }
      }
 
      private Face getFaceAt(Point p) {
         Face faceAtP = null;
         for (int i = faces.size() - 1; i >= 0; i--) {
            if (faces.get(i).
                    contains(p)) {
               faceAtP = faces.get(i);
               faces.remove(faceAtP);
               faces.add(faceAtP);
               break;
            }
         }
         return faceAtP;
      }
   }
 
   private class Face extends Ellipse2D.Double {
 
      static final int MIN_SIDE = 30;
      final static int MOUTH_TYPES = 5;
      int mouthType;
      Point offset = new Point();
      Graphics2D g2;
 
      public Face(Ellipse2D.Double circle) {
         super(circle.x, circle.y, circle.width, circle.height);
      }
 
      public void setOffset(Point p) {
         offset.x = p.x - (int) x;
         offset.y = p.y - (int) y;
      }
 
      public void setLocation(Point p) {
         x = p.x - offset.x;
         y = p.y - offset.y;
      }
 
      public void setSize(int increment) {
         double ratioX = offset.x / width;
         double ratioY = offset.y / height;
 
         width = Math.max(MIN_SIDE, width - increment);
         height = width;
 
         x += offset.x - ratioX * width;
         y += offset.y - ratioY * height;
      }
 
      public void changeMouth() {
         mouthType++;
         mouthType %= MOUTH_TYPES;
      }
 
      public void paint(Graphics2D g) {
         g2 = g;
 
         Point2D.Double noseCenter = new Point2D.Double(getCenterX(),
                 getCenterY());
         float[] floats = {0.1f, 0.3f, 0.6f};
         Color[] colors = {Color.WHITE, Color.YELLOW, Color.ORANGE};
         RadialGradientPaint paint = getPaint(this, noseCenter, floats, colors);
         g2.setPaint(paint);
         g2.fill(this);
         g2.setColor(Color.BLACK);
         g2.draw(this);
 
         double dia = width * 0.27;
         double top = y + getHeight() * 0.16;
         double left = getCenterX() - dia * 1.3;
         paintEye(left, top, dia);
 
         left = getCenterX() + dia * 0.3;
         paintEye(left, top, dia);
 
         left = noseCenter.x - dia * 0.5;
         top = noseCenter.y - dia * 0.5;
         paintNose(left, top, dia);
 
         paintMouth();
      }
 
      private void paintEye(double left, double top, double dia) {
         Ellipse2D.Double eye = new Ellipse2D.Double(left, top, dia, dia);
         Point2D.Double focus = getOffCenter(eye, mouseHere);
         float[] floats = {0.1f, 0.25f, 1.0f};
         Color[] colors = {Color.WHITE, Color.LIGHT_GRAY.brighter(),
            Color.LIGHT_GRAY
         };
         RadialGradientPaint paint = getPaint(eye, focus, floats, colors);
         g2.setPaint(paint);
         g2.fill(eye);
         g2.setColor(Color.BLACK);
         g2.draw(eye);
 
         Shape clip = g2.getClip();
         g2.setClip(eye);
 
         Ellipse2D iris = new Ellipse2D.Double(focus.x - dia * 0.3,
                 focus.y - dia * 0.3, dia * 0.6, dia * 0.6);
         g2.fill(iris);
         iris = new Ellipse2D.Double(focus.x - dia * 0.3 - 2,
                 focus.y - dia * 0.3 - 2, dia * 0.6 + 4, dia * 0.6 + 4);
         g2.draw(iris);
 
         g2.setClip(clip);
      }
 
      private void paintNose(double left, double top, double dia) {
         Ellipse2D.Double nose = new Ellipse2D.Double(left, top, dia, dia);
         Point2D.Double focus = getOffCenter(nose, mouseClicked);
         float[] floats = {0.01f, 0.2f, 1.0f};
         Color[] colors = {Color.WHITE, Color.RED, Color.RED.darker()};
         RadialGradientPaint paint = getPaint(nose, focus, floats, colors);
         g2.setPaint(paint);
         g2.fill(nose);
         g2.setColor(Color.BLACK);
         g2.draw(nose);
      }
 
      public void paintMouth() {
         Shape mouth = null;
         double mouthX;
         double mouthY;
         double mouthW;
         double mouthH;
         double start;
         double extent;
         boolean fill = false;
 
         switch (mouthType) {
            case 0: // :)
 
               mouthX = x + width * 0.2;
               mouthY = y + height * 0.2;
               mouthW = width * 0.6;
               mouthH = height * 0.6;
               start = 210;
               extent = 120;
               mouth = new Arc2D.Double(mouthX, mouthY, mouthW, mouthH,
                       start, extent, Arc2D.OPEN);
               break;
            case 1: // :(
 
               mouthX = x + width * 0.2;
               mouthY = y + height * 0.7;
               mouthW = width * 0.6;
               mouthH = height * 0.5;
               start = 30;
               extent = 120;
               mouth = new Arc2D.Double(mouthX, mouthY, mouthW, mouthH,
                       start, extent, Arc2D.OPEN);
               break;
            case 2: // :D
 
               mouthX = x + width * 0.25;
               mouthY = y + height * 0.35;
               mouthW = width * 0.5;
               mouthH = height * 0.5;
               start = 200;
               extent = 140;
               mouth = new Arc2D.Double(mouthX, mouthY, mouthW, mouthH,
                       start, extent, Arc2D.CHORD);
               fill = true;
               break;
            case 3: // :O
 
               mouthX = x + width * 0.41;
               mouthY = y + width * 0.69;
               mouthW = width * 0.18;
               mouthH = mouthW;
               mouth = new Ellipse2D.Double(mouthX, mouthY, mouthW, mouthH);
               fill = true;
               break;
            case 4: // :S
 
               Path2D.Double path = new Path2D.Double();
               path.moveTo(x + width * 0.25, y + height * 0.75);
               path.curveTo(x + width * 0.44, y + height * 0.95,
                       x + width * 0.56, y + height * 0.55,
                       x + width * 0.75, y + height * 0.75);
               mouth = path;
         }
 
         if (fill) {
            g2.setColor(Color.WHITE);
            g2.fill(mouth);
         }
         g2.setColor(Color.BLACK);
         Stroke stroke = g2.getStroke();
         g2.setStroke(new BasicStroke((float) height * 0.02f,
                 BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
         g2.draw(mouth);
         g2.setStroke(stroke);
      }
 
      private RadialGradientPaint getPaint(Ellipse2D.Double circle,
              Point2D.Double focus, float[] floats, Color[] colors) {
         Point2D.Double center = new Point2D.Double(circle.getCenterX(),
                 circle.getCenterY());
         return new RadialGradientPaint(center, (float) circle.width, focus,
                 floats, colors, CycleMethod.NO_CYCLE);
      }
 
      private Point2D.Double getOffCenter(Ellipse2D.Double circle,
              Point mousePoint) {
         double circleX = circle.getCenterX();
         double circleY = circle.getCenterY();
         Point2D.Double circleCenter = new Point2D.Double(circleX, circleY);
         double circleDia = circle.width;
 
         Point2D.Double intersect = getIntersect(circleX, circleY, mousePoint);
 
         double mouseX = mousePoint.getX();
         double mouseY = mousePoint.getY();
         Point2D.Double refPoint = new Point2D.Double(mouseX, mouseY);
 
         double ratio = getDistance(circleCenter, refPoint) / getDistance(
                 circleCenter, intersect);
         double offCenter = circleDia * ratio * 0.4;
 
         double distance = getDistance(circleCenter, refPoint);
         if (distance == 0.0) {
            return circleCenter;
         }
         double offsetX = circleX - offCenter * (circleX - mouseX) / distance;
         double offsetY = circleY - offCenter * (circleY - mouseY) / distance;
 
         return new Point2D.Double(offsetX, offsetY);
      }
 
      private double getDistance(Point2D.Double one, Point2D.Double two) {
         return Math.sqrt(squareOf(one.x - two.x) + squareOf(one.y - two.y));
      }
 
      private double squareOf(double d) {
         return d * d;
      }
 
      private Point2D.Double getIntersect(double centerX, double centerY,
              Point mousePoint) {
         double mouseX = mousePoint.getX();
         double mouseY = mousePoint.getY();
 
         if (centerX == mouseX && centerY == mouseY) {
            return new Point2D.Double(0, 0);
         }
         if (centerX == mouseX) {
            return new Point2D.Double(centerX, 0);
         }
         if (centerY == mouseY) {
            return new Point2D.Double(0, centerY);
         }
 
         double w = FacePanel.this.getWidth();
         double h = FacePanel.this.getHeight();
         double intersectX,  intersectY;
 
         if (mouseX < centerX) {
            intersectY = centerY - (centerY - mouseY) * (centerX / (centerX -
                    mouseX));
         } else {
            intersectY = centerY + (mouseY - centerY) * (w - centerX) / 
                    (mouseX - centerX);
         }
         intersectY = Math.min(Math.max(0, intersectY), h);
 
         if (mouseY < centerY) {
            intersectX = centerX - (centerX - mouseX) * (centerY / (centerY -
                    mouseY));
         } else {
            intersectX = centerX + (mouseX - centerX) * (h - centerY) / 
                    (mouseY - centerY);
         }
         intersectX = Math.min(Math.max(0, intersectX), w);
 
         return new Point2D.Double(intersectX, intersectY);
      }
   }
}