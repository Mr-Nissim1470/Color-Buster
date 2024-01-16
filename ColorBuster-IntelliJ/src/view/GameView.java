/**
 * 
 */
package view;
import javax.swing.JFrame;
import java.awt.*;

//import view.ScoreView;
//import view.ButtonView;
import controller.GameController;
import model.Board;
import view.BoardView;
import java.awt.event.*;

/**
 * @author Frank J. Mitropoulos
 *
 */
public class GameView extends JFrame {
	// Create the HUD Panel
	// Create the Board


	private static final long serialVersionUID = 1L;
	private ScoreView scoreView;
	private ButtonView buttonView;
	private static BoardView boardView;

	private int score;

	private int MS;

	ActionListener NL;

	// Setting defaul to 8x8

	int rows = 8, cols = 8;
	int width, height;


	ActionListener newGameListener;
	ActionListener tileTouchedListener;

	ActionListener LevelListener;

	/**
	 * @param title
	 * @throws HeadlessException
	 */


	public GameView(String title, int rows, int cols, MouseListener listener, ActionListener newGameListener, ActionListener tileTouched, int ms, ActionListener NL) throws HeadlessException {
		super(title);
		setResizable(false);

		width = 400;
		height = 500;
		score = 0;
		this.rows = rows;
		this.cols = cols;
		this.MS = ms;

		this.newGameListener = newGameListener;
		this.tileTouchedListener = tileTouched;

		// Set up some reasonable sizes for the gameview

		setBounds(100,50,width, height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		scoreView = new ScoreView();
		add(scoreView, BorderLayout.NORTH);

		buttonView = new ButtonView(newGameListener, NL);
		add(buttonView, BorderLayout.SOUTH);

		this.MS = MS;

		boardView = new BoardView(rows,cols, tileTouchedListener, MS);

		boardView.setMatchSize(MS);

		add(boardView, BorderLayout.CENTER);


		setVisible(true);

	}

	// Delegate to boardView
	public boolean isMoveAvailable() {
		if(boardView.isMoveAvailable()){
			return true;
		}
		return false;

	}

	// Call this method to start a new Game

	public void newGame(int ms) {
		// Recreate some components and update the GUI.

		Container c = getContentPane();
		c.remove(boardView);
		c.invalidate();
		pack();

		boardView = null;
		score = 0;
		scoreView.reset();

		boardView = new BoardView(rows,cols, tileTouchedListener, MS);

		ClearSize(ms);

		add(boardView, BorderLayout.CENTER);
		pack();
		revalidate();
		setBounds(100,50,width, height);

		System.out.println(boardView);  // debug
	}

	public void processTouchedTile(TileView tv) {
		// TODO
		int x = boardView.processTouchedTile(tv);
		scoreView.updateScore(x);
		System.out.println("GameView == processing tile touch");
		System.out.println(boardView); // debug
	}
	public void ClearSize(int ms){
		this.MS = ms;
		boardView.setMatchSize(MS);
	}

}
