import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class homeScreenController {
    
    private static homeScreenController instance;
    public homeScreenController() {
        instance = this;
    }

    @FXML
    private ListView<String> accountsListView;

    @FXML
    private Button deleteButton;

    @FXML
    private Text currentBalanceText;

    public void initialize() {
        //Load tasks when the home screen is initialized
        accountsListView.setItems(TaskService.getTasks());
        accountsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //delete button action
        deleteButton.setOnAction(event -> deleteSelectedAccount());
    }

    //method to add a new account button
    public static void addAccountButton(String accountName, double startingBalance) {
        if (instance != null && instance.accountsListView != null) {
            instance.accountsListView.getItems().add(accountName);

            // Assuming you have a method to add the account to the in-memory database
            InMemoryDatabase.addSavingsAccount(accountName, "current date", Double.valueOf(startingBalance));
        }
        
    }

    private void deleteSelectedAccount() {
        ObservableList<String> selectedItems = accountsListView.getSelectionModel().getSelectedItems();
        if(!selectedItems.isEmpty()) {
            String selectedItem = selectedItems.get(0); //assuming single selection
            accountsListView.getItems().remove(selectedItem);
            TaskService.getTasks().remove(selectedItem);
        }
        
    }

    // This method is called when an account is selected in the ListView
    @FXML
    private void handleAccountSelection() {
        String selectedAccount = accountsListView.getSelectionModel().getSelectedItem();
        System.out.println("Selected Account: " + selectedAccount);
    
        // Handle potential whitespace in the selected account name
        selectedAccount = selectedAccount.trim();
        System.out.println("Trimmed Account Name: " + selectedAccount);

    
        // Assuming you have a method in InMemoryDatabase to get the starting balance by account name
        String startingBalance = InMemoryDatabase.getStartingBalance(selectedAccount);
    
        // Check if the starting balance is a valid double
        try {
            double balance = Double.parseDouble(startingBalance);
    
            // Update the Text field with the starting balance
            currentBalanceText.setText(String.valueOf(balance));
        } catch (NumberFormatException e) {
            // Handle the case where the starting balance is not a valid double
            System.err.println("Error parsing starting balance to double: " + e.getMessage());
    
            // Optionally, you can display an error message or take other actions
        }
    }


    //Methods for creating Accounts and loading pages----------------------------------------------------------------------


    @FXML
    private Button createNewSavingsButton;

    @FXML
    private void handleCreateNewSavings() {
        // Logic for handling the "Create New Savings" button click
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("savingsAccountWindow.fxml"));
            Parent root = loader.load();

            // Create a new stage for the new savings window
            Stage newSavingsStage = new Stage();
            newSavingsStage.initModality(Modality.APPLICATION_MODAL);
            newSavingsStage.setTitle("Create New Savings Account");
            newSavingsStage.setScene(new Scene(root));

            // Show the new savings window
            newSavingsStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button createNewCheckingsButton;

    @FXML
    private void handleCreateNewCheckings() {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("checkingsAccountWindow.fxml"));
            Parent root = loader.load();

            // Create a new stage for the new savings window
            Stage newCheckingsStage = new Stage();
            newCheckingsStage.initModality(Modality.APPLICATION_MODAL);
            newCheckingsStage.setTitle("Create New Checkings Account");
            newCheckingsStage.setScene(new Scene(root));

            // Show the new Checkings window
            newCheckingsStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button addNewCardButton;

    @FXML
    private void handleAddNewCard() {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addCardWindow.fxml"));
            Parent root = loader.load();

            // Create a new stage for the new savings window
            Stage newCardStage = new Stage();
            newCardStage.initModality(Modality.APPLICATION_MODAL);
            newCardStage.setTitle("Add New Card");
            newCardStage.setScene(new Scene(root));

            // Show the new card window
            newCardStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

