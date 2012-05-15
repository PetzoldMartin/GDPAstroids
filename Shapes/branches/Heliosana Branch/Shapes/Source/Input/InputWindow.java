package Input;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import Astroids.GameController;

public class InputWindow extends java.awt.Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static GameController gameController;
	private Integer speed=(int) 0.0;
	private String speedString ="Speed";
	private Integer acceleration=(int) 0.0;
	private String accelerationString ="acceleration";
	public InputWindow(String name,GameController gameController)
	{
		super(name);
		 gameController=this.gameController ;
	}
	 
	 

	public void paint(Graphics g) {
	        super.paint(g);
	        //FIXME Waum Statische referenz aus dem inputcontroller????
	        speed = (int) InputController.gameController.getSpaceShip().getVector().getAmount();
	        speedString = speed.toString();
	        acceleration = (int) InputController.gameController.getSpaceShip().getVector().getPhi();
	        accelerationString = speed.toString();
	       
	        g.setColor(new Color(255, 255, 255));
	        g.drawLine(150, 0, 150, 300);
	        g.drawLine(0, 150, 300, 150);
	        g.drawString(speedString, 150, 150);
	        g.drawString(accelerationString, 10, 150);
	    }
}
