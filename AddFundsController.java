import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddFundsController {

    @FXML
    private ComboBox<String> accountComboBox;

    @FXML
    private TextField amountTextField;

    // You can initialize the controller (e.g., populate the combo box) in the initialize method.
    @FXML
    private void initialize() {
        // Clear existing items in ComboBox
        accountComboBox.getItems().clear();

        // Populate the ComboBox with account names
        List<String> accountNames = InMemoryDatabase.getAccountNames();
        accountComboBox.getItems().addAll(accountNames);
    }

    // You can add more methods here to handle button clicks, form submissions, etc.
    @FXML
    private void handleConfirmButton() {
        // Logic for handling confirmation button click
        String selectedAccount = accountComboBox.getValue();
        String amountStr = amountTextField.getText();

        // Validate that both account and amount are selected/entered
        if (selectedAccount == null || amountStr.isEmpty()) {
            // Display an error message or handle the situation appropriately
            System.out.println("Please select an account and enter an amount.");
            return;
        }

        // Convert the amount from String to double
        double amount;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            // Handle the case where the amount is not a valid number
            System.out.println("Please enter a valid amount.");
            return;
        }

        // Update the balance in the in-memory database based on the selected account type
        String accountType = InMemoryDatabase.getAccountType(selectedAccount);
        switch (accountType) {
            case "savings":
                InMemoryDatabase.updateSavingsAccountBalance(selectedAccount, amount);
                break;
            case "checking":
                InMemoryDatabase.updateCheckingAccountBalance(selectedAccount, amount);
                break;
            default:
                System.out.println("Unsupported account type: " + accountType);
                // Handle the situation appropriately
        }

        // Display a success message or handle the situation appropriately
        System.out.println("Funds added successfully to " + selectedAccount);
    }
}