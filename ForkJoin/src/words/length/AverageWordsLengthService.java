package words.length;

import words.Word;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class AverageWordsLengthService {

    private final List<Word> words;
    private final int wordsAmount;

    public AverageWordsLengthService(List<Word> words, int wordsAmount) {

        this.words = words;
        this.wordsAmount = wordsAmount;
    }

    public double count() {

        ForkJoinPool pool = new ForkJoinPool(8);

        return pool.invoke(new SumWordsLengthTask(words)) / ((double) wordsAmount);
    }
}
