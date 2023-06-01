package words.commonWords;

import resourses.ResourcesPath;
import words.FilesProcessor;
import words.ProcessWordsTask;
import words.Word;
import words.WordsScanner;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.*;

public class CommonWordsFilesProcessor extends FilesProcessor {

    public CommonWordsFilesProcessor(Set<String> fileNames) {

        super(fileNames);
    }

    public Set<String> findCommonWords() throws InterruptedException {

        processFiles();

        ForkJoinPool pool = new ForkJoinPool(8);


        return pool.invoke(
                new CommonWordTask(processedFiles.get(0).words().values().stream().toList(),
                        processedFiles.subList(1, processedFiles.size()).stream().map(file -> file.words()).toList())
        );
    }
}
