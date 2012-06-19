package Input;

import java.awt.Color;
import java.awt.Graphics;

import Astroids.GameController;

/**
 * 
 * @author Aismael
 * Das Fenster für die Eingabe im Panel
 *
 */
public class InputControllPanelWindow extends BufferedAWTWindow {

	private static final long serialVersionUID = 1L;

	public InputControllPanelWindow(String name, GameController gameController) {
		super(name, gameController);
	}
	
	@Override
	/**
	 * Die Paintmethode von InputControllPanelWindow  die einen Kreis und die Orientierungslinien zeichnet
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(new Color(0, 0, 255));
		g.fillOval(70, 70, 160, 160);
		g.setColor(new Color(255, 0, 0));
		g.drawLine(0, 150, 300, 150);
		g.drawLine(150, 0, 150, 300);

	}


}
