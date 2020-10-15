import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

public class DictionaryManagement {


    static void insertFromCommandline(Dictionary d) {
        Scanner sc = new Scanner(System.in);
        String Eng = sc.next();
        String Viet = sc.next();
        Word w1 = new Word(Viet,Eng);
        d.word_list.add(w1);
    }

    static void insertFromFile(Dictionary d, String path) throws Exception {
        Scanner scanner = new Scanner(Paths.get(path
        ), "UTF-8");
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            String[] nW;
            nW = line.split("\\s");
            Word w = new Word(nW[0],nW[1]);
            w.setWord_target(nW[0]);
            w.setWord_explain(nW[1]);
            d.word_list.add(w);
        }

        scanner.close();
    }
    static void DictionaryLookup(Dictionary d){
        Scanner scanner = new Scanner(System.in);
        String Find = scanner.next();
        System.out.printf("Ban muon tim gi: %s \n", Find);
        for(Word tu : d.word_list){
            if(Find.equals(tu.getWord_target())){
                System.out.printf("Ban da tim thay : %s", Find);
            }
            else{
                System.out.println("Ban khong tim thay");
            }

        }
    }

    static String dictionaryLookup(Dictionary d, String s){
        for(Word tu : d.word_list){
            if(s.equals(tu.getWord_target())){
                return tu.word_explain;
            }
        }
        return "Not Found";
    }

    static String dicLookup(String s){
        for(Word tu : Dictionary.word_list){
            if(s.equals(tu.getWord_target())){
                return tu.word_explain;
            }
        }
        return "Not Found";
    }


    void DictionaryFixorDel(Dictionary d){
        System.out.print("Do you want to fix, del or do nothing : ");
        Scanner sc = new Scanner(System.in);
        String WF_or_Del = sc.next();
        String a= "Fix";
        String b= "Del";
        String c= "Nothing";
        if(WF_or_Del.equals(a)){
            String w1, w2;
            System.out.printf("Prefix :   ");
            w1= sc.next();
            System.out.printf("Afterfix:  ");
            w2=sc.next();
            int i;
            for(i=0;i<d.word_list.size();i++){
                if(w1.equals(d.word_list.get(i).getWord_target())){
                    d.word_list.get(i).setWord_explain(w2);
                }
            }
        }
        if(WF_or_Del.equals(b)){
            String w1;
            System.out.print("Word need to delete:  ");
            w1= sc.next();
            int i=0;
            for(Word w : d.word_list){
                if(w1.equals(d.word_list.get(i).getWord_target())){
                    d.word_list.remove(w);
                }
            }

        }
        if(WF_or_Del.equals(c)){
            System.out.print("Khong co gi can sua");
        }
    }


    void DictionaryExportToFile(Dictionary d) throws Exception {
        PrintWriter pW = new PrintWriter(String.valueOf(getClass().getClassLoader().getResource("DictionaryTest.txt")),"UTF-8");
        for (Word word : d.word_list) {
            pW.printf("%s\t%s\n", word.getWord_target(),word.getWord_explain());//Idol java
        }
        pW.close();
    }
}




