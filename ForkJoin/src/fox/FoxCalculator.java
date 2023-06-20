package fox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class FoxCalculator {

    public static Result multiply(Matrix matrix1, Matrix matrix2, int size, int processNumber) throws InterruptedException {

        int blockSize = (int) Math.ceil((double) size / processNumber);

        List<Row> resultRows = new ArrayList<Row>(size);
        for (int i = 0; i < size; i++) { resultRows.add(Row.instantiateClearRow(i, size)); }

        ForkJoinPool pool = new ForkJoinPool(8);

        for (var i = 0; i < processNumber; i++) {
            for (var j = 0; j < processNumber; j++) {

                List<Matrix> blockNeedsToBeAdded = getBlocks(pool, matrix1, matrix2, blockSize, i, j, processNumber);

                pool.submit(new FoxBlockAddingAction(blockNeedsToBeAdded, resultRows, i*blockSize, j*blockSize));
            }
        }

        pool.shutdown();
        pool.awaitTermination(100L, TimeUnit.SECONDS);

        double [][] result = new double[size][size];

        for (Row row: resultRows) { result[row.getIndex()] = row.getData(); }

        return new Result(result);
    }

    private static List<Matrix> getBlocks(ForkJoinPool pool,
                                          Matrix matrix1, Matrix matrix2,
                                          int blockSize, int fromIndex, int tillIndex, int processNumber) throws InterruptedException {

        List<Matrix> blocks = Collections.synchronizedList(new ArrayList<>());
        List<ForkJoinTask> multiplierActions = new ArrayList<>();

        for (int block = 0; block < processNumber; block++) {

            int leftStartRow = fromIndex * blockSize;
            int rightStartColumn = tillIndex * blockSize;
            int startC = block * blockSize;

            FoxBlock leftBlock = new FoxBlock(leftStartRow, startC, leftStartRow + blockSize, startC + blockSize);
            FoxBlock rightBlock = new FoxBlock(startC, rightStartColumn, startC + blockSize, rightStartColumn + blockSize);

            var action = pool.submit(new FoxBlockMultiplierAction(blocks, matrix1, matrix2, leftBlock, rightBlock));
            multiplierActions.add(action);
        }

        for (var action: multiplierActions) {

            action.join();
        }

        return blocks;
    }
}
