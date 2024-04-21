import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ex4 {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Main Window");
        mainFrame.setLayout(new BorderLayout());
        final JTextArea inputTextArea = new JTextArea(10, 30); // Text area for entering lines
        JButton sendButton = new JButton("Envoyer");
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
        mainFrame.setJMenuBar(mainMenuBar);
        mainFrame.add(inputTextArea, BorderLayout.CENTER);
        mainFrame.add(sendButton, BorderLayout.SOUTH);
        mainFrame.setSize(300, 200);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        JFrame secondaryFrame = new JFrame("Secondary Window");
        final JTextArea receivedTextArea = new JTextArea(10, 30); // Text area for displaying received lines
        secondaryFrame.add(receivedTextArea, BorderLayout.CENTER);
        secondaryFrame.setSize(200, 200);
        secondaryFrame.setLocation(400, 0);
        secondaryFrame.setVisible(true);
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String lineToSend = inputTextArea.getText();
                receivedTextArea.append(lineToSend + "\n");
                inputTextArea.setText("");
            }
        });
    }

    private static ActionListener createItemActionListener(final String itemName) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
            }
        };
    }
}
