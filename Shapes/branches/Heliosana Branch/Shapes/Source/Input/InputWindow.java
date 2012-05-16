package Input;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

import Astroids.GameController;

public class InputWindow extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static GameController gameController;
	Image bufImage;
	Graphics bufG;

	public InputWindow(String name, GameController gameController) {
		super(name);
		gameController = this.gameController;

	}

	public void paint(Graphics g) {
		super.paint(g);
		// FIXME Waum Statische referenz aus dem inputcontroller????
		Double speed = InputController.gameController.getSpaceShip()
				.getVector().getAmount();
		String speedString = "0";
		if (speed < 0) {
			String speedString0 = "Speed: " + speed.toString();
			speedString = speedString0.substring(0, 11);
		}
		if (speed >= 0) {
			String speedString0 = "Speed: " + speed.toString();
			speedString = speedString0.substring(0, 10);
		}
		int ac2 = (int) InputController.gameController.getSpaceShip()
				.getVector().getPhi() - 90;
		Integer acceleration = Math.abs(ac2 % 360);
		String accelerationString = "Angle: " + acceleration.toString();
		Integer xcoord = (int) InputController.gameController.getSpaceShip()
				.getCenterPoint().getX();
		Integer ycoord = (int) InputController.gameController.getSpaceShip()
				.getCenterPoint().getY();
		String xString = "X:" + xcoord.toString();
		String yString = "Y:" + ycoord.toString();

		g.setColor(new Color(255, 255, 255));
		g.drawString(speedString, 160, 50);
		g.drawString(accelerationString, 10, 50);
		g.drawString(xString, 10, 280);
		g.drawString(yString, 160, 280);
		g.setColor(new Color(0, 0, 255));
		g.fillOval(70, 70, 160, 160);
		g.setColor(new Color(255, 0, 0));
		g.drawLine(0, 150, 300, 150);
		g.drawLine(150, 0, 150, 300);
	}

	public void update(Graphics g) {

		int w = this.getSize().width;
		int h = this.getSize().height;

		if (bufImage == null) {
			bufImage = this.createImage(w, h);
			bufG = bufImage.getGraphics();
		}

		bufG.setColor(this.getBackground());
		bufG.fillRect(0, 0, w, h);

		bufG.setColor(this.getForeground());

		paint(bufG);

		g.drawImage(bufImage, 0, 0, this);

	}

}
