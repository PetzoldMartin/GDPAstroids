package Astroids;

import java.awt.Color;

import Input.InputController;
import Shapes.Drawable;
import Shapes.Point;
import Shapes.Rectangle;

/**
 * class that manage the program
 * 
 * @author (Martin Petz)
 * @version (0.1)
 */
public class ToTestAstroidsControllerAismael extends GameController {
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Drawable background = new Rectangle(new Point(0, 0), window.getX(),
				window.getY(), Color.BLACK, true);
		background.draw();
		Thread inputKeyController= new Thread(new InputController());
		inputKeyController.start();
			
		
	}
}
