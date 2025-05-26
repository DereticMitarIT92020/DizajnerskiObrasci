package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import geometry.CustomShape;
import mvc.DrawingModel;

public class RemoveShapesCmd implements Command {
	private String name;
	private List<CustomShape> shapes;
	private int prevIndex;
	private DrawingModel model;
	List<Integer> indexList = new ArrayList<Integer>();
	
	public RemoveShapesCmd(List<CustomShape> shapes, int prevIndex, DrawingModel model, String name) {
		this.shapes = shapes;
		this.prevIndex= prevIndex;
		this.model = model;
		this.name = name;
		for (CustomShape s : shapes) {
			indexList.add(model.getShapes().indexOf(s));
		}
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name) {
        this.name = name;
    }
	
	public int getIndex() {
		return this.prevIndex;
	}
	
	public CustomShape getShape() {
		return this.shapes.get(0);
	}
	
	public String toFileFormat() {
		return "removeshapes";
	}
	
	public void execute() {
		for (CustomShape cs : this.shapes) {
			model.getShapes().remove(cs);
		}
	}
	
	public void unexecute() {
		List<Integer> helpList = new ArrayList<Integer>();
		for (CustomShape cs : this.shapes) {
			int index = indexList.remove(0);
			model.getShapes().add(index,cs);
			helpList.add(index);
		}
		indexList = helpList;
	}
}
