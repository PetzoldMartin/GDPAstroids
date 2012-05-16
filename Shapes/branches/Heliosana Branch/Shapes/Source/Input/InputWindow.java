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
//	private Integer speed=(int) 0.0;
//	private String speedString ="Speed";
//	private Integer acceleration=(int) 0.0;
//	private String accelerationString ="acceleration";
	public InputWindow(String name,GameController gameController)
	{
		super(name);
		gameController=this.gameController ;
	}
	 
	 

	public synchronized void paint(Graphics g) {
	        super.paint(g);
	        //FIXME Waum Statische referenz aus dem inputcontroller????
	        Integer speed = (int) InputController.gameController.getSpaceShip().getVector().getAmount();
	        String speedString = "Speed: "+speed.toString();
	        int ac2=(int) InputController.gameController.getSpaceShip().getVector().getPhi()-90;
	        Integer acceleration = Math.abs(ac2%360);
	        String accelerationString = "Angle: "+acceleration.toString();
	        Integer xcoord=(int) InputController.gameController.getSpaceShip().getCenterPoint().getX();
	        Integer ycoord=(int) InputController.gameController.getSpaceShip().getCenterPoint().getY();
	        String  xString="X:"+xcoord.toString();
	        String  yString="Y:"+ycoord.toString();
	       
	        g.setColor(new Color(255, 255, 255));
	        g.drawLine(0, 130, 300, 130);
	        g.drawLine(0, 170, 300, 170);
	        g.drawString(speedString, 160, 50);
	        g.drawString(accelerationString, 10, 50);
	        g.drawString(xString, 10, 280);
	        g.drawString(yString, 160, 280);
//	        g.drawOval(130, 130, 40, 40);
	        g.setColor(new Color(255, 0, 0));
	        g.fillOval(130, 130, 40, 40);
	        g.drawLine(0, 150, 300, 150);
	        g.drawLine(150, 0, 150, 300);
	    }
}
