public class Controller
{
    Model model;

    Controller(Model model)
    {
        this.model = model;
    }

    void openCell(int row, int column) throws NullPointerException
    {
        model.openCell(row, column);
    }
    void newGame()
    {
        model.generateBoard(Model.FIELD_SIZE, Model.mines);
    }
}
