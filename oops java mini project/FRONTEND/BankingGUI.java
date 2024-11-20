import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BankingGUI extends JFrame {
    private JTextField nameField;
    private JTextField amountField;
    private JTextArea outputArea;
    private BankingSystem bankingSystem;

    public BankingGUI() {
        bankingSystem = new BankingSystem(); // Instantiate the banking system

        setTitle("Simple Banking System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Input fields
        nameField = new JTextField(30);
        amountField = new JTextField(30);
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        // Buttons
        JButton createAccountButton = new JButton("Create Account");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton checkBalanceButton = new JButton("Check Balance");

        // Add action listeners
        createAccountButton.addActionListener(e -> createAccount());
        depositButton.addActionListener(e -> deposit());
        withdrawButton.addActionListener(e -> withdraw());
        checkBalanceButton.addActionListener(e -> checkBalance());

        // Add components to frame
        add(new JLabel("Account Name:"));
        add(nameField);
        add(new JLabel("Amount:"));
        add(amountField);
        add(createAccountButton);
        add(depositButton);
        add(withdrawButton);
        add(checkBalanceButton);
        add(new JScrollPane(outputArea));

        setVisible(true);
    }

    private void createAccount() {
        String name = nameField.getText();
        try {
            bankingSystem.createAccount(name);
            outputArea.append("Account created for: " + name + "\n");
        } catch (SQLException e) {
            outputArea.append("Error creating account: " + e.getMessage() + "\n");
        }
    }

    private void deposit() {
        String name = nameField.getText();
        double amount = Double.parseDouble(amountField.getText());
        try {
            bankingSystem.deposit(name, amount);
            outputArea.append("Deposited " + amount + " to account: " + name + "\n");
        } catch (SQLException e) {
            outputArea.append("Error during deposit: " + e.getMessage() + "\n");
        }
    }

    private void withdraw() {
        String name = nameField.getText();
        double amount = Double.parseDouble(amountField.getText());
        try {
            bankingSystem.withdraw(name, amount);
            outputArea.append("Withdrew " + amount + " from account: " + name + "\n");
        } catch (SQLException e) {
            outputArea.append("Error during withdrawal: " + e.getMessage() + "\n");
        }
    }

    private void checkBalance() {
        String name = nameField.getText();
        try {
            double balance = bankingSystem.checkBalance(name);
            outputArea.append("Balance for " + name + ": " + balance + "\n");
        } catch (SQLException e) {
            outputArea.append("Error checking balance: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankingGUI::new);
    }
}
