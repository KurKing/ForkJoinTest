package fox;

public class FoxBlock {

    public final int startRow;
    public final int startColumn;
    public final int endRow;
    public final int endColumn;

    public FoxBlock(int startRow, int startColumn, int endRow, int endColumn) {

        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
    }
}
