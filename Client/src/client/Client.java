package client;

import javax.swing.*;
import user_management.UserManager;
import java.util.List;

public class Client {
    private final UserManager userManager;
    private JTextField usernameField; // Πεδίο για το όνομα χρήστη

    public Client(UserManager userManager) {
        this.userManager = userManager;
    }

    public void start() {
        JFrame frame = new JFrame("Client Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20); // Δήλωση και αρχικοποίηση του usernameField

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                if (userManager.authenticate(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Authenticated!");
                    showOptions(username);
                } else {
                    JOptionPane.showMessageDialog(frame, "Authentication failed.");
                }
            } catch (Exception ex) {
                ex.printStackTrace(); // Θα έπρεπε να χρησιμοποιήσετε μηχανισμό καταγραφής σφαλμάτων
            }
        });

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void showOptions(String username) {
        JFrame optionsFrame = new JFrame("Options");
        optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton searchButton = new JButton("Search Products");
        JButton purchaseButton = new JButton("Purchase Product");
        JButton addButton = new JButton("Add Product");
        JButton removeButton = new JButton("Remove Product");
        JButton exitButton = new JButton("Exit");

        panel.add(searchButton);
        panel.add(purchaseButton);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(exitButton);

        searchButton.addActionListener(e -> {
            String criteria = JOptionPane.showInputDialog(optionsFrame, "Enter search criteria:");
            try {
                List<warehouse_management.Product> searchResults = userManager.searchProducts(criteria);

                if (searchResults.isEmpty()) {
                    JOptionPane.showMessageDialog(optionsFrame, "No products found matching the criteria.");
                } else {
                    StringBuilder message = new StringBuilder("Search results:\n");
                    for (warehouse_management.Product product : searchResults) {
                        message.append(product.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(optionsFrame, message.toString());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(optionsFrame, "Error occurred during product search.");
            }
        });

        purchaseButton.addActionListener(e -> {
            String productCode = JOptionPane.showInputDialog(optionsFrame, "Enter product code:");
            String quantityString = JOptionPane.showInputDialog(optionsFrame, "Enter quantity:");
            try {
                int quantity = Integer.parseInt(quantityString);
                boolean purchaseSuccessful = userManager.purchaseProduct(username, productCode, quantity);
                if (purchaseSuccessful) {
                    JOptionPane.showMessageDialog(optionsFrame, "Purchase successful.");
                } else {
                    JOptionPane.showMessageDialog(optionsFrame, "Purchase failed or insufficient quantity available.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(optionsFrame, "Invalid quantity input.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(optionsFrame, "Error occurred during purchase.");
            }
        });

        addButton.addActionListener(e -> {
            if (username.equals("admin")) {
                String productCode = JOptionPane.showInputDialog(optionsFrame, "Enter product code:");
                String productName = JOptionPane.showInputDialog(optionsFrame, "Enter product name:");
                String quantityString = JOptionPane.showInputDialog(optionsFrame, "Enter quantity:");
                try {
                    int quantity = Integer.parseInt(quantityString);
                    boolean addSuccessful = userManager.addProduct(username, productCode, productName, quantity);
                    if (addSuccessful) {
                        JOptionPane.showMessageDialog(optionsFrame, "Product added successfully.");
                    } else {
                        JOptionPane.showMessageDialog(optionsFrame, "Failed to add product.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(optionsFrame, "Invalid quantity input.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(optionsFrame, "Error occurred during product addition.");
                }
            } else {
                JOptionPane.showMessageDialog(optionsFrame, "You do not have permission to add products.");
            }
        });

        removeButton.addActionListener(e -> {
            if (username.equals("admin")) {
                String productCode = JOptionPane.showInputDialog(optionsFrame, "Enter product code:");
                try {
                    boolean removeSuccessful = userManager.removeProduct(username, productCode);
                    if (removeSuccessful) {
                        JOptionPane.showMessageDialog(optionsFrame, "Product removed successfully.");
                    } else {
                        JOptionPane.showMessageDialog(optionsFrame, "Failed to remove product.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(optionsFrame, "Error occurred during product removal.");
                }
            } else {
                JOptionPane.showMessageDialog(optionsFrame, "You do not have permission to remove products.");
            }
        });

        exitButton.addActionListener(e -> {
            optionsFrame.dispose(); // Κλείσιμο του παραθύρου επιλογών
        });

        optionsFrame.getContentPane().add(panel);
        optionsFrame.pack();
        optionsFrame.setVisible(true);
    }
}
