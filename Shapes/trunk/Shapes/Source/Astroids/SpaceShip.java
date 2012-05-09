package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Shapes.Point;
import Shapes.Polygon;

public class SpaceShip extends Sprite {

	public SpaceShip()
	//TODO multiple Constuctors with middle point and vector input with uses super Constructor 
	{
	super();
	ArrayList<Point> shipList = new ArrayList<Point>();
	shipList.add(new Point(-10, -10));
	shipList.add(new Point(-10, 10));
	shipList.add(new Point(15, 0));
	this.addShape(new Polygon(shipList, Color.WHITE, false));
	}

	@Override
	public void update() {
		{
			this.draw();
			if(rotationPhi!=vector.getPhi()){
			this.rotate(rotationPhi-vector.getPhi());
			}
			rotationPhi=this.vector.getPhi();
			this.move(vector);
			
		}
		
	}
	

	
}
