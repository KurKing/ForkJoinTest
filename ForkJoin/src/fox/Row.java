package fox;

public class Row {

    private int index;
    private double[] data;

    public Row(int index, double[] data) {

        this.index = index;
        this.data = data;
    }

    public static Row instantiateClearRow(int index, int size) {

        double[] matrix = new double[size];
        for (int i = 0; i < size; i++) { matrix[i] = 0; }

        return new Row(index, matrix);
    }

    public double[] getData() {
        return data;
    }

    public double getItem(int index) {
        return data[index];
    }

    public int getIndex() {
        return index;
    }

    synchronized public void addValue(double value, int index) {

        data[index] += value;
    }
}
