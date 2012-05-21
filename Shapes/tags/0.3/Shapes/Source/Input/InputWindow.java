package Input;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

import Astroids.GameController;

public class InputWindow extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameController gameController;
	Image bufImage;
	Graphics bufG;
	Image offscreenImage;
	Graphics offscreenGraphics;

	public InputWindow(String name, GameController gameController) {
		super(name);
		this.gameController = gameController;

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(new Color(255, 255, 255));
		g.drawString(speedToString(), 160, 50);
		g.drawString(accelerationToString(), 10, 50);
		g.drawString(xToString(), 10, 280);
		g.drawString(yToString(), 160, 280);
		g.setColor(new Color(0, 0, 255));
		g.fillOval(70, 70, 160, 160);
		g.setColor(new Color(255, 0, 0));
		g.drawLine(0, 150, 300, 150);
		g.drawLine(150, 0, 150, 300);

	}

	@Override
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

	private String speedToString() {
		Double speed = gameController.getSpaceShip().getVector().getAmount();
		String speedString = "0";
		if (speed < 0) {
			String speedString0 = "Speed: " + speed.toString();
			speedString = speedString0.substring(0, 11);
		}
		if (speed >= 0) {
			String speedString0 = "Speed: " + speed.toString();
			speedString = speedString0.substring(0, 10);

		}
		return speedString;
	}

	private String accelerationToString() {
		int ac2 = (int) gameController.getSpaceShip().getVector().getPhi() - 90;
		Integer acceleration = Math.abs(ac2 % 360);
		String accelerationString = "Angle: " + acceleration.toString();
		return accelerationString;
	}

	private String xToString() {
		Integer xcoord = (int) gameController.getSpaceShip().getCenterPoint()
				.getX();
		String xString = "X:" + xcoord.toString();
		return xString;

	}

	private String yToString() {
		Integer ycoord = (int) gameController.getSpaceShip().getCenterPoint()
				.getY();
		String yString = "Y:" + ycoord.toString();
		return yString;
	}
}
