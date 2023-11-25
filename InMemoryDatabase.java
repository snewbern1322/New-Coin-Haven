import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryDatabase {
    private static ArrayList<HashMap<String, String>> users = new ArrayList<>();
    private static ArrayList<HashMap<String, String>> savingsAccounts = new ArrayList<>();
    private static ArrayList<HashMap<String, String>> checkingAccounts = new ArrayList<>();
    private static ArrayList<HashMap<String, String>> cards = new ArrayList<>();

    


    public InMemoryDatabase() {
        this.users = new ArrayList<>();
        this.savingsAccounts = new ArrayList<>();
        this.checkingAccounts = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    // Methods to add data to the in-memory database

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
    private static String getAccountType(String accountName) {
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
    
    // Additional methods for accessing data in-memory
}
