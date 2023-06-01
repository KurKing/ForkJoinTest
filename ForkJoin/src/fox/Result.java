package fox;

public class Result extends Matrix {

    public Result(double[][] matrix) {
        super(matrix);
    }

    @Override
    public void print() {

        System.out.println("\nResult object:");
        super.print();
    }
}
