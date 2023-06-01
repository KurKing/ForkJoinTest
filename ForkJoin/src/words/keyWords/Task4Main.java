package words.keyWords;

import resourses.ResourcesPath;
import words.commonWords.CommonWordsFilesProcessor;

import java.util.Set;

public class Task4Main {

    public static void main(String[] args) throws InterruptedException {

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
