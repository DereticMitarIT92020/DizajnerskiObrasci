package geometry;

import java.awt.Color;
import java.awt.Graphics;

import visitor.ShapeVisitor;

public class Rectangle extends FilledShape {

	private Point upperLeftPoint;
	private int width;
	private int height;

	public Rectangle() {
		
	}

	public Rectangle(Point upperLeftPoint, int width, int height, Color borderColor, Color shapeColor) {
		super(borderColor, shapeColor);
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
	}

	public Rectangle(Point upperLeftPoint, int width, int height, Color borderColor, Color shapeColor, boolean selected) {

		this(upperLeftPoint, width, height, borderColor, shapeColor);
		setSelected(selected);
	}
	
	private Rectangle(Builder builder) { // NOSONAR
		super(builder.borderColor, builder.shapeColor); // NOSONAR
		this.upperLeftPoint = builder.upperLeftPoint; // NOSONAR
		this.width = builder.width; // NOSONAR
		this.height = builder.height; // NOSONAR
	} // NOSONAR

	public int area() {
		return this.width * this.height;
	}

	public int circumference() {
		return this.width * 2 + this.height * 2;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle pomocna = (Rectangle) obj;
			if (this.upperLeftPoint.equals(pomocna.upperLeftPoint) && this.width == pomocna.width
					&& this.height == pomocna.height)
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public boolean contains(int x, int y) {
		if (upperLeftPoint.getX() <= x && x <= upperLeftPoint.getX() + width && upperLeftPoint.getY() <= y
				&& y <= upperLeftPoint.getY() + height)
			return true;
		return false;
	}

	public boolean contains(Point p) {
		if (upperLeftPoint.getX() <= p.getX() && p.getX() <= upperLeftPoint.getX() + width
				&& upperLeftPoint.getY() <= p.getY() && p.getY() <= upperLeftPoint.getY() + height)
			return true;
		return false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getBorderColor());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		g.setColor(this.getShapeColor());
		g.fillRect(upperLeftPoint.getX() + 1, upperLeftPoint.getY() + 1, width - 1, height - 1);
		
		if (isSelected()) {
			g.setColor(Color.blue);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width  - 2, upperLeftPoint.getY() + height - 2, 4, 4);
		}

	}

	@Override
	public void moveTo(int x, int y) {
		upperLeftPoint.moveTo(x, y);

	}

	@Override
	public void moveBy(int x, int y) {
		upperLeftPoint.moveBy(x, y);

	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Rectangle) {
			return this.area()-((Rectangle)o).area();
		}
		return 0;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String toString() {
		return "Upper left point:" + upperLeftPoint + ", width =" + width + ",height = " + height + ", Border color: " + getBorderColor().toString() + ", Shape color: " + getShapeColor().toString();
	}
	
	public Rectangle clone() { 
		Rectangle rectangle = new Rectangle(this.getUpperLeftPoint(), this.getWidth(), this.getHeight(), this.getBorderColor(), this.getShapeColor()); 
		return rectangle; 
	} 
	 
	public String toFileFormat() {
		return "rectangle " + getUpperLeftPoint().getX() + " " + getUpperLeftPoint().getY() + " " + getWidth() + " " + getHeight() + " " + getBorderColor().getRed() + " " + getBorderColor().getGreen() + " " + getBorderColor().getBlue() + " " + getShapeColor().getRed() + " " + getShapeColor().getGreen() + " " + getShapeColor().getBlue() + " " + isSelected();
	}
	
	public boolean accept(ShapeVisitor shapeVisitor) { // NOSONAR
		return shapeVisitor.visitRectangle(this); // NOSONAR
	} // NOSONAR
	 
	public static Builder builder() { // NOSONAR
		return new Builder(); // NOSONAR
	} // NOSONAR
	
	public static class Builder { // NOSONAR
		private Point upperLeftPoint; // NOSONAR 
		private int width; // NOSONAR
		private int height; // NOSONAR
		private Color borderColor; // NOSONAR
		private Color shapeColor; // NOSONAR
		
		public Builder upperLeftPoint(Point upperLeftPoint) { // NOSONAR
			this.upperLeftPoint = upperLeftPoint; // NOSONAR
			return this; // NOSONAR
		} // NOSONAR
		
		public Builder width(int width) { // NOSONAR
			this.width = width; // NOSONAR
			return this; // NOSONAR
		} // NOSONAR
		
		public Builder height(int height) { // NOSONAR
			this.height = height; // NOSONAR
			return this; // NOSONAR
		} // NOSONAR
		
		public Builder borderColor(Color borderColor) { // NOSONAR
			this.borderColor = borderColor; // NOSONAR
			return this; // NOSONAR
		} // NOSONAR
		
		public Builder shapeColor(Color shapeColor) { // NOSONAR
			this.shapeColor = shapeColor; // NOSONAR
			return this; // NOSONAR
		} // NOSONAR
		
		public Rectangle build() { // NOSONAR
			if (width < 0 || height < 0 || borderColor == null || shapeColor == null)  // NOSONAR
				throw new IllegalStateException("Podaci nisu validni."); // NOSONAR
			 
			return new Rectangle(this); // NOSONAR
		} // NOSONAR
	} // NOSONAR
}
