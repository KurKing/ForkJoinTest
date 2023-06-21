package words.keyWords;

import words.*;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class KeyWordsFilesProcessor extends FilesProcessor {

    private final Set<String> keyWords;

    public KeyWordsFilesProcessor(Set<String> keyWords, Set<String> files) {

        super(files);
        this.keyWords = keyWords;
    }

    public Set<String> filesWithKeyWords() throws InterruptedException, FileNotFoundException {

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
