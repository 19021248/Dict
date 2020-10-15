import javafx.fxml.FXML;

import javax.xml.soap.Text;
import java.awt.*;

public class RepairController {
    Dictionary newDic1 = new Dictionary();
    @FXML
    private TextField repairWord;
    private TextField repairMeaning;

    @FXML
    private Button repairConfirmButton;
    void repairWord(){
        String a = repairWord.getText();
        String b = repairMeaning.getText();
        SqlReader.Repair(a, b, newDic1);
    }
}
