import java.util.Random;

public class AIPlayer extends Player {

    private Random rn = new Random();
    public AIPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

    @Override
    public void makeMove(Board board) {

        int move = rn.nextInt(7) + 1;
        if (board.blockWin() != -1) {
            move = board.blockWin();
        }
        board.setMove(-1, symbol);
        if (board.blockWin() != -1) {
            move = board.blockWin();
        }
        while (board.setMove(move, symbol) != true) {
            move = rn.nextInt(7) + 1;
        }
    }
    
}
