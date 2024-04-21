import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI extends JFrame {
    private JTextField wordInput;
    private JComboBox<String> fromLanguageCombo;
    private JComboBox<String> toLanguageCombo;
    private JTextArea translationArea;

    private String[] languages = {"Ar", "Fr", "En", "Es"};
    private TranslationService translationService;

    public ClientRMI() {
        setTitle("Translation App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel wordLabel = new JLabel("Word: ");
        wordInput = new JTextField(20);
        inputPanel.add(wordLabel);
        inputPanel.add(wordInput);
        mainPanel.add(inputPanel);

        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel fromLabel = new JLabel("From: ");
        fromLanguageCombo = new JComboBox<>(languages);
        JLabel toLabel = new JLabel("To: ");
        toLanguageCombo = new JComboBox<>(languages);
        comboPanel.add(fromLabel);
        comboPanel.add(fromLanguageCombo);
        comboPanel.add(toLabel);
        comboPanel.add(toLanguageCombo);
        mainPanel.add(comboPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton translateButton = new JButton("Translate");
        translateButton.addActionListener(new TranslateButtonListener());
        buttonPanel.add(translateButton);
        mainPanel.add(buttonPanel);

        // Text area to display translation
        translationArea = new JTextArea(10, 30);
        translationArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(translationArea);
        mainPanel.add(scrollPane);

        add(mainPanel);

        initRMI();
    }

    private void initRMI() {
        try {
            // Locate the RMI registry on the server
            Registry registry = LocateRegistry.getRegistry("localhost",6968);

            // Look up the translation service
            translationService = (TranslationService) registry.lookup("TranslationService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class TranslateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String word = wordInput.getText();
            String sourceLanguage = (String) fromLanguageCombo.getSelectedItem();
            String targetLanguage = (String) toLanguageCombo.getSelectedItem();
            try {
                // Call the translate method on the server
                String translation = translationService.translate(word, sourceLanguage, targetLanguage);
                translationArea.setText("Translated word: " + translation);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ClientRMI app = new ClientRMI();
                app.setVisible(true);
            }
        });
    }
}
