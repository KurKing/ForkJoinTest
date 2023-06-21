package words;

import resourses.ResourcesPath;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class FilesProcessor {

    protected final Set<String> fileNames;
    protected final List<ProcessedFile> processedFiles;
    protected final ForkJoinPool pool = new ForkJoinPool(8);

    public FilesProcessor(Set<String> fileNames) {

        this.fileNames = fileNames;
        this.processedFiles = new ArrayList<>(3);
    }

    protected void processFiles() throws InterruptedException, FileNotFoundException {

        for (String fileName: fileNames) {

            List<String> words = WordsScanner.words(ResourcesPath.loremIpsumPath(fileName));

            HashMap<String, Word> result = pool.invoke(new ProcessWordsTask(words));

            processedFiles.add(new ProcessedFile(fileName, result));
        }
    }
}
