
/**
 *  Tic-Tac-Toe game
 *
 *  @author  Nathan M. Lui
 *  @version Mar 19, 2016
 *  @author  Assignment: TicTacToe
 */
public class TicTacToe
{
	public enum State {
		ONE("Player 1","X"),TWO("Player 2","O"),TIE,RUN;
		private String player, visual;
		
		private State() {
			this("","");
		}
		
		private State(String player, String visual) {
			this.player = player;
			this.visual = visual;
		}
		
		public String toPlayer() {
			return player;
		}
		
		public String toVisual() {
			return visual;
		}
		
		public boolean isPlayer(State s) {
			return s == ONE || s == TWO;
		}
	}
	
    private int size;
    private State currentPlayer;
    private State otherPlayer;
    private State[][] board;
    /**
     * Makes a default Tic-Tac-Toe game with size 3x3
     */
    public TicTacToe()
    {
        this( 3 );
    }
    
    /**
     * Makes a Tic-Tac-Toe game with given size
     * @param size
     */
    public TicTacToe( int size )
    {
        this.currentPlayer = State.ONE;
        this.otherPlayer = State.TWO;
        this.size = size;
        this.reset();
    }
    
    /**
     * Clears the board
     */
    public void reset()
    {
        board = new State[size][size];
        for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = State.TIE;
			}
		}
    }
    
    /**
     * Returns the current player as a State
     * @return current Player
     */
    public State getCurrentPlayer()
    {
        return currentPlayer;
    }
    
    /**
     * Returns the other player as a State
     * @return other Player
     */
    public State getOtherPlayer()
    {
        return otherPlayer;
    }
    
    /**
     * Changes a tile in the board
     * @param x -coordinate on board
     * @param y -coordinate on board
     * @return the player that changed the spot on the board
     * <br> -1: player 2
     * <br> 0: no player
     * <br> 1: player 1
     */
    public State change( int x, int y )
    {
        if ( board[x][y] == State.TIE )
        {
            board[x][y] = currentPlayer;
            swapPlayers();
        }
        return board[x][y];
    }
    
    public void swapPlayers()
    {
    	State temp = otherPlayer;
    	otherPlayer = currentPlayer;
    	currentPlayer = temp;
    }
    
    /**
     * Checks whether the board has a winning condition
     * @return
     */
    public State checkWin(int x, int y)
    {
    	int[] xChanges = {0,1,1,1,0,size-1,size-1,size-1};
    	int[] yChanges = {1,1,0,size-1,size-1,size-1,0,1};
    	int numDir = xChanges.length;
    	State player = board[x][y];
    	State tie = State.TIE;
    	for (int i = 0; i < numDir; i++) {
        	int x1 = x;
        	int y1 = y;
    		int j;
    		for (j = 0; j < size; j++) {
				x1 += xChanges[i]; x1 %= size;
				y1 += yChanges[i]; y1 %= size;
				if (board[x1][y1] == State.TIE)
					tie = State.RUN;
				if (board[x1][y1] != player)
					break;
			}
    		if (j == size)
    			return player;
    	}
    	return tie;
    }
}
