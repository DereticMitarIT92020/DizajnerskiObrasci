package geometry;

import java.awt.Color;
import java.awt.Graphics;

import visitor.ShapeVisitor;

public class Line extends UnfilledShape {

	private Point startPoint;
	private Point endPoint;

	public Line() {
	}

	public Line(Point startPoint, Point endPoint, Color color) {
		super(color);
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, Color color, boolean selected) {
		this(startPoint, endPoint, color);
		setSelected(selected);
	}
	
	private Line(Builder builder) { 
		super(builder.color); 
		this.startPoint = builder.startPoint; 
		this.endPoint = builder.endPoint; 
	} 

	public double length() {
		return this.startPoint.distance(this.endPoint.getX(), this.endPoint.getY());
	}

	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line pomocna = (Line) obj;
			if (this.startPoint.equals(pomocna.startPoint) && this.endPoint.equals(pomocna.endPoint))
				return true;
			else
				return false;
		} else
			return false;
	}

	public boolean contains(int x, int y) {
		return (this.startPoint.distance(x, y) + this.endPoint.distance(x, y)) - this.length() <= 2;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		
		if(isSelected() == true) {
			g.setColor(Color.BLUE);
			g.drawRect(startPoint.getX()-2, startPoint.getY()-2, 4, 4);
			g.drawRect(endPoint.getX()-2, endPoint.getY()-2, 4, 4);
		}
	}

	@Override
	public void moveTo(int x, int y) {
		
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Line) {
			return (int)(this.length()-((Line)o).length());
		}
		return 0;
	}

	@Override
	public void moveBy(int x, int y) {
		startPoint.moveBy(x, y);
		endPoint.moveBy(x, y);
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getStartPoint() {
		return this.startPoint;
	}

	public Point getEndPoint() {
		return this.endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public String toString() {
		return startPoint + "-- >" + endPoint + ", Color: " + getColor().toString();
	}
	
	public Line clone() { // NOSONAR
		Line line = new Line(this.getStartPoint(), this.getEndPoint(), this.getColor()); // NOSONAR
		return line; // NOSONAR
	} // NOSONAR
	
	public String toFileFormat() {
		return "line " + getStartPoint().getX() + " " + getStartPoint().getY() + " " + getEndPoint().getX() + " " + getEndPoint().getY() + " " + getColor().getRed() + " " + getColor().getGreen() + " " + getColor().getBlue() + " " + isSelected();
	}
	
	public boolean accept(ShapeVisitor shapeVisitor) { // NOSONAR
		return shapeVisitor.visitLine(this); // NOSONAR
	} // NOSONAR
	
	public static Builder builder() { 
		return new Builder(); 
	} 
	
	public static class Builder { 
		private Point startPoint; 
		private Point endPoint; 
		private Color color; 
		
		public Builder startPoint(Point startPoint) { 
			this.startPoint = startPoint; 
			return this; 
		} 
		
		public Builder endPoint(Point endPoint) { 
			this.endPoint = endPoint; 
			return this; 
		} 
		
		public Builder color(Color color) { 
			this.color = color; 
			return this; 
		} 
		
		public Line build() { 
			if (color == null) 
				throw new IllegalStateException("Podaci nisu validni."); 
			
			return new Line(this); 
		} 
	} 
	
}
