  
package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import visitor.ShapeVisitor;

import java.awt.Shape;

public class Donut extends Circle {

	private int innerRadius;

	public Donut() {
	}

	public Donut(Point center, int radius, int innerRadius, Color borderColor, Color shapeColor) {
		super(center, radius, borderColor, shapeColor);
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, Color borderColor, Color shapeColor, boolean selected) {
		this(center, radius, innerRadius, borderColor, shapeColor);
		setSelected(selected);
	}
	
	private Donut(DonutBuilder donutBuilder) {
		super(donutBuilder.center, donutBuilder.radius, donutBuilder.borderColor, donutBuilder.shapeColor);
		this.innerRadius = donutBuilder.innerRadius;
	}
	
	public double area() {
		return super.area() - innerRadius*innerRadius*Math.PI;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocni = (Donut) obj;
			if (getCenter().equals(pomocni.getCenter()) && getRadius() == pomocni.getRadius() && innerRadius == pomocni.innerRadius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean contains(int x, int y) {
		double dFromCenter = getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}
	
	public boolean contains(Point p) {
		double dFromCenter = getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}	

	@Override
	public void draw(Graphics g) {
		
		
		Graphics2D g2d = (Graphics2D) g;
		
		Shape donut = new Ellipse2D.Double(getCenter().getX() - getRadius(), getCenter().getY() - getRadius(), 2 * getRadius(), 2 * getRadius());
		Shape innerCircle = new Ellipse2D.Double(getCenter().getX() - getInnerRadius(), getCenter().getY() - getInnerRadius(), 2 * getInnerRadius(), 2 * getInnerRadius());
		
		Area donutArea = new Area(donut);
		Area innerCircleArea = new Area(innerCircle);
		
		donutArea.subtract(innerCircleArea);
		
		g2d.setColor(getBorderColor());
		g2d.draw(donutArea);
		g2d.setColor(getShapeColor());
		g2d.fill(donutArea);
		
		
		System.out.println("Boja ivice krofne: " + g2d.getStroke().toString());
		System.out.println("Boja krofne: " + g2d.getBackground().toString());
		
		if(isSelected() == true) {
			g.setColor(Color.blue);
			
			g.drawRect(getCenter().getX() - 2, getCenter().getY() - 2, 4, 4);
			g.drawRect(getCenter().getX() - getRadius() - 2, getCenter().getY() - 2, 4, 4);
			g.drawRect(getCenter().getX() + getRadius() - 2, getCenter().getY() - 2, 4, 4);
			g.drawRect(getCenter().getX() - 2, getCenter().getY() - getRadius() - 2, 4, 4);
			g.drawRect(getCenter().getX() - 2, getCenter().getY() + getRadius() - 2, 4, 4);
			
			g.drawRect(this.getCenter().getX() - innerRadius - 2, this.getCenter().getY() - 2, 4, 4);
			g.drawRect(this.getCenter().getX() + innerRadius - 2, this.getCenter().getY() - 2, 4, 4);
			g.drawRect(this.getCenter().getX() - 2, this.getCenter().getY() - innerRadius - 2, 4, 4);
			g.drawRect(this.getCenter().getX() - 2, this.getCenter().getY() + innerRadius - 2, 4, 4);
		}
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Donut) {
			return (int)(this.area()-((Donut)o).area());
		}
		return 0;
	}

	public String toString() {
		return super.toString()+", innerRadius="+innerRadius;
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public Donut clone() {
		Donut donut = new Donut(this.getCenter() ,this.getRadius(), this.getInnerRadius(), this.getBorderColor(), this.getShapeColor());
		return donut;
	}
	
	public String toFileFormat() {
		return "donut " + getCenter().getX() + " " + getCenter().getY() + " " + getRadius() + " " + getInnerRadius()  + " " + getBorderColor().getRed() + " " + getBorderColor().getGreen() + " " + getBorderColor().getBlue() + " " + getShapeColor().getRed() + " " + getShapeColor().getGreen() + " " + getShapeColor().getBlue() + " " + isSelected();
	}
	
	@Override
	public boolean accept(ShapeVisitor shapeVisitor) {
		return shapeVisitor.visitDonut(this);
	}
	// sonar-start-exclude
	public static DonutBuilder donutBuilder() {
		return new DonutBuilder();
	}
	
	public static class DonutBuilder {
		private Point center;
		private int radius;
		private int innerRadius;
		private Color shapeColor;
		private Color borderColor;
		
		public DonutBuilder center(Point center) {
			this.center = center;
			return this;
		}
		
		public DonutBuilder radius(int radius) {
			this.radius = radius;
			return this;
		}
		
		public DonutBuilder innerRadius(int innerRadius) {
			this.innerRadius = innerRadius;
			return this;
		}
		
		public DonutBuilder shapeColor(Color shapeColor) {
			this.shapeColor = shapeColor;
			return this;
		}
		
		public DonutBuilder borderColor(Color borderColor) {
			this.borderColor = borderColor;
			return this;
		}
		
		public Donut build() {
			if (radius < innerRadius || radius < 0 || innerRadius < 0 || borderColor == null || shapeColor == null)
				throw new IllegalStateException("Podaci nisu validni.");
			
			return new Donut(this);
		}
	} 
	// sonar-end-exclude
}
