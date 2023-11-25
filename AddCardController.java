import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCardController {
    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField cardHolderField;

    @FXML
    private DatePicker expirationDateField;

    @FXML
    private TextField cvvField;

    @FXML
    private Button confirmButton;

    @FXML
    private void handleConfirm() {
        // Logic for handling the "Confirm" button click
        String cardNumber = cardNumberField.getText();
        String cardHolder = cardHolderField.getText();
        String expirationDate = expirationDateField.getEditor().getText(); // Get the text representation
        String cvv = cvvField.getText();

        // Validate the input if needed

        // Call a method to add the card to the database
        InMemoryDatabase.addCard(cardNumber, cardHolder, expirationDate, cvv);

        // Close the current window (if needed)
        closeWindow();
    }

    private void closeWindow() {
        //get the current stage
        Stage stage = (Stage) confirmButton.getScene().getWindow();

        //close the stage
        stage.close();


    }
}
