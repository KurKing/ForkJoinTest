package words.length;

import words.Word;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class VarianceWordsService {

    private final List<Word> words;
    private final int wordsAmount;
    private final double mean;

    public VarianceWordsService(List<Word> words, int wordsAmount, double mean) {

        this.words = words;
        this.wordsAmount = wordsAmount;
        this.mean = mean;
    }

    public double count() {

        ForkJoinPool pool = new ForkJoinPool(8);

        return pool.invoke(new VarianceSumWordsLengthTask(words, mean)) / ((double) wordsAmount);
    }
}