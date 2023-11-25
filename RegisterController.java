// class used to manage logic for login page
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;


public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    public void registerButtonAction() {
        //handle registration login here
        String username = usernameField.getText(); //gets username entered by user
        String password = passwordField.getText(); //gets password entered by user

        //Call method from database class to insert the user
        InMemoryDatabase.addUser(username, password);

        //after registration is complete, load main frame...
        loadMainFrame();
    }

    private void loadMainFrame() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainframe.fxml"));
            Parent root = fxmlLoader.load();

            //set controller for main frame FXML
            MainFrameController mainFrameController = fxmlLoader.getController();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("Coin Haven");
            mainFrameStage.setScene(new Scene((root)));
            mainFrameStage.show();

            //close current registration window
            ((Stage) usernameField.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}