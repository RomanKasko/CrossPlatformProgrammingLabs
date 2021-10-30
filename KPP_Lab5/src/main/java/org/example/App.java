package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.math.NumberUtils;
/*
З тексту видалити всі слова заданої довжини, що починаються на приголосну букву.
Створити окремий список з трійок слів, які починаються з великої літери.
 */

public class App {

    private static final List<String> filteredText = new LinkedList<>();
    public static void main(String[] args) throws IOException {

        if (!NumberUtils.isParsable(args[0])) {
            System.out.println("Try again. Enter size of word to delete");
            return;
        }

        File inFile = new File("src/main/resources/text.txt");
        File outFile = new File("src/main/resources/output.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
        int lengthOfWordToDelete = Integer.parseInt(args[0]);
        List<String> resThreeWords = new LinkedList<>();

        try (Scanner scanner = new Scanner(inFile)) {

            if(!scanner.hasNext()){
                System.out.println("There is no text in the file");
                return;
            }

            while (scanner.hasNext()) {
                String line = scanner.nextLine().concat("\n");

                if (line.isEmpty()) {
                    continue;
                }

                resThreeWords.addAll(getThreeWords(line));
                line = removeWordsWithCertainLength(line, lengthOfWordToDelete);

                writer.append(line);
            }

        } catch (IOException ex) {
            System.out.println("Can`t open file to read");
        }
        writer.close();

        if(resThreeWords.isEmpty()){
            System.out.println("There is no words that`s fit the condition");
            return;
        }
        System.out.println("Found three words with capital letter in sequence: ");
        resThreeWords.forEach(System.out::println);
    }

    static List<String> getThreeWords(String line) {
        final String reg = "[A-Z]\\w{0,}\\W{1,}";
        Pattern threeWordsPattern = Pattern.compile(reg.repeat(3));

        String[] singleWords = line.split("[\\s.!?,;]");
        filteredText.addAll(Arrays.asList(singleWords).subList(Math.max(singleWords.length - 2, 0), singleWords.length));

        List<String> temp = new LinkedList<>();
        if (filteredText.size() > 3) {
            String str = filteredText.toString();
            filteredText.clear();
            temp.addAll(getThreeWords(str));
        }

        Matcher threeWordsMatcher = threeWordsPattern.matcher(line);

        while (threeWordsMatcher.find()) {
            temp.add(line.substring(threeWordsMatcher.start(), threeWordsMatcher.end() - 1));
        }

        return temp;
    }

    static String removeWordsWithCertainLength(String line, int number) {
        String reg  = "\\b[^AOUYIEaouyie]\\w{%d}\\b";
        Pattern pattern = Pattern.compile(String.format(reg, number - 1));
        Matcher matcher = pattern.matcher(line);
        return matcher.replaceAll("");
    }
}
