/**
 * 
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.GameView;
import view.TileView;

/**
 * @author Frank J. Mitropoulos
 *
 */
public class GameController {
	// These aren't used, but could be depending on your game and what you want to do
	private int score;
	private int gameStatus;
	private int rows;
	private int cols;

	private final int df = 3;
	
	public static int moveNumber = 5;
	
	private GameView gameView;

	private int MS;
	
	

	/**
	 * Create the GameView and pass in the appropriate listeners
	 */
	public GameController() {
		super();		
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MS = df;
			
				gameView = new GameView("Title",6,6,null,new NewGameListener(), new TileTouchedListener(),MS, new LevelListener());

				gameView.setVisible(true);
				
			}
		});
		
	}
	
	// Listener used to process touches on TileView
	
	class TileTouchedListener implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent event) {
			TileView tv = (TileView) event.getSource();
			System.out.println("Tile touched..." + tv.toString());
			// TODO -- you implement this
			// delegate to GameView!
			
			gameView.processTouchedTile(tv);
			System.out.println("---Proccessed---");
			
			
			
			// If no move is available display a message
			
			if (!gameView.isMoveAvailable()) {
				JOptionPane.showMessageDialog(gameView,
					    "No more moves \nStart a new Game");
			}
		}
		
	}
	
	// Listener used to process click on New Game Button
	
	class NewGameListener implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent event) {
			System.out.println("Starting new game...");

			gameView.newGame(MS);
		}
		
	}
	class LevelListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent E){
			System.out.println("New game commencing");
			JComboBox CB = (JComboBox)E.getSource();
			String L = (String)CB.getSelectedItem();
			MS = Integer.parseInt(L);
			gameView.ClearSize(MS);
			gameView.newGame(MS);
		}
	}
}
