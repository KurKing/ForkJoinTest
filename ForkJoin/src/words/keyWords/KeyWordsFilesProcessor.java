package words.keyWords;

import resourses.ResourcesPath;
import words.*;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class KeyWordsFilesProcessor extends FilesProcessor {

    private final Set<String> keyWords;

    public KeyWordsFilesProcessor(Set<String> keyWords, Set<String> files) {

        super(files);
        this.keyWords = keyWords;
    }

    public Set<String> filesWithKeyWords() throws InterruptedException {

        processFiles();

        HashSet<String> result = new HashSet<>();

        for (String keyWord: keyWords) {

            keyWord = keyWord.toLowerCase();

            for (ProcessedFile file: processedFiles) {

                if (file.words().get(keyWord) != null) {

                    result.add(file.name());
                }
            }
        }

        return result;
    }
}
