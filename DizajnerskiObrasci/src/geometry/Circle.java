package geometry;

import java.awt.Color;
import java.awt.Graphics;

import visitor.ShapeVisitor;

public class Circle extends FilledShape {

	private Point center;
	private int radius;

	public Circle() {
		super();
	}
	
	public Circle(Point center, int radius) {
		super();
	}

	public Circle(Point center, int radius, Color borderColor, Color shapeColor) {
		super(borderColor, shapeColor);
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, Color borderColor, Color shapeColor ,boolean selected) {
		this(center, radius, borderColor, shapeColor);
		setSelected(selected);
	}
	
	private Circle(Builder builder) { // NOSONAR
		super(builder.borderColor, builder.shapeColor); // NOSONAR
		this.center = builder.center; // NOSONAR
		this.radius = builder.radius; // NOSONAR
	} // NOSONAR

	public double area() {
		return radius * radius * Math.PI;
	}

	public double circumference() {
		return 2 * radius * Math.PI;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle pomocni = (Circle) obj;
			if (this.center.equals(pomocni.getCenter()) && this.radius == pomocni.getRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean contains(int x, int y) {
		return center.distance(x, y) <= radius;
	}

	public boolean contains(Point p) {
		return center.distance(p.getX(), p.getY()) <= radius;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getBorderColor());
		g.drawOval(center.getX() - radius, center.getY() - radius, radius + radius, radius + radius);
		g.setColor(this.getShapeColor());
		g.fillOval(center.getX() - radius, center.getY() - radius, radius + radius, radius + radius);
		
		if(isSelected() == true) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getX() - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - radius - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() + radius - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() - radius - 2, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() + radius - 2, 4, 4);
		}
	}

	@Override
	public void moveTo(int x, int y) {
		center.moveTo(x, y);
	}

	@Override
	public void moveBy(int x, int y) {
		center.moveBy(x, y);
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Circle) {
			return (int)(this.area()-((Circle)o).area());
		}
		return 0;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) throws Exception {
		if(radius<0)
			throw new Exception("Vrednost radiusa ne sme biti negativna");
		this.radius = radius;
	}

	public String toString() {
		return "Center=" + center + ", radius=" + radius + ", Border color: " + getBorderColor().toString() + ", Shape color: " + getShapeColor().toString();
	}
    
	public Circle clone() { // NOSONAR
		Circle circle = new Circle(this.getCenter(), this.getRadius(), this.getBorderColor(), this.getShapeColor()); // NOSONAR
		return circle; // NOSONAR
	} // NOSONAR
	 
	public String toFileFormat() {
		return "circle " + getCenter().getX() + " " + getCenter().getY() + " " + getRadius() + " " + getBorderColor().getRed() + " " + getBorderColor().getGreen() + " " + getBorderColor().getBlue() + " " + getShapeColor().getRed() + " " + getShapeColor().getGreen() + " " + getShapeColor().getBlue() + " " + isSelected();
	}
	
	public boolean accept(ShapeVisitor shapeVisitor) { // NOSONAR
		return shapeVisitor.visitCircle(this); // NOSONAR
	} // NOSONAR
	
	public static Builder builder() { // NOSONAR
		return new Builder(); // NOSONAR
	} // NOSONAR
	
	public static class Builder { // NOSONAR
		private Point center; // NOSONAR
		private int radius; // NOSONAR
		private Color shapeColor; // NOSONAR
		private Color borderColor; // NOSONAR
		
		public Builder center(Point center) { // NOSONAR
			this.center = center; // NOSONAR
			return this; // NOSONAR
		} // NOSONAR
		
		public Builder radius(int radius) { // NOSONAR
			this.radius = radius; // NOSONAR
			return this; // NOSONAR
		} // NOSONAR
		
		public Builder shapeColor(Color shapeColor) { // NOSONAR
			this.shapeColor = shapeColor; // NOSONAR
			return this; // NOSONAR 
		} // NOSONAR
		
		public Builder borderColor(Color borderColor) { // NOSONAR
			this.borderColor = borderColor; // NOSONAR
			return this; // NOSONAR
		} // NOSONAR
		
		public Circle build() { // NOSONAR
			if (radius < 0 || shapeColor == null || borderColor == null) // NOSONAR
				throw new IllegalStateException("Podaci nisu validni."); // NOSONAR
			
			return new Circle(this); // NOSONAR
		} // NOSONAR
	} // NOSONAR
}
