
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DrawMyFrame extends JFrame
{
  private JMenuBar menuBar = new JMenuBar();

  private JMenu fileMenu = new JMenu("File");
  private String[] fileOptions = {"Undo", "Redo", "Clear", "Save To", "Open From"};

  private JMenu shapeMenu = new JMenu("Shapes");
  private String[] shapeOptions = {"Line", "Rectangle", "Oval"};

  private JMenu methodMenu = new JMenu("Method");
  private String[] methodOptions = {"Fill", "Draw"};

  private JPanel colorPanel = new JPanel();
  private String[] colors = {"Black", "Red", "Green", "Blue", "Cyan", "Magenta", "Yellow"};

  DrawMyPanel panel = new DrawMyPanel();

  public DrawMyFrame()
  {
    super("GCU Paint Tools");

    setJMenuBar(menuBar);

    colorPanel.setLayout(new GridLayout( 1, 6, 10, 10 ));
    add(colorPanel, BorderLayout.NORTH);
    
    add(panel, BorderLayout.CENTER);

    addButtonsToJPanel(colorPanel, colors);

    addMenuItemsToJMenu(fileMenu, fileOptions);
    addMenuItemsToJMenu(shapeMenu, shapeOptions);
    addMenuItemsToJMenu(methodMenu, methodOptions);
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);
    setVisible(true);
  }

  public void addMenuItemsToJMenu(JMenu menu, String[] arr) {
    MenuHandler handler = new MenuHandler();
    for (int i = 0; i < arr.length; i++) {
      JMenuItem menuItem = new JMenuItem(arr[i]);
      menu.add(menuItem);
      menuItem.addActionListener(handler);
    }
    menuBar.add(menu);
  }

  public void addButtonsToJPanel(JPanel panel, String[] arr) {
    ColorHandler handler = new ColorHandler();
    for (int i = 0; i < arr.length; i++) {
      JButton button = new JButton(arr[i]);
      button.addActionListener(handler);
      colorPanel.add(button);
    }
  }

  private class ColorHandler implements [Mission 1] {
    public void actionPerformed(ActionEvent e) {
      String actionCommand = e.getActionCommand();
      switch (actionCommand) {
        case "Black":
          panel.setCurrentShapeColor(Color.BLACK);
          break;
        case "Red":
          panel.setCurrentShapeColor(Color.RED);
          break;
        case "Green":
          panel.setCurrentShapeColor(Color.GREEN);
          break;
        case "Blue":
          panel.setCurrentShapeColor(Color.BLUE);
          break;
        case "Cyan":
          panel.setCurrentShapeColor(Color.CYAN);
          break;
        case "Magenta":
          panel.setCurrentShapeColor(Color.MAGENTA);
          break;
        case "Yellow":
          panel.setCurrentShapeColor(Color.YELLOW);
          break;
      }
    }
  }

  private class MenuHandler implements [Mission 1] {
    public void actionPerformed(ActionEvent e) {
      String actionCommand = e.getActionCommand();
      switch (actionCommand) {
        case "Undo":
          panel.clearLastShape();
        break;
        case "Redo":
          panel.redoLastShape();
        break;
        case "Clear":
          panel.clearDrawing();
        break;
        case "Save To":
          panel.saveImageFromJPanel();
          break;
        case "Open From":
          panel.openImageToJPanel();

          break;
        case "Line":
          panel.setCurrentShapeType("Line");
          break;
        case "Rectangle":
          panel.setCurrentShapeType("Rectangle");
          break;
        case "Oval":
          panel.setCurrentShapeType("Oval");
          break;
        case "Fill":
          panel.setCurrentShapeFilled(true);
          break;
        case "Draw":
          panel.setCurrentShapeFilled(false);
          break;
      }
    }
  }
}
