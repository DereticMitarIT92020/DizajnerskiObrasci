package geometry;

import java.awt.Color;

public abstract class FilledShape extends CustomShape {
	private Color borderColor;
	private Color shapeColor;
	
	public FilledShape() {
		
	}
	
	public FilledShape(Color borderColor, Color shapeColor) {
		this.borderColor = borderColor;
		this.shapeColor = shapeColor;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}

	public Color getShapeColor() {
		return shapeColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public void setShapeColor(Color shapeColor) {
		this.shapeColor = shapeColor;
	}
	

}
