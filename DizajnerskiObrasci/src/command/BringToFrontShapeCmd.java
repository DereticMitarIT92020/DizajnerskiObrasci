package command;

import java.util.Collections;

import geometry.CustomShape;
import mvc.DrawingModel;

public class BringToFrontShapeCmd implements Command {
	private String name;
	private CustomShape shape;
	private DrawingModel model;
	private int prevIndex;
	
	public BringToFrontShapeCmd(CustomShape shape, DrawingModel model, int prevIndex, String name) {
		this.shape = shape;
		this.model = model;
		this.prevIndex = prevIndex;
		this.name = name;
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
	
	public String toFileFormat() {
		return "bringtofront " + prevIndex; 
	}

	public void execute() {			
		model.getShapes().remove(shape);
		model.getShapes().add(model.getShapes().size(), shape);
	}
	
	public void unexecute() {
		model.getShapes().remove(shape);
		model.getShapes().add(prevIndex, shape);
	}
}
