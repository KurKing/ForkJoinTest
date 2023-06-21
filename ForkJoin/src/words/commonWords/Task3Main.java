package words.commonWords;

import resourses.ResourcesPath;
import java.io.FileNotFoundException;
import java.util.Set;

public class Task3Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        Set<String> fileNames = Set.of(
                ResourcesPath.whyUseLoremIpsumName,
                ResourcesPath.whatIsLoremIpsumName,
                ResourcesPath.loremIpsumName
        );

        var processor = new CommonWordsFilesProcessor(fileNames);
        System.out.println("Common words: " + processor.findCommonWords().stream().sorted().toList());
    }
}
