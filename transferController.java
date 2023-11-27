import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class transferController {

    @FXML
    private ComboBox<String> fromComboBox;

    @FXML
    private ComboBox<String> toComboBox;

    @FXML
    private TextField amountTextField;

    @FXML
    private Button confirmButton;

    public void initialize() {
        // Initialize ComboBoxes with empty lists
        ObservableList<String> emptyList = FXCollections.observableArrayList();
        fromComboBox.setItems(emptyList);
        toComboBox.setItems(emptyList);

        // Add event handler for the confirmButton
        confirmButton.setOnAction(event -> handleConfirmButton());

        // Initially update the combo boxes
        updateComboBoxOptions();
    }

    public void updateComboBoxOptions() {
       // Get the list of account names from your in-memory database
       ObservableList<String> accountNames = FXCollections.observableArrayList(InMemoryDatabase.getAccountNames());

       // Update the ComboBox options
       fromComboBox.setItems(accountNames);
       toComboBox.setItems(accountNames);
    }

    private void handleConfirmButton() {
        // Get selected values from ComboBoxes and amount from TextField
        String fromAccount = fromComboBox.getValue();
        String toAccount = toComboBox.getValue();
        String amountText = amountTextField.getText();

        // Validate input
        if (fromAccount == null || toAccount == null || amountText.isEmpty()) {
            // Handle invalid input (display an error message, etc.)
            System.out.println("Please fill in all fields");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);

            // Perform the transfer logic here
            InMemoryDatabase.transferFunds(fromAccount, toAccount, amount);

        } catch (NumberFormatException e) {
            // Handle invalid amount format
            System.out.println("Invalid amount format");
        }
        // After the transfer, update the ComboBox options
        updateComboBoxOptions();
    }
}
