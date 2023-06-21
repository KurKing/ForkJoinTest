package words.keyWords;

import resourses.ResourcesPath;
import java.io.FileNotFoundException;
import java.util.Set;

public class Task4Main {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        Set<String> fileNames = Set.of(
                ResourcesPath.whyUseLoremIpsumName,
                ResourcesPath.whatIsLoremIpsumName,
                ResourcesPath.loremIpsumName
        );

        Set<String> keyWords = Set.of("JAVA", "SWIFT");

        var processor = new KeyWordsFilesProcessor(keyWords, fileNames);

        System.out.println("Files with key words: " + processor.filesWithKeyWords().stream().sorted().toList());
    }
}
