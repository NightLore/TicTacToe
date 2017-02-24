
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *  Tic-Tac-Toe Game
 *
 *  @author  Nathan M. Lui
 *  @version Mar 19, 2016
 *  @author  Assignment: TicTacToe
 */
public class TicTacBoard extends JFrame
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final int SIZE = 3;
    public static final int WINDOW_SIZE = 500;
    
    private TicTacToe game;
    private JTextField text;
    private boolean inGame;
    private JButton[][] buttons;
    
    public TicTacBoard()
    {
        this.setTitle( "Tic-Tac-Toe" );
        this.setVisible( true );
        this.setDefaultCloseOperation( EXIT_ON_CLOSE );
        this.getContentPane().setPreferredSize( new Dimension( WINDOW_SIZE, WINDOW_SIZE ) );
        this.pack();
        
        JPanel mainPanel = new JPanel( new BorderLayout() );
        JPanel gamePanel = new JPanel( new GridLayout( SIZE, SIZE ) );
        JPanel textPanel = new JPanel();
        
        inGame = true;
        buttons = new JButton[SIZE][SIZE];
        game = new TicTacToe( SIZE );
        for ( int i = 0; i < SIZE; i++ )
        {
            for ( int j = 0; j < SIZE; j++ )
            {
                JButton b = new JButton();
                b.setOpaque( false );
                b.setContentAreaFilled( false );
                b.addActionListener( new ActionAdapter( i, j ) );
                Font font = b.getFont();
                b.setFont( new Font( font.getFontName(), font.getStyle(), WINDOW_SIZE / SIZE ) );
                
                buttons[i][j] = b;
                gamePanel.add( b );
            }
        }
        text = new JTextField( "Let's Play a game of Tic-Tac-Toe" );
        text.setEditable( false );
        text.setPreferredSize( new Dimension( WINDOW_SIZE - 100, 50 ) );
        
        JButton reset = new JButton( "RESET" );
        reset.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent arg0 )
            {
                reset();
            }
        } );
        textPanel.add( text );
        textPanel.add( reset );
        
        mainPanel.add( gamePanel, BorderLayout.CENTER );
        mainPanel.add( textPanel, BorderLayout.SOUTH );
        this.add( mainPanel );
    }
    
    public void reset()
    {
        game.reset();
        inGame = true;
        for ( int i = 0; i < SIZE; i++ )
        {
            for ( int j = 0; j < SIZE; j++ )
            {
                buttons[i][j].setText( "" );
            }
        }
    }
    
    private class ActionAdapter implements ActionListener
    {
        private int x, y;
        public ActionAdapter( int x, int y )
        {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public void actionPerformed( ActionEvent e )
        {
            if ( !inGame )
                return;
            ((JButton)e.getSource()).setText( toVisual( game.change( x, y ) ) );
            int win = game.checkWin();
            String s = "";
            switch( win )
            {
                case 1:
                case -1:
                    s = "Winner is " + toPlayer( win );
                    inGame = false;
                    break;
                case 0:
                    s = toPlayer( !game.isXTurn() ) + " moved, " + toPlayer( game.isXTurn() ) + "'s turn";
                    break;
                    
            }
            text.setText( s );
        }
    }
    
    private static String toPlayer( int i )
    {
        if ( i == 0 )
            return "";
        return toPlayer( i == 1 );
    }
    
    private static String toPlayer( boolean b )
    {
        return "Player " + ( b ? "1 (X)" : "2 (O)" );
    }

    private static String toVisual( int i )
    {
        String s = "";
        switch ( i )
        {
            case -1:
                s = "O";
                break;
            case 1:
                s = "X";
                break;
        }
        return s;
    }

    public static void main( String[] args ) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacBoard();
            }
        });
    }
}
