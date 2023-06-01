package words;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordsScanner {

    public static List<String> words(String filePath) throws FileNotFoundException {

        List<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filePath));

        while (scanner.hasNext()) {

            words.add(scanner.next().replaceAll("\\p{Punct}", ""));
        }

        scanner.close();
        return words;
    }
}
