
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
    private int emptySpaces;
    
    /**
     * Makes a Tic-Tac-Toe game with given size
     * @param size
     */
    public TicTacToe( int size )
    {
        this.size = size;
        this.reset();
    }
    
    /**
     * Clears the board
     */
    public void reset()
    {
        this.currentPlayer = State.ONE;
        this.otherPlayer = State.TWO;
        this.emptySpaces = size * size;
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
            emptySpaces--;
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
    	State player = board[x][y];
    	int i;
		for ( i = 0; i < size; i++ ) // check column
		{
			if ( board[x][i] != player )
				break;
		}
		if ( i == size )
			return player;
		for ( i = 0; i < size; i++ ) // check row
		{
			if ( board[i][y] != player )
				break;
		}
		if ( i == size )
			return player;
		for ( i = 0; i < size; i++ ) // check top-left, bottom-right diagonal
		{
			if ( board[i][i] != player )
				break;
		}
		if ( i == size )
			return player;
		for ( i = 0; i < size; i++ ) // check other diagonal
		{
			if ( board[i][size-i-1] != player )
				break;
		}
		if ( i == size )
			return player;
    	return emptySpaces <= 0 ? State.TIE : State.RUN;
    }
}
