import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Scanner;

public class Dictionary {
    static ArrayList<Word> word_list = new ArrayList<Word>();

    public Dictionary(){
        word_list = new ArrayList<Word>();
    }
//
    public void addWord(String target, String explain) {
        word_list.add(new Word(target,explain));
    }

    public void removeWord(String a){
        for(int i=0;i<word_list.size();i++){
            if(word_list.get(i).getWord_target().equals(a)){
                word_list.remove(i);
            }
        }
    }

    public void showAllWords(){
        System.out.println("NO      VIET        ENG");
        for (int i=0 ;i<word_list.size();i++)
        {
            System.out.printf("%d   %s  %s \n",i,word_list.get(i).getWord_target(),word_list.get(i).getWord_explain());
        }
    }
    public static ObservableList<String> dicSearch(String choosenWord) {
        ObservableList<String> result = FXCollections.observableArrayList();
        for (Word i : word_list) {
            int index = i.getWord_target().indexOf(choosenWord);
            if (index == 0) {
                result.add(i.getWord_target());
            }
        }
        return result;
    }
    public static String dictionarylookup(String findWord) {
        int left = 0;
        int right = word_list.size()-1;
        while(left <= right) {
            int mid = (left + right)/2;
            int res = findWord.compareToIgnoreCase(word_list.get(mid).getWord_target());
            if(res == 0) {
                return word_list.get(mid).getWord_explain();
            }
            else if(res > 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return "Not Found";
    }


}

