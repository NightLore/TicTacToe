
/**
 *  Tic-Tac-Toe game
 *
 *  @author  Nathan M. Lui
 *  @version Mar 19, 2016
 *  @author  Assignment: TicTacToe
 */
public class TicTacToe
{
    private int size;
    private boolean xTurn;
    private int[][] board;
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
        this.xTurn = true;
        this.size = size;
        this.reset();
    }
    
    /**
     * Clears the board
     */
    public void reset()
    {
        board = new int[size][size];
    }
    
    /**
     * Returns whether it is X player's turn
     * @return xTurn
     */
    public boolean isXTurn()
    {
        return xTurn;
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
    public int change( int x, int y )
    {
        if ( board[x][y] == 0 )
        {
            board[x][y] = xTurn ? 1 : -1;
            xTurn = !xTurn;
        }
        return board[x][y];
    }
    
    /**
     * Checks whether the board has a winning condition
     * @return
     */
    public int checkWin()
    {
        int i, j;
        for ( int player = -1; player <= 1; player+=2 ) // check player 1 then player 2
        {
            for( i = 0; i < size; i++ ) // check the grid
            {
                for ( j = 0; j < size; j++ ) // check horizontally
                {
                    if ( board[i][j] != player )
                        break;
                }
                if ( j == size )
                    return player;
                for ( j = 0; j < size; j++ ) // check vertically
                {
                    if ( board[j][i] != player )
                        break;
                }
                if ( j == size )
                    return player;
            }
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
        }
        for ( int[] k : board )
        {
            for ( int l : k )
            {
                if ( l == 0 )
                    return 0;
            }
        }
        return -2;
    }
}
