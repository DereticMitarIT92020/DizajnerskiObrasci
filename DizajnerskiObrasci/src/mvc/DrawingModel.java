package mvc;

import java.util.ArrayList;
import java.util.List;
import geometry.CustomShape;

public class DrawingModel {
    private List<CustomShape> shapes = new ArrayList<>();
	
	public void add(CustomShape s) {
		shapes.add(s);
	}
	
	public void addToIndex(CustomShape s, int index) {
		shapes.add(index, s);
	}
	
	public void addShapes(List<CustomShape> shapes) {
		this.shapes.addAll(shapes);
	}
	
	public void remove(CustomShape s) {
		shapes.remove(s);
	}

	public List<CustomShape> getShapes() {
		return shapes;
	}
}
