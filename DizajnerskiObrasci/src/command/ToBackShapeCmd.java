package command;

import java.util.Collections;

import geometry.CustomShape;
import mvc.DrawingModel;

public class ToBackShapeCmd implements Command {
	private String name;
	private int index;
	private CustomShape shape;
	private DrawingModel model;
	
	public ToBackShapeCmd(CustomShape shape, DrawingModel model, int index, String name) {
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
		return "toback " + index;
	}
	
	public void execute() {

		int currentIndex = index;
		if (currentIndex != 0) {
			Collections.swap(model.getShapes(), currentIndex, currentIndex - 1);
		}
	}
	
	public void unexecute() {

		int currentIndex = index - 1;
		if (currentIndex != -1) {
			Collections.swap(model.getShapes(), currentIndex, currentIndex + 1);
		}
	}
}
