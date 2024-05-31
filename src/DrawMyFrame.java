import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DrawMyFrame extends JFrame {
  private JMenuBar menuBar = new JMenuBar();

  private JMenu fileMenu = new JMenu("File");
  private String[] fileOptions = {"Undo", "Redo", "Clear", "Save Only Image", "Save With History","Open From"};
  private String[] fileIcons = {"./assets/icons/undo.png", "./assets/icons/redo.png", "./assets/icons/clear.png", "./assets/icons/save.png", "./assets/icons/save.png", "./assets/icons/open.png"};

  private JMenu shapeMenu = new JMenu("Shapes");
  private String[] shapeOptions = {"Line", "Rectangle", "Oval"};
  private String[] shapeIcons = {"./assets/icons/line.png", "./assets/icons/rectangle.png", "./assets/icons/oval.png"};

  private JMenu methodMenu = new JMenu("Method");
  private String[] methodOptions = {"Fill", "Draw"};
  private String[] methodIcons = {"./assets/icons/fill.png", "./assets/icons/draw.png"};

  private JMenu colorMenu = new JMenu("Color");

  DrawMyPanel panel = new DrawMyPanel();

  public DrawMyFrame() {
    super("GCU Paint Tools");

    setJMenuBar(menuBar);

    add(panel, BorderLayout.CENTER);

    addMenuItemsToJMenu(fileMenu, fileOptions, fileIcons);
    addMenuItemsToJMenu(shapeMenu, shapeOptions, shapeIcons);
    addMenuItemsToJMenu(methodMenu, methodOptions, methodIcons);
    addColorMenu(colorMenu);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);
    setVisible(true);
  }

  public void addMenuItemsToJMenu(JMenu menu, String[] arr, String[] icons) {
    MenuHandler handler = new MenuHandler();
    for (int i = 0; i < arr.length; i++) {
      // 상대 경로를 사용하여 ImageIcon 생성
      ImageIcon originalIcon = new ImageIcon(icons[i]);
      // 아이콘 크기를 16x16으로 조정
      Image image = originalIcon.getImage();
      Image newimg = image.getScaledInstance(12, 12, java.awt.Image.SCALE_SMOOTH);
      ImageIcon resizedIcon = new ImageIcon(newimg);
      
      JMenuItem menuItem = new JMenuItem(arr[i], resizedIcon);
      menuItem.setIconTextGap(5);  // 아이콘과 텍스트 사이의 간격을 5로 설정
      menuItem.setBorder(new EmptyBorder(0, -10, 0, 10));  // 상하좌우 2픽셀 패딩 설정

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
        case "Save Only Image":
          panel.saveImageFromJPanel();
          break;
        case "Save With History":
          panel.saveImageWithLinkedList();
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
