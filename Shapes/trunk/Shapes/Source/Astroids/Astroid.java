package Astroids;

import java.awt.Color;

import Shapes.*;

public class Astroid extends Sprite {
	//TODO multiple Constuctors with middle point and vector input with uses super Constructor 

	public Astroid() {
		super();	
		this.vector=new Vector(Math.random()*10,Math.random()*360);
		this.middlePoint= new Point(Math.random()*Main.windowX-Main.windowX/2,Math.random()*Main.windowY-Main.windowY/2);
		this.addShape(new Rectangle(middlePoint, 10, 10, Color.WHITE, false));
		this.addShape(new Circle(10, middlePoint, Color.RED, false));
	}
	public Astroid(Point middlePoint,Vector vector,double rotationPhi) {
		this();
		this.middlePoint=middlePoint;
		this.vector=vector;
		this.rotationPhi=rotationPhi;
	}
	@Override
	public void update() {
		{
			this.draw();
			this.move(vector);
			rotate(this.rotationPhi);
		}
		
	}
}
