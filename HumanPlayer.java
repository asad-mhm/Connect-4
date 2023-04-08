import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner input = new Scanner(System.in);
    public HumanPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);

    }

    @Override
    public void makeMove(Board board) {
        int move = input.nextInt();
        //System.out.println(name + ", please input your next move: ");
        while (board.setMove(move, symbol) != true) {
            System.out.println("Invalid move, please input your next move: ");
            move = input.nextInt();

            
        }
        
    }
    
}
