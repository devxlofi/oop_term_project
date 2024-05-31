import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class DrawMyFrame extends JFrame {
  private JMenuBar menuBar = new JMenuBar();

  private JMenu fileMenu = new JMenu("File");
  private String[] fileOptions = {"Undo", "Redo", "Clear", "Save To", "Open From"};

  private JMenu shapeMenu = new JMenu("Shapes");
  private String[] shapeOptions = {"Line", "Rectangle", "Oval"};

  private JMenu methodMenu = new JMenu("Method");
  private String[] methodOptions = {"Fill", "Draw"};

  private JMenu colorMenu = new JMenu("Color");

  DrawMyPanel panel = new DrawMyPanel();

  public DrawMyFrame() {
    super("GCU Paint Tools");

    setJMenuBar(menuBar);

    add(panel, BorderLayout.CENTER);

    addMenuItemsToJMenu(fileMenu, fileOptions);
    addMenuItemsToJMenu(shapeMenu, shapeOptions);
    addMenuItemsToJMenu(methodMenu, methodOptions);
    addColorMenu(colorMenu);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);
    setVisible(true);
  }

  public void addMenuItemsToJMenu(JMenu menu, String[] arr) {
    MenuHandler handler = new MenuHandler();
    for (int i = 0; i < arr.length; i++) {
      JMenuItem menuItem = new JMenuItem(arr[i]);
      if (arr[i].equals("Undo")) {
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.META_MASK));
      } else if (arr[i].equals("Redo")) {
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.META_MASK | ActionEvent.SHIFT_MASK));
      }
      menu.add(menuItem);
      menuItem.addActionListener(handler);
    }
    menuBar.add(menu);
  }

  public void addColorMenu(JMenu menu) {
    menu.addMenuListener(new javax.swing.event.MenuListener() {
      @Override
      public void menuSelected(javax.swing.event.MenuEvent e) {
        Color selectedColor = JColorChooser.showDialog(null, "Choose a color", panel.getCurrentShapeColor());
        if (selectedColor != null) {
          panel.setCurrentShapeColor(selectedColor);
        }
        menu.setSelected(false); // Automatically close the menu after selecting color
      }

      @Override
      public void menuDeselected(javax.swing.event.MenuEvent e) {}

      @Override
      public void menuCanceled(javax.swing.event.MenuEvent e) {}
    });
    menuBar.add(menu);
  }

  private class MenuHandler implements ActionListener {
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