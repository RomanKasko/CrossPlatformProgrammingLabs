import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    
    private final static String filePath = "D:\\University\\Java\\Lab4\\lab4.txt";
    protected static String textFromFile;

    public static String readFromFile() throws FileNotFoundException {
        textFromFile ="";
        File allText = new File(filePath);
        Scanner scanner = new Scanner(allText);
        while (scanner.hasNextLine()){
            String tempText = scanner.nextLine();
            textFromFile += tempText + "\n";
        }
        return textFromFile;
    }

    public static List<String> splitToSentence(String data){
        var sentences = new ArrayList<String>();
        int previousStart = 0;
        for(int i = 0 ; i < data.length(); ++i){
            if(data.charAt(i) == '.'
               || data.charAt(i) == '!'
               || data.charAt(i) == '?')
            {
                if(isEndOfSentence(data, i)){
                    sentences.add(data.substring(previousStart, i+1));
                    previousStart = i + 1;
                }
            }
        }
        return sentences;
    }

    private static boolean isEndOfSentence(String data, int index){
        if(data.charAt(index+1) == '\n'){
            return true;
        }
        return index + 1 < data.length() && Character.isUpperCase(data.charAt(index + 1));
    }

    public static List<String> deleteSentencesMoreThanOneLine(List<String> sentences) {
        var normSentences = new ArrayList<String>();
        for (String str: sentences) {
            if(!str.contains("\n")){
                normSentences.add(str);
            }
        }
        return normSentences;
    }

    public static List<String> findFilePaths(String data) {
        var filePaths = new ArrayList<String>();
        int pathBeginPos = 0;
        int pathEndPos = 0;
        for(int i = 0 ; i < data.length()-2; ++i){
            if(Character.isUpperCase(data.charAt(i)) &&
               data.charAt(i+1) == ':' &&
               (data.charAt(i+2) == '\\' || data.charAt(i+2) == '/')) {
                    pathBeginPos = i;
                    pathEndPos = i;
                    while(data.charAt(i) != ' ' && data.charAt(i) != '\n') {
                        ++pathEndPos;
                        ++i;
                }
                filePaths.add(data.substring(pathBeginPos,pathEndPos));
            }
        }
        return filePaths;
    }
}