import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class addCheckingsController {
    @FXML
    private TextField accountNameField;
    @FXML
    private DatePicker dateCreatedField;
    @FXML
    private TextField startingBalanceField;
    @FXML
    private Button confirmButton;

    @FXML
    private void handleConfirm() {
        // Logic for handling the "Confirm" button click
        String accountName = accountNameField.getText();
        String dateCreated = dateCreatedField.getEditor().getText();
        double startingBalance = Double.parseDouble(startingBalanceField.getText());

        // Validate the input if needed

        // Call a method to add the checking account to the database
        InMemoryDatabase.addCheckingAccount(accountName, dateCreated, startingBalance);

        //Call the method in homeScreenController to update the main page
        homeScreenController.addAccountButton(accountName, startingBalance);

        // Close the current window (if needed)
        closeWindow();
    }

    private void closeWindow() {
        // Get the current stage
        Stage stage = (Stage) confirmButton.getScene().getWindow();

        // Close the stage
        stage.close();
    }


    
}
