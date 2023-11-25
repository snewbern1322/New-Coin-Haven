import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.net.URL;


public class FxmlLoader {
    private Pane view;
    
    public Pane getPage(String fileName) {
        try {
            URL fileUrl = MainFrameController.class.getResource(fileName + ".fxml");
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }

            FXMLLoader loader = new FXMLLoader();
            view = loader.load(fileUrl.openStream());

        } catch (Exception e) {
            System.out.println("No page" + fileName + "please check FxmlLoader.");
        }
    return view;
    }

}
