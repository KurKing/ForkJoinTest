package fox;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class FoxBlockAddingAction extends RecursiveAction  {

    private final List<Matrix> blocksNeedsToBeAdded;
    private final List<Row> resultRows;

    private final int startRow;
    private final int startColumn;

    private final int blockSize;

    public FoxBlockAddingAction(List<Matrix> blocksNeedsToBeAdded, List<Row> resultRows, int startRow, int startColumn) {

        this.blocksNeedsToBeAdded = blocksNeedsToBeAdded;
        this.resultRows = resultRows;
        this.blockSize = blocksNeedsToBeAdded.get(0).getSize();
        this.startRow = startRow;
        this.startColumn = startColumn;
    }

    @Override
    protected void compute() {

        for (Matrix block: blocksNeedsToBeAdded) {

            for (int i = 0; i < blockSize; i++) {

                for (int j = 0; j < blockSize; j++) {

                    resultRows.get(i+ startRow).addValue(block.getItem(i, j), j+ startColumn);
                }
            }
        }
    }
}
