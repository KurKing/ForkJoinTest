package words.length;

import resourses.ResourcesPath;
import words.*;
import words.ProcessWordsTask;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class Task1Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> words = WordsScanner.words(ResourcesPath.smallFilePath);

        int parallelism = 8;
        ForkJoinPool pool = new ForkJoinPool(parallelism);
        long startTime = System.currentTimeMillis();
        Map<String, Word> result = pool.invoke(new ProcessWordsTask(words));
        long resultProcessingTime = System.currentTimeMillis() - startTime;
        System.out.println("Unique words amount: " + result.values().size());
        System.out.println("Result processing time: " + resultProcessingTime + " ms; Parallelism :"+parallelism);

        startTime = System.currentTimeMillis();
        var lengthService = new AverageWordsLengthService(result.values().stream().toList(), words.size());
        var mean = lengthService.count();
        resultProcessingTime = System.currentTimeMillis() - startTime;
        System.out.println("\nAverage words length: " + mean + ";\nTime: "+resultProcessingTime + " ms");

        startTime = System.currentTimeMillis();
        var varianceService = new VarianceWordsService(result.values().stream().toList(), words.size(), mean);
        resultProcessingTime = System.currentTimeMillis() - startTime;
        System.out.println("\nVariance: " + varianceService.count() + ";\nTime: "+resultProcessingTime + " ms");
    }
}
