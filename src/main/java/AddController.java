import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddController extends Controller {
    Dictionary newDic = new Dictionary();
    @FXML
    private TextField addtext;

    @FXML
    private TextField addmeaning;

    @FXML
    private Button addConfirmButton;

    @FXML
    void addNewWord() {
        String a = addtext.getText();
        String b = addmeaning.getText();
        SqlReader.add(a, b, newDic);
    }

}

