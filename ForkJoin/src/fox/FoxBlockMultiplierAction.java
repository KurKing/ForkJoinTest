package fox;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class FoxBlockMultiplierAction extends RecursiveAction {

    private final List<Matrix> blocks;
    private final Matrix leftMatrix;
    private final Matrix rightMatrix;

    private final FoxBlock leftBlock;
    private final FoxBlock rightBlock;

    public FoxBlockMultiplierAction(List<Matrix> blocks,
                                    Matrix leftMatrix,
                                    Matrix rightMatrix,
                                    FoxBlock leftBlock,
                                    FoxBlock rightBlock) {

        this.blocks = blocks;
        this.leftMatrix = leftMatrix;
        this.rightMatrix = rightMatrix;
        this.leftBlock = leftBlock;
        this.rightBlock = rightBlock;
    }

    @Override
    protected void compute() {

        Matrix newBlock = new Matrix(multiplyMatrixes(leftMatrix.getData(), rightMatrix.getData()));

        blocks.add(newBlock);
    }

    private double[][] multiplyMatrixes(double[][] lhs, double[][] rhs) {

        int matrixSize = leftBlock.endRow - leftBlock.startRow;

        double[][] multiplied = new double[matrixSize][matrixSize];

        for (int rowIndex = leftBlock.startRow; rowIndex < leftBlock.endRow; rowIndex++) {

            for (int writeColumnIndex = rightBlock.startColumn; writeColumnIndex < rightBlock.endColumn; writeColumnIndex++) {

                double item = 0;

                for (int leftColumnIndex = leftBlock.startColumn; leftColumnIndex < leftBlock.endColumn; leftColumnIndex++) {

                    item += lhs[rowIndex][leftColumnIndex] * rhs[leftColumnIndex][writeColumnIndex];
                }

                multiplied[rowIndex%matrixSize][writeColumnIndex%matrixSize] = item;
            }
        }

        return multiplied;
    }
}
