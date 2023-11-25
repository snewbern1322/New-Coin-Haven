import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BankAppMenu {

    private static final String DATABASE_URL = "jdbc:sqlite:banking_app.db";

    public static void main(String[] args) {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.println("1. View Users");
            System.out.println("2. Remove User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    viewUsers();
                    break;
                case 2:
                    removeUser();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Exiting the application.");
        scanner.close(); // Close the scanner
    }

    private static void viewUsers() {
        // ... (unchanged code)
    }

    private static void removeUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the user ID to remove: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {

            statement.setInt(1, userId);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("User with ID " + userId + " removed.");
            } else {
                System.out.println("User with ID " + userId + " not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close(); // Close the scanner in the finally block
        }
    }
}
