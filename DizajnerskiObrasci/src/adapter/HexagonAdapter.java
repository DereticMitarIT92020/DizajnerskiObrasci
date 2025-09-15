package adapter;
import java.awt.Color;
import java.awt.Graphics;

import geometry.FilledShape;
import hexagon.Hexagon;
import visitor.ShapeVisitor;

public class HexagonAdapter extends FilledShape {
	private Hexagon hexagon;
	
	public HexagonAdapter(int x, int y, int r) {
		this.hexagon = new Hexagon(x, y, r);
	}
	
	public HexagonAdapter(boolean selected, Hexagon hexagon, Color borderColor, Color shapeColor) {
		this.hexagon = hexagon;
		this.setSelected(selected);
		this.setBorderColor(borderColor);
		this.setShapeColor(shapeColor);
	}
	
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	private HexagonAdapter(Builder builder) { // NOSONAR
		this.hexagon = new Hexagon(builder.x, builder.y, builder.r); // NOSONAR
		this.setSelected(builder.selected); // NOSONAR
		this.setBorderColor(builder.borderColor); // NOSONAR
		this.setShapeColor(builder.shapeColor); // NOSONAR
	} // NOSONAR
	
	public int getCenterX() {
		return this.hexagon.getX();
	}
	
	public void setCenterX(int x) {
		this.hexagon.setX(x);
	}
	
	public int getCenterY() {
		return this.hexagon.getY();
	}
	
	public void setCenterY(int y) {
		this.hexagon.setY(y);
	}
	
	public int getRadius() {
		return this.hexagon.getR();
	}
	
	public void setRadius(int r) {
		this.hexagon.setR(r);
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
	    this.hexagon.setX(x);
	    this.hexagon.setY(y);
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		this.hexagon.setX(this.hexagon.getX() + x);
		this.hexagon.setY(this.hexagon.getY() + y);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		boolean selected = this.hexagon.doesContain(x, y);

		return selected;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		this.hexagon.paint(g);
	}
	
	public Color getBorderColor() {
		return this.hexagon.getBorderColor();
	}
	
	public Color getShapeColor() {
		return this.hexagon.getAreaColor();
	}
	
	public void setBorderColor(Color borderColor) {
		this.hexagon.setBorderColor(borderColor);
	}
	
	public void setShapeColor(Color shapeColor) {
		this.hexagon.setAreaColor(shapeColor);
	}
	
	public boolean isSelected() {
		return this.hexagon.isSelected();
	}
	
	public void setSelected(boolean selected) {
		this.hexagon.setSelected(selected);
	}
	
	public HexagonAdapter clone() { // NOSONAR
		Hexagon hexagon = new Hexagon(this.getCenterX(), this.getCenterY(), this.getRadius()); // NOSONAR
		hexagon.setBorderColor(this.getBorderColor()); // NOSONAR
		hexagon.setAreaColor(this.getShapeColor()); // NOSONAR
		HexagonAdapter ha = new HexagonAdapter(hexagon); // NOSONAR
		return ha; // NOSONAR 
	} // NOSONAR
	
	public String toString() {
		return "Center=(" + this.getCenterX() + "," + this.getCenterY() + ")" + ", radius=" + this.getRadius() + ", Border color: " + this.getBorderColor().toString() + ", Shape color: " + this.getShapeColor().toString();
	}
	
	public String toFileFormat() {
		return "hexagon " + getCenterX() + " " + getCenterY() + " " + getRadius() + " " + getBorderColor().getRed() + " " + getBorderColor().getGreen() + " " + getBorderColor().getBlue() + " " + getShapeColor().getRed() + " " + getShapeColor().getGreen() + " " + getShapeColor().getBlue() + " " + isSelected();
	}
	
	public boolean accept(ShapeVisitor shapeVisitor) { 
		return shapeVisitor.visitHexagon(this); 
	} 
	
	public static Builder builder() { // NOSONAR
		return new Builder(); // NOSONAR
	} // NOSONAR
	
	public static class Builder { // NOSONAR
		private int x; // NOSONAR
		private int y; // NOSONAR
		private int r; // NOSONAR
		private Color borderColor; // NOSONAR
		private Color shapeColor; // NOSONAR
		private boolean selected; // NOSONAR
		
		public Builder x(int x) { // NOSONAR
			this.x = x; // NOSONAR
			return this; // NOSONAR
		} // NOSONAR
		
		public Builder y(int y) { // NOSONAR
			this.y = y; // NOSONAR
			return this; // NOSONAR
		} // NOSONAR
		
		public Builder r(int r) { // NOSONAR
			this.r = r; // NOSONAR
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
		
		public Builder selected(boolean selected) { // NOSONAR
			this.selected = selected; // NOSONAR
			return this; // NOSONAR
		} // NOSONAR
		
		public HexagonAdapter build() { // NOSONAR
			if (r < 0 || borderColor == null || shapeColor == null) // NOSONAR
				throw new IllegalStateException("Podaci nisu validni."); // NOSONAR
			
			return new HexagonAdapter(this); // NOSONAR
		} // NOSONAR
	} // NOSONAR
}
