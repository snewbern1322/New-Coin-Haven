import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.net.URL;


public class SpendingsController implements Initializable {
    @FXML
    private ComboBox<String> accountComboBox;

    @FXML
    private Text accountBalanceText;

    @FXML
    private ListView<String> taskListView;

    @FXML
    private TextField amountTextField;

    @FXML
    private Button newTaskButton;

    @FXML
    private Button deleteTaskButton;

    @FXML
    private Button purchaseButton;

    @FXML
    private TextField taskNameTextField;



    private ObservableList<String> tasksList = FXCollections.observableArrayList();

    //methods-----------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourcesBundle) {
    
        // Set the tasks to the ListView
        tasksList = FXCollections.observableArrayList();
        taskListView.setItems(tasksList);
    
        // Set the event handlers for the buttons
        newTaskButton.setOnAction(event -> handleNewTaskButtonClick());
    
        // Initialize your ComboBox with account names
        accountComboBox.getItems().addAll(InMemoryDatabase.getAccountNames());
    
        // Set a listener to update balance when a different account is selected
        accountComboBox.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> updateBalance(newValue));
    
        taskListView.setItems(tasksList);

        initializeSpendingsPage();

        // Set the event handler for the delete button
        deleteTaskButton.setOnAction(event -> handleDeleteTaskButtonClick());
    }

    // New method for initializing the Spendings page
    private void initializeSpendingsPage() {
        // Set the tasks to the ListView
        tasksList = FXCollections.observableArrayList();
        taskListView.setItems(tasksList);

        // Set the event handlers for the buttons
        newTaskButton.setOnAction(event -> handleNewTaskButtonClick());

        // Initialize your ComboBox with account names
        accountComboBox.getItems().addAll(InMemoryDatabase.getAccountNames());

        // Set a listener to update balance when a different account is selected
        accountComboBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateBalance(newValue));

        // Retrieve tasks from the InMemoryDatabase and add them to the ListView
        tasksList.addAll(InMemoryDatabase.getTasks());
    }

     @FXML
     private void handleNewTaskButtonClick() {
        // Get the text from the taskNameTextField
        String taskName = taskNameTextField.getText().trim();
    
        // Check if the taskName is not empty
        if (!taskName.isEmpty()) {
            // Add the task to the InMemoryDatabase and update the ListView
            InMemoryDatabase.addTask(taskName);
            // Add the task to the list, clear the taskNameTextField, and update the ListView
            tasksList.add(taskName);
            taskNameTextField.clear();
        }
    }
    

    // Update the balance text based on the selected account
    private void updateBalance(String selectedAccount) {
        if (selectedAccount != null) {
            String balance = InMemoryDatabase.getStartingBalance(selectedAccount);
            accountBalanceText.setText(balance);
        }
    }

    
    @FXML
    private void handleDeleteTaskButtonClick() {
        // Get the selected task from the ListView
    String selectedTask = taskListView.getSelectionModel().getSelectedItem();

    // Check if a task is selected
    if (selectedTask != null) {
        // Remove the task from the InMemoryDatabase and update the ListView
        InMemoryDatabase.removeTask(selectedTask);
        tasksList.remove(selectedTask);
        System.out.println("Task deleted successfully");
    }
    
  }  
  
  @FXML
  private void handlePurchaseButtonClick() {
    // Get the selected account from the ComboBox
    String selectedAccount = accountComboBox.getValue();

    // Check if an account is selected
    if (selectedAccount == null || selectedAccount.isEmpty()) {
        System.out.println("Please select an account.");
        return;
    }

    // Get the amount entered in the amountTextField
    String amountText = amountTextField.getText().trim();

    // Check if the amount is not empty
    if (amountText.isEmpty()) {
        System.out.println("Please enter the amount.");
        return;
    }

    // Parse the amount to a double
    try {
        double amount = Double.parseDouble(amountText);

        // Deduct the amount from the selected account in the InMemoryDatabase
        InMemoryDatabase.deductAmount(selectedAccount, amount);

        // Update the balance in the accountBalanceText
        updateBalance(selectedAccount);

        // Remove the selected task from the taskListView and InMemoryDatabase
        String selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            tasksList.remove(selectedTask);
            InMemoryDatabase.removeTask(selectedTask);
        }

        System.out.println("Purchase successful");
    } catch (NumberFormatException e) {
        System.out.println("Invalid amount format. Please enter a valid number.");
    }
}

}
