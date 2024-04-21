import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class FilmChaineGUI extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton ajouterButton, modifierButton, supprimerButton;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public FilmChaineGUI() {
        setTitle("Film & Chaine Table");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DB database = new DB();
        connection = database.getConnection();

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel) { 
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        ajouterButton = new JButton("Ajouter");
        modifierButton = new JButton("Modifier");
        supprimerButton = new JButton("Supprimer");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ajouterButton);
        buttonPanel.add(modifierButton);
        buttonPanel.add(supprimerButton);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        ajouterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddRecordWindow();
            }
        });

        modifierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                openModifyRecordWindow(selectedRow);
            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(FilmChaineGUI.this, "veuillez selecter une ligne");
                    return;
                }
                int confirmDelete = JOptionPane.showConfirmDialog(
                        FilmChaineGUI.this,
                        "Êtes-vous sûr de vouloir supprimer cet enregistrement ?",
                        "Confirmer la suppression",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirmDelete == JOptionPane.YES_OPTION) {
                    deleteRecord(selectedRow);
                }
            }
        });

        loadTableData();
    }

    private void loadTableData() {
        try {
            String sql = "SELECT film.Num, film.titre, film.langue, chaine.Code, chaine.nom, chaine.pays " +
                    "FROM film LEFT JOIN diffuser ON film.Num = diffuser.film_Num " +
                    "LEFT JOIN chaine ON diffuser.chaine_Code = chaine.Code " +
                    "WHERE diffuser.chaine_Code IS NOT NULL";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Clear previous columns
            tableModel.setColumnCount(0);

            // Add only the required 6 columns
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }

            // Fetch data and populate the table
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void openAddRecordWindow() {
        final JFrame addRecordFrame = new JFrame("Add Record");
        addRecordFrame.setSize(300, 200);
        addRecordFrame.setLayout(null); 

        JLabel labelNum = new JLabel("Num:");
        JLabel labelTitre = new JLabel("Titre:");
        JLabel labelLangue = new JLabel("Langue:");

        final JTextField fieldNum = new JTextField();
        final JTextField fieldTitre = new JTextField();
        final JTextField fieldLangue = new JTextField();

        JButton okButton = new JButton("OK");

        labelNum.setBounds(10, 10, 80, 25);
        fieldNum.setBounds(100, 10, 160, 25);
        labelTitre.setBounds(10, 40, 80, 25);
        fieldTitre.setBounds(100, 40, 160, 25);
        labelLangue.setBounds(10, 70, 80, 25);
        fieldLangue.setBounds(100, 70, 160, 25);
        okButton.setBounds(100, 100, 80, 25);

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String sql = "INSERT INTO film (Num, titre, langue) VALUES (?, ?, ?)";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, Integer.parseInt(fieldNum.getText()));
                    preparedStatement.setString(2, fieldTitre.getText());
                    preparedStatement.setString(3, fieldLangue.getText());
                    preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(addRecordFrame, "Film bien ajouter.");

                    addRecordFrame.dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        addRecordFrame.add(labelNum);
        addRecordFrame.add(fieldNum);
        addRecordFrame.add(labelTitre);
        addRecordFrame.add(fieldTitre);
        addRecordFrame.add(labelLangue);
        addRecordFrame.add(fieldLangue);
        addRecordFrame.add(okButton);

        addRecordFrame.setVisible(true);
    }

    private void openModifyRecordWindow(int selectedRow) {
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to modify.");
            return;
        }

        final JFrame modifyRecordFrame = new JFrame("Modify Record");
        modifyRecordFrame.setSize(300, 250);
        modifyRecordFrame.setLayout(null);

        JLabel labelNum = new JLabel("Num:");
        JLabel labelTitre = new JLabel("Titre:");
        JLabel labelLangue = new JLabel("Langue:");
        JLabel labelCode = new JLabel("Code:");
        JLabel labelNom = new JLabel("Nom:");
        JLabel labelPays = new JLabel("Pays:");

        final JTextField fieldNum = new JTextField(table.getValueAt(selectedRow, 0).toString());
        final JTextField fieldTitre = new JTextField(table.getValueAt(selectedRow, 1).toString());
        final JTextField fieldLangue = new JTextField(table.getValueAt(selectedRow, 2).toString());
        final JTextField fieldCode = new JTextField(table.getValueAt(selectedRow, 3).toString());
        final JTextField fieldNom = new JTextField(table.getValueAt(selectedRow, 4).toString());
        final JTextField fieldPays = new JTextField(table.getValueAt(selectedRow, 5).toString());

        fieldNum.setEditable(false);
        fieldCode.setEditable(false);

        JButton okButton = new JButton("OK");

        labelNum.setBounds(10, 10, 80, 25);
        fieldNum.setBounds(100, 10, 160, 25);
        labelTitre.setBounds(10, 40, 80, 25);
        fieldTitre.setBounds(100, 40, 160, 25);
        labelLangue.setBounds(10, 70, 80, 25);
        fieldLangue.setBounds(100, 70, 160, 25);
        labelCode.setBounds(10, 100, 80, 25);
        fieldCode.setBounds(100, 100, 160, 25);
        labelNom.setBounds(10, 130, 80, 25);
        fieldNom.setBounds(100, 130, 160, 25);
        labelPays.setBounds(10, 160, 80, 25);
        fieldPays.setBounds(100, 160, 160, 25);
        okButton.setBounds(100, 190, 80, 25);

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int num = Integer.parseInt(fieldNum.getText());
                    String titre = fieldTitre.getText();
                    String langue = fieldLangue.getText();
                    String nom = fieldNom.getText();
                    String pays = fieldPays.getText();

                    String updateFilm = "UPDATE film SET titre = ?, langue = ? WHERE Num = ?";
                    preparedStatement = connection.prepareStatement(updateFilm);
                    preparedStatement.setString(1, titre);
                    preparedStatement.setString(2, langue);
                    preparedStatement.setInt(3, num);
                    preparedStatement.executeUpdate();

                    String updateChaine = "UPDATE chaine SET nom = ?, pays = ? WHERE Code = ?";
                    preparedStatement = connection.prepareStatement(updateChaine);
                    preparedStatement.setString(1, nom);
                    preparedStatement.setString(2, pays);
                    preparedStatement.setInt(3, Integer.parseInt(fieldCode.getText()));
                    preparedStatement.executeUpdate();

                    tableModel.setRowCount(0); // Clear previous data
                    loadTableData();
                    modifyRecordFrame.dispose(); // Close the modify record window
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(modifyRecordFrame, "Please enter valid data.");
                }
            }
        });

        modifyRecordFrame.add(labelNum);
        modifyRecordFrame.add(fieldNum);
        modifyRecordFrame.add(labelTitre);
        modifyRecordFrame.add(fieldTitre);
        modifyRecordFrame.add(labelLangue);
        modifyRecordFrame.add(fieldLangue);
        modifyRecordFrame.add(labelCode);
        modifyRecordFrame.add(fieldCode);
        modifyRecordFrame.add(labelNom);
        modifyRecordFrame.add(fieldNom);
        modifyRecordFrame.add(labelPays);
        modifyRecordFrame.add(fieldPays);
        modifyRecordFrame.add(okButton);

        modifyRecordFrame.setVisible(true);
    }
    private void deleteRecord(int selectedRow) {
        Object numObject = table.getValueAt(selectedRow, 0);
        Object codeObject = table.getValueAt(selectedRow, 3);

        if (numObject != null && codeObject != null) {
            int numToDelete = Integer.parseInt(numObject.toString());
            int codeToDelete = Integer.parseInt(codeObject.toString());

            try {
            	 String deleteDiffuser = "DELETE FROM diffuser WHERE film_Num = ? AND chaine_Code = ?";
                 preparedStatement = connection.prepareStatement(deleteDiffuser);
                 preparedStatement.setInt(1, numToDelete);
                 preparedStatement.setInt(2, codeToDelete);
                 preparedStatement.executeUpdate();

                 // Delete the record from 'film' table
                 String deleteFilm = "DELETE FROM film WHERE Num = ?";
                 preparedStatement = connection.prepareStatement(deleteFilm);
                 preparedStatement.setInt(1, numToDelete);
                 preparedStatement.executeUpdate();

                 // Delete the record from 'chaine' table
                 String deleteChaine = "DELETE FROM chaine WHERE Code = ?";
                 preparedStatement = connection.prepareStatement(deleteChaine);
                 preparedStatement.setInt(1, codeToDelete);
                 preparedStatement.executeUpdate();

                 tableModel.removeRow(selectedRow); 
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Invalid data type for deletion");
        }
    }
    public static void main(String[] args) {
        
                new FilmChaineGUI().setVisible(true);
        
    }
}
