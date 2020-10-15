import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Controller {
    public Stage stage1 = new Stage();
    public Stage stage2 = new Stage();
    public DictionaryApplication art;
    @FXML
    public TextField addtext;
    public TextField addmeaning;
    public TextField removetext;
    @FXML
    public Button addButton;
    public Button removeButton;
    public Button addConfirmButton;
    public Button removeConfirmButton;
    public Button SoundButton;
    @FXML
    public TextField Enter;
    @FXML
    public VBox vbox;
    @FXML
    public WebView webView = new WebView();
    @FXML
    public WebEngine webEngine = webView.getEngine();
    @FXML
    public ListView<String> listView;
    public ObservableList<String> resultView = FXCollections.observableArrayList();
    public Dictionary d;
    private Sound sound = new Sound();

    @FXML
    public void initialize(){
        SqlReader c = new SqlReader();
        c.connect();
        d = new Dictionary();
        c.statement(d);
    }
    @FXML
    public void init() {
        testListener(Enter);
    }
    public void testListener(TextField text){
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.equals("")){
                resultView = d.dicSearch(newValue);
                listView.setItems(resultView);
            }
            else
                resultView.clear();
        });
    }
    public void mouseClickOnSoundButton(ActionEvent event){
        String word = Enter.getText();
        sound.speakUp(word);
    }

    public void mouseClickEvents(){
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
                    Enter.setText(listView.getSelectionModel().getSelectedItem());
                    String a = Enter.getText();
                    String b = DictionaryManagement.dicLookup(a);
                    webEngine.loadContent(b);
                    vbox.getChildren().setAll(webView);
                }
            }
        });
    }


    @FXML
    public void EnterWord(ActionEvent event) throws Exception {
        DictionaryManagement a = new DictionaryManagement();
        DictionaryCommandline b = new DictionaryCommandline();
        ArrayList<Word> wordList = new ArrayList<>();
       //ObservableList<String> wordList = FXCollections.observableArrayList();
        //a.insertFromFile(c, getClass().getClassLoader().getResource("dictionaries.txt").getPath().substring(1));
        String _word = Enter.getText();
        String tranWord = a.dictionaryLookup(d, _word);
        wordList = DictionaryCommandline.dictionarySearch(d, _word);
        webEngine.loadContent(tranWord);
        vbox.getChildren().setAll(webView);

    }
    @FXML
    public void newAddWindow(ActionEvent event) throws Exception {
        try {
            Parent root2 = FXMLLoader.load(Controller.class.getResource("/add.fxml"));
            System.out.println(Controller.class.getResource("/add.fxml"));
            stage1.setScene(new Scene(root2));
            stage1.setTitle("Add Window");
            stage1.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void removeWindow(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/remove.fxml"));
            Parent root1 = fxmlLoader.load();
            stage2.setScene(new Scene(root1));
            stage2.setTitle("Remove Window");
            stage2.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
