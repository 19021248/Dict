import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class DictionaryApplication extends Application {
    Dictionary wordList = new Dictionary();

    public DictionaryApplication() throws Exception {
        DictionaryManagement.insertFromFile(wordList, DictionaryApplication.class.getClassLoader().getResource("dictionaries.txt").getPath().substring(1));
    }
    public Dictionary getWordList(){
        return wordList;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            primaryStage.setTitle("My Dictionary");
            Parent newroot = FXMLLoader.load(getClass().getResource("newscenebuilder.fxml"));
            primaryStage.setScene(new Scene(newroot, 600, 400));
            primaryStage.show();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    Platform.exit();
                    System.exit(0);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
