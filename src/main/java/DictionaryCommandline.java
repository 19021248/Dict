import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Scanner;
import java.util.ArrayList;

public class DictionaryCommandline {
    int x=1;

    void showAllWords(Dictionary d){
        d.showAllWords();
    }
    void dictionaryBasic(Dictionary d){
        DictionaryManagement.insertFromCommandline(d);
        d.showAllWords();
    }
    void DictionaryAdvanced(Dictionary d){
        DictionaryManagement.insertFromCommandline(d);
        d.showAllWords();
        DictionaryManagement.DictionaryLookup(d);


    }

    static void dictionarySearcher(Dictionary d) {
        String wordSearch;
        Scanner in = new Scanner(System.in);
        wordSearch = in.nextLine();
        ArrayList<Word> wordList = new ArrayList<>();
        for (Word word : d.word_list) {
            if(word.getWord_target().contains(wordSearch) && wordSearch.charAt(0) == word.getWord_target().charAt(0)) {
                wordList.add(word);
            }
        }
        for (Word word : wordList) {
            System.out.println(word.getWord_target());
        }
    }


    static ArrayList<Word> dictionarySearch(Dictionary d, String wordSearch) {
        if (wordSearch == null || wordSearch.equals("")) return null;
        ArrayList<Word> wordList = new ArrayList<>();
        for (Word i : d.word_list) {
            int index = i.getWord_target().indexOf(wordSearch);
            if(index == 0) {
                wordList.add(i);
            }
        }
        return wordList;
    }

    public static void main(String[] args )throws Exception {
        Dictionary d1 = new Dictionary();
        d1.addWord("Dictionary","Tu dien");
        d1.addWord("Dick","Cu");
        DictionaryManagement dm = new DictionaryManagement();
        dm.insertFromFile(d1, DictionaryCommandline.class.getClassLoader().getResource("dictionary.txt").getPath().substring(1));
        // dm.insertFromFile(d1,"D:\\dictionary.txt");
        //  dictionarySearcher(d1);
        // dm.DictionaryExportToFile(d1);
        d1.showAllWords();
    }
}
