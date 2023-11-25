import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NewSavingsController {
    @FXML
    private Button confirmButton;

    @FXML
    private void handleConfirmButton() {
        // Handle the logic for confirming the new savings account
        // You can add code to save the new savings account details to the database, etc.
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}
