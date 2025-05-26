package geometry;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Area;
import java.io.Serializable;

import visitor.ShapeVisitor;

public abstract class CustomShape implements Serializable, Moveable, Comparable<Object> {

	private boolean selected;

	public CustomShape() {

	}

	public CustomShape(boolean selected) {
		this.selected = selected;
	}
	
	public abstract boolean contains(int x, int y);
	public abstract void draw(Graphics g);
	public abstract String toFileFormat();
	public abstract boolean accept(ShapeVisitor shapeVisitor);

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
