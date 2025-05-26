package command;

import geometry.Circle;
import mvc.DrawingModel;

public class AddCircleCmd implements Command {
	private String name;
	private int index;
	private Circle circle;
	private DrawingModel model;
	private String log = "";
	
	public AddCircleCmd(Circle circle, DrawingModel model, int index, String name) {
		this.circle = circle;
		this.model = model;
		this.name = name;
		this.index = index;
		log ="addcircle " + 
				this.circle.getCenter().getX() + "," + 
				this.circle.getCenter().getY() + "," + 
				this.circle.getRadius() + "," + 
				this.circle.getBorderColor().getRGB() + "," + 
				this.circle.getShapeColor().getRGB() + "," + 
				this.circle.isSelected();
	}
	
	public String getName() {
        return this.name;
    }
	
	public void setName(String name) {
        this.name = name;
    }
	
	public int getIndex() {
		return this.index;
	}
	
	public String toFileFormat() {
		return log;
	}
	
	public void execute() {
    	model.add(circle);
	}
	
	public void unexecute() {
		model.getShapes().remove(index);
	}
}
