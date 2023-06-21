package words.commonWords;

import words.FilesProcessor;
import java.io.FileNotFoundException;
import java.util.*;

public class CommonWordsFilesProcessor extends FilesProcessor {

    public CommonWordsFilesProcessor(Set<String> fileNames) {
        super(fileNames);
    }

    public Set<String> findCommonWords() throws InterruptedException, FileNotFoundException {

        processFiles();

        return pool.invoke(
                new CommonWordTask(processedFiles.get(0).words().values().stream().toList(),
                        processedFiles.subList(1, processedFiles.size()).stream().map(file -> file.words()).toList())
        );
    }
}
