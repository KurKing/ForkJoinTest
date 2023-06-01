import fox.FoxCalculator;
import fox.Matrix;
import fox.Result;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        measureFoxTime();

//        Matrix m = new Matrix(500);
//        Matrix m2 = new Matrix(500);
//
//        m.print();
//        m2.print();
//
//        FoxCalculator.multiply(m,m2,500,50).print();
    }

    private static void measureFoxTime() throws InterruptedException {

        for (int matrixSize : new int[]{100, 500, 1000}) {

            Matrix m = new Matrix(matrixSize);
            Matrix m2 = new Matrix(matrixSize);

            int summ = 0;
//            for (int i = 0; i < 10; i++) {

                Instant start = Instant.now();
                Result result = FoxCalculator.multiply(m, m2, matrixSize, matrixSize / 10);
                summ += Duration.between(start, Instant.now()).toMillis();
//            }

//            System.out.println("S: " + matrixSize + "; T: " + summ/10);
            System.out.println("Matrix size: " + matrixSize + "; Time: " + summ);
        }
    }
}