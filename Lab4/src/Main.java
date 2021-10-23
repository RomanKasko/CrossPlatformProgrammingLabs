import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Text from file:");
        String textFromFile = FileManager.readFromFile();
        System.out.println(textFromFile);
        System.out.println();

        System.out.println("Task1. Sentences from text:");
        List<String> text = new ArrayList<>(FileManager.splitToSentence(textFromFile));
        for (String sentence: text) {
            System.out.println(sentence);
        }

        System.out.println("\nTask2. Deleted sentences more than one line:");
        List<String> normText = new ArrayList<>(FileManager.deleteSentencesMoreThanOneLine(text));
        for (String sentence: normText) {
            System.out.println(sentence);
        }

        System.out.println("\nTask3. Found file paths in text: ");
        List<String> filePaths = new ArrayList<>(FileManager.findFilePaths(textFromFile));
        for (String path : filePaths) {
            System.out.println(path);
        }
    }
}