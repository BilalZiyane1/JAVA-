import javax.swing.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.*;

public class Exemple15 extends JFrame {
    private JTextField txtCNE, txtNom, txtPrenom, txtAge;
    private JComboBox<String> cmbTournoi;
    private JButton btnOk;

    private Connection conn;
    private PreparedStatement insertEtudiantStmt, insertParticiperStmt;

    public Exemple15() {
        setTitle("Insert into Etudiant and Participer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblCNE = new JLabel("CNE");
        lblCNE.setBounds(50, 30, 80, 25);
        panel.add(lblCNE);

        txtCNE = new JTextField();
        txtCNE.setBounds(150, 30, 160, 25);
        panel.add(txtCNE);

        JLabel lblNom = new JLabel("Nom");
        lblNom.setBounds(50, 60, 80, 25);
        panel.add(lblNom);

        txtNom = new JTextField();
        txtNom.setBounds(150, 60, 160, 25);
        panel.add(txtNom);

        JLabel lblPrenom = new JLabel("Prenom");
        lblPrenom.setBounds(50, 90, 80, 25);
        panel.add(lblPrenom);

        txtPrenom = new JTextField();
        txtPrenom.setBounds(150, 90, 160, 25);
        panel.add(txtPrenom);

        JLabel lblAge = new JLabel("Age");
        lblAge.setBounds(50, 120, 80, 25);
        panel.add(lblAge);

        txtAge = new JTextField();
        txtAge.setBounds(150, 120, 160, 25);
        panel.add(txtAge);

        JLabel lblTournoi = new JLabel("Tournoi");
        lblTournoi.setBounds(50, 150, 80, 25);
        panel.add(lblTournoi);
        connectToDB();
        cmbTournoi = new JComboBox<>();
        cmbTournoi.addItem(""); // Add an empty default item
        cmbTournoi.setBounds(150, 150, 160, 25);
        loadTournoiData(); // Load tournoi data from the database
        panel.add(cmbTournoi);

        btnOk = new JButton("OK");
        btnOk.setBounds(150, 190, 80, 30);
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertData();
                displayNewWindow();
            }
        });
        panel.add(btnOk);

        add(panel);
        connectToDB();
    }

    public void connectToDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp5", "root", "200405");
            insertEtudiantStmt = conn.prepareStatement("INSERT INTO Etudiant (CNE, nom, prenom, age) VALUES (?, ?, ?, ?)");
            insertParticiperStmt = conn.prepareStatement("INSERT INTO Participer (CNE, code) VALUES (?, ?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTournoiData() {
        try {
            String query = "SELECT code FROM Tournoi";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                cmbTournoi.addItem(rs.getString("code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertOnlyEtudiant(String cne, String nom, String prenom, int age) {
        try {
            insertEtudiantStmt.setString(1, cne);
            insertEtudiantStmt.setString(2, nom);
            insertEtudiantStmt.setString(3, prenom);
            insertEtudiantStmt.setInt(4, age);

            int rowsInserted = insertEtudiantStmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data inserted into Etudiant successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Error inserting data into Etudiant.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Database error.");
        }
    }

    private void insertEtudiantAndParticiper(String cne, String nom, String prenom, int age, String code) {
        try {
            insertEtudiantStmt.setString(1, cne);
            insertEtudiantStmt.setString(2, nom);
            insertEtudiantStmt.setString(3, prenom);
            insertEtudiantStmt.setInt(4, age);

            int rowsInsertedEtudiant = insertEtudiantStmt.executeUpdate();

            if (rowsInsertedEtudiant > 0) {
                insertParticiperStmt.setString(1, cne); // Assuming CNE in Participer is a varchar
                insertParticiperStmt.setString(2, code);
                int rowsInsertedParticiper = insertParticiperStmt.executeUpdate();

                if (rowsInsertedParticiper > 0) {
                    JOptionPane.showMessageDialog(this, "Data inserted into Etudiant and Participer successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error inserting data into Participer.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error inserting data into Etudiant.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Database error.");
        }
    }




    private void insertData() {
        try {
            String cne = txtCNE.getText();
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            String ageText = txtAge.getText();

            // Validate the age field to ensure it's not empty and a valid integer
            if (ageText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: Age field is empty.");
                return;
            }

            int age;
            try {
                age = Integer.parseInt(ageText);
                if (age < 0) {
                    JOptionPane.showMessageDialog(this, "Error: Age must be a positive number.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: Age must be a valid integer.");
                return;
            }

            String selectedValue = cmbTournoi.getSelectedItem().toString();

            if (selectedValue.isEmpty()) {
                insertOnlyEtudiant(cne, nom, prenom, age);
            } else {
                insertEtudiantAndParticiper(cne, nom, prenom, age, selectedValue);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Invalid data.");
        }
    }

    
    private void displayNewWindow() {
        JFrame newFrame = new JFrame("New Window");
        newFrame.setSize(400, 300);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel newPanel = new JPanel();
        newPanel.setLayout(null);

        JLabel lblCode = new JLabel("Code");
        lblCode.setBounds(50, 30, 80, 25);
        newPanel.add(lblCode);

        final JTextField txtCode = new JTextField();
        txtCode.setBounds(150, 30, 160, 25);
        newPanel.add(txtCode);

        JLabel lblPrix = new JLabel("Prix");
        lblPrix.setBounds(50, 60, 80, 25);
        newPanel.add(lblPrix);

        final JTextField txtPrix = new JTextField();
        txtPrix.setBounds(150, 60, 160, 25);
        newPanel.add(txtPrix);

        JLabel lblPays = new JLabel("Pays");
        lblPays.setBounds(50, 90, 80, 25);
        newPanel.add(lblPays);

        final JTextField txtPays = new JTextField();
        txtPays.setBounds(150, 90, 160, 25);
        newPanel.add(txtPays);

        JLabel lblSport = new JLabel("Sport");
        lblSport.setBounds(50, 120, 80, 25);
        newPanel.add(lblSport);

        JComboBox<String> cmbSport = new JComboBox<>();
        cmbSport.setBounds(150, 120, 160, 25);

        // Add an empty default item at the beginning
        cmbSport.addItem("");

        try {
            // Fetch 'titre' column data from the 'Sport' table
            String query = "SELECT titre FROM Sport";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Populate the JComboBox with data fetched from the database
            while (rs.next()) {
                cmbSport.addItem(rs.getString("titre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching data from the database.");
        }

        newPanel.add(cmbSport);

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(150, 160, 80, 30);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = txtCode.getText();
                String prix = txtPrix.getText();
                String pays = txtPays.getText();

                // Validate the input fields here if necessary

                insertIntoTournoi(code, prix, pays);
            }
        });
        newPanel.add(btnSave);

        newFrame.add(newPanel);
        newFrame.setVisible(true);
    }

    
    private void insertIntoTournoi(String code, String prix, String pays) {
        try {
            // Prepare the SQL statement to insert into the 'Tournoi' table
            String insertQuery = "INSERT INTO Tournoi (code, prix, pays) VALUES (?, ?, ?)";
            PreparedStatement insertTournoiStmt = conn.prepareStatement(insertQuery);

            // Set values to be inserted into the 'Tournoi' table
            insertTournoiStmt.setString(1, code);
            insertTournoiStmt.setBigDecimal(2, new BigDecimal(prix)); // Assuming prix is of type DECIMAL in the database
            insertTournoiStmt.setString(3, pays);

            // Execute the insertion
            int rowsInserted = insertTournoiStmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data inserted into Tournoi successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Error inserting data into Tournoi.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Database error.");
        }
    }





    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Exemple15().setVisible(true);
            }
        });
    }
}