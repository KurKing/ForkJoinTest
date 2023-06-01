package words.commonWords;

import words.Word;

import java.util.*;
import java.util.concurrent.RecursiveTask;

public class CommonWordTask extends RecursiveTask<Set<String>> {

    private final static int amountOfWordsToProcess = 10000;

    private final List<Word> wordsToProccess;
    private final int wordsLength;
    private final List<HashMap<String, Word>> otherFiles;

    public CommonWordTask(List<Word> wordsToProccess, List<HashMap<String, Word>> otherFiles) {

        this.wordsToProccess = wordsToProccess;
        this.wordsLength = wordsToProccess.size();
        this.otherFiles = otherFiles;
    }

    @Override
    protected Set<String> compute() {

        if (wordsLength < amountOfWordsToProcess) {

            return processText(wordsToProccess);
        }

        return createRecursiveTasks(wordsToProccess);
    }

    private Set<String> createRecursiveTasks(List<Word> words) {

        var leftSetTask = new CommonWordTask(words.subList(0, wordsLength / 2), otherFiles);
        var rightSetTask = new CommonWordTask(words.subList(wordsLength / 2, wordsLength), otherFiles);

        leftSetTask.fork();
        rightSetTask.fork();

        var leftSet = leftSetTask.join();
        leftSet.addAll(rightSetTask.join());

        return leftSet;
    }

    private Set<String> processText(List<Word> words) {

        Set<String> result = new HashSet<>();
        int otherFilesSize = otherFiles.size();

        for (Word word: words) {

            int c = 0;
            for (Map<String, Word> file: otherFiles) {

                if (file.get(word.getString()) != null) {

                    c++;
                }
            }

            if (c == otherFilesSize) {

                result.add(word.getString());
            }
        }

        return result;
    }
}
