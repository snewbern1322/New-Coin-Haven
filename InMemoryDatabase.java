import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InMemoryDatabase {
    private static ArrayList<HashMap<String, String>> users = new ArrayList<>();
    private static ArrayList<HashMap<String, String>> savingsAccounts = new ArrayList<>();
    private static ArrayList<HashMap<String, String>> checkingAccounts = new ArrayList<>();
    private static ArrayList<HashMap<String, String>> cards = new ArrayList<>();
    private static ArrayList<String> tasks = new ArrayList<>();
    


    public InMemoryDatabase() {
        /*this.users = new ArrayList<>();
        this.savingsAccounts = new ArrayList<>();
        this.checkingAccounts = new ArrayList<>();
        this.cards = new ArrayList<>();*/
    }

    // Methods to add data to the in-memory database------------------------------------------------------
    //adds task from "Spendings" page
    public static void addTask(String taskName) {
        tasks.add(taskName);
    }

    public static List<String> getTasks() {
        return new ArrayList<>(tasks);
    }

    public static void addUser(String username, String password) {
        HashMap<String, String> user = new HashMap<>();
        user.put("username", username);
        user.put("password", password);
        users.add(user);
        System.out.println("User registered successfully");
    }

    public static void addCheckingAccount(String accountName, String dateCreated, double startingBalance) {
        HashMap<String, String> account = new HashMap<>();
        account.put("accountName", accountName);
        account.put("dateCreated", dateCreated);
        account.put("startingBalance", Double.toString(startingBalance)); // Convert double to String
        checkingAccounts.add(account);
        System.out.println("Checking account added successfully");
    }

    public static void addSavingsAccount(String accountName, String dateCreated, double startingBalance) {
        HashMap<String, String> account = new HashMap<>();
        account.put("accountName", accountName);
        account.put("dateCreated", dateCreated);
        account.put("startingBalance", Double.toString(startingBalance)); // Convert double to String
        savingsAccounts.add(account);
        System.out.println("Savings account added successfully");
    }

    public static void addCard(String cardNumber, String cardHolder, String expirationDate, String cvv) {
        HashMap<String, String> card = new HashMap<>();
        card.put("cardNumber", cardNumber);
        card.put("cardHolder", cardHolder);
        card.put("expirationDate", expirationDate);
        card.put("cvv", cvv);
        cards.add(card);
        System.out.println("Card added successfully");
    }

    public static String getStartingBalance(String accountName) {
        System.out.println("Requested account name: " + accountName);
        // Determine the account type based on the accountName
        String accountType = getAccountType(accountName);
    
        // Find the account with the specified name in the corresponding ArrayList
        ArrayList<HashMap<String, String>> accountList = getAccountList(accountType);
    
        // Return a default value if the account type is not recognized or the account is not found
        if (accountList == null) {
            System.out.println("Account type not recognized");
            return "-1.0";  // Use a value that indicates an invalid starting balance
        }
    
        // Find the account with the specified name
        for (HashMap<String, String> account : accountList) {
            System.out.println("Account found: " + account.get("accountName"));
            if (account.get("accountName").equals(accountName)) {
                System.out.println("Starting Balance found: " + account.get("startingBalance"));
                return account.get("startingBalance");
            }
        }
    
        // Account not found, return a default value
        System.out.println("Account not found");
        return "-1.0";  // Use a value that indicates an invalid starting balance
    }
    
    // Helper method to determine the account type based on the accountName
    public static String getAccountType(String accountName) {
        System.out.println("Checking account type for: " + accountName);
        if (accountName.toLowerCase().contains("savings")) {
            return "savings";
        } else if (accountName.toLowerCase().contains("checking")) {
            return "checking";
        } else {
            return "unknown";
        }
    }
    
    
    // Helper method to get the corresponding ArrayList based on the account type
    private static ArrayList<HashMap<String, String>> getAccountList(String accountType) {
        switch (accountType) {
            case "savings":
                return savingsAccounts;
            case "checking":
                return checkingAccounts;
            // Add cases for other account types if needed
            default:
                return null;
        }
    }
    
    // Add itional methods for accessing data in-memory
    public static void updateSavingsAccountBalance(String accountName, double amount) {
        for (HashMap<String, String> account : savingsAccounts) {
            if (account.get("accountName").equals(accountName)) {
                double currentBalance = Double.parseDouble(account.get("startingBalance"));
                double newBalance = currentBalance + amount;
                account.put("startingBalance", Double.toString(newBalance));
                System.out.println("Savings account balance updated: " + newBalance);
                break;
            }
        }
    }
    
    public static void updateCheckingAccountBalance(String accountName, double amount) {
        for (HashMap<String, String> account : checkingAccounts) {
            if (account.get("accountName").equals(accountName)) {
                double currentBalance = Double.parseDouble(account.get("startingBalance"));
                double newBalance = currentBalance + amount;
                account.put("startingBalance", Double.toString(newBalance));
                System.out.println("Checking account balance updated: " + newBalance);
                break;
            }
        }
    }

    // Method to get a list of all account names
    public static List<String> getAccountNames() {
        Set<String> uniqueAccountNames = new HashSet<>();

        for (HashMap<String, String> savingsAccount : savingsAccounts) {
            uniqueAccountNames.add(savingsAccount.get("accountName"));
        }

        for (HashMap<String, String> checkingAccount : checkingAccounts) {
            uniqueAccountNames.add(checkingAccount.get("accountName"));
        }

        // Add other account types if needed

        return new ArrayList<>(uniqueAccountNames);
    }



    public static void transferFunds(String fromAccountName, String toAccountName, double amount) {
        // Get account types based on names
        String fromAccountType = getAccountType(fromAccountName);
        String toAccountType = getAccountType(toAccountName);
    
        // Update balances based on account types
        switch (fromAccountType) {
            case "savings":
                updateSavingsAccountBalance(fromAccountName, -amount); // Deduct amount from source account
                break;
            case "checking":
                updateCheckingAccountBalance(fromAccountName, -amount); // Deduct amount from source account
                break;
            // Add cases for other account types if needed
            default:
                System.out.println("Invalid source account type");
                return;
        }
    
        switch (toAccountType) {
            case "savings":
                updateSavingsAccountBalance(toAccountName, amount);  // Add amount to destination account
                break;
            case "checking":
                updateCheckingAccountBalance(toAccountName, amount);  // Add amount to destination account
                break;
            // Add cases for other account types if needed
            default:
                System.out.println("Invalid destination account type");
                // If the destination account type is invalid, you might want to rollback the source account balance change
                // (e.g., add the deducted amount back to the source account)
                switch (fromAccountType) {
                    case "savings":
                        updateSavingsAccountBalance(fromAccountName, amount);
                        break;
                    case "checking":
                        updateCheckingAccountBalance(fromAccountName, amount);
                        break;
                    // Add cases for other account types if needed
                }
                return;
        }
        System.out.println("Funds transferred successfully");
    }

    private static void updateAccountBalance(String accountName, double amount, String accountType) {
        // Determine the account type based on the accountName
        ArrayList<HashMap<String, String>> accountList = getAccountList(accountType);
    
        // Update the balance
        for (HashMap<String, String> account : accountList) {
            if (account.get("accountName").equals(accountName)) {
                double currentBalance = Double.parseDouble(account.get("startingBalance"));
                double newBalance = currentBalance + amount;
                account.put("startingBalance", Double.toString(newBalance));
                System.out.println(accountType + " account balance updated: " + newBalance);
                break;
            }
        }
    }

    // Method to remove a task
    public static void removeTask(String taskName) {
        tasks.remove(taskName);
        System.out.println("Task removed successfully");
    }


    public static void deductAmount(String accountName, double amount) {
        // Get the account type based on the account name
        String accountType = getAccountType(accountName);
    
        // Deduct the amount from the account balance
        switch (accountType) {
            case "savings":
                updateSavingsAccountBalance(accountName, -amount);
                break;
            case "checking":
                updateCheckingAccountBalance(accountName, -amount);
                break;
            default:
                System.out.println("Invalid account type");
        }
    }
}
