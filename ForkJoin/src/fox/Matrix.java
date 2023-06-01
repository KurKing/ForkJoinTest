package fox;

import java.util.Random;

public class Matrix {

    private final int size;
    private final double[][] data;

    public Matrix(double[][] matrix) {

        this.data = matrix;
        this.size = matrix.length;
    }

    public Matrix(int size) {

        this.size = size;

        double[][] matrix = new double[size][size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

//                matrix[i][j] = random.nextDouble() * 10;
                matrix[i][j] = 1;
            }
        }

        this.data = matrix;
    }

    public double[][] getData() {

        return data;
    }

    public Row getRow(int index) {

        return new Row(index, data[index]);
    }

    public double getItem(int row, int column) {

        return data[row][column];
    }

    public int getSize() {

        return size;
    }

    public void print() {

        for (double[] row : data) {

            for (int j = 0; j < data[0].length; j++) {

                System.out.print(row[j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
