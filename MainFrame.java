import java.awt.Dimension;

import javax.swing.JFrame;


public class MainFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WINDOW_SIZE = 500;
	
	public MainFrame() {
        super( "Tic-Tac-Toe" );
        this.setVisible( true );
        this.setDefaultCloseOperation( EXIT_ON_CLOSE );
        this.getContentPane().setPreferredSize( new Dimension( WINDOW_SIZE, WINDOW_SIZE ) );
        this.pack();
        
        this.add(new TicTacBoard());
	}

	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
	}

}
