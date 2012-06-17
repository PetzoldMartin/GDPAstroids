package Input;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Astroids.GameController;

public class InputWindow extends BufferedAWTWindow {

	public InputWindow(String name, GameController gameController) {
		super(name, gameController);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(new Color(0, 0, 255));
		g.fillOval(70, 70, 160, 160);
		g.setColor(new Color(255, 0, 0));
		g.drawLine(0, 150, 300, 150);
		g.drawLine(150, 0, 150, 300);

	}


}
