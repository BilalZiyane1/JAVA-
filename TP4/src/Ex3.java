import javax.swing.*;
import java.awt.event.*;
public class Ex3 {
    private static JTextArea displayTextArea;
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Customized Example");
        JMenuBar mainMenuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenu propertySubMenu = new JMenu("Property");
        JMenuItem bigPropertyItem = new JMenuItem("Big");
        JMenuItem smallPropertyItem = new JMenuItem("Small");
        newItem.addActionListener(createItemActionListener("New"));
        openItem.addActionListener(createItemActionListener("Open"));
        saveItem.addActionListener(createItemActionListener("Save"));
        bigPropertyItem.addActionListener(createItemActionListener("Big"));
        smallPropertyItem.addActionListener(createItemActionListener("Small"));
        propertySubMenu.add(bigPropertyItem);
        propertySubMenu.add(smallPropertyItem);
        mainMenu.add(newItem);
        mainMenu.add(openItem);
        mainMenu.add(saveItem);
        mainMenu.addSeparator();
        mainMenu.add(propertySubMenu);
        mainMenuBar.add(mainMenu);
        displayTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayTextArea);
        mainFrame.add(scrollPane);
        mainFrame.setJMenuBar(mainMenuBar);
        mainFrame.setSize(300, 200);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
    private static ActionListener createItemActionListener(final String itemName) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayTextArea.append(itemName + " selected\n");
            }
        };
    }
}
