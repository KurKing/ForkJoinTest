package words;

import resourses.ResourcesPath;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class FilesProcessor {

    protected final Set<String> fileNames;
    protected final List<ProcessedFile> processedFiles;

    public FilesProcessor(Set<String> fileNames) {

        this.fileNames = fileNames;
        this.processedFiles = new ArrayList<>(3);
    }

    protected void processFiles() throws InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(8);

        for (String fileName: fileNames) {

            pool.submit(new Runnable() {
                @Override
                public void run() {

                    try {

                        List<String> words = WordsScanner.words(ResourcesPath.loremIpsumPath(fileName));
                        ForkJoinPool pool = new ForkJoinPool(8);

                        HashMap<String, Word> result = pool.invoke(new ProcessWordsTask(words));

                        processedFiles.add(new ProcessedFile(fileName, result));

                    } catch (FileNotFoundException e) { throw new RuntimeException(e); }
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(100L, TimeUnit.SECONDS);
    }
}
