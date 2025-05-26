package visitor;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

public class ValidationVisitor implements ShapeVisitor {

	public boolean visitPoint(Point point) {
		if (point.getX() < 0 || point.getY() < 0)
			return false;
		
		return true;
	}

	public boolean visitLine(Line line) {
		if (line.getStartPoint().getX() < 0 || line.getStartPoint().getY() < 0 || line.getEndPoint().getX() < 0 || line.getEndPoint().getY() < 0)
			return false;
		
		return true;
	}

	public boolean visitRectangle(Rectangle rectangle) {
		if (rectangle.getUpperLeftPoint().getX() < 0 || rectangle.getUpperLeftPoint().getY() < 0 || rectangle.getHeight() < 0 || rectangle.getWidth() < 0)
			return false;
		
		return true;
	}

	public boolean visitCircle(Circle circle) {
		if (circle.getCenter().getX() < 0 || circle.getCenter().getY() < 0 || circle.getRadius() <= 0) 
			return false;
			
		return true;
	}

	public boolean visitDonut(Donut donut) {
		if (donut.getCenter().getX() < 0 || donut.getCenter().getY() < 0 || donut.getRadius() <= 0 || donut.getInnerRadius() <= 0 || donut.getRadius() < donut.getInnerRadius())
			return false;
		
		return true;
	}

	public boolean visitHexagon(HexagonAdapter hexagon) {
		if (hexagon.getCenterX() < 0 || hexagon.getCenterY() < 0 || hexagon.getRadius() < 0) 
			return false;
		
		return true;
	}

}
