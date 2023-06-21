package words.length;

import words.Word;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SumWordsLengthTask extends RecursiveTask<Double> {

    private final static int amountOfWordsToProcess = 10000;

    private final List<Word> wordList;
    private final int wordsLength;

    public SumWordsLengthTask(List<Word> wordList) {
        this.wordList = wordList;
        this.wordsLength = wordList.size();
    }

    @Override
    protected Double compute() {

        if (wordsLength < amountOfWordsToProcess) {

            return createRecursiveTasks(wordList);
        }

        return processText(wordList);
    }

    private Double createRecursiveTasks(List<Word> words) {

        var leftSubList = new SumWordsLengthTask(words.subList(0, wordsLength / 2));
        var rightSubList = new SumWordsLengthTask(words.subList(wordsLength / 2, wordsLength));

        leftSubList.fork();
        rightSubList.fork();

        return leftSubList.join() + rightSubList.join();
    }

    protected Double processText(List<Word> words) {

        return words.stream().mapToDouble(value -> value.getLength() * value.getCount()).sum();
    }
}