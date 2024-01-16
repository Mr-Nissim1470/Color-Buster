/**
 * 
 */
package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;


/**
 * @author Frank J. Mitropoulos
 * 
 *  A very simple score panel
 *  call updateScore and pass in the score to update the display
 */
public class ScoreView extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	private JLabel scoreLabel;

	private int pscore;

	public ScoreView() {
		pscore=0;
		scoreLabel = new JLabel("Score: 0");
		add(scoreLabel);

	}
	
	public void updateScore(int score) {
		pscore += score;
		scoreLabel.setText("Score: " + pscore);
	}

	public void reset(){
		pscore = 0;
		scoreLabel.setText("Score: " + 0);

	}
}
