package Input;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

import Astroids.GameController;

public class BufferedAWTWindow extends Panel {

	/**
	 * Ein Gepuffertes AWT-Panel für eine Einfache Grafische Ausgabe
	 */
	private static final long serialVersionUID = 1L;
	protected GameController gameController;
	Image bufImage;
	Graphics bufG;
	Image offscreenImage;
	Graphics offscreenGraphics;
	private Font l;
	private int fontSize=20;
	protected String name;
	

	public BufferedAWTWindow(String name, GameController gameController) {
		this.gameController = gameController;
		this.setBackground(Color.BLACK);
		l = new Font("Arial", Font.BOLD, fontSize);
		this.setPreferredSize(new Dimension(300, fontSize+20));
		this.setFont(l);
		this.name=name;

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

}
