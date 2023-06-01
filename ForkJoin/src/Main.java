import resourses.ResourcesPath;
import words.WordsScanner;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        List<String> words = WordsScanner.words(ResourcesPath.bigFilePath);
    }
}