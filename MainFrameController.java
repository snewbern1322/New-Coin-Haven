// handles the logic for the main landing page
import java.util.ResourceBundle;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import java.net.URL;

public class MainFrameController implements Initializable {
    @FXML
    private Label welcomeLabel;

    @FXML
    private BorderPane mainPane;

    @FXML
    private void handleSpendingsPage(ActionEvent event) {
        System.out.println("You clicked Spendings");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Spendings");
        mainPane.setCenter(view);
    }

    @FXML
    private void handleAddFunds(ActionEvent event) {
        System.out.println("You clicked me!");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("addFunds");
        mainPane.setCenter(view);
    }

    @FXML
    private void handleTransferButton(ActionEvent event) {
        System.out.println("You clicked me!");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("transferPage");
        mainPane.setCenter(view);
    }

    @FXML
    private void handleHomePage(ActionEvent event) {
        System.out.println("You clicked me!");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("homeScreen");
        mainPane.setCenter(view);
    }

    @FXML
    private void handleUserSettings(ActionEvent event) {
        System.out.println("You clicked me!");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("userinfo");
        mainPane.setCenter(view);
    }

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization logic, if needed
        
        welcomeLabel.setText("Welcome to Coin Haven Main Frame!");
    }

    // Additional logic for the main frame goes here
}