package Astroids;

public class Astroid extends Sprite {
	//TODO multiple Constuctors with middle point and vector input with uses super Constructor 

	public Astroid() {
		
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
