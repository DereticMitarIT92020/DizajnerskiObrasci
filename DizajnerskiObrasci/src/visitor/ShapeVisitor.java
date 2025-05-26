package visitor;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

public interface ShapeVisitor {
	
	public boolean visitPoint(Point point);
	public boolean visitLine(Line line);
	public boolean visitRectangle(Rectangle rectangle);
	public boolean visitCircle(Circle circle);
	public boolean visitDonut(Donut donut);
	public boolean visitHexagon(HexagonAdapter hexagon);
}
