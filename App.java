
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class App extends Application {
    public void start(Stage primaryStage){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registration.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root);



            primaryStage.setTitle("Coin Haven Login"); //window title
            primaryStage.setScene(scene);

            //set stage dimensions
            primaryStage.setWidth(1000);
            primaryStage.setHeight(650);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }         
    }
    
    public static void main(String[]args) {
        launch(args); //launches application class
    }
}