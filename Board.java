public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;
	private char[][] b;
	private int[] stackHeight;
	private char last;
	/* 
	 * The board object must contain the board state in some manner.
	 * You must decide how you will do this.
	 * 
	 * You may add addition private/public methods to this class is you wish.
	 * However, you should use best OO practices. That is, you should not expose
	 * how the board is being implemented to other classes. Specifically, the
	 * Player classes.
	 * 
	 * You may add private and public methods if you wish. In fact, to achieve
	 * what the assignment is asking, you'll have to
	 * 
	 */
	
	public Board() {
		last = ' ';
		stackHeight = new int[NUM_OF_COLUMNS];
		for (int k = 0; k < stackHeight.length; k++) {
			stackHeight[k] = 0;
		}
		b = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
		for (int i = 0; i < b.length; i++)
		{
			for (int j = 0; j < b[0].length; j++)
			{
				b[i][j] = ' ';
			}
		}
	}
	
	public void printBoard() {
		for (int i = 0; i < b.length; i++)
		{
			for (int j = 0; j < b[0].length; j++)
			{
				System.out.print("|" + b[i][j] + "|");
			}
			System.out.print("\n");
		}
	}
	
	public boolean containsWin() {
		return this.diagonalWinL() || this.diagonalWinR() || this.horizontalWin() || this.verticalWin();
	}

	public int blockWin() {
		if (this.blockVertical() != -1) {
			return this.blockVertical() + 1;
		}
		if (this.blockHorizontal() != -1) {
			return this.blockHorizontal() + 1;
		}
		if (this.blockDiagonalR() != -1) {
			return this.blockDiagonalR() + 1;
		}
		if (this.blockDiagonalL() != -1) {
			return this.blockDiagonalL() + 1;
		}
		return -1;
	}

	private int blockVertical() {
		int counter = 0;
		for (int i = 0; i < stackHeight.length; i++) {
			if (stackHeight[i] > 2) {
				for (int j = this.b.length - 1; j >=0; j--) {
					if (b[j][i] == last) {
						counter++;
					}
					else {
						counter = 0;
					}
					if (counter == 3 && j > 0 && b[j-1][i] == ' ') {
						return i;
					}

				}
				counter = 0;
			}
		}
		return -1;
	}
	
	private int blockHorizontal() {
		if (last == ' ') {
			return -1;
		}
		int counter = 0;
		for (int i = 0; i < b.length; i++) {
			for (int j = 1; j < b[0].length - 1; j++) {
				if (b[i][j] == last) {
					counter++;
				}
				else {
					counter = 0;
				}
				if (counter == 3 && b[i][j-3] == ' ') {
					return j-3;
				}
				if (counter == 3 && b[i][j+1] == ' ') {
					return j+1;
				}
			}
			counter = 0;
		}
		return -1;
	}
	
	private int blockDiagonalL() {
		if (last == ' ') {
			return -1;
		}
		int a = 0;
		int c = 0;
		int counter = 0;
		for (int i = this.b.length - 1; i > 2; i--) {
			for (int j = 6; j > 2; j--) {
				while(i - a >= 0 && j - c >= 0) {
					if (b[i - a][j - c] == last) {
						counter++;
					}
					else {
						counter = 0;
					}
					if (counter == 3 && i - a + 3 <= this.b.length - 2 && j - c + 3 <= 7 && b[i-a-3][j-c+3] == ' ' && stackHeight[j-c+3] == i - a + 4) {
						return j-c+3;
					}
					if (counter == 3 && i - a - 1 > 0 && j - c - 1 >= 0 && b[i-a-1][j-c-1] == ' ' && stackHeight[j-c-1] == stackHeight[j-c]) {
						return j - c - 1;
					}
					a++;
					c++;
				}
				counter = 0;
				a = 0;
				c = 0;
			}
		}
		return -1;
	}

	private int blockDiagonalR() {
		if (last == ' ') {
			return -1;
		}
		int a = 0;
		int c = 0;
		int counter = 0;
		for (int i = this.b.length - 1; i > 2; i--) {
			for (int j = 0; j < 4; j++) {
				while(i - a >= 0 && c + j < 7) {
					if (b[i - a][j + c] == last) {
						counter++;
					}
					else {
						counter = 0;
					}
					if (counter == 3 && i - a + 3 <= this.b.length - 2 && j + c - 3 >= 0 && b[i-a-3][j+c-3] == ' ' && stackHeight[j+c-3] == i - a + 4) {
						return j+c-3;
					}
					if (counter == 3 && i - a - 1 > 0 && j + c + 1 < 7 && b[i-a-1][j+c+1] == ' ' && stackHeight[j+c+1] == stackHeight[j+c]) {
						return j + c + 1;
					}
					a++;
					c++;
				}
				counter = 0;
				a = 0;
				c = 0;
			}
		}
		return -1;
	}


	private boolean verticalWin() {
		int counter = 0;
		for (int i = 0; i < stackHeight.length; i++) {
			if (stackHeight[i] > 3) {
				for (int j = this.b.length - 1; j >=0; j--) {
					if (b[j][i] == last) {
						counter++;
					}
					else {
						counter = 0;
					}
					if (counter == 4) {
						return true;
					}

				}
				counter = 0;
			}
		}
		return false;
	}

	private boolean horizontalWin() {
		if (last == ' ') {
			return false;
		}
		int counter = 0;
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				if (b[i][j] == last) {
					counter++;
				}
				else {
					counter = 0;
				}
				if (counter == 4) {
					return true;
				}
			}
			counter = 0;
		}
		return false;
	}

	private boolean diagonalWinR() {
		if (last == ' ') {
			return false;
		}
		int a = 0;
		int c = 0;
		int counter = 0;
		for (int i = this.b.length - 1; i > 2; i--) {
			for (int j = 0; j < 4; j++) {
				while(i - a >= 0 && c + j < 7) {
					if (b[i - a][j + c] == last) {
						counter++;
					}
					else {
						counter = 0;
					}
					if (counter == 4) {
						return true;
					}
					a++;
					c++;
				}
				counter = 0;
				a = 0;
				c = 0;
			}
		}
		return false;
	}

	private boolean diagonalWinL() {
		if (last == ' ') {
			return false;
		}
		int a = 0;
		int c = 0;
		int counter = 0;
		for (int i = this.b.length - 1; i > 2; i--) {
			for (int j = 6; j > 2; j--) {
				while(i - a >= 0 && j - c >= 0) {
					if (b[i - a][j - c] == last) {
						counter++;
					}
					else {
						counter = 0;
					}
					if (counter == 4) {
						return true;
					}
					a++;
					c++;
				}
				counter = 0;
				a = 0;
				c = 0;
			}
		}
		return false;
	}
	
	public boolean isTie() {
		boolean tie = false;
		for (int i = 0; i < stackHeight.length; i++) {
			if (stackHeight[i] < NUM_OF_ROW) {
				return tie;
			}
		}
		return !tie;

	}
	
	public void reset() {
		for (int k = 0; k < stackHeight.length; k++) {
			stackHeight[k] = 0;
		}
		for (int i = 0; i < b.length; i++)
		{
			for (int j = 0; j < b[0].length; j++)
			{
				b[i][j] = ' ';
			}
		}
	}

	public boolean setMove(int m, char symbol) {
		m--;
		last = symbol;
		if (m >= NUM_OF_COLUMNS || m < 0 || stackHeight[m] == NUM_OF_ROW) {
			return false;
		}
		this.b[NUM_OF_ROW - stackHeight[m] - 1][m] = symbol;
		stackHeight[m]++;
		return true;
	}
	
}
