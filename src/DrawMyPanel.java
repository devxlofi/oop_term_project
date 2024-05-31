import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import javax.imageio.*;
import javax.swing.*;

public class DrawMyPanel extends JPanel 
{
  private LinkedList<GcuShape> myShapes; 
  private LinkedList<GcuShape> clearedShapes; 
  private String currentShapeType; 
  private GcuShape currentShapeObject; 
  private Color currentShapeColor;
  private boolean currentShapeFilled; 

  private JLabel statusLabel;

  public DrawMyPanel() 
  {
    myShapes = new LinkedList<GcuShape>();
    clearedShapes = new LinkedList<GcuShape>();

    // Default values
    currentShapeType = "Line";
    currentShapeObject = null;
    currentShapeColor = Color.BLACK;
    currentShapeFilled = false;

    statusLabel = new JLabel("");

    setLayout(new BorderLayout());
    setBackground(Color.WHITE);
    add(statusLabel, BorderLayout.SOUTH);

    MouseHandler handler = new MouseHandler();                             
    addMouseListener(handler);
    addMouseMotionListener(handler);
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    int width = getWidth(), height = getHeight();

    ArrayList<GcuShape> shapeArr = myShapes.getArray();
    for (int counter = shapeArr.size() - 1; counter >= 0; counter --) {
      shapeArr.get(counter).draw(g);
    }

    if (currentShapeObject != null) {
      currentShapeObject.draw(g);
    }
  }

  public void clearDrawing() {
    int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear the drawing?\nThis action cannot be undone.", "Confirm Clear", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    if (response == JOptionPane.YES_OPTION) {
      myShapes.makeEmpty();
      clearedShapes.makeEmpty();
      repaint();
    }
  }


  public void clearLastShape() {
    if (!myShapes.isEmpty()) {
        clearedShapes.addFront(myShapes.removeFront());
        repaint();
    }
  }


  public void redoLastShape() {
    if (!clearedShapes.isEmpty()) {
        myShapes.addFront(clearedShapes.removeFront());
        repaint();
    }
  }

  public void openImageToJPanel() {
    try {
      String fileName = JOptionPane.showInputDialog("Enter a file name:");
      if (fileName.equals(null) || fileName.trim().equals("") ) {
        throw new Exception("Please enter a file name!");
      }

      BufferedImage image = ImageIO.read(new File("./" + fileName + ".png"));
      add(new JLabel(new ImageIcon(image)));
    }
    catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(null, "The file was not found!");
    }
    catch (IOException e) {
      JOptionPane.showMessageDialog(null, "There was an error reading the file!");
    }
    catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
  }


  public void saveImageFromJPanel() {
    try {
        JFileChooser fileChooser = new JFileChooser(new File("."));
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getPath(); // 상대 경로 사용

            if (!filePath.toLowerCase().endsWith(".png")) {
                filePath += ".png";
                fileToSave = new File(filePath);
            }

            if (fileToSave.exists()) {
                throw new FileAlreadyExistsException("File already exists.");
            }

            this.remove(statusLabel); // 마우스 좌표를 저장하지 않기 위해 statusLabel을 제거

            BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            paint(img.createGraphics());
            ImageIO.write(img, "PNG", fileToSave);

            this.add(statusLabel, BorderLayout.SOUTH); // statusLabel을 다시 추가
            this.validate();
            JOptionPane.showMessageDialog(null, "The file was saved successfully.");
        }
    } catch (FileAlreadyExistsException e) {
        JOptionPane.showMessageDialog(null, "This file already exists");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}

  /**
   * Mutator Methods
   */

  public void setCurrentShapeType(String type) {
    currentShapeType = type;
  }

  public void setCurrentShapeColor(Color color) {
      currentShapeColor = color;
  }

  public Color getCurrentShapeColor() {
    return currentShapeColor;
  }


  public void setCurrentShapeFilled(boolean filled) {
      currentShapeFilled = filled;
  }

  private class MouseHandler extends MouseAdapter
  {
    @Override
    public void mousePressed(MouseEvent event)
    {
      switch (currentShapeType) {
          case "Line":
              currentShapeObject = new GcuLine( event.getX(), event.getY(), 
                                             event.getX(), event.getY(), currentShapeColor);
              break;
          case "Rectangle":
              currentShapeObject = new GcuRectangle( event.getX(), event.getY(), 
                                                  event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
              break;
          case "Oval":
              currentShapeObject = new GcuOval(event.getX(), event.getY(), 
                                                event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
              break;
      }
    }

    @Override
    public void mouseReleased(MouseEvent event)
    {
        currentShapeObject.setEndX(event.getX());
        currentShapeObject.setEndY(event.getY());
        
        myShapes.addFront(currentShapeObject);
        
        currentShapeObject = null;
        clearedShapes.makeEmpty();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent event)
    {
        statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d", event.getX(), event.getY()));
    }

    @Override
    public void mouseDragged(MouseEvent event)
    {
        currentShapeObject.setEndX(event.getX());
        currentShapeObject.setEndY(event.getY());
        
        statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d", event.getX(), event.getY()));
        
        repaint();
    }
  }
}