import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RepairController {
    Dictionary newDic1 = new Dictionary();
    @FXML
    public TextField repairWord;
    @FXML
    public TextField repairMeaning;

    @FXML
    public Button repairConfirmButton;
    @FXML
    void repairWord(){
        String a = repairWord.getText();
        String b = repairMeaning.getText();
        SqlReader.Repair(a, b, newDic1);
    }
}
