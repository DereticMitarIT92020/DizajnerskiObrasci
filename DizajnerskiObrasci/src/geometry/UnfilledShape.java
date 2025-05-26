package geometry;

import java.awt.Color;

public abstract class UnfilledShape extends CustomShape{
	private Color color;
	
	public UnfilledShape() {
		
	}
	
	public UnfilledShape(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
