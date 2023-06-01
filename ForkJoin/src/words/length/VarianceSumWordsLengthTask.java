package words.length;

import words.Word;
import words.length.SumWordsLengthTask;

import java.util.List;

public class VarianceSumWordsLengthTask extends SumWordsLengthTask {

    private final double mean;

    public VarianceSumWordsLengthTask(List<Word> wordList, double mean) {

        super(wordList);
        this.mean = mean;
    }

    @Override
    protected Double processText(List<Word> words) {

        return words.stream().mapToDouble(value -> Math.pow(value.getLength() - mean, 2) * value.getCount()).sum();
    }
}
