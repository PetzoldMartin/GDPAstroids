package Shapes;

public class Astroid extends Sprite {
	//TODO multiple Constuctors with middle point and vector input with uses super Constructor 

	@Override
	public void update() {
		{
			this.draw();
			this.move(vector);
		}
		
	}
}
