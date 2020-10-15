import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RemoveController extends Controller{
    Dictionary newDic1 = new Dictionary();
    @FXML
    private TextField removetext;

    @FXML
    private Button removeConfirmButton;

    @FXML
    void removeWord(ActionEvent event) {
        String a = removetext.getText();
        SqlReader.deleteDb(newDic1, a);
        Platform.exit();
    }
}
