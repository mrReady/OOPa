public class Cell
{
    int row, column;
    String state;
    boolean mined;
    int counter;

    Cell(int row, int column)
    {
        mined = false;
        counter = 0;
        state = "closed";
        this.row = row;
        this.column = column;
    }
}
