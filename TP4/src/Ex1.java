import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ex1 {
    private JLabel label;
    private int clickCount; 

    public Ex1() {
        JFrame frame = new JFrame("exemple");
        JButton button = new JButton("clic");
        
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                clickCount++;
                label.setText(Integer.toString(clickCount));
            }
        };
        
        button.addActionListener(actionListener);
        label = new JLabel("0");
        JPanel pane = new JPanel();
        pane.add(button);
        pane.add(label);
        frame.getContentPane().add(pane, BorderLayout.CENTER);
        frame.pack(); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.show();  // Changed from frame.show() to frame.setVisible(true)
    }

    public static void main(String[] args) {
        new Ex1();
    }
}
