package words;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class ProcessWordsTask extends RecursiveTask<HashMap<String, Word>> {

    private final static int amountOfWordsToProcess = 10000;

    private final List<String> words;
    private final int wordsLength;

    public ProcessWordsTask(List<String> words) {

        this.words = words;
        this.wordsLength = words.size();
    }

    @Override
    protected HashMap<String, Word> compute() {

        if (wordsLength < amountOfWordsToProcess) {

            return processText(words);
        }

        return createRecursiveTasks(words);
    }

    private HashMap<String, Word> createRecursiveTasks(List<String> words) {

        var leftSubList = new ProcessWordsTask(words.subList(0, wordsLength / 2));
        var rightSubList = new ProcessWordsTask(words.subList(wordsLength / 2, wordsLength));

        leftSubList.fork();
        rightSubList.fork();

        return mergeResults(leftSubList.join(), rightSubList.join());
    }

    private HashMap<String, Word> processText(List<String> words) {

        HashMap<String, Word> result = new HashMap<>();

        for (String word: words) {

            if (word.length() < 2) { continue; }

            word = word.toLowerCase();
            Word item = result.get(word);

            if (item == null) {

                result.put(word, new Word(word));
            } else {

                item.updateCount();
            }
        }

        return result;
    }

    private HashMap<String, Word> mergeResults(HashMap<String, Word> lhs, HashMap<String, Word> rhs) {

        for (Map.Entry<String, Word> entry : rhs.entrySet()) {

            String key = entry.getKey();
            Word value = entry.getValue();

            Word leftItem = lhs.get(key);

            if (leftItem == null) {

                lhs.put(key, value);
            } else {

                leftItem.addToCount(value.getCount());
            }
        }

        return lhs;
    }
}
