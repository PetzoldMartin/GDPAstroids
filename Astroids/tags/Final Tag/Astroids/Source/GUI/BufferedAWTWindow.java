package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class BufferedAWTWindow extends Panel {

	/**
	 * Ein Gepuffertes AWT-Panel fuer eine einfache grafische Ausgabe mit
	 * Voreinstellungen fuerr die Textausgabe
	 */
	private static final long serialVersionUID = 1L;
	Image bufImage;//Das Image zum puffern
	Graphics bufG;//die Grafiken die in den Puffer geschrieben werden
	private Font l;//die Schrift für die Voreinstellung
	private int fontSize = 20;//die Schriftgroesse fuer die Voreinstellung
	protected String name;//der intern gespeicherte Name des Panels

	/**
	 * der Konstruktor des {@link BufferedAWTWindow}
	 * @param name  der name des Panels der Als String übergeben werden muss
	 */
	public BufferedAWTWindow(String name) {
		this.setBackground(Color.BLACK);
		l = new Font("Arial", Font.BOLD, fontSize);
		this.setPreferredSize(new Dimension(300, fontSize + 20));
		this.setFont(l);
		this.name = name;

	}
	

	@Override
	/**
	 * der Puffer fuer die Grafische AWT Ausgabe
	 */
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
