package command;

import java.util.Collections;

import geometry.CustomShape;
import mvc.DrawingModel;

public class ToFrontShapeCmd implements Command {
	private String name;
	private int index;
	private CustomShape shape;
	private DrawingModel model;
	
	public ToFrontShapeCmd(CustomShape shape, DrawingModel model, int index, String name) {
		this.shape = shape;
		this.model = model;
		this.name = name;
		this.index = index;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name) {
        this.name = name;
    }
	
	public int getIndex() {
		return this.index;
	}
	
	public String toFileFormat() {
		return "tofront " + getIndex();
	}
	
	public void execute() {

		int currentIndex = index;
		if (currentIndex != model.getShapes().size() - 1) {
			Collections.swap(model.getShapes(), currentIndex, currentIndex + 1);
		}
	}
	
	public void unexecute() {
		int currentIndex = index + 1;
		if (currentIndex != model.getShapes().size()) {
			Collections.swap(model.getShapes(), currentIndex, currentIndex - 1);
		}
	}
}